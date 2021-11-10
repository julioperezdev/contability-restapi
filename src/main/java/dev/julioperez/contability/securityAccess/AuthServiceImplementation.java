package dev.julioperez.contability.securityAccess;



import dev.julioperez.contability.mail.MailServiceImplementation;
import dev.julioperez.contability.mail.NotificationEmail;
import dev.julioperez.contability.user.User;
import dev.julioperez.contability.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImplementation implements AuthService {

    Logger logger = LoggerFactory.getLogger(AuthServiceImplementation.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailServiceImplementation mailServiceImplementation;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenServiceImplementation refreshTokenService;

    @Autowired
    public AuthServiceImplementation(PasswordEncoder passwordEncoder,
                                     UserRepository userRepository,
                                     VerificationTokenRepository verificationTokenRepository,
                                     MailServiceImplementation mailServiceImplementation,
                                     AuthenticationManager authenticationManager,
                                     JwtProvider jwtProvider,
                                     RefreshTokenServiceImplementation refreshTokenService){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailServiceImplementation = mailServiceImplementation;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    @Transactional
    public void signup(RegisterRequestDto registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        //user.setCreated(Date.from(Instant.now()));
        user.setCreated(Instant.now());
        user.setEnable(false);
        User userCreated = userRepository.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getCreated(),
                user.isEnable());
        System.out.println(userCreated);
        System.out.println(userCreated.getEmail());
        System.out.println(String.valueOf(userCreated.getCreated()));
        System.out.println(userCreated.getUsername());
        System.out.println(userCreated.getPassword());
        System.out.println(String.valueOf(userCreated.isEnable()));
//        generateVerificationToken(user);
        String token = generateVerificationToken(userCreated);
        mailServiceImplementation.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thanks you for signin up to Spring Reddit," +
                " please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        System.out.println(token);
        verificationToken.setToken(token);
        verificationToken.setUserId(user.getUserId());
        System.out.println(verificationToken.getToken());
        System.out.println(verificationToken.getUserId());
        //verificationTokenRepository.createToken(verificationToken.getToken()) ;
        verificationTokenRepository.createToken(verificationToken.getToken(), verificationToken.getUserId());
        //verificationTokenRepository.save(verificationToken);
        return token;

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new InvalidTokenException());
        fetchUserAndEnable(verificationToken.get());

    }

    @Transactional
    @Override
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        Long userid = verificationToken.getUserId();
        User user = userRepository.findUserById(userid)
                .orElseThrow(() -> new UserNotFoundWithUserIdException(userid.toString()));

        user.setEnable(true);
        userRepository.updateEnableUser(user.getUserId(), user.isEnable());
    }

    public AuthenticationResponseDto login(LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expireAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();

    }
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expireAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
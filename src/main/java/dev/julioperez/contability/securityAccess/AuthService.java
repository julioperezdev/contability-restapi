package dev.julioperez.contability.securityAccess;

import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    void signup(RegisterRequestDto registerRequest);

    //String generateVerificationToken(User user);

    void verifyAccount(String token);


    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken);

    AuthenticationResponseDto login(LoginRequestDto loginRequest);
}
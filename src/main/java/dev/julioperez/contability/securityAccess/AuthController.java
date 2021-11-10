package dev.julioperez.contability.securityAccess;

import dev.julioperez.contability.shared.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImplementation authServiceImplementation;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthServiceImplementation authServiceImplementation,
                          RefreshTokenService refreshTokenService) {
        this.authServiceImplementation = authServiceImplementation;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signup")
    public RestResponse<?> signup(@RequestBody RegisterRequestDto registerRequest) {
        authServiceImplementation.signup(registerRequest);
        return new RestResponse<>(HttpStatus.OK, "User Register Successfully");
    }

    @GetMapping("/accountVerification/{token}")
    public RestResponse<?> verifyAccount(@PathVariable String token) {
        authServiceImplementation.verifyAccount(token);
        return new RestResponse<>(HttpStatus.OK, "Account Activate Successfully");

    }

    @PostMapping("/login")
    public RestResponse<AuthenticationResponseDto> login(@RequestBody LoginRequestDto loginRequest) {

        AuthenticationResponseDto authenticationResponse = authServiceImplementation.login(loginRequest);
        System.out.println(authenticationResponse);
        return new RestResponse<>(HttpStatus.OK, authenticationResponse);

    }

    @PostMapping("/refresh/token")
    public AuthenticationResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return authServiceImplementation.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public RestResponse<String> logout(@RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return new RestResponse<>(HttpStatus.OK, "Refresh token deleted successfully");
    }

}


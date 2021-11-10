package dev.julioperez.contability.securityAccess;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}

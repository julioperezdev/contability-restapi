package dev.julioperez.contability.securityAccess;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImplementation implements RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreateDate(Date.from(Instant.now()));

        return refreshTokenRepository.saveRefreshToken(refreshToken.getToken(), refreshToken.getCreateDate());
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.getRefreshTokenByToken(token)
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteRefreshTokenByToken(token);
    }
}
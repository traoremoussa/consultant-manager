package com.kodiatech.traore.auth.services;

import com.kodiatech.traore.auth.exception.TokenRefreshException;
import com.kodiatech.traore.auth.models.RefreshToken;
import com.kodiatech.traore.auth.repositories.RefreshTokenRepository;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
//@ComponentScan("java.lang")
@Service
@RequiredArgsConstructor
//pour delete par exemple
@Transactional

public class RefreshTokenService {
    @Value("${kodiatech.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    //@Value("${key.name}")
    private String abc;


    private final RefreshTokenRepository refreshTokenRepository;
    private final UtilisateurRepository utilisateurRepository;

    public RefreshToken generateRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUtilisateur(utilisateurRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }


    public int deleteRefreshToken(String userId) {
        return refreshTokenRepository.deleteByUtilisateur(utilisateurRepository.findById(userId).get());
    }
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

}
//https://www.bezkoder.com/spring-boot-refresh-token-jwt/
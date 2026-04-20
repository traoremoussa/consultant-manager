package com.kodiatech.traore.auth.refresh.services;

import com.kodiatech.traore.auth.exception.TokenRefreshException;
import com.kodiatech.traore.auth.models.RefreshToken;
import com.kodiatech.traore.auth.refresh.repositories.RefreshTokenRepository;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@ComponentScan("java.lang")
@Service
@RequiredArgsConstructor
//pour delete par exemple
@Transactional
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Value("${kodiatech.app.refresh-expiration}")
    private Long refreshTokenDurationMs;

    public RefreshToken generateRefreshToken(String userId, String device,
                                             String ip) {

        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UtilisateurNotFoundException("User not found"));

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUtilisateurId(user.getId());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        refreshToken.setDevice(device);
        refreshToken.setIpAddress(ip);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));

        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token, String currentIp,
                                         String currentDevice) {
        // 🔥 1. expiration
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        // 🔥 2. replay attack
        if (token.isUsed()) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Token already used (possible attack)");
        }

        // 🔥 3. IP check (soft)
        if (!token.getIpAddress().equals(currentIp)) {
            log.warn("IP changed for user {}", token.getUtilisateurId());
        }

        // 🔥 4. device check (strict)
        if (!token.getDevice().equals(currentDevice)) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Device mismatch");
        }

        return token;
    }

    public void markAsUsed(RefreshToken token) {
        token.setUsed(true);
        refreshTokenRepository.save(token);
    }

    // optionnel : supprimer tous les tokens d’un user
    public void deleteAllByUser(String userId) {
        Utilisateur user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UtilisateurNotFoundException("User not found"));

        refreshTokenRepository.deleteByUtilisateurId(user.getId());
    }

    public int deleteRefreshToken(String userId) {
        return refreshTokenRepository.deleteByUtilisateurId(utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UtilisateurNotFoundException("User not found")).getId());
    }

    // 🔥 suppression ciblée (mieux)
    public void delete(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }

    //------------------------- SESSION-----------------------------------------------------
    public List<RefreshToken> getUserSessions(String userId) {
        return refreshTokenRepository.findByUtilisateurId(userId);
    }

    public void deleteSession(String tokenId) {
        refreshTokenRepository.deleteById(tokenId);
    }
}
//https://www.bezkoder.com/spring-boot-refresh-token-jwt/
package com.kodiatech.traore.auth.services;

import com.kodiatech.traore.auth.config.jwt.JwtService;
import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.exception.TokenRefreshException;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.auth.models.RefreshToken;
import com.kodiatech.traore.auth.models.RefreshTokenRequest;
import com.kodiatech.traore.auth.models.TokenRefreshResponse;
import com.kodiatech.traore.auth.refresh.services.RefreshTokenService;
import com.kodiatech.traore.auth.session.model.SessionResponse;
import com.kodiatech.traore.feature.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.feature.profiles.models.Utilisateur;
import com.kodiatech.traore.feature.profiles.repositories.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletRequest httpRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // SecurityContextHolder.getContext().setAuthentication(authenticate);

        if (authenticate.isAuthenticated()) {

            var utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UtilisateurNotFoundException("not fund user"));


            String device = httpRequest.getHeader("User-Agent");
            String ip = httpRequest.getRemoteAddr();

            UserDetails userDetails = new CustomUserDetails(utilisateur);

            var jwtToken = jwtService.generateToken(userDetails);

            RefreshToken refreshToken = refreshTokenService.generateRefreshToken(utilisateur.getId(), device, ip);

            return AuthenticationResponse.builder()
                    .authenticationToken(jwtToken)
                    .id(utilisateur.getId())
                    .nom(utilisateur.getNom())
                    .email(utilisateur.getEmail())
                    //POUR REFRESH qu'on stocke qui sera reutiliser pour etre sur
                    .refreshToken(refreshToken.getToken())
                    .expiresAt(Instant.now().plusMillis(jwtService.getJwtExpirationInMillis()))
                    .build();
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    /**
     * demande de refresh le token
     * charger de bdd,
     *
     * @param refreshTokenRequest
     * @return
     */
    public TokenRefreshResponse refreshToken(RefreshTokenRequest refreshTokenRequest, HttpServletRequest httpRequest) {

        String requestRefresToken = refreshTokenRequest.getRefreshToken();
        String device = httpRequest.getHeader("User-Agent");
        String ip = httpRequest.getRemoteAddr();

        return refreshTokenService.findByToken(requestRefresToken)
                .map(r -> refreshTokenService.verifyExpiration(r, ip, device))
                .map(oldToken -> {
                    // 🔥 rotation
                    refreshTokenService.markAsUsed(oldToken);
                    // ❗ supprimer ancien token
                    refreshTokenService.delete(oldToken);

                    Utilisateur user = utilisateurRepository.findById(oldToken.getUtilisateurId())
                            .orElseThrow(() -> new UsernameNotFoundException("No user " +
                                    "Found with Id : " + oldToken.getUtilisateurId()));
                    // ✅ nouveau access token
                    String newAccessToken = jwtService.generateToken(new CustomUserDetails(user));

                    // ✅ nouveau refresh token
                    String newRefreshToken = refreshTokenService.generateRefreshToken(user.getId(), device, ip).getToken();

                    return TokenRefreshResponse.builder()
                            .accessToken(newAccessToken)
                            .refreshToken(newRefreshToken)
                            .build();
                }).orElseThrow(() -> new TokenRefreshException(requestRefresToken,
                        "Refresh token is not in database!"));
    }

    //------------------------------


    public List<SessionResponse> getSessions(Authentication authentication) {
        String email = authentication.getName();
        var utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UtilisateurNotFoundException("not fund user"));


        List<SessionResponse> sessions =
                refreshTokenService.getUserSessions(utilisateur.getId())
                        .stream()
                        .map(token -> SessionResponse.builder()
                                .id(token.getId())
                                .device(token.getDevice())
                                .ipAddress(token.getIpAddress())
                                .createdDate(token.getCreatedDate())
                                .build())
                        .toList();

        return sessions;
    }

    public void deleteSession(String tokenId) {
        refreshTokenService.deleteSession(tokenId);
    }
}

package com.kodiatech.traore.auth.services;

import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.exception.TokenRefreshException;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.auth.models.RefreshToken;
import com.kodiatech.traore.auth.models.RefreshTokenRequest;
import com.kodiatech.traore.auth.models.TokenRefreshResponse;
import com.kodiatech.traore.config.jwt.JwtUtils;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authenticate= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        if (authenticate.isAuthenticated()) {
            var utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UtilisateurNotFoundException("not fund user"));

            var jwtToken = jwtUtils.generateToken(utilisateur);
            return AuthenticationResponse.builder()
                    .authenticationToken(jwtToken)
                    .id(utilisateur.getId())
                    .nom(utilisateur.getNom())
                    .email(utilisateur.getEmail())
                    //POUR REFRESH qu'on stocke qui sera reutiliser pour etre sur
                    .refreshToken(refreshTokenService.generateRefreshToken(utilisateur.getId()).getToken())
                    .expiresAt(Instant.now().plusMillis(jwtUtils.getJwtExpirationInMillis()))
                    .build();
        }else {
                throw new UtilisateurNotFoundException("invalid user request !");
            }
    }

    /**
     * demande de refresh le token
     * charger de bdd,
     * @param refreshTokenRequest
     * @return
     */
    public TokenRefreshResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String requestRefresToken = refreshTokenRequest.getRefreshToken();
     return    refreshTokenService.findByToken(requestRefresToken)
              //  .map(r->refreshTokenService.verifyExpiration(r)); //car l'objet de l'option pass dans arg verify....
                .map( refreshTokenService::verifyExpiration) //-->RfreshToken
                .map(RefreshToken::getUtilisateur)//-->Utilisateur
                .map(util->{
                    String newToken = jwtUtils.generateToken(util);
                    return TokenRefreshResponse.builder()
                            .accessToken(newToken)
                            .refreshToken(requestRefresToken)
                            .build();
                }).orElseThrow(() -> new TokenRefreshException(requestRefresToken,
                        "Refresh token is not in database!"));}
}

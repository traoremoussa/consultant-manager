package com.kodiatech.traore.auth.services;

import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.config.jwt.JwtUtils;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtUtils.generateToken(utilisateur);
        return AuthenticationResponse.builder()
                .authenticationToken (jwtToken)
                .id(utilisateur.getId())
                .build();
    }
}

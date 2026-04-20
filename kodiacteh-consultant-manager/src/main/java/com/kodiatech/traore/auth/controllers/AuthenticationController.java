package com.kodiatech.traore.auth.controllers;


import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.auth.models.RefreshTokenRequest;
import com.kodiatech.traore.auth.models.TokenRefreshResponse;
import com.kodiatech.traore.auth.services.AuthenticationService;
import com.kodiatech.traore.auth.utils.constantes.EndPointBaseConstante;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping(EndPointBaseConstante.AUTHENTICATE)
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(request, httpRequest));

    }

    @PostMapping(EndPointBaseConstante.REFRESH)
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest, httpRequest));
    }
}

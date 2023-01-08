package com.kodiatech.traore.auth.controllers;


import com.kodiatech.traore.auth.dao.UserDao;
import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.auth.services.AuthenticationService;
import com.kodiatech.traore.config.jwt.JwtUtils;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}

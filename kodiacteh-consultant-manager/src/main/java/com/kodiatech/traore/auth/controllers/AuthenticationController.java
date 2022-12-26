package com.kodiatech.traore.auth.controllers;


import com.kodiatech.traore.auth.dao.UserDao;
import com.kodiatech.traore.auth.dto.AuthenticationRequest;
import com.kodiatech.traore.auth.models.AuthenticationResponse;
import com.kodiatech.traore.config.jwt.JwtUtils;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails userDetails = userDao.findByUsername(request.getEmail());

        if (userDetails != null) {
            AuthenticationResponse authenticationResponse=
                    AuthenticationResponse.builder().authenticationToken(jwtUtils.generateToken(userDetails))
                            .id(userDetails.getUsername()).build();
            return ResponseEntity.ok(authenticationResponse);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(AuthenticationResponse.builder().badRequest("Some error has occured").build());
    }
}

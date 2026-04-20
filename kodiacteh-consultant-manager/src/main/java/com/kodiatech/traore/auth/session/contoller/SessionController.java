package com.kodiatech.traore.auth.session.contoller;

import com.kodiatech.traore.auth.services.AuthenticationService;
import com.kodiatech.traore.auth.session.model.SessionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions(Authentication authentication) {
        return ResponseEntity.ok(authenticationService.getSessions(authentication));
    }

    @DeleteMapping("/{tokenId}")
    public ResponseEntity<?> deleteSession(@PathVariable String tokenId) {
        authenticationService.deleteSession(tokenId);
        return ResponseEntity.ok("Session supprimée");
    }
}
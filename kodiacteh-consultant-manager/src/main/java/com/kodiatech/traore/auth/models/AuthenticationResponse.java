package com.kodiatech.traore.auth.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder//permet d'alimenter tous les param (lombok)
public class AuthenticationResponse {
    private String authenticationToken;
  //  private String refreshToken;
    //private Instant expiresAt;
    private String id;
    private String badRequest;

}
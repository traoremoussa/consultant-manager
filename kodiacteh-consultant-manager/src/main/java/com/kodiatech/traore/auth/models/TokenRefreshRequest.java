package com.kodiatech.traore.auth.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder//permet d'alimenter tous les param (lombok)
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}

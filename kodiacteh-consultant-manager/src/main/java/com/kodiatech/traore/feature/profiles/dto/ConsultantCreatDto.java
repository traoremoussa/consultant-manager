package com.kodiatech.traore.feature.profiles.dto;

import lombok.Builder;

@Builder
public record ConsultantCreatDto(
        String nom,
        String prenom,
        String email,
        String telephone
) {
}

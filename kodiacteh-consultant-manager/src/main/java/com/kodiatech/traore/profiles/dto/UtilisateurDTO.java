package com.kodiatech.traore.profiles.dto;

import com.kodiatech.traore.profiles.models.Adresse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UtilisateurDTO {

    private String id;//important pour modif
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Adresse adresse;

}

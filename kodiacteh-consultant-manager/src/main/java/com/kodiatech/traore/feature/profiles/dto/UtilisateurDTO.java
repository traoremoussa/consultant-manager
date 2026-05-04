package com.kodiatech.traore.feature.profiles.dto;

import com.kodiatech.traore.auth.models.Statut;
import com.kodiatech.traore.feature.profiles.models.Adresse;
import lombok.AllArgsConstructor;
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

    private String fonctionTitle;
    private Statut statut; // ACTIF, INACTIF, EN_ATTENTE

    private String photoProfil;

    //
    private Double completudeProfil; // Pourcentage de complétude
    private Boolean profileComplete;
}

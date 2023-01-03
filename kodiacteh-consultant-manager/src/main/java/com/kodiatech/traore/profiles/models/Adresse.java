package com.kodiatech.traore.profiles.models;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Adresse {

    private String adresse;
    private String complementAdresse;
    private String codePostal;
    private String ville;

}

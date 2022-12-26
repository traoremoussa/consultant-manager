package com.kodiatech.traore.profiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

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

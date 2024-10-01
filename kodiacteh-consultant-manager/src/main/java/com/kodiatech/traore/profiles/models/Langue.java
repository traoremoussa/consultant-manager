package com.kodiatech.traore.profiles.models;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Langue {
    @Id
    private String id;
    private String nom;
    private String niveauEcrit;
    private String niveauOral;
}

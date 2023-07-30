package com.kodiatech.traore.profiles.mapper.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class ProjetPersonnel {
    @Id
    private String id;
    private String nom;
    private Integer durer;
    private String lieuProjet;
    private Ville  ville;
    private String environnementTech;


}

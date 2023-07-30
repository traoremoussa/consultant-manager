package com.kodiatech.traore.profiles.mapper.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Formation {
    @Id
    private String id;

    private String intituler;
    private String etablissement;
    //calendrier from extrai l'annee et persiste (affiche calendrier juste year)
    private Integer annee;
}

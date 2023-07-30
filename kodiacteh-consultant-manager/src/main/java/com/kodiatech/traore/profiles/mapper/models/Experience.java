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
public class Experience {
    @Id
    private String id;

    private String societe;
    //ici on a pas une ville controle en fonction du colone donc je laisse en string
    private  String ville;

    //POSTE
    //on pouvait faire une classe à part si necessaire de predefinir auto les poste possible
    private  String poste;
    private Date periodeDebut;
    private Date periodeFin;
    private boolean posteOccupe;

}

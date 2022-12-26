package com.kodiatech.traore.profiles.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;


@AllArgsConstructor

@NoArgsConstructor
@Data
@Document("utilisateur")
public class Utilisateur {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;


    //private String photoProfil;
    //Adresse
    @Embedded
    /*
    @AttributeOverrides(value = {
            @AttributeOverride( name = "adresse", column = @Column(name = "adresse_adresse")),
            @AttributeOverride( name = "complementAdresse", column = @Column(name = "adresse_complement_adresse")),
            @AttributeOverride( name = "codePostal", column = @Column(name = "adresse_code_ostal")),
            @AttributeOverride( name = "ville", column = @Column(name = "adresse_ville")),
    })*/
    private Adresse adresse;
/*
    public Utilisateur(String nom, String prenom, String password, String email, String telephone, Adresse adresse) {
        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
        this.email=email;
        this.telephone=telephone;
        this.adresse=adresse;
    }*/
}

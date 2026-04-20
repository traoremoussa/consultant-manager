package com.kodiatech.traore.profiles.models;


import com.kodiatech.traore.auth.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("utilisateurs")
public class Utilisateur {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;

    private String fonctionTitle;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    //private String photoProfil;

    @DocumentReference
    private List<ConnaissanceTech> connaissanceTeches;
    @DocumentReference
    private List<Competence> competences;
    @DocumentReference
    private List<Experience> experiences;
    @DocumentReference
    private List<Formation> formations;
    @DocumentReference
    private List<Langue> langues;
    @DocumentReference
    private List<ProjetPersonnel> projetPersonnels;


    //Adresse
    @DBRef
    /*
    @AttributeOverrides(value = {
            @AttributeOverride( name = "adresse", column = @Column(name = "adresse_adresse")),
            @AttributeOverride( name = "complementAdresse", column = @Column(name = "adresse_complement_adresse")),
            @AttributeOverride( name = "codePostal", column = @Column(name = "adresse_code_ostal")),
            @AttributeOverride( name = "ville", column = @Column(name = "adresse_ville")),
    })*/
    private Adresse adresse;


    public Utilisateur(String nom, String prenom, String password, String email, String telephone, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    /**
     *
     * @List reference
     */


//----------------------------------SECURITY
}

/*POSTMAN
  {

        "nom": "diaby",
        "prenom": "traore",
        "password": "password8",
        "email": "klm@gmail.com",
        "telephone": "00-00-00-00-00-00",
        "adresse": {
            "adresse": "Thomas edison",
            "complementAdresse": "bat a, appt 68",
            "codePostal": "31400",
            "ville": "Toulouse"
        }
    }
 */
package com.kodiatech.traore.profiles.mapper.models;


import com.kodiatech.traore.auth.models.Role;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("utilisateur")
public class Utilisateur implements UserDetails {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;

    private String fonctionTitle;
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


/*
    public Utilisateur(String nom, String prenom, String password, String email, String telephone, Adresse adresse) {
        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
        this.email=email;
        this.telephone=telephone;
        this.adresse=adresse;
    }*/

    /**
     *
     * @List reference
     */





//----------------------------------SECURITY
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
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
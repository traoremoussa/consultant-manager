package com.kodiatech.traore.profiles.services;

import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public Utilisateur addConsultant(Utilisateur util){

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(util.getNom());
        utilisateur.setPrenom(util.getPrenom());
        utilisateur.setEmail(util.getEmail());
        utilisateur.setTelephone(util.getTelephone());
        utilisateur.setPassword(passwordEncoder.encode(util.getPassword()));
          utilisateur.setAdresse(util.getAdresse());
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> findByEmail(String email){
        return utilisateurRepository.findByEmail(email);
    }
   public List<Utilisateur> consultants(){
        return utilisateurRepository.findAll();
    }

    public Utilisateur consultantById(String id){
        return utilisateurRepository.findById(id).orElseThrow(()-> new UtilisateurNotFoundException(id));
    }
}

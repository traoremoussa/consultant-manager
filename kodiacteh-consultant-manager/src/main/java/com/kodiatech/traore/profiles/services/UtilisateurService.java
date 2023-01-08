package com.kodiatech.traore.profiles.services;

import com.kodiatech.traore.auth.models.Role;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
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

    //Observation
    private ObservationRegistry observationRegistry;

    public Utilisateur addConsultant(Utilisateur util) {

        var utilisateur = Utilisateur.builder()

                .nom(util.getNom())
                .prenom(util.getPrenom())
                .email(util.getEmail())
                .telephone(util.getTelephone())
                .password(passwordEncoder.encode(util.getPassword()))
                .adresse(util.getAdresse())
                .role(Role.USER)
                .build();

//TODO envoye message et token
        return Observation.createNotStarted("addConsult", observationRegistry)
                .observe(() -> (utilisateurRepository.save(utilisateur)));
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public List<Utilisateur> consultants() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur consultantById(String id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new UtilisateurNotFoundException(id));
    }
}

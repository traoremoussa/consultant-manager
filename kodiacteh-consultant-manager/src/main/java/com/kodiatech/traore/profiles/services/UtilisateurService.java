package com.kodiatech.traore.profiles.services;

import com.kodiatech.traore.auth.models.Role;
import com.kodiatech.traore.profiles.dto.UtilisateurDTO;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.mapper.UtilisateurMapper;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    //inj
    private final UtilisateurMapper utilisateurMapper;
    //Observation
    private ObservationRegistry observationRegistry;

    private List<Utilisateur> utilisateurs= new ArrayList<>();

@SuppressWarnings("syntheticAccess" )
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

//TODO envoye message et token pour valider l'inscruption
        return Observation.createNotStarted("addConsult", observationRegistry)
                .observe(() -> (utilisateurRepository.save(utilisateur)));
    }

    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    /**
     * Method to get the employee default values
     * First time, it'' get from database
     * Next time onwards it will get it from Cache
     *
     * @return employees
     */

    @Cacheable("utilisateurs")
    public List<Utilisateur> consultants() {
        utilisateurs=utilisateurRepository.findAll();
        return utilisateurs;
    }

    public UtilisateurDTO consultantById(String id) {
        Utilisateur util= utilisateurRepository.findById(id).orElseThrow(() -> new UtilisateurNotFoundException(id));

        return utilisateurMapper.utilisateurToUtilisateurDTO(util);
    }

    public Utilisateur consultantAllInfoById(String id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new UtilisateurNotFoundException(id));
    }
}

package com.kodiatech.traore.feature.profiles.services.impl;

import com.kodiatech.traore.auth.models.Role;
import com.kodiatech.traore.feature.profiles.dto.ConsultantCreatDto;
import com.kodiatech.traore.feature.profiles.dto.UtilisateurDTO;
import com.kodiatech.traore.feature.profiles.exceptions.UtilisateurFoundException;
import com.kodiatech.traore.feature.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.feature.profiles.mapper.UtilisateurMapper;
import com.kodiatech.traore.feature.profiles.models.Utilisateur;
import com.kodiatech.traore.feature.profiles.repositories.UtilisateurRepository;
import com.kodiatech.traore.feature.profiles.services.IAdminService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kodiatech.traore.auth.models.Statut.ACTIF;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;
    private ObservationRegistry observationRegistry;

    public List<UtilisateurDTO> getconsultantDto() {
        List<Utilisateur> utilisateurs;
        utilisateurs = utilisateurRepository.findAll();

        if (utilisateurs.isEmpty()) {
            throw new UtilisateurNotFoundException("Aucun consulatant trouvé");
        }
        return utilisateurs.stream()
                .map(utilisateurMapper::utilisateurToUtilisateurDTO)
                .toList();
    }

    /**
     * //TODO envoye message de completer son profil et premier connextion de modifier son mdp?
     * //TODO envoye message et token pour valider l'inscruption
     */
    @Override
    public ConsultantCreatDto addConsultant(ConsultantCreatDto dto) {
        boolean userExist = utilisateurRepository.findByEmail(dto.email()).isPresent();
        if (userExist) {
            throw new UtilisateurFoundException("Cet utilisateur existe déjà avec cet email: " + dto.email());
        }
        var utilisateur = utilisateurMapper.ConsultantCreatDtoToUtil(dto);
        utilisateur.setPassword(passwordEncoder.encode("temp123"));
        utilisateur.setRole(Role.CONSULTANT);
        utilisateur.setStatut(ACTIF);
        utilisateur.setProfileComplete(false);

        var savedUser = Observation.createNotStarted("add new Consult", observationRegistry)
                .observe(() -> utilisateurRepository.save(utilisateur));

        return utilisateurMapper.UtilToConsultantCreatDto(savedUser);
    }
}

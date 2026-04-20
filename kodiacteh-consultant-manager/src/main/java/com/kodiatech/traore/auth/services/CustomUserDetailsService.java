package com.kodiatech.traore.auth.services;

import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameEmail) throws UsernameNotFoundException {
        Optional<Utilisateur> userOptional = utilisateurRepository.findByEmail(usernameEmail);

        Utilisateur utilisateur = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + usernameEmail));

        return new CustomUserDetails(utilisateur);
    }
}

package com.kodiatech.traore.profiles.repositories;

import com.kodiatech.traore.profiles.models.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur,String> {
    Optional<Utilisateur> findByEmail(String email);
}

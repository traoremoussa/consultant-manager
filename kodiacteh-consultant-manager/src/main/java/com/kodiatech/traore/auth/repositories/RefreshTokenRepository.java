package com.kodiatech.traore.auth.repositories;

import com.kodiatech.traore.auth.models.RefreshToken;
import com.kodiatech.traore.profiles.models.Utilisateur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface   RefreshTokenRepository extends MongoRepository<RefreshToken,String> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
    @Modifying
    int deleteByUtilisateur(Utilisateur utilisateur);
}

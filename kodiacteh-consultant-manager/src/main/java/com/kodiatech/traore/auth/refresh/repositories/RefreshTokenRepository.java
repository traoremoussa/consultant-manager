package com.kodiatech.traore.auth.refresh.repositories;

import com.kodiatech.traore.auth.models.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);


    int deleteByUtilisateurId(String utilisateurId);

    //
    List<RefreshToken> findByUtilisateurId(String utilisateurId);
}

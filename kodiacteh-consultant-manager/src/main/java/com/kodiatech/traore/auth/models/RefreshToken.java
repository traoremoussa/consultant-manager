package com.kodiatech.traore.auth.models;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@NoArgsConstructor
@Data
@Document(collection = "refresh_tokens")
public class RefreshToken {
    @Id
    private String id;
    
    @Indexed
    private String utilisateurId;

    @Indexed(unique = true)
    private String token;

    //Mongo supprime automatiquement les tokens expirés
    @Indexed(expireAfterSeconds = 0)
    private Instant expiryDate;

    private Instant createdDate;
    /**
     * Sans device / IP : User se connecte → token A / Hacker vole A 😬 /Hacker utilise A depuis un autre pays
     */
    private String device;
    private String ipAddress;

    private boolean used; // 🔥 anti replay attack
}

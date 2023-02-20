package com.kodiatech.traore.auth.models;

import com.kodiatech.traore.profiles.models.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class RefreshToken {
    @Id
    private String id;
    @DocumentReference
    private Utilisateur utilisateur;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    private Instant createdDate;
}

package com.kodiatech.traore.feature.profiles.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Certification {
    private String nom;
    private String organisme;
    private LocalDate dateObtention;
    private String urlCertificat;

}

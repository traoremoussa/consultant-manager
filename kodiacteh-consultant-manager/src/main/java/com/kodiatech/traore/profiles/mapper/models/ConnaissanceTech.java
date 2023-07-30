package com.kodiatech.traore.profiles.mapper.models;

import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class ConnaissanceTech {
    @Id
    private String id;
    @Enumerated
    private TypeConnaissance typeConnaissace;
    private String description;
}

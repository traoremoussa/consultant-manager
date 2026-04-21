package com.kodiatech.traore.feature.profiles.mapper;

import com.kodiatech.traore.feature.profiles.dto.UtilisateurDTO;
import com.kodiatech.traore.feature.profiles.models.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @See https://www.baeldung.com/mapstruct
 * @See https://stackoverflow.com/questions/63034956/mapstruct-no-property-named-packaging-exists-in-source-parameters
 * @See https://github.com/SaiUpadhyayula/spring-reddit-clone
 *
 */
@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    @Mapping(target = "id", source = "utilisateur.id")
    @Mapping(target = "nom", source = "utilisateur.nom")
    @Mapping(target = "prenom", source = "utilisateur.prenom")
    @Mapping(target = "email", source = "utilisateur.email")
    @Mapping(target = "telephone", source = "utilisateur.telephone")
    @Mapping(target = "adresse", source = "utilisateur.adresse")
    UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur utilisateur);

    //TODO pour dto --> l'objet
}

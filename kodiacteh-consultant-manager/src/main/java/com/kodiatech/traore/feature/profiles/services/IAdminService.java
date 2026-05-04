package com.kodiatech.traore.feature.profiles.services;

import com.kodiatech.traore.feature.profiles.dto.ConsultantCreatDto;
import com.kodiatech.traore.feature.profiles.dto.UtilisateurDTO;

import java.util.List;

public interface IAdminService {
    List<UtilisateurDTO> getconsultantDto();

    ConsultantCreatDto addConsultant(ConsultantCreatDto dto);
}

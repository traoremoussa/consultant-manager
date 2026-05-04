package com.kodiatech.traore.feature.profiles.controllers;

import com.kodiatech.traore.feature.profiles.dto.ConsultantCreatDto;
import com.kodiatech.traore.feature.profiles.dto.UtilisateurDTO;
import com.kodiatech.traore.feature.profiles.services.IAdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
@SecurityRequirement(name = "kodiatech-api")
public class AdminController {

    private final IAdminService adminService;

    @PostMapping("/add/consultant")
    public ResponseEntity<ConsultantCreatDto> addNewConsult(@RequestBody ConsultantCreatDto dto) {
        ConsultantCreatDto saved = adminService.addConsultant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/consultants/")
    public ResponseEntity<List<UtilisateurDTO>> getConsult() {
        return ResponseEntity.ok(adminService.getconsultantDto());
    }
}

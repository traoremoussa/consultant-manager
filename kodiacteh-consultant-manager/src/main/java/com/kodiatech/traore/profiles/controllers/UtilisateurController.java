package com.kodiatech.traore.profiles.controllers;

import com.kodiatech.traore.profiles.dto.UtilisateurDTO;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import com.kodiatech.traore.profiles.models.Utilisateur;
import com.kodiatech.traore.profiles.services.UtilisateurService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;
//http://localhost:8080/api/v1/consultant
@RestController
@Data
@RequestMapping("api/v1/consultant")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "kodiatech-api")
public class UtilisateurController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UtilisateurService utilisateurService;

    //@ApiIgnore

    @GetMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    //CRUD
    //
    @GetMapping("/consultants")
    public ResponseEntity<List<Utilisateur>> consultants(){
        LOG.info("Getting all users.");
        return ResponseEntity.ok(utilisateurService.consultants());
    }
    @GetMapping("all-info/{id}")
    public ResponseEntity<Utilisateur> consultantsByIdAllInfo(@PathVariable String id){
        try {
            LOG.info("---------------Getting  users.");
            return ResponseEntity.ok(utilisateurService.consultantAllInfoById(id));
        } catch (UtilisateurNotFoundException exc) {
            throw new UtilisateurNotFoundException(
                    HttpStatus.NOT_FOUND.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> consultantsById(@PathVariable String id){
        try {
            LOG.info("---------------Getting  users.");
            return ResponseEntity.ok(utilisateurService.consultantById(id));
        } catch (UtilisateurNotFoundException exc) {
                throw new UtilisateurNotFoundException(
                        HttpStatus.NOT_FOUND.toString());
            }
    }

    @PostMapping("/addCons")
    public ResponseEntity<String> addConsultant(@RequestBody Utilisateur util){
        LOG.info("Saving user.{}",util);
        boolean userExists = utilisateurService
                .findByEmail(util.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            return ResponseEntity.badRequest().body("email already taken");
        }
        utilisateurService.addConsultant(util);
        return ResponseEntity.ok("Add succes");
    }

}

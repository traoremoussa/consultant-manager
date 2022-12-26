package com.kodiatech.traore.profiles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    /**
     *
     * à l'ecoute d'evenement de la classe excepection en argument,
     * si ce type devenement se produise et ça declenche
     */
    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ProblemDetail handlePostNotFoundException(UtilisateurNotFoundException e) throws URISyntaxException {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,e.getMessage());
        problemDetail.setProperty("consultantId",e.getId());
        problemDetail.setType(new URI("http://localhost:8080/problems/post-not-found"));
        return problemDetail;
    }
}

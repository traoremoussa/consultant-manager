package com.kodiatech.traore.commun.exceptionadvice;

import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;

@RestControllerAdvice
public class ExceptionHandlerAdvice  extends ResponseEntityExceptionHandler {
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

    /**
     *  Au niveau d'angular il faut gerer par httpstatus
     * @param ex
     * @return
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<ProblemDetail> handleAuthenticationException(Exception ex) {

        ProblemDetail re = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,
                "Authentication failed at controller advice");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
}

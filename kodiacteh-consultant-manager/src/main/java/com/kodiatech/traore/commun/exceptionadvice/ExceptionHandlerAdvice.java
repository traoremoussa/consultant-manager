package com.kodiatech.traore.commun.exceptionadvice;

import com.kodiatech.traore.auth.exception.TokenRefreshException;
import com.kodiatech.traore.profiles.exceptions.UtilisateurNotFoundException;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;

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

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleTokenRefreshException(TokenRefreshException e) {
        return ErrorResponse.builder(e, HttpStatus.FORBIDDEN, e.getMessage())
                .title("Token Refresh Exception")
                .type(URI.create("https:///errors/not-found"))
                .property("errorCategory", "Generic")
                .property("timestamp", Instant.now())
                .build();
    }
}
//https://www.sivalabs.in/spring-boot-3-error-reporting-using-problem-details/

// TODO gestion erreur meilleur et simple (code-status et message) pour chaq exception
//https://www.geeksforgeeks.org/spring-boot-exception-handling/
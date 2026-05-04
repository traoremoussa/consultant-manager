package com.kodiatech.traore.feature.profiles.exceptions;

public class UtilisateurFoundException extends RuntimeException {

    public UtilisateurFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurFoundException(String message) {
        super(message);
    }

    public UtilisateurFoundException(Throwable cause) {
        super(cause);
    }
}



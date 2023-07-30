package com.kodiatech.traore.profiles.exceptions;

public class UtilisateurNotFoundException extends RuntimeException {

    public UtilisateurNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public UtilisateurNotFoundException(String message) {
        super(message);
    }
    public UtilisateurNotFoundException(Throwable cause) {
        super(cause);
    }
}

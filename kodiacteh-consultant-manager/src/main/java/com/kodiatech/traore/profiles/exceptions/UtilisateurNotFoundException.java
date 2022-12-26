package com.kodiatech.traore.profiles.exceptions;

public class UtilisateurNotFoundException extends RuntimeException {

    private String id;

    public UtilisateurNotFoundException(String id) {
        super("Post not found!");
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

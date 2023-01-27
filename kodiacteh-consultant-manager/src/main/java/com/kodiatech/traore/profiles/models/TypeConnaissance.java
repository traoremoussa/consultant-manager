package com.kodiatech.traore.profiles.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



public enum TypeConnaissance {
    BASE_DE_DONNEES("Base de données"),
    LANGAGES("Langues"),
    OUTILS_DE_DEVELOPPEMENT("Outils de developement"),
    METHODOLOGIES("Methodologies"),
    FRAMEWORKS("Frameworks");

        private String value;

        TypeConnaissance(String value){
            this.value=value;
        }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

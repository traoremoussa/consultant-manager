package com.kodiatech.traore.profiles.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    public void afficher (){
        TypeConnaissance.typeConnaissanceStream()
                .filter(d -> d.getValue().equals("Frameworks"))
                .forEach(System.out::println);
    }

    public static Stream<TypeConnaissance> typeConnaissanceStream() {
        return Stream.of(TypeConnaissance.values());
    }
    public static List<TypeConnaissance> typeConnaissanceList(){
        List typeConnaissances= Stream.of(TypeConnaissance.values()).collect(Collectors.toList());
        return typeConnaissances;
    }
}
//@See https://www.baeldung.com/java-enum-iteration
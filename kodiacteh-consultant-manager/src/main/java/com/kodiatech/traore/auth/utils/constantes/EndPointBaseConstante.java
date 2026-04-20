package com.kodiatech.traore.auth.utils.constantes;

public class EndPointBaseConstante {

    public static final String AUTHENTICATE = "/authenticate";

    //REFRESH TOKEN
    public static final String REFRESH = "/refresh";

    //SESSION
    public static final String DELETE_SESSION = "/sessions/{tokenId}";
    public static final String SESSION = "/sessions";

}

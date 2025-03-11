package com.bd_java.application.ui;

public class Validacion {

    public static boolean esEmailValido(String input) {
        return input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        
    }
    
}
package com.bd_java.application.ui;

public class MenuFactory {
    public static Gestionable getMenu(int option) {
        switch (option) {
            case 1: return new UiClient(null);
            case 2: return new UiProduct(null);
            default: return null;
        }
    }
}

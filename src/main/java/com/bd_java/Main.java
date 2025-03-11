package com.bd_java;

import java.sql.Connection;
import java.util.Scanner;

import com.bd_java.application.ui.Gestionable;
import com.bd_java.application.ui.MenuFactory;
import com.bd_java.infraestructure.database.ConnMySql;


public class Main {
    public static void main(String[] args) {
        String entrada = """
        =========================================================
        |   SE HA ESTABLECIDO LA CONEXION A LA BASE DE DATOS    |
        =========================================================    
        """;
        String salida = """
        ===================================================        
        |    LA CONEXION A LA BASE DE DATOS HA FALLADO    |
        ===================================================
        """;
        ConnMySql conexionDB = new ConnMySql();
        try {
            Connection conexion = conexionDB.getConexion();
            if (conexion != null) {
                System.out.println(entrada);

                System.out.println("Cargando Menú Principal...");                 
                Thread.sleep(5000); // Esperar 5 segundos
                limpiarConsola();  
            }
        } catch (Exception e) {
            System.err.println(salida);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            String men = """
            ====================================
            |          MENU PRINCIPAL          |
            ====================================
            |    1. Gestionar Clientes         |
            |    2. Gestionar Productos        |
            |    3. Salir                      |
            ====================================
            """;
            System.out.println(men);
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            Gestionable menu = MenuFactory.getMenu(option);
            if (menu != null) {
                menu.gestionar(scanner);
            } else if (option != 3) {
                System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 3);
        
        scanner.close();
        System.out.println("Saliendo...");
        
    }

    private static void limpiarConsola() {
        try {
            // Intenta limpiar la consola en sistemas Windows
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            try {
                // Intenta limpiar la consola en sistemas Unix/Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (Exception ex) {
                // Si no se puede limpiar la consola, simplemente imprime varias líneas en blanco
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            }
        }
    }
}
package com.bd_java;

import java.sql.Connection;
import java.util.Scanner;

import com.bd_java.application.ui.Gestionable;
import com.bd_java.application.ui.MenuFactory;
import com.bd_java.infraestructure.database.ConnMySql;


public class Main {
    public static void main(String[] args) throws InterruptedException {
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
                Thread.sleep(3000); // Esperar 5 segundos
                limpiarConsola();  
            }
        } catch (Exception e) {
            System.err.println(salida);
            return;
        }
        Scanner sc = new Scanner(System.in);
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
            while (!sc.hasNextInt()) {
                System.err.println("Error: El valor Ingresado no es valido.");
                sc.next();
                System.out.print("Seleccione una opción: ");
            }
            option = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            System.out.println("Cargando...");                 
                Thread.sleep(2000); // Esperar 5 segundos
                limpiarConsola(); 

            Gestionable menu = MenuFactory.getMenu(option);
            if (menu != null) {
                menu.gestionar(sc);
            } else if (option != 3) {
                System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (option != 3);
        
        sc.close();
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
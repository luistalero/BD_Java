package com.bd_java;

import java.sql.Connection;
import java.util.Scanner;

import com.bd_java.application.UseCase.client.ClientUseCase;
import com.bd_java.application.UseCase.product.ProductUseCase;
import com.bd_java.application.ui.Gestionable;
import com.bd_java.application.ui.MenuFactory;
import com.bd_java.domain.repository.ClientRepository;
import com.bd_java.domain.repository.ProductRepository;
import com.bd_java.infraestructure.database.ConnMySql;
import com.bd_java.infraestructure.persistence.client.ClientRepositoryImpl;
import com.bd_java.infraestructure.persistence.product.ProductRepositoryImpl;


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
            }
        } catch (Exception e) {
            System.err.println(salida);
            return;
        }
        
        
            
        //     ProductRepository repository = new ProductRepositoryImpl(conexionDB);
        //     ProductUseCase productoCasoUso = new ProductUseCase(repository);
        //     try (Scanner cs = new Scanner(System.in)) {
        //         System.out.print("Ingrese ID del Producto: ");
        //         String di = cs.nextLine();
        //         System.out.print("Ingrese Nombre: ");
        //         String name = cs.nextLine();
        //         System.out.print("Ingrese Stock: ");
        //         int stock = cs.nextInt();
        //         productoCasoUso.registrarproducto(di, name, stock);
        //         System.out.println("✅ Producto registrado exitosamente.");
        
        //     }
        // }
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("===== Menú Principal =====");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Salir");
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
}
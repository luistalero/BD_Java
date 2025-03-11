package com.bd_java.application.ui;

import java.util.List;
import java.util.Scanner;

import com.bd_java.application.UseCase.product.ProductUseCase;
import com.bd_java.domain.entity.Product;
import com.bd_java.domain.repository.ProductRepository;
import com.bd_java.infraestructure.database.ConnMySql;
import com.bd_java.infraestructure.database.ConnectionDb;
import com.bd_java.infraestructure.persistence.product.ProductRepositoryImpl;

public class UiProduct implements Gestionable {
    private final ProductUseCase ProductUseCase;

    public UiProduct(Object object) {
        ConnectionDb connectionDb = new ConnMySql();
        ProductRepository repository = new ProductRepositoryImpl(connectionDb);
        this.ProductUseCase = new ProductUseCase(repository);
    }

    @Override
    public void gestionar(Scanner sc) {
        int opcion;
        do {
            String menu = """
            =====================================
            |       GESTION DE PRODUCTOS        |
            =====================================
            |    1. Agregar producto            |
            |    2. Modificar producto          |
            |    3. Eliminar producto           |   
            |    4. Listar productos            |
            |    5. Volver al menú principal    |
            =====================================
            """;
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            while (!sc.hasNextInt()) {
                System.err.println("Error: El valor Ingresado no es valido.");
                sc.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println("Cargando...");                 
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
                limpiarConsola();

            switch (opcion) {
                case 1:
                    agregarProducto(sc);
                    break;
                case 2:
                    actualizarproducto(sc);
                    break;
                case 3:
                    eliminarproducto(sc);
                    break;
                case 4:
                    listarproductos();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void agregarProducto(Scanner sc) {
        System.out.print("Ingrese ID del Producto: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese ID del Producto: ");
        }
        String id = sc.nextLine();
        System.out.print("Ingrese Nombre: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese Nombre: ");
        }
        String name = sc.nextLine();
        System.out.print("Ingrese Stock: ");
        while (!sc.hasNextInt()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese Stock: ");
        }
        int stock = sc.nextInt();
        ProductUseCase.registrarproducto(id, name, stock);
        limpiarConsola();
        System.out.println("✅ Producto registrado exitosamente.");
        
    }

    private void actualizarproducto(Scanner sc) {
        System.out.print("Ingrese el ID del producto a modificar: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese el ID del producto a modificar: ");
        }
        String id = sc.nextLine();
        System.out.print("Ingrese el nuevo nombre: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese el nuevo nombre: ");
        }
        String name = sc.nextLine();
        System.out.print("Ingrese el nuevo stock: ");
        while (!sc.hasNextInt()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese el nuevo stock: ");
        }
        int stock = sc.nextInt();
        ProductUseCase.actualizarproducto(id, name, stock);
        System.out.println("✅ producto modificado exitosamente.");
        limpiarConsola();
    }

    private void eliminarproducto(Scanner sc) {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese el ID del producto a eliminar: ");
        }
        String id = sc.nextLine();
        ProductUseCase.eliminarproducto(id);
        limpiarConsola();
        System.out.println("✅ producto eliminado exitosamente.");
        
    }

    private void listarproductos() {
        List<Product> productos = ProductUseCase.listarproductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay Productos registrados.");
            System.out.println("Debe ingresar productos para poder visusalizarlos");

        } else {
            String mensage = """
            +------+----------------------+------------+
            |  ID  |        Nombre        |    Stock   |
            +------+----------------------+------------+        
            """;
            System.out.println(mensage);

            for (Product product : productos) {
                System.out.println(product);
            }

            System.out.println("+------+----------------------+------------+");
        }
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
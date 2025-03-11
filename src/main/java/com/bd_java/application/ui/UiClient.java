package com.bd_java.application.ui;

import java.util.List;
import java.util.Scanner;

import com.bd_java.application.UseCase.client.ClientUseCase;
import com.bd_java.infraestructure.database.ConnMySql;
import com.bd_java.infraestructure.database.ConnectionDb;
import com.bd_java.infraestructure.persistence.client.ClientRepositoryImpl;
import com.bd_java.domain.entity.Client;
import com.bd_java.domain.repository.ClientRepository;

public class UiClient implements Gestionable {
    private final ClientUseCase ClientUseCase;

    public UiClient(Object object) {
        ConnectionDb connectionDb = new ConnMySql();
        ClientRepository repositorio = new ClientRepositoryImpl(connectionDb);
        this.ClientUseCase = new ClientUseCase(repositorio);
    }

    @Override
    public void gestionar(Scanner sc) {
        int opcion;
        do {
            String menu = """
            ====================================
            |       GESTION DE CLIENTES        |
            ====================================
            |    1. Agregar cliente            |
            |    2. Modificar cliente          |
            |    3. Eliminar cliente           |   
            |    4. Listar clientes            |
            |    5. Volver al menú principal   |
            ====================================
            """;
            System.out.println(menu);
            System.out.print("Seleccione una opción: ");
            while (!sc.hasNextInt()) {
                System.err.println("Error: El valor Ingresado no es valido.");
                sc.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            System.out.println("Cargando...");                 
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
                limpiarConsola(); 

            switch (opcion) {
                case 1:
                    agregarCliente(sc);
                    break;
                case 2:
                    actualizarCliente(sc);
                    break;
                case 3:
                    eliminarCliente(sc);
                    break;
                case 4:
                    listarClientes();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void agregarCliente(Scanner sc) {
        int id;
        System.out.print("Ingrese ID del Cliente: ");
        while (!sc.hasNextInt()) {
            System.err.println("Error: El valor Ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese ID del Cliente: ");
        }
        id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        String name;
        System.out.print("Ingrese Nombre: ");
        while (sc.hasNextInt() || sc.hasNextDouble()) {
            System.err.println("Error: El valor ingresado no es valido.");
            sc.next();
            System.out.print("Ingrese el Nombre: ");
        }
        name = sc.nextLine();
        String email = null;
        System.out.print("Ingrese Email: ");
        while (!Validacion.esEmailValido(email)) {
            System.err.println("Error: Ingrese un Correo Valido.");
            sc.next();
            System.out.print("Ingrese Email: ");
        }
        email= sc.nextLine();
        ClientUseCase.registrarCliente(id, name, email);
        limpiarConsola();
        System.out.println("✅ Cliente registrado exitosamente.");
    }

    private void actualizarCliente(Scanner sc) {
        System.out.print("Ingrese el ID del cliente a modificar: ");
        
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el nuevo nombre: ");
        String name = sc.nextLine();
        System.out.print("Ingrese el nuevo email: ");
        String email = sc.nextLine();
        ClientUseCase.actualizarCliente(id, name, email);
        limpiarConsola();
        System.out.println("✅ Cliente modificado exitosamente.");
    }

    private void eliminarCliente(Scanner sc) {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        
        int id = sc.nextInt();
        sc.nextLine();
        ClientUseCase.eliminarCliente(id);
        limpiarConsola();
        System.out.println("✅ Cliente eliminado exitosamente.");
    }

    private void listarClientes() {
        List<Client> clientes = ClientUseCase.listarClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            System.out.println("Debe ingresar Clientes para poder visusalizarlos");

        } else {
            String mensage = """
            +------+----------------------+--------------------------------+
            |  ID  |        Nombre        |             Email              |
            +------+----------------------+--------------------------------+        
            """;
            System.out.println(mensage);

            for (Client cliente : clientes) {
                System.out.println(cliente);
            }

            System.out.println("+------+----------------------+--------------------------------+");
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
package com.bd_java.application.ui;

import java.util.Scanner;

import com.bd_java.application.UseCase.client.ClientUseCase;
import com.bd_java.infraestructure.database.ConnMySql;
import com.bd_java.infraestructure.database.ConnectionDb;
import com.bd_java.infraestructure.persistence.client.ClientRepositoryImpl;
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
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

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
        System.out.print("Ingrese ID del Cliente: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        System.out.print("Ingrese Nombre: ");
        String name = sc.nextLine();
        System.out.print("Ingrese Email: ");
        String email = sc.nextLine();
        ClientUseCase.registrarCliente(id, name, email);
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
        System.out.println("✅ Cliente modificado exitosamente.");
    }

    private void eliminarCliente(Scanner sc) {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ClientUseCase.eliminarCliente(id);
        System.out.println("✅ Cliente eliminado exitosamente.");
    }

    private void listarClientes() {
        ClientUseCase.listarClientes().forEach(System.out::println);
    }
}
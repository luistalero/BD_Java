package com.bd_java.application.ui;

import java.util.Scanner;

import com.bd_java.application.UseCase.client.ClientUseCase;
import com.bd_java.infraestructure.database.ConnectionDb;

public class UiClient implements Gestionable {
    private final ClientUseCase ClientUSeCase;

    public UiClient(ConnectionDb conexionDB) {
        ClientRepository repositorio = new ClientRepositoryImpl(conexionDB);
        this.ClientUSeCase = new ClientUseCase(repositorio);
    }

    @Override
    public void gestionar(Scanner scanner) {
        int opcion;
        do {
            System.out.println("--- Gestión de Clientes ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Modificar cliente");
            System.out.println("3. Eliminar cliente");
            System.out.println("4. Listar clientes");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarCliente(scanner);
                    break;
                case 2:
                    modificarCliente(scanner);
                    break;
                case 3:
                    eliminarCliente(scanner);
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

    private void agregarCliente(Scanner scanner) {
        System.out.print("Ingrese ID del Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Email: ");
        String email = scanner.nextLine();
        ClientUSeCase.registrarCliente(id, nombre, email);
        System.out.println("✅ Cliente registrado exitosamente.");
    }

    private void modificarCliente(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo email: ");
        String email = scanner.nextLine();
        ClientUSeCase.modificarCliente(id, nombre, email);
        System.out.println("✅ Cliente modificado exitosamente.");
    }

    private void eliminarCliente(Scanner scanner) {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ClientUSeCase.eliminarCliente(id);
        System.out.println("✅ Cliente eliminado exitosamente.");
    }

    private void listarClientes() {
        ClientUSeCase.listarClientes().forEach(System.out::println);
    }
}
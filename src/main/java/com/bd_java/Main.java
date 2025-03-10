package com.bd_java;

import java.util.Scanner;

import com.bd_java.application.UseCase.client.ClientUseCase;
import com.bd_java.domain.repository.ClientRepository;
import com.bd_java.infraestructure.persistence.client.ClientRepositoryImpl;


public class Main {
    public static void main(String[] args) {
        ClientRepository repositorio = new ClientRepositoryImpl(null);
        ClientUseCase clienteCasoUso = new ClientUseCase(repositorio);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrese ID del Cliente: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea
            System.out.print("Ingrese Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese Email: ");
            String email = sc.nextLine();
            clienteCasoUso.registrarCliente(id, nombre, email);
            System.out.println("✅ Cliente registrado exitosamente.");        
        }
    }
}
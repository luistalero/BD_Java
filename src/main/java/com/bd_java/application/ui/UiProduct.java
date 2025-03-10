package com.bd_java.application.ui;

import java.util.Scanner;

public class UiProduct implements Gestionable {
    @Override
    public void gestionar(Scanner sc) {
        System.out.println("--- Gestión de Productos ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Modificar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Listar productos");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        
        // Aquí se llamarían los métodos correspondientes según la opción seleccionada
    }
}
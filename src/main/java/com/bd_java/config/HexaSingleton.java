package com.bd_java.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum HexaSingleton {
    INSTANCIA;

    private final Properties propiedades = new Properties();

    HexaSingleton() {
        cargarConfiguraciones("config.properties");
    }

    private void cargarConfiguraciones(String rutaArchivo) {
        try (InputStream archivo = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (archivo == null) {
                throw new IOException("Archivo no encontrado: " + rutaArchivo);
            }
    
            propiedades.load(archivo);
        } catch (IOException e) {
            System.err.println("❌ Error cargando configuración: " + e.getMessage());
            throw new RuntimeException("No se pudo cargar",e);
        }
    }

    public String get(String clave) {
        return propiedades.getProperty(clave, "No encontrado");
    }
}
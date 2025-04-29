# 📦 BD_Java

Proyecto desarrollado en **Java** que implementa una arquitectura hexagonal para gestionar productos y clientes en una tienda. Está conectado a una base de datos **MySQL** para el almacenamiento persistente de la información. Utiliza buenas prácticas de diseño y programación limpia.

## 🚀 Características

- Gestión de clientes 👥  
- Gestión de productos 🛒  
- Conexión a base de datos MySQL 🐬  
- Arquitectura Hexagonal 🛠️  
- Interfaz por consola intuitiva 🖥️  

## 🧱 Estructura del Proyecto

```bash
src/
     ├── application/ # Lógica de negocio (casos de uso y UI) 
     ├── config/ # Configuración y patrones de diseño (Singleton) 
     ├── domain/ # Entidades y repositorios 
     ├── infrastructure/ # (Carpeta reservada para la persistencia o adaptadores) 
     └── Main.java # Punto de entrada principal
```

## ⚙️ Tecnologías Utilizadas

- Java 17 ☕  
- MySQL 🐬  
- Maven 🧰  
- VS Code 💻  

## 🗄️ Base de Datos

Asegúrate de tener una base de datos MySQL activa y configurar las credenciales de conexión dentro del proyecto.

Ejemplo de conexión:
```java
String url = "jdbc:mysql://localhost:3306/tu_basededatos";
String user = "root";
String password = "tu_contraseña";
```
## ▶️ Cómo Ejecutar

1. Clona el repositorio:
```bash
  git clone https://github.com/tuusuario/BD_Java.git
  cd BD_Java
  ```
2. Compila y ejecuta con Maven:
```bash
  mvn clean install
  mvn exec:java -Dexec.mainClass="com.bd_java.Main"
  ```
# 📁 Organización por Capas
- Domain: Entidades puras y contratos de repositorios.
- Application: Casos de uso y lógica del negocio.
- UI: Interfaz de usuario por consola.
- Config: Singleton para manejo centralizado de objetos.

🧑‍💻 Autor
- Luis Alberto Talero Martínez
- 📧 luisalbertotaleromartinez@gmail.com
- 📱 3008932430

¡Gracias por visitar este proyecto! ⭐ Si te fue útil, no olvides dejar una estrella.

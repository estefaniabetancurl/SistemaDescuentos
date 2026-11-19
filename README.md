# Sistema de Gestión de Descuentos - POO II (EA4)

Aplicación de escritorio desarrollada en **Java (JDK estándar)** para la Institución Universitaria Digital de Antioquia (IU Digital), que integra Programación Orientada a Objetos (SOLID), programación funcional con expresiones Lambda, interfaz gráfica en Swing y conexión JDBC a base de datos local.

## 📋 Estructura del Proyecto

```text
SistemaDescuentos/
├── lib/
│   └── sqlite-jdbc-3.45.3.0.jar
├── src/
│   ├── modelo/
│   │   ├── ReglaDescuento.java
│   │   ├── Producto.java
│   │   ├── Bebida.java
│   │   └── Comida.java
│   ├── persistencia/
│   │   └── ConexionBD.java
│   └── vista/
│       └── VentanaDescuentos.java
└── README.md
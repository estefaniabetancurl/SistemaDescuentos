# Sistema de Gestión y Cálculo de Descuentos (POO II)

Aplicación de escritorio desarrollada en **Java (Swing)** bajo una **arquitectura de tres capas** (`modelo`, `persistencia`, `vista`), aplicando Programación Orientada a Objetos (herencia, polimorfismo e interfaces funcionales con expresiones lambda), principios SOLID y persistencia relacional en **MySQL** mediante **JDBC**.

---

## 1. Arquitectura y Estructura del Proyecto

```text
SistemaDescuentos/
├── lib/
│   └── mysql-connector-j-26.10.0.jar   # Driver JDBC oficial para MySQL
├── src/
│   ├── modelo/
│   │   ├── Producto.java               # Clase abstracta base (Principio Abierto/Cerrado)
│   │   ├── Bebida.java                 # Subclase que extiende Producto (volumenMl)
│   │   ├── Comida.java                 # Subclase que extiende Producto (esVegetariana)
│   │   └── ReglaDescuento.java         # Interfaz funcional para estrategias de descuento
│   ├── persistencia/
│   │   └── ConexionBD.java             # Gestión de conexión JDBC hacia MySQL
│   └── vista/
│       └── VentanaDescuentos.java      # Interfaz gráfica en Java Swing
├── .gitignore
└── README.md
```

---

## 2. Requisitos Previos

Antes de iniciar, asegúrate de tener instalado en tu equipo:
1. **Java Development Kit (JDK 17 o superior)**.
2. **Visual Studio Code** con el paquete de extensiones **Extension Pack for Java**.
3. **MySQL Server 8.0+** y **MySQL Workbench**.
4. **Git** configurado con tu cuenta de GitHub.

---

## 3. Guía Paso a Paso para Configurar el Entorno Local

### Paso 1: Clonar el repositorio
Abre una terminal (PowerShell o Git Bash) en la carpeta donde guardas tus proyectos y ejecuta:

```bash
git clone https://github.com/estefaniabetancurl/SistemaDescuentos.git
cd SistemaDescuentos
```

### Paso 2: Crear la base de datos y las tablas en MySQL
1. Abre **MySQL Workbench** e inicia sesión en tu instancia local (`localhost:3306`).
2. Abre una nueva pestaña de consulta (**Query**), pega y ejecuta el siguiente script SQL completo:

```sql
CREATE DATABASE IF NOT EXISTS descuento;
USE descuento;

CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio_base DECIMAL(10, 2) NOT NULL,
    tipo ENUM('BEBIDA', 'COMIDA') NOT NULL
);

CREATE TABLE IF NOT EXISTS bebida (
    producto_id INT PRIMARY KEY,
    volumen_ml DOUBLE NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comida (
    producto_id INT PRIMARY KEY,
    es_vegetariana BOOLEAN NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE
);
```
3. Haz clic derecho sobre **Schemas** en el panel izquierdo -> **Refresh All** y verifica que aparezca la base de datos `descuento` con las tablas `bebida`, `comida` y `producto`.

### Paso 3: Vincular el driver JDBC en Visual Studio Code
1. Abre la carpeta `SistemaDescuentos` en **Visual Studio Code**.
2. En la barra lateral izquierda (**Explorer**), despliega la sección inferior **JAVA PROJECTS**.
3. Despliega `SistemaDescuentos` -> **Referenced Libraries** y haz clic en el ícono **`+`**.
4. Selecciona el archivo `lib/mysql-connector-j-26.10.0.jar`.

### Paso 4: Ajustar la contraseña local de MySQL y ejecutar
1. Abre `src/persistencia/ConexionBD.java`.
2. Verifica que la constante `PASSWORD` coincida con la contraseña de tu usuario `root` en tu MySQL local:

```java
private static final String URL = "jdbc:mysql://localhost:3306/descuento?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "TU_CONTRASEÑA_LOCAL";
```

3. Abre `src/vista/VentanaDescuentos.java` y haz clic en **Run** sobre el método `main`.
4. **Verificación:** Se abrirá la ventana *Calculadora de Descuentos - POO II* mostrando el mensaje:
   > `Conexión exitosa a la base de datos local (MySQL).`

---

## 4. Flujo de Trabajo Colaborativo en Git (Para el Equipo)

Para evitar conflictos al trabajar en equipo sobre el mismo repositorio:

1. **Antes de empezar a programar**, descarga siempre los últimos cambios:
   ```bash
   git pull origin main
   ```
2. **Al terminar tus cambios**, verifica qué archivos modificaste (procura **no** subir tu contraseña personal si es distinta a la del equipo):
   ```bash
   git status
   git add .
   git commit -m "Describe brevemente el cambio realizado"
   git push origin main
   ```

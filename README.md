
# ConectaComercio Web - Módulo de Gestión de Productos

## 1. Descripción del proyecto

ConectaComercio Web es un módulo desarrollado para gestionar los productos de la plataforma ConectaComercio, que conecta mayoristas y tenderos.

El módulo permite registrar, consultar, actualizar y eliminar productos mediante una aplicación web desarrollada con Java Servlets, JSP y conexión JDBC a MariaDB.

## 2. Objetivo

Implementar un módulo web que permita administrar la información de los productos, aplicando el patrón de separación por capas y los métodos HTTP GET y POST.

## 3. Tecnologías utilizadas

- Java JDK 21.
- Apache Maven 3.10.1.
- Apache Tomcat 10.1.
- Java Servlets (Jakarta Servlet 6.0).
- JSP (JavaServer Pages).
- HTML5.
- CSS3.
- JDBC.
- MariaDB.
- Git y GitHub.
- Visual Studio Code.

## 4. Funcionalidades

El módulo implementa las siguientes operaciones:

- Registrar productos.
- Consultar el catálogo de productos.
- Editar productos existentes.
- Eliminar productos.
- Validar los datos ingresados en los formularios.
- Conectarse a la base de datos mediante JDBC.

## 5. Arquitectura del proyecto

El proyecto está organizado en las siguientes capas:

- **config:** configuración de la conexión con MariaDB.
- **dao:** operaciones de acceso a datos.
- **model:** representación de las entidades del sistema.
- **service:** reglas de negocio y validaciones.
- **servlet:** control de solicitudes HTTP.
- **webapp:** formularios HTML, páginas JSP y estilos CSS.

## 6. Base de datos

Nombre de la base de datos: `conectacomercio_api`.

Tabla utilizada: `productos`.

Campos principales:

- id
- nombre
- descripcion
- precio
- stock
- id_mayorista
- fecha_creacion

La conexión se configura en la clase `ConexionDB.java`.

## 7. Ejecución del proyecto

1. Instalar Java JDK 21.
2. Instalar Apache Maven.
3. Instalar Apache Tomcat 10.1.
4. Iniciar MariaDB y verificar la existencia de la base de datos `conectacomercio_api`.
5. Configurar las credenciales de conexión en `ConexionDB.java`.
6. Abrir una terminal en la carpeta del proyecto.
7. Ejecutar:

   `mvn clean package`

8. Copiar el archivo WAR generado en `target` a la carpeta `webapps` de Tomcat.
9. Iniciar Tomcat.
10. Abrir en el navegador:

   `http://localhost:8080/ConectaComercioWebServlets/`

## 8. Pruebas realizadas

Se realizaron pruebas funcionales del módulo:

- Consulta del catálogo.
- Registro de productos.
- Actualización de productos.
- Eliminación de productos.
- Verificación de la conexión JDBC con MariaDB.

Los resultados de las pruebas fueron satisfactorios.

## 9. Control de versiones

El proyecto utiliza Git para el control de versiones y GitHub como repositorio remoto.

Repositorio:

https://github.com/gabivega2024-ux/ConectaComercioWebServlets

## 10. Proyecto de formación

Servicio Nacional de Aprendizaje - SENA.

Evidencia: GA7-220501096-AA2-EV02.

Módulo web desarrollado como parte del proyecto formativo ConectaComercio.


package com.conectacomercio.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión JDBC con la base de datos
 * de ConectaComercio.
 */
public class ConexionDB {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/conectacomercio_api";

    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    /**
     * Establece una conexión con MariaDB.
     *
     * @return conexión activa con la base de datos.
     * @throws SQLException si ocurre un error de conexión.
     */
    public static Connection conectar() throws SQLException {

        try {

            // Cargar explícitamente el controlador JDBC.
            Class.forName("org.mariadb.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "No se encontró el controlador JDBC de MariaDB.",
                    e
            );
        }

        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }
}

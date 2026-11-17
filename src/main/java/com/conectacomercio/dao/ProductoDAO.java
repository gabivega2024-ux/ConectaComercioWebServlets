package com.conectacomercio.dao;

import com.conectacomercio.config.ConexionDB;
import com.conectacomercio.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona las operaciones CRUD de productos.
 */
public class ProductoDAO {

    /**
     * Registra un producto en la base de datos.
     */
    public boolean insertar(Producto producto) throws SQLException {

        String sql = """
                INSERT INTO productos
                (nombre, descripcion, precio, stock, id_mayorista)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getDescripcion());
            sentencia.setBigDecimal(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setInt(5, producto.getIdMayorista());

            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Consulta todos los productos registrados.
     */
    public List<Producto> consultarTodos() throws SQLException {

        List<Producto> productos = new ArrayList<>();

        String sql = """
                SELECT id, nombre, descripcion, precio,
                       stock, id_mayorista
                FROM productos
                ORDER BY id DESC
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Producto producto = new Producto(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("descripcion"),
                        resultado.getBigDecimal("precio"),
                        resultado.getInt("stock"),
                        resultado.getInt("id_mayorista")
                );

                productos.add(producto);
            }
        }

        return productos;
    }

    /**
     * Actualiza la información de un producto.
     */
    public boolean actualizar(Producto producto) throws SQLException {

        String sql = """
                UPDATE productos
                SET nombre = ?,
                    descripcion = ?,
                    precio = ?,
                    stock = ?
                WHERE id = ?
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getDescripcion());
            sentencia.setBigDecimal(3, producto.getPrecio());
            sentencia.setInt(4, producto.getStock());
            sentencia.setInt(5, producto.getId());

            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un producto por su identificador.
     */
    public boolean eliminar(int id) throws SQLException {

        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;
        }
    }

    /**
     * Busca un producto por su identificador.
     */
    public Producto buscarPorId(int id) throws SQLException {

        String sql = """
                SELECT id, nombre, descripcion, precio,
                       stock, id_mayorista
                FROM productos
                WHERE id = ?
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    return new Producto(
                            resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getString("descripcion"),
                            resultado.getBigDecimal("precio"),
                            resultado.getInt("stock"),
                            resultado.getInt("id_mayorista")
                    );
                }
            }
        }

        return null;
    }
}

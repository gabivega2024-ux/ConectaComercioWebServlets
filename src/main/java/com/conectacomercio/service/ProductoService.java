
package com.conectacomercio.service;

import com.conectacomercio.dao.ProductoDAO;
import com.conectacomercio.model.Producto;

import java.sql.SQLException;
import java.util.List;

/**
 * Contiene las reglas de negocio para gestionar productos.
 */
public class ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    /**
     * Registra un producto después de validar sus datos.
     */
    public boolean registrarProducto(Producto producto)
            throws SQLException {

        validarProducto(producto);

        return productoDAO.insertar(producto);
    }

    /**
     * Obtiene todos los productos.
     */
    public List<Producto> listarProductos() throws SQLException {

        return productoDAO.consultarTodos();
    }

    /**
     * Actualiza un producto existente.
     */
    public boolean actualizarProducto(Producto producto)
            throws SQLException {

        validarProducto(producto);

        if (producto.getId() <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del producto no es válido."
            );
        }

        return productoDAO.actualizar(producto);
    }

    /**
     * Elimina un producto por su identificador.
     */
    public boolean eliminarProducto(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero."
            );
        }

        return productoDAO.eliminar(id);
    }

    /**
     * Busca un producto por su identificador.
     */
    public Producto buscarProducto(int id) throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor que cero."
            );
        }

        return productoDAO.buscarPorId(id);
    }

    /**
     * Valida los datos de un producto.
     */
    private void validarProducto(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "Los datos del producto son obligatorios."
            );
        }

        if (producto.getNombre() == null
                || producto.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                    "El nombre del producto es obligatorio."
            );
        }

        if (producto.getNombre().length() > 100) {
            throw new IllegalArgumentException(
                    "El nombre no puede superar 100 caracteres."
            );
        }

        if (producto.getPrecio() == null
                || producto.getPrecio().signum() <= 0) {

            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero."
            );
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo."
            );
        }

        if (producto.getIdMayorista() <= 0) {
            throw new IllegalArgumentException(
                    "Debe indicar un mayorista válido."
            );
        }
    }
}

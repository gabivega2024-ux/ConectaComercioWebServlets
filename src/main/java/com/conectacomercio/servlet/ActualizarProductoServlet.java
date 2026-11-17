
package com.conectacomercio.servlet;

import com.conectacomercio.model.Producto;
import com.conectacomercio.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * Servlet encargado de actualizar productos.
 */
@WebServlet("/actualizar-producto")
public class ActualizarProductoServlet extends HttpServlet {

    private ProductoService productoService;

    @Override
    public void init() throws ServletException {
        productoService = new ProductoService();
    }

    /**
     * Recibe los datos del formulario mediante POST
     * y actualiza el producto en MariaDB.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int id = Integer.parseInt(
                    request.getParameter("id")
            );

            String nombre =
                    request.getParameter("nombre");

            String descripcion =
                    request.getParameter("descripcion");

            BigDecimal precio = new BigDecimal(
                    request.getParameter("precio")
            );

            int stock = Integer.parseInt(
                    request.getParameter("stock")
            );

            // Consultar el producto para conservar su mayorista.
            Producto productoExistente =
                    productoService.buscarProducto(id);

            if (productoExistente == null) {

                request.setAttribute(
                        "error",
                        "El producto que intentas actualizar no existe."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);

                return;
            }

            Producto producto = new Producto();

            producto.setId(id);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setIdMayorista(
                    productoExistente.getIdMayorista()
            );

            boolean actualizado =
                    productoService.actualizarProducto(producto);

            if (actualizado) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/productos?mensaje=actualizado"
                );

            } else {

                request.setAttribute(
                        "error",
                        "No fue posible actualizar el producto."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Verifica que el ID, precio y stock sean números válidos."
            );

            request.getRequestDispatcher(
                    "/error.jsp"
            ).forward(request, response);

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                    "/error.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Error al actualizar el producto en MariaDB.",
                    e
            );
        }
    }
}

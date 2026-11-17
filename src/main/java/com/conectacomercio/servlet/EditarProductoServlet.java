
package com.conectacomercio.servlet;

import com.conectacomercio.model.Producto;
import com.conectacomercio.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet encargado de consultar un producto
 * para mostrar su formulario de edición.
 */
@WebServlet("/editar-producto")
public class EditarProductoServlet extends HttpServlet {

    private ProductoService productoService;

    @Override
    public void init() throws ServletException {
        productoService = new ProductoService();
    }

    /**
     * Busca el producto mediante su identificador.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String idTexto = request.getParameter("id");

            if (idTexto == null || idTexto.isBlank()) {
                response.sendRedirect(
                        request.getContextPath() + "/productos"
                );
                return;
            }

            int id = Integer.parseInt(idTexto);

            Producto producto =
                    productoService.buscarProducto(id);

            if (producto == null) {

                request.setAttribute(
                        "error",
                        "El producto solicitado no existe."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);

                return;
            }

            request.setAttribute("producto", producto);

            request.getRequestDispatcher(
                    "/editar-producto.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "El identificador del producto no es válido."
            );

            request.getRequestDispatcher(
                    "/error.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Error al consultar el producto.",
                    e
            );
        }
    }
}


package com.conectacomercio.servlet;

import com.conectacomercio.service.ProductoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet encargado de eliminar productos.
 */
@WebServlet("/eliminar-producto")
public class EliminarProductoServlet extends HttpServlet {

    private ProductoService productoService;

    @Override
    public void init() throws ServletException {
        productoService = new ProductoService();
    }

    /**
     * Elimina un producto mediante su identificador.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            String idTexto = request.getParameter("id");

            if (idTexto == null || idTexto.isBlank()) {

                request.setAttribute(
                        "error",
                        "No se recibió el identificador del producto."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);

                return;
            }

            int id = Integer.parseInt(idTexto);

            boolean eliminado =
                    productoService.eliminarProducto(id);

            if (eliminado) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/productos?mensaje=eliminado"
                );

            } else {

                request.setAttribute(
                        "error",
                        "No se encontró el producto que deseas eliminar."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "El identificador del producto no es válido."
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
                    "Error al eliminar el producto de MariaDB.",
                    e
            );
        }
    }
}

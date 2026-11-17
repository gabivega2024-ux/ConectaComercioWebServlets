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
import java.util.List;

/**
 * Servlet encargado de gestionar los productos.
 */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private ProductoService productoService;

    @Override
    public void init() throws ServletException {
        productoService = new ProductoService();
    }

    /**
     * Consulta y muestra los productos registrados.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Producto> productos =
                    productoService.listarProductos();

            request.setAttribute("productos", productos);

            request.getRequestDispatcher(
                    "/productos.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Error al consultar los productos.",
                    e
            );
        }
    }

    /**
     * Recibe y procesa el formulario de registro.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String precioTexto = request.getParameter("precio");
            String stockTexto = request.getParameter("stock");
            String mayoristaTexto = request.getParameter("idMayorista");

            BigDecimal precio = new BigDecimal(precioTexto);
            int stock = Integer.parseInt(stockTexto);
            int idMayorista = Integer.parseInt(mayoristaTexto);

            Producto producto = new Producto();

            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setIdMayorista(idMayorista);

            boolean registrado =
                    productoService.registrarProducto(producto);

            if (registrado) {

                response.sendRedirect(
                        request.getContextPath()
                        + "/productos?mensaje=registrado"
                );

            } else {

                request.setAttribute(
                        "error",
                        "No fue posible registrar el producto."
                );

                request.getRequestDispatcher(
                        "/error.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Verifica que el precio, stock e ID del mayorista sean numéricos."
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
                    "Error al guardar el producto en MariaDB.",
                    e
            );
        }
    }
}

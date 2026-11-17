
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.conectacomercio.model.Producto" %>

<%
    List<Producto> productos =
        (List<Producto>) request.getAttribute("productos");

    String mensaje = request.getParameter("mensaje");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Catálogo de productos | ConectaComercio</title>

    <link rel="stylesheet" href="css/estilos.css">
</head>

<body>

    <main class="contenedor">

        <header class="encabezado">
            <h1>ConectaComercio</h1>
            <p>Gestión de productos</p>
        </header>

        <section class="tarjeta">

            <h2>Catálogo de productos</h2>

            <% if ("registrado".equals(mensaje)) { %>
                <p class="mensaje-exito">
                    Producto registrado correctamente.
                </p>
            <% } %>

            <% if ("actualizado".equals(mensaje)) { %>
                <p class="mensaje-exito">
                    Producto actualizado correctamente.
                </p>
            <% } %>

            <% if ("eliminado".equals(mensaje)) { %>
                <p class="mensaje-exito">
                    Producto eliminado correctamente.
                </p>
            <% } %>

            <div class="acciones">
                <a href="registrar-producto.html" class="boton">
                    Registrar producto
                </a>
            </div>

            <div class="tabla-contenedor">

                <table>

                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Mayorista</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>

                        <% if (productos != null && !productos.isEmpty()) { %>

                            <% for (Producto producto : productos) { %>

                                <tr>

                                    <td><%= producto.getId() %></td>

                                    <td>
                                        <%= producto.getNombre() == null ? "" :
                                            producto.getNombre()
                                                .replace("&", "&amp;")
                                                .replace("<", "&lt;")
                                                .replace(">", "&gt;")
                                                .replace("\"", "&quot;")
                                                .replace("'", "&#39;") %>
                                    </td>

                                    <td>
                                        <%= producto.getDescripcion() == null ? "" :
                                            producto.getDescripcion()
                                                .replace("&", "&amp;")
                                                .replace("<", "&lt;")
                                                .replace(">", "&gt;")
                                                .replace("\"", "&quot;")
                                                .replace("'", "&#39;") %>
                                    </td>

                                    <td>$<%= producto.getPrecio() %></td>

                                    <td><%= producto.getStock() %></td>

                                    <td><%= producto.getIdMayorista() %></td>

                                    <td>

                                        <a href="editar-producto?id=<%= producto.getId() %>">
                                            Editar
                                        </a>

                                        <form action="eliminar-producto" method="post"
                                              onsubmit="return confirm('¿Estás seguro de eliminar este producto?');">

                                            <input
                                                type="hidden"
                                                name="id"
                                                value="<%= producto.getId() %>"
                                            >

                                            <button type="submit">
                                                Eliminar
                                            </button>

                                        </form>

                                    </td>

                                </tr>

                            <% } %>

                        <% } else { %>

                            <tr>
                                <td colspan="7">
                                    No hay productos registrados.
                                </td>
                            </tr>

                        <% } %>

                    </tbody>

                </table>

            </div>

        </section>

    </main>

</body>
</html>

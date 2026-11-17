
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.conectacomercio.model.Producto" %>

<%
    Producto producto =
        (Producto) request.getAttribute("producto");

    if (producto == null) {
        response.sendRedirect(
            request.getContextPath() + "/productos"
        );
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Editar producto | ConectaComercio</title>

    <link rel="stylesheet" href="css/estilos.css">
</head>

<body>

    <main class="contenedor">

        <header class="encabezado">
            <h1>ConectaComercio</h1>
            <p>Actualización de productos</p>
        </header>

        <section class="tarjeta">

            <h2>Editar producto</h2>

            <form action="actualizar-producto" method="post">

                <input
                    type="hidden"
                    name="id"
                    value="<%= producto.getId() %>"
                >

                <label for="nombre">Nombre del producto</label>
                <input
                    type="text"
                    id="nombre"
                    name="nombre"
                    maxlength="100"
                    value="<%= producto.getNombre() == null ? "" : producto.getNombre().replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;") %>"
                    required
                >

                <label for="descripcion">Descripción</label>
                <textarea
                    id="descripcion"
                    name="descripcion"
                    rows="4"
                ><%= producto.getDescripcion() == null ? "" : producto.getDescripcion().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") %></textarea>

                <label for="precio">Precio</label>
                <input
                    type="number"
                    id="precio"
                    name="precio"
                    min="0.01"
                    step="0.01"
                    value="<%= producto.getPrecio() %>"
                    required
                >

                <label for="stock">Stock disponible</label>
                <input
                    type="number"
                    id="stock"
                    name="stock"
                    min="0"
                    value="<%= producto.getStock() %>"
                    required
                >

                <button type="submit">
                    Guardar cambios
                </button>

            </form>

            <a href="productos">Cancelar y volver al catálogo</a>

        </section>

    </main>

</body>
</html>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String error = (String) request.getAttribute("error");

    if (error == null) {
        error = "Ha ocurrido un error al procesar la solicitud.";
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Error | ConectaComercio</title>

    <link rel="stylesheet" href="css/estilos.css">
</head>

<body>

    <main class="contenedor">

        <section class="tarjeta tarjeta-error">

            <h1>ConectaComercio</h1>

            <h2>No se pudo completar la operación</h2>

            <p class="mensaje-error">
                <%= org.apache.taglibs.standard.functions.Functions.escapeXml(error) %>
            </p>

            <a href="productos" class="boton">
                Volver al catálogo
            </a>

        </section>

    </main>

</body>
</html>

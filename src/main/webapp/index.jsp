
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Inicio | ConectaComercio</title>

    <link rel="stylesheet" href="css/estilos.css">

    <style>
        .inicio {
            text-align: center;
            padding: 55px 25px;
        }

        .inicio h2 {
            font-size: 30px;
            margin-bottom: 18px;
            color: #166534;
        }

        .inicio p {
            font-size: 17px;
            line-height: 1.7;
            margin-bottom: 30px;
        }

        .botones-inicio {
            display: flex;
            justify-content: center;
            gap: 15px;
            flex-wrap: wrap;
        }

        .boton-secundario {
            background-color: #64748b;
        }

        .boton-secundario:hover {
            background-color: #475569;
        }

        .pie-pagina {
            text-align: center;
            margin-top: 30px;
            color: #64748b;
            font-size: 13px;
        }
    </style>

</head>

<body>

    <main class="contenedor">

        <header class="encabezado">

            <h1>ConectaComercio</h1>

            <p>Conectando mayoristas y tenderos</p>

        </header>

        <section class="tarjeta inicio">

            <h2>Bienvenido a ConectaComercio</h2>

            <p>
                Plataforma web para gestionar el catálogo de productos,
                consultar inventarios y administrar la información
                comercial de manera sencilla.
            </p>

            <div class="botones-inicio">

                <a href="productos" class="boton">
                    Consultar productos
                </a>

                <a href="registrar-producto.html" class="boton boton-secundario">
                    Registrar producto
                </a>

            </div>

        </section>

        <footer class="pie-pagina">

            <p>
                ConectaComercio Web - Proyecto de formación SENA
            </p>

        </footer>

    </main>

</body>
</html>

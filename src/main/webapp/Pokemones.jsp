<%@page import="java.util.ArrayList"%>
<%@page import="servlet.Pokemon.Pokemon"%>
<%
    // String idPokemon = String.valueOf(request.getAttribute("idPokemon"));
    //  String namePokemon = String.valueOf(request.getAttribute("namePokemon"));
    // String imagenPokemon = String.valueOf(request.getAttribute("imagenPokemon"));
    ArrayList<Pokemon> listaPoke = (ArrayList<Pokemon>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/styles.css"/>

        <!--Css boostrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!--Js boostrap-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <!--Font de google fonts-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@400;700&display=swap" rel="stylesheet">

    </head>
    <body>
        <h1>Mi Pokemon </h1>

        <div class="pokemon-container">
            <%for (Pokemon p : listaPoke) {%>
            <div class="flip-card">
                <a href="MostrarDatosAdicional?accion=<%=p.getIdPokemon()%>" class="card-container">
                    <div class="pokemon-block">
                        <div class="img-container">
                            <img src=<%= p.getUrlImagen()%> alt="alt"/> 
                        </div>
                        <p><%= p.getIdPokemon()%></p>
                        <p><%= p.getNombre()%></p>
                    </div>
                    <div class="pokemon-block-back">
                        <p>Carta de atras</p>
                    </div>
                </a>
            </div>
            <%}%>
        </div>
        
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="RecibirDatos?accion=restar" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                
                <li class="page-item">
                     <a class="page-link" href="RecibirDatos?siguiente=sumar">Next</a>
                </li>
            </ul>
        </nav>
        
        
    <center>
        




    </center>


</body>
</html>

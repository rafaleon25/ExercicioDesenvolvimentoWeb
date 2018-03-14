<%-- 
    Document   : index
    Created on : 13/03/2018, 19:39:18
    Author     : Nakamura-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">

        <style type="text/css">
            .navbar {
                width: 80%;
            }
        </style>

        <title>Loja Prod</title>
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-12" id="headder">
                    <h1>Loja Produtos</h1>

                    <nav class="navbar navbar-expand-sm navbar-dark bg-primary">
                        <a class="navbar-brand">Loja</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                            <div class="navbar-nav">
                                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/index.jsp">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/cadastrarProd.jsp">Cadastrar</a>
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/consultarProd.jsp">Consultar</a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>

<%-- 
    Document   : consultarProd
    Created on : 13/03/2018, 20:26:37
    Author     : Nakamura-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">

        <style type="text/css">
            .navbar {
                width: 80%;
            }

            .cad-prod {
                margin: 25px;
            }

            .input-group {
                width: 40%;
                margin: 10px;
            }

            .btn{
                margin: 5px;
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
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/index.jsp">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/cadastrarProd.jsp">Cadastrar</a>
                                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/consultarProd.jsp">Consultar</a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>



            <div class="row">
                <div class="col-12">
                    <h4 class="cad-prod">Consultar Produto</h4>


                    <form class="buscaCliente"  action="${pageContext.request.contextPath}/ConsultaProd" method="post">
                        <div class="input-group input-group-sm mb-3">
                            <div class="input-group-prepend" id="campo-nome">
                                <span class="input-group-text" id="inputGroup-sizing-sm">Nome</span>
                            </div>
                            <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">
                            <button type="submit" class="btn btn-primary btn-sm">Pesquisar</button>

                        </div>
                    </form>


                    <form class="editCli" action="${pageContext.request.contextPath}/EditarExcluir" method="post">   
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th></th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Descrição</th>
                                    <th scope="col">Valor Comp.</th>
                                    <th scope="col">Valor Vend.</th>
                                    <th scope="col">Qnt</th>
                                    <th style="visibility: hidden">ID</th>
                                </tr>

                            </thead>                            
                            <tbody>

                                <c:if test="${empty sessionScope.buscar}">
                                    <c:forEach var="prod" items="${sessionScope.ResultProdLista}">
                                        <tr>
                                            <td><input type="radio" value="${prod.id}" name="selecionarProd" /></td>
                                            <td>${prod.nome}</td>
                                            <td>${prod.descricao}</td>
                                            <td>${prod.precoCompra}</td>
                                            <td>${prod.precoVenda}</td>
                                            <td>${prod.quantidade}</td> 

                                            <td style="visibility: hidden">${prod.id}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                                <c:if test="${not empty sessionScope.buscar}">
                                    <c:forEach var="prod" items="${sessionScope.ResultProdLista2}">    
                                        <tr>
                                            <td><input type="radio" value="${prod.id}" name="selecionarProd" /></td>
                                            <td>${prod.nome}</td>
                                            <td>${prod.descricao}</td>
                                            <td>${prod.precoCompra}</td>
                                            <td>${prod.precoVenda}</td>
                                            <td>${prod.quantidade}</td>

                                            <td style="visibility: hidden">${prod.id}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>

                            </tbody>
                        </table>

                        <button class="btn btn-secondary" name="btnEditar" value="editarProd">Alterar</button>
                        <button class="btn btn-danger" name="btnExluir" value="exluirProd">Excluir</button>
                    </form>
                </div>
            </div>

        </div>             
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    </body>
</html>

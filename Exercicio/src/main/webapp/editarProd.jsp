<%-- 
    Document   : editarProd
    Created on : 13/03/2018, 21:22:20
    Author     : Nakamura-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">

        <style type="text/css">
            .navbar {
                width: 80%;
            }

            .cad-prod {
                margin: 25px;
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
                                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/consultarProd.jsp">Consultar</a>
                            </div>
                        </div>
                    </nav>
                </div>

                <div class="row">
                    <div class="col-12">
                        <h4 class="cad-prod">Cadastrar Produto</h4>

                        <form class="signup" action="${pageContext.request.contextPath}/AtualizarProd" method="post">

                            <div class="row">
                                <input style="visibility: hidden" type="text" placeholder="id" name="id-prod" value="${sessionScope.produto.id}">
                            </div> 

                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend" id="campo-nome">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Nome</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Small" name="nome-prod" value="${sessionScope.produto.nome}"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>

                            <div class="input-group input-group-sm mb-3">
                                <div class="input-group-prepend" id="campo-desc">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Descrição</span>
                                </div>
                                <input type="text" class="form-control" aria-label="Small" name="descricao-prod" value="${sessionScope.produto.descricao}"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>

                            <div class="input-group input-group-sm mb-4">
                                <div class="input-group-prepend" id="campo-pCompra">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Preço compra: $</span>
                                </div>
                                <input type="number" class="form-control" aria-label="Small" name="preco-compra" value="${sessionScope.produto.precoCompra}"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>

                            <div class="input-group input-group-sm mb-4">
                                <div class="input-group-prepend" id="campo-pVenda">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Preço venda: $</span>
                                </div>
                                <input type="number" class="form-control" aria-label="Small" name="preco-venda" value="${sessionScope.produto.precoVenda}"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>

                            <div class="input-group input-group-sm mb-4">
                                <div class="input-group-prepend" id="campo-qnt">
                                    <span class="input-group-text" id="inputGroup-sizing-sm">Quantidade</span>
                                </div>
                                <input type="number" class="form-control" aria-label="Small" name="quantidade-prod" value="${sessionScope.produto.quantidade}"
                                       aria-describedby="inputGroup-sizing-sm">
                            </div>

                            <button type="submit" class="btn btn-primary btn-sm">Salvar</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
                                       
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>

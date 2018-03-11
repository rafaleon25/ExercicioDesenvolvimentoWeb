<%-- 
    Document   : cliente
    Created on : 17/10/2017, 20:46:33
    Author     : Nakamura-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <title>Consultar produtos</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">



        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/superfish.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.ui.totop.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.equalheights.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.mobilemenu.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.easing.1.3.js"></script>

        <script>
            $(document).ready(function () {
                $().UItoTop({easingType: 'easeOutQuart'});
            });
        </script>

    </head>
    <body>
        <!--==============================header=================================-->
        <header>
            <div class="container_12">
                <div class="grid_12">
                    <div class="menu_block">
                        <nav class="horizontal-nav full-width horizontalNav-notprocessed">
                            <ul class="sf-menu">
                                <li><a href="${pageContext.request.contextPath}/home.jsp">HOME</a></li>                                
                                <li class="current"><a href="${pageContext.request.contextPath}/index.jsp">Cliente</a></li>                                
                                <li><a href="${pageContext.request.contextPath}/consultarProd.jsp">Usuario</a></li>
                                <li><a href="${pageContext.request.contextPath}/reservaVoo.jsp">Reserva</a></li>
                                <li><a href="${pageContext.request.contextPath}/relatorioReserva.jsp">Relatório</a></li>
                            </ul>
                        </nav>
                        <div class="clear"></div>
                    </div>
                </div>


            </div>

        </header>

        <div class="content"><div class="ic"></div>

            <div class="container_12">
                <div class="grid_8">
                    <div class="tituloCliente">

                        <h5 class="opcao">CONSULTAR PRODUTOS</h5>

                    </div>          



                    <form class="buscaCliente"  action="${pageContext.request.contextPath}/ConsultaProd" method="post">

                        <div class="busca">
                            <input type="text" id="busca1" placeholder="Digite sua busca" name="buscaProd">
                            <button id="botoesBusca" type="submit">BUSCAR</button>
                        </div>

                    </form>

                    <form class="editCli" action="${pageContext.request.contextPath}/EditarExcluir" method="post">

                        <div id="bg"></div>
                        <div id="tabelaConsulta">
                            <table>
                                <tr>
                                    <th></th>
                                    <th>Nome</th>
                                    <th>Descrição</th>
                                    <th>Quantidade</th>
                                    <th>Preço de Compra</th>
                                    <th>Preço de Venda</th>    
                                    <th style="visibility: hidden">ID</th>

                                </tr>    

                                <c:if test="${empty sessionScope.buscar}">
                                    <c:forEach var="prod" items="${sessionScope.ResultProdLista}">    
                                        <tr>
                                            <td><input type="radio" value="${prod.id}" name="selecionarProd" /></td>
                                            <td>${prod.nome}</td>
                                            <td>${prod.descricao}</td>
                                            <td>${prod.quantidade}</td>
                                            <td>${prod.precoCompra}</td>
                                            <td>${prod.precoVenda}</td>

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
                                            <td>${prod.quantidade}</td>
                                            <td>${prod.precoCompra}</td>
                                            <td>${prod.precoVenda}</td>

                                            <td style="visibility: hidden">${prod.id}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>


                            </table>
                        </div>

                        <div class="form-footer">
                            <button class="botoes" name="btnExluir" value="exluirProd">EXCLUIR</button>
                            <button class="botoes" name="btnEditar" value="editarProd">EDITAR</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--==============================footer=================================-->

        <footer>

        </footer>

    </body>
</html>

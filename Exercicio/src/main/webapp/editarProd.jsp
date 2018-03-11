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

        <title>Editar cliente</title>

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
        <script src="${pageContext.request.contextPath}/js/limitarCampos.js"></script>

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
                                <li><a href="${pageContext.request.contextPath}/relatorioReserva.jsp">Relátorio</a></li>
                            </ul>
                        </nav>
                        <div class="clear"></div>
                    </div>
                </div>


            </div>


        </header>
        <!--==============================Content=================================-->
        <div class="content"><div class="ic"></div>

            <div class="container_12">

                <div class="grid_8">

                    <div class="tituloCliente">
                        <h5>EDITAR PRODUTO</h5>
                    </div>

                    <div class="box">                           

                        <form class="signup" action="${pageContext.request.contextPath}/AtualizarProd" method="post">
                            <BR>

                            <div class="form-body">

                                <div class="row">
                                    <input style="visibility: hidden" type="text" placeholder="id" name="id-prod" value="${sessionScope.produto.id}">
                                </div> 
                                <div class="row">
                                    <input type="text" placeholder="Nome*" name="nome-prod" value="${sessionScope.produto.nome}" >
                                    <input type="text" placeholder="descrição*" name="descricao-prod" value="${sessionScope.produto.descricao}">
                                </div>  
                                <div class="row">
                                    <input type="number" placeholder="qtd*"  name="quantidade" value="${sessionScope.produto.quantidade}">
                                </div>
                                <div class="row">
                                    <input type="number" placeholder="preço compra*"  name="preco-compra" value="${sessionScope.produto.precoCompra}">
                                </div>
                                <div class="row">
                                    <input type="text" placeholder="preço venda*" name="preco-venda" value="${sessionScope.produto.precoVenda}">
                                </div>

                                <div class="row">
                                    <c:if test="${not empty requestScope.erroAtualizar}">
                                        <p class="error"><c:out value="${requestScope.erroAtualizar}"/></p>
                                    </c:if>
                                </div>

                            </div>

                            <div class="form-footer">

                                <button type="button" class="botoes"><a href="${pageContext.request.contextPath}/consultaProd.jsp">CANCELAR</a></button>

                                <button class="botoes" type="submit">SALVAR</button>

                            </div>
                        </form>
                    </div>  
                </div>
                

            </div>


        </div>
        <!--==============================footer=================================-->
        <footer>
            
        </footer>
        
    </body>
</html>

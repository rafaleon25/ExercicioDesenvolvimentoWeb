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

        <title>Cadastrar Produtos</title>

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
                                <li class="current"><a href="${pageContext.request.contextPath}/cadastrarCliente.jsp">Cliente</a></li>                                
                                <li><a href="${pageContext.request.contextPath}/cadastrarUsuario.jsp">Usuario</a></li>
                                <li><a href="${pageContext.request.contextPath}/reservaVoo.jsp">Reserva</a></li>
                                <li><a href="${pageContext.request.contextPath}/relatorioReserva.jsp">Relatório</a></li>

                            </ul>
                        </nav>
                        <div class="clear"></div>
                    </div>
                </div>                                



            </div>

        </header>
        <!--==============================Content=================================-->
        <div class="content">

            <div class="container_12">

                <div class="grid_8">

                    <div class="tituloCliente">
                        <h5 class="opcao">CADASTRAR PRODUTO</h5>
                    </div>



                    <div class="card-form">
                        <form class="signup"  action="${pageContext.request.contextPath}/CadastroProd" method="post">

                            <div class="row">
                                <c:if test="${not empty requestScope.prodErro}">
                                    <p class="sucess"><c:out value="${requestScope.prodErro}"/></p>
                                </c:if>
                            </div>

                            <div class="form-body">
                                <div class="row">
                                    <input type="text" placeholder="Nome*" value="${requestScope.prodRepreenche.nome}" name="nome-prod">
                                    <input type="text" placeholder="Descrição*" value="${requestScope.prodRepreenche.descricao}" name="descricao-prod">
                                </div>  
                                <div class="row">
                                    <input type="number" placeholder="quantidade*"   value="${requestScope.prodRepreenche.quantidade}" name="qtd-prod"/>
                                </div>
                                <div class="row">
                                    <input type="number" placeholder="preço de Compra*" value="${requestScope.prodRepreenche.precoCompra}" name="preco-compra">
                                </div>
                                <div class="row">
                                    <input type="number" placeholder="preço de Venda" value="${requestScope.prodRepreenche.precoVenda}" name="preco-venda">       
                                </div>


                                <div class="row">
                                    <label for="cat-1">cat 1</label>
                                    <input type="radio" placeholder="categoria" value="1"  id='cat-1' name="cat-1">
                                    <label for="cat-2">cat 2</label>
                                    <input type="radio" placeholder="categoria" value="2" id='cat-2' name="cat-2">  
                                    <label for="cat-3">cat 3</label>
                                    <input type="radio" placeholder="categoria" value="3" id='cat-3' name="cat-3">  
                                    <label for="cat-4">cat 4</label>
                                    <input type="radio" placeholder="categoria" value="4" id='cat-4' name="cat-4">  
                                    <label for="cat-5">cat 5</label>
                                    <input type="radio" placeholder="categoria" value="5" id='cat-5' name="cat-5">  
                                </div>

                                <div class="form-footer">

                                    <button class="botoes" type="submit">SALVAR</button>

                                </div>
                        </form>

                    </div>
                </div>
            </div>


        </div>
        <!--==============================footer=================================-->
        <footer>
            <div class="container_12">
                <div class="grid_12">
                    <div class="socials">
                        <a href="#" class="fa fa-facebook"></a>
                        <a href="#" class="fa fa-google-plus"></a>
                    </div>

                </div>
            </div>
        </footer>

    </body>
</html>

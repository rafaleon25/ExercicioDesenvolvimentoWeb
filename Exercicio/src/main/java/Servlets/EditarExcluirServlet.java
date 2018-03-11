/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Produto;
import Exceptions.DataExceptions;
import Exceptions.ProdutoException;
import Servicos.Servico;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rafael Rodrigues
 */
@WebServlet(name = "EditarExcluirServlet", urlPatterns = {"/EditarExcluir"})
public class EditarExcluirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Produto prod = new Produto();
        String valor = request.getParameter("selecionarProd");

        int id = 0;

        if (valor.equals("") || valor == null) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultarProd.jsp");
            dispatcher.forward(request, response);

        } else {
            id = Integer.parseInt(valor);
        }

        String btnEditar = request.getParameter("btnEditar");
        String btnExcluir = request.getParameter("btnExluir");
//
        if (btnEditar != null) {
            try {
                prod = Servico.obterProduto(id);

            } catch (DataExceptions | ProdutoException e) {
                e.getMessage();
            }

            sessao.setAttribute("produto", prod);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/editarProd.jsp");
            dispatcher.forward(request, response);

        } else if (btnExcluir != null) {

            try {
                prod = Servico.obterProduto(id);
                Servico.excluirProduto(prod.getId());
            } catch (DataExceptions | ProdutoException e) {
                request.setAttribute("erroExcluir", e.getMessage());

            }

            response.sendRedirect(request.getContextPath() + "/consultarProd.jsp");

        }
    }

}

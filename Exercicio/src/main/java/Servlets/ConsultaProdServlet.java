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
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ConsultaProdServlet", urlPatterns = {"/ConsultaProd"})
public class ConsultaProdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String valor = request.getParameter("buscaProd");
        HttpSession sessao = request.getSession();
        sessao.setAttribute("buscar", valor);

        Produto prod = new Produto();

        try {

            if (valor == null || valor.trim().equals("")) {
                List<Produto> listaProd = Servico.procurarProduto(valor);
                if (!listaProd.isEmpty() || listaProd != null) {
                    sessao.setAttribute("ResultProdLista", listaProd);
                } else {
                    request.setAttribute("erroConsulta", "Não houve resultados para esta pesquisa.");
                }
            }
        } catch (DataExceptions | ProdutoException e) {
            request.setAttribute("erroConsulta", e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/consultarProd.jsp");
        rd.forward(request, response);

    }

}

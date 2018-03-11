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
@WebServlet(name = "AtualizarProdServlet", urlPatterns = {"/AtualizarProd"})
public class AtualizarProdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Produto prod = new Produto();       
        
        String id = request.getParameter("id-prod");
        String nome = request.getParameter("nome-prod");
        String descricao = request.getParameter("descricao-prod");
        String quantidade = request.getParameter("quantidade-prod");
        String precoCompra = request.getParameter("preco-compra");
        String precoVenda = request.getParameter("preco-venda");

        if (precoCompra.equals("")) {
            request.setAttribute("erroAtualizar", "Favor informar uma data de compra válida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editarProd.jsp");
            dispatcher.forward(request, response);
        }
        if (precoVenda.equals("")) {
            request.setAttribute("erroAtualizar", "Favor informar uma data de venda válida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editarProd.jsp");
            dispatcher.forward(request, response);
        }

        double precoC = 0;
        double precoV = 0;
        int qtd = 0;

        try {
            precoC = Double.parseDouble(precoCompra);
            precoV = Double.parseDouble(precoVenda);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            qtd = Integer.parseInt(quantidade);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int idProd = 0;
        try {
            idProd = Integer.parseInt(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        prod.setId(idProd);
        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setQuantidade(qtd);
        prod.setPrecoCompra(precoC);
        prod.setPrecoVenda(precoV);


        try {
            Servico.atualizarProduto(prod);
            request.setAttribute("sucessCadastro", "Cadastro realizado com sucesso.");

        } catch (ProdutoException | DataExceptions e) {
            request.setAttribute("erroAtualizar", e.getMessage());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editarProd.jsp");
            dispatcher.forward(request, response);
        }
        sessao.setAttribute("prod", prod);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/consultarProd.jsp");
        dispatcher.forward(request, response);

    }

}

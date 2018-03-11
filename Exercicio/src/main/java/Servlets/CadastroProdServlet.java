/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Categoria;
import Classes.Produto;
import Exceptions.DataExceptions;
import Exceptions.ProdutoException;
import Servicos.Servico;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "CadastroProdServlet", urlPatterns = {"/CadastroProd"})
public class CadastroProdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Produto prod = new Produto();
        ArrayList<Categoria> cat = new ArrayList<>();
        Categoria cate = new Categoria();
        Categoria cate2 = new Categoria();
        Categoria cate3 = new Categoria();
        Categoria cate4 = new Categoria();
        Categoria cate5 = new Categoria();

        String nome = request.getParameter("nome-prod");
        String descricao = request.getParameter("descricao-prod");
        String quantidade = request.getParameter("quantidade-prod");
        String precoCompra = request.getParameter("preco-compra");
        String precoVenda = request.getParameter("preco-venda");

        String catego1 = request.getParameter("cat-1");
        String catego2 = request.getParameter("cat-2");
        String catego3 = request.getParameter("cat-3");
        String catego4 = request.getParameter("cat-4");
        String catego5 = request.getParameter("cat-5");

        if (precoCompra.equals("")) {
            request.setAttribute("erroCadastro", "Favor informar uma data de nascimento válida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroProd.jsp");
            dispatcher.forward(request, response);
        }
        if (precoVenda.equals("")) {
            request.setAttribute("erroCadastro", "Favor informar uma data de nascimento válida");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroProd.jsp");
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

        if (catego1 != null) {
            int categoria1 = Integer.parseInt(catego1);
            cate.setId(categoria1);
            cat.add(cate);
        }
        if (catego2 != null) {
            int categoria2 = Integer.parseInt(catego2);
            cate2.setId(categoria2);
            cat.add(cate2);
        }
        if (catego3 != null) {
            int categoria3 = Integer.parseInt(catego3);
            cate3.setId(categoria3);
            cat.add(cate3);
        }
        if (catego4 != null) {
            int categoria4 = Integer.parseInt(catego4);
            cate4.setId(categoria4);
            cat.add(cate4);
        }
        if (catego5 != null) {
            int categoria5 = Integer.parseInt(catego5);
            cate5.setId(categoria5);
            cat.add(cate5);
        }

        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setQuantidade(qtd);
        prod.setPrecoCompra(precoC);
        prod.setPrecoVenda(precoV);
        prod.setCat(cat);

        try {
            Servico.CadastrarProduto(prod);
            request.setAttribute("sucessCadastro", "Cadastro realizado com sucesso.");

        } catch (ProdutoException | DataExceptions e) {
            request.setAttribute("erroCadastro", e.getMessage());
            request.setAttribute("prodRepreenche", prod);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        sessao.setAttribute("prod", prod);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroProd.jsp");
        dispatcher.forward(request, response);

    }
}

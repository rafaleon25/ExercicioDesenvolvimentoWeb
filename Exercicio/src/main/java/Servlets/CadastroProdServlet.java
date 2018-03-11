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

        String nome = request.getParameter("nome-prod");
        String descricao = request.getParameter("descricao-prod");
        String quantidade = request.getParameter("qtd-prod");
        String precoCompra = request.getParameter("preco-compra");
        String precoVenda = request.getParameter("preco-venda");
        String catego1 = request.getParameter("cat-1");
        String catego2 = request.getParameter("cat-2");
        String catego3 = request.getParameter("cat-3");
        String catego4 = request.getParameter("cat-4");
        String catego5 = request.getParameter("cat-5");

        double precoComp = 0;
        double precoVend = 0;
        int qtd = 0;

        try {
            precoComp = Double.parseDouble(precoCompra);
            precoVend = Double.parseDouble(precoVenda);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        try {
            qtd = Integer.parseInt(quantidade);
        } catch (NumberFormatException e) {
            e.getMessage();
        }
        ArrayList<Categoria> cat = new ArrayList<>();
        Produto prod = new Produto();
        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setPrecoCompra(precoComp);
        prod.setPrecoVenda(precoVend);
        prod.setQuantidade(qtd);

        Categoria cate = new Categoria();
        cate.setNome(catego1);
        Categoria cate2 = new Categoria();
        cate2.setNome(catego2);
        Categoria cate3 = new Categoria();
        cate3.setNome(catego3);
        Categoria cate4 = new Categoria();
        cate4.setNome(catego4);
        Categoria cate5 = new Categoria();
        cate5.setNome(catego5);

        if (catego1 != null) {
            cat.add(cate);
        }
        if (catego2 != null) {
            cat.add(cate2);
        }
        if (catego3 != null) {
            cat.add(cate3);
        }
        if (catego4 != null) {
            cat.add(cate4);
        }
        if (catego5 != null) {
            cat.add(cate5);
        }

        prod.setCat(cat);

        try {
            Servico.CadastrarProduto(prod);
            request.setAttribute("prodRepreenche", prod);
        } catch (DataExceptions | ProdutoException e) {
            request.setAttribute("prodErro", e);
            System.out.println(e.getMessage());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarProd.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadastroProdServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarProd.jsp");
        dispatcher.forward(request, response);

    }

}

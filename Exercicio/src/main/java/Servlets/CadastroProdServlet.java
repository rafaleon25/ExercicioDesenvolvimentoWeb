/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Produto;
import Classes.ValidadorCadastroProd;
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
        
        Produto prod = new Produto();
        prod.setNome(nome);
        prod.setDescricao(descricao);
        prod.setPrecoCompra(precoComp);
        prod.setPrecoVenda(precoVend);
        prod.setQuantidade(qtd);
      
        try {
            
        } catch (Exception e) {
        }

       
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastrarProd.jsp");
        dispatcher.forward(request, response);
            
      
    }

}

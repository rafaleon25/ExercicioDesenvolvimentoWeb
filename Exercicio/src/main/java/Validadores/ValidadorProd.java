/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validadores;

import Classes.Produto;
import Exceptions.ProdutoException;

/**
 *
 * @author Rafael Rodrigues
 */
public class ValidadorProd {

    public static void validar(Produto prod) throws ProdutoException {

        if (prod == null) {
            throw new ProdutoException("Favor informar um produto.");
        }

        if (prod.getNome() == null || "".equals(prod.getNome())) {
            throw new ProdutoException("Favor informar o nome do produto.");
        }
        
        if (prod.getNome().length() > 100) {
            throw new ProdutoException("Nome inválido.");
        }
        
        if (prod.getDescricao()== null || "".equals(prod.getDescricao())) {
            throw new ProdutoException("Favor informar uma descrição do produto.");
        }
        
        if (prod.getDescricao().length() > 1000) {
            throw new ProdutoException("Descrição inválida.");
        }
        
        if (prod.getPrecoCompra() == 0 || prod.getPrecoCompra() < 0) {
            throw new ProdutoException("Favor informar um preço de compra válido.");
        }
        
        if (prod.getPrecoVenda()== 0 || prod.getPrecoVenda()< 0) {
            throw new ProdutoException("Favor informar um preço de venda válido.");
        }
        
    }
}

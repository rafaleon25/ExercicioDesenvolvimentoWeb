/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Classes.Produto;
import DAOs.daoAtualizar;
import DAOs.daoIncluir;
import Exceptions.ProdutoException;
import Exceptions.DataExceptions;
import Validadores.ValidadorProd;
import java.util.List;



/**
 *
 * @author Rafael Rodrigues
 */
public class Servico {

    public static void CadastrarProduto(Produto prod) throws ProdutoException, DataExceptions {
        ValidadorProd.validar(prod);

        try {
            daoIncluir.inserir(prod);
        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

    public static void atualizarProduto(Produto prod) throws ProdutoException, DataExceptions {

        ValidadorProd.validar(prod);

        try {
            daoAtualizar.atualizar(prod);
        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

//    public static Produto procurarProduto(String valor) throws ProdutoException, DataExceptions {
//
//        try {
//            return Dao.procurar(cpf);
//
//        } catch (Exception e) {
//            throw new DataExceptions("Erro na fonte de dados", e);
//        }
//    }
//
//    public static List<Produto> listarProduto() throws ProdutoException, DataExceptions {
//
//        try {
//            return Dao.listar();
//
//        } catch (Exception e) {
//            throw new DataExceptions("Erro na fonte de dados", e);
//        }
//    }
//
//    public static Produto obterProduto(Integer id) throws ProdutoException, DataExceptions {
//
//        try {
//            return Dao.obter(id);
//        } catch (Exception e) {
//            throw new DataExceptions("Erro na fonte de dados", e);
//        }
//    }
//
//    public static void excluirProduto(Integer id) throws ProdutoException, DataExceptions {
//
//        try {
//
//            Dao.excluir(id);
//        } catch (Exception e) {
//            throw new DataExceptions("Erro na fonte de dados", e);
//        }
//    }

}

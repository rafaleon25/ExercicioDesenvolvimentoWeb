/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Classes.Produto;
import DAOs.daoAtualizar;
import DAOs.daoConsultar;
import DAOs.daoExcluir;
import DAOs.daoIncluir;
import DAOs.daoListar;
import DAOs.daoObter;
import Exceptions.ProdutoException;
import Exceptions.DataExceptions;
import Validadores.ValidadorProd;
import java.sql.SQLException;
import java.util.List;



/**
 *
 * @author Rafael Rodrigues
 */
public class Servico {

    public static void CadastrarProduto(Produto prod) throws ProdutoException, DataExceptions, SQLException {
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

    public static List<Produto> procurarProduto(String valor) throws ProdutoException, DataExceptions {

        try {
            return daoConsultar.procurar(valor);

        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

    public static List<Produto> listar() throws ProdutoException, DataExceptions {

        try {
            return daoListar.listar();

        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

    public static Produto obterProduto(Integer id) throws ProdutoException, DataExceptions {

        try {
            return daoObter.obter(id);
        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

    public static void excluirProduto(Integer id) throws ProdutoException, DataExceptions {

        try {

            daoExcluir.excluir(id);
        } catch (Exception e) {
            throw new DataExceptions("Erro na fonte de dados", e);
        }
    }

}

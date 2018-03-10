/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Classes.Produto;
import dbUtils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Josué
 */
public class daoIncluir {

    public static void inserir(Produto produto)
            throws SQLException, Exception {

        String sql1 = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DT_CADASTRO"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO PRODUTO_CATEGORIA (ID_PRODUTO, ID_CATEGORIA)"
                + "VALUES (?, ?)";
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPrecoCompra());
            preparedStatement.setDouble(4, produto.getPrecoVenda());
            preparedStatement.setInt(5, produto.getQuantidade());
            preparedStatement.setDate(6, new java.sql.Date(System.currentTimeMillis()));

            //Executa o comando no banco de dados
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            preparedStatement2 = connection.prepareStatement(sql2);

            for (int i = 0; i < produto.getCat().size(); i++) {
                preparedStatement2.setInt(1, rs.getInt(1));
                preparedStatement2.setInt(2, produto.getCat().get(i).getId());
                preparedStatement2.execute();

            }
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (preparedStatement2 != null && !preparedStatement2.isClosed()) {
                preparedStatement2.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}

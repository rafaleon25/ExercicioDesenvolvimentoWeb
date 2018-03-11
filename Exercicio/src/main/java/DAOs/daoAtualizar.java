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
import java.sql.SQLException;

/**
 *
 * @author Josué
 */
public class daoAtualizar {

    public static void atualizar(Produto produto)
            throws SQLException, Exception {
        String sql = "UPDATE PRODUTO SET NOME=?, DESCRICAO=?, PRECO_COMPRA=?, PRECO_VENDA=?, QUANTIDADE=?"
                + " WHERE (ID=?)";

        String sql2 = "UPDATE PRODUTO_CATEGORIA SET ID_CATEGORIA=?"
                + " WHERE (ID_PRODUTO=?)";
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
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.setDouble(3, produto.getPrecoCompra());
            preparedStatement.setDouble(4, produto.getPrecoVenda());
            preparedStatement.setInt(5, produto.getQuantidade());
            preparedStatement.setInt(6, produto.getId());

            //Executa o comando no banco de dados
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(sql2);

            for (int i = 0; i < produto.getCat().size(); i++) {
                preparedStatement2.setInt(1, produto.getCat().get(i).getId());
                preparedStatement2.setInt(2, produto.getId());
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

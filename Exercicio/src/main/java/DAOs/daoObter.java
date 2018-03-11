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

/**
 *
 * @author Josué
 */
public class daoObter {

    public static Produto obter(int valor)
            throws SQLException, Exception {

        String sql = "SELECT * FROM PRODUTO WHERE ID=?";
        Produto produto = new Produto();
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setInt(1, valor);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            produto.setId(result.getInt("ID"));
            produto.setDescricao(result.getString("DESCRICAO"));
            produto.setNome(result.getString("NOME"));
            produto.setPrecoCompra(result.getDouble("PRECO_COMPRA"));
            produto.setPrecoVenda(result.getDouble("PRECO_VENDA"));
            produto.setQuantidade(result.getInt("QUANTIDADE"));
            produto.setDataCadastro(result.getDate("DT_CADASTRO"));
            //Adiciona a instância na list

        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return produto;

    }
}

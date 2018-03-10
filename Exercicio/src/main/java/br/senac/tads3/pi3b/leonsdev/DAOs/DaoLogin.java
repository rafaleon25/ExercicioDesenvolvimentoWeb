/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.login.classes.Login;
import br.senac.tads3.pi3b.leonsdev.login.classes.SingletonLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Josu
 */
public class DaoLogin {

    public static void Autenticacao(Login login) throws SQLException, Exception {
        String sql = "CALL autenticacao (?, ?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        ResultSet result;
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um preparedStatement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do prepared preparedStatement
            preparedStatement.setString(1, login.getUserName());
            preparedStatement.setString(2, login.getSenha());

            result = preparedStatement.executeQuery();

            if (result.next()) {
                SingletonLogin.getInstance().setNome(result.getString("Nome"));
                SingletonLogin.getInstance().setCargo(result.getString("Cargo"));
                SingletonLogin.getInstance().setFunc_id(result.getInt("Usuario_ID"));
            }

        } finally {
            //Se o preparedStatement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}

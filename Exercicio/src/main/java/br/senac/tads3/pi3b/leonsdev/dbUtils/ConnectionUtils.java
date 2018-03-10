/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Josu
 */
//Obtém um objeto de conexão do banco de dados.
//Pode ser utilizado para abertura e fechamento de conexões com o banco
public class ConnectionUtils {

    //Obtém uma conexão do banco de dados
    public static Connection getConnection() throws ClassNotFoundException {
        //Conexão para abertura e fechamento
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            //Só tenta abrir uma conexão se não existir ou estiver fechada            
            //Endereço de conexão com o banco de dados
            
            String dbURL = Constants.DB_ADDRESS;
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", Constants.DB_USER);
            properties.put("password", Constants.DB_PASS);
            //Realiza a conexão com o banco
            connection = DriverManager.getConnection(dbURL, properties);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Retorna a conexão
        return connection;
    }
}
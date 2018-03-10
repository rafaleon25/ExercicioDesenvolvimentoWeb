/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.usuario.classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué
 */
public class DaoUsuarios {
    public static void inserir(Usuario usuario)
            throws SQLException, Exception {
        
        String sql = "INSERT INTO Usuarios (Nome, Sobrenome, CPF, Cargo, Email, Login, Senha, Ativo, Admissao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getCpf());
            preparedStatement.setString(4, usuario.getCargo());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getLogin());
            preparedStatement.setString(7, usuario.getSenha());
            preparedStatement.setBoolean(8, true);
            preparedStatement.setDate(9, new java.sql.Date(System.currentTimeMillis()));

            //Executa o comando no banco de dados
            preparedStatement.execute();

        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void atualizar(Usuario usuario)
            throws SQLException, Exception {
        String sql = "UPDATE Usuarios SET Nome=?, Sobrenome=?, CPF=?, Cargo=?, Email=?, Login=?, Senha=?, Ativo=?, Atualizacao=? "            
                + "WHERE (Usuario_ID=?)";
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getSobrenome());
            preparedStatement.setString(3, usuario.getCpf());
            preparedStatement.setString(4, usuario.getCargo());
            preparedStatement.setString(5, usuario.getEmail());
            preparedStatement.setString(6, usuario.getLogin());
            preparedStatement.setString(7, usuario.getSenha());
            preparedStatement.setBoolean(8, usuario.getEnable());
            preparedStatement.setDate(9, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setInt(10, usuario.getId());
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static void excluir(Integer id) throws SQLException, Exception {
        //Monta a string de atualização do usuario no BD, utilizando
        //prepared statement
        String sql = "UPDATE Usuarios SET Ativo=? WHERE (Usuario_ID=?)";
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);

            //Executa o comando no banco de dados
            preparedStatement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

 
    public static List<Usuario> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Usuarios WHERE (Ativo=?)";
 
        List<Usuario> listaUsuario = null;
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
            preparedStatement.setBoolean(1, true);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaUsuario == null) {
                    listaUsuario = new ArrayList<>();
                }
                
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt("Usuario_ID"));
                usuario.setCpf(result.getString("CPF"));
                usuario.setCargo(result.getString("Cargo"));
                usuario.setEmail(result.getString("Email"));
                usuario.setEnable(result.getBoolean("Ativo"));
                usuario.setLogin(result.getString("Login"));
                usuario.setNome(result.getString("Nome"));
                usuario.setSenha(result.getString("Senha"));
                usuario.setSobrenome(result.getString("Sobrenome"));

                //Adiciona a instância na lista
                listaUsuario.add(usuario);
            }
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
        //Retorna a lista de usuarios do banco de dados
        return listaUsuario;
    }

    
    public static Usuario procurar(String valor)
            throws SQLException, Exception {
    
        String sql = "SELECT * FROM Usuarios WHERE CPF=? AND Ativo=? limit 1";
    
        Usuario DadosUsuario = new Usuario();
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
            preparedStatement.setString(1, valor);
            preparedStatement.setBoolean(2, true);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                DadosUsuario.setId(result.getInt("Usuario_ID"));
                DadosUsuario.setCpf(result.getString("CPF"));
                DadosUsuario.setCargo(result.getString("Cargo"));
                DadosUsuario.setEmail(result.getString("CPF"));
                DadosUsuario.setEnable(result.getBoolean("Ativo"));
                DadosUsuario.setLogin(result.getString("Login"));
                DadosUsuario.setNome(result.getString("Nome"));
                DadosUsuario.setSenha(result.getString("Senha"));
                DadosUsuario.setSobrenome(result.getString("Sobrenome"));
            }
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
    
        return DadosUsuario;
    }
    
    public static Usuario obter(Integer id)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM Usuarios WHERE (Usuario_ID=? AND Ativo=?)";

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
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, true);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
        
                Usuario usuario = new Usuario();
                usuario.setCpf(result.getString("CPF"));
                usuario.setCargo(result.getString("Cargo"));
                usuario.setEmail(result.getString("CPF"));
                usuario.setEnable(result.getBoolean("Ativo"));
                usuario.setLogin(result.getString("Login"));
                usuario.setNome(result.getString("Nome"));
                usuario.setSenha(result.getString("Senha"));
                usuario.setSobrenome(result.getString("Sobrenome"));
                //Retorna o resultado
                return usuario;
            }
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

        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
        return null;
    }
}

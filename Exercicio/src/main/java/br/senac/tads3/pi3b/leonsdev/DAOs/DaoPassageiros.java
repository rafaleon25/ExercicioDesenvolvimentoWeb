/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.passageiros.classes.Passageiros;
import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josué
 */
public class DaoPassageiros {

    public static void inserir(Passageiros passageiros)
            throws SQLException, Exception {

        String sql = "INSERT INTO Passageiros (Primeiro_Nome, Ultimo_Nome, CPF, "
                + "Dt_Nascimento, Email, Reserva_ID "
                + "VALUES (?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setString(1, passageiros.getNome());
            preparedStatement.setString(2, passageiros.getSobrenome());
            preparedStatement.setString(3, passageiros.getCpf());
            Timestamp t = new Timestamp(passageiros.getDataNascimento().getTime());
            preparedStatement.setTimestamp(4, t);
            preparedStatement.setString(5, passageiros.getEmail());
            preparedStatement.setInt(6, passageiros.getReserva().getIdReserva());

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

    public static void atualizar(Passageiros passageiros)
            throws SQLException, Exception {
        String sql = "UPDATE Passageiros SET Primeiro_Nome=?, Ultimo_Nome=?, CPF=?, "
                + "Dt_Nascimento=?, Email=?, Reserva_ID=?	"
                + "WHERE (Passageiro_ID=?)";
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
            preparedStatement.setString(1, passageiros.getNome());
            preparedStatement.setString(2, passageiros.getSobrenome());
            preparedStatement.setString(3, passageiros.getCpf());
            Timestamp t = new Timestamp(passageiros.getDataNascimento().getTime());
            preparedStatement.setTimestamp(4, t);
            preparedStatement.setString(5, passageiros.getEmail());
            preparedStatement.setInt(6, passageiros.getReserva().getIdReserva());
            preparedStatement.setInt(7, passageiros.getId());
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
        //Monta a string de atualização do passageiros no BD, utilizando
        //prepared statement
        String sql = "UPDATE Passageiros SET Ativo=? WHERE (Passageiro_ID=?)";
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

    public static List<Passageiros> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Passageiros WHERE (Ativo=?)";

        List<Passageiros> listaPassageiros = null;
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
                if (listaPassageiros == null) {
                    listaPassageiros = new ArrayList<>();
                }

                Passageiros passageiros = new Passageiros();
                passageiros.setId(result.getInt("Passageiro_ID"));
                passageiros.setCpf(result.getString("CPF"));
                passageiros.setDataNascimento(result.getDate("Dt_Nascimento"));
                passageiros.setEmail(result.getString("Email"));
                passageiros.setNome(result.getString("Primeiro_Nome"));
                passageiros.setSobrenome(result.getString("Ultimo_Nome"));
                passageiros.setReserva_ID(result.getInt("Reserva_ID"));

                //Adiciona a instância na lista
                listaPassageiros.add(passageiros);
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
        //Retorna a lista de passageiross do banco de dados
        return listaPassageiros;
    }

    public static List<Passageiros> procurar(String valor)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Passageiros WHERE CPF=? AND Ativo=?";

        List<Passageiros> listaPassageiros = null;
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
                if (listaPassageiros == null) {
                    listaPassageiros = new ArrayList<>();
                }

                Passageiros passageiros = new Passageiros();
                passageiros.setId(result.getInt("Passageiro_ID"));
                passageiros.setCpf(result.getString("CPF"));
                passageiros.setDataNascimento(result.getDate("Dt_Nascimento"));
                passageiros.setEmail(result.getString("Email"));
                passageiros.setNome(result.getString("Primeiro_Nome"));
                passageiros.setSobrenome(result.getString("Ultimo_Nome"));
                passageiros.setReserva_ID(result.getInt("Reserva_ID"));
                //Adiciona a instância na lista
                listaPassageiros.add(passageiros);
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

        return listaPassageiros;
    }

    public static Passageiros obter(Integer id)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Passageiros WHERE (Passageiros_ID=? AND Ativo=?)";

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

                Passageiros passageiros = new Passageiros();
                passageiros.setId(result.getInt("Passageiro_ID"));
                passageiros.setCpf(result.getString("CPF"));
                passageiros.setDataNascimento(result.getDate("Dt_Nascimento"));
                passageiros.setEmail(result.getString("Email"));
                passageiros.setNome(result.getString("Primeiro_Nome"));
                passageiros.setSobrenome(result.getString("Ultimo_Nome"));
                passageiros.setReserva_ID(result.getInt("Reserva_ID"));
                //Retorna o resultado
                return passageiros;
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

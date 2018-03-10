/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.reserva.classes.Reserva;
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
public class DaoReservas {

    public static void inserir(Reserva reserva)
            throws SQLException, Exception {

        String sql = "INSERT INTO Reserva (Data_Criado,	Status,	Vendedor, Forma_Pagto, Custo_Total, Cliente_ID, Ativo, Ticket "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            Timestamp t = new Timestamp(reserva.getDataReserva().getTime());
            preparedStatement.setTimestamp(1, t);
            preparedStatement.setString(2, reserva.getStatus());
            preparedStatement.setString(3, reserva.getVendedor());
            preparedStatement.setString(4, reserva.getFormaPgto());
            preparedStatement.setDouble(5, reserva.getCustoTotal());
            preparedStatement.setInt(6, reserva.getCliente().getId());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, reserva.getTicket());

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

    public static void atualizar(Reserva reserva)
            throws SQLException, Exception {
        String sql = "UPDATE Reserva SET Data_Criado=?, Status=?, Vendedor=?, "
                + "Forma_Pagto=?, Custo_Total=?, Cliente_ID=?, Ativo=?, Ticket=? "
                + "WHERE (Reserva_ID=?)";
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
            Timestamp t = new Timestamp(reserva.getDataReserva().getTime());
            preparedStatement.setTimestamp(1, t);
            preparedStatement.setString(2, reserva.getStatus());
            preparedStatement.setString(3, reserva.getVendedor());
            preparedStatement.setString(4, reserva.getFormaPgto());
            preparedStatement.setDouble(5, reserva.getCustoTotal());
            preparedStatement.setInt(6, reserva.getCliente().getId());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, reserva.getTicket());
            preparedStatement.setInt(9, reserva.getIdReserva());
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
        //Monta a string de atualização do reserva no BD, utilizando
        //prepared statement
        String sql = "UPDATE Reserva SET Ativo=? WHERE (Reserva_ID=?)";
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

    public static List<Reserva> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Reservas WHERE (Ativo=?)";

        List<Reserva> listaReserva = null;
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
                if (listaReserva == null) {
                    listaReserva = new ArrayList<>();
                }

                Reserva reserva = new Reserva();
                reserva.setAtivo(result.getBoolean("Ativo"));
                reserva.setCustoTotal(result.getDouble("Custo_Total"));
                reserva.setDataReserva(result.getDate("Data_Criado"));
                reserva.setFormaPgto(result.getString("Forma_Pagto"));
                reserva.setIdCliente(result.getInt("Cliente_ID"));
                reserva.setIdReserva(result.getInt("Reserva_ID"));
                reserva.setStatus(result.getString("Status"));
                reserva.setVendedor(result.getString("Vendedor"));
                reserva.setTicket(result.getString("Ticket"));

                //Adiciona a instância na lista
                listaReserva.add(reserva);
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
        //Retorna a lista de reservas do banco de dados
        return listaReserva;
    }

    public static List<Reserva> procurar(String valor)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Reserva WHERE Ticket=? AND Ativo=?";

        List<Reserva> listaReserva = null;
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
                if (listaReserva == null) {
                    listaReserva = new ArrayList<>();
                }

                Reserva reserva = new Reserva();
                reserva.setAtivo(result.getBoolean("Ativo"));
                reserva.setCustoTotal(result.getDouble("Custo_Total"));
                reserva.setDataReserva(result.getDate("Data_Criado"));
                reserva.setFormaPgto(result.getString("Forma_Pagto"));
                reserva.setIdCliente(result.getInt("Cliente_ID"));
                reserva.setIdReserva(result.getInt("Reserva_ID"));
                reserva.setStatus(result.getString("Status"));
                reserva.setVendedor(result.getString("Vendedor"));
                reserva.setTicket(result.getString("Ticket"));
                //Adiciona a instância na lista
                listaReserva.add(reserva);
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

        return listaReserva;
    }

    public static Reserva obter(Integer id)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Reserva WHERE (Reserva_ID=? AND Ativo=?)";

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

                Reserva reserva = new Reserva();
                reserva.setAtivo(result.getBoolean("Ativo"));
                reserva.setCustoTotal(result.getDouble("Custo_Total"));
                reserva.setDataReserva(result.getDate("Data_Criado"));
                reserva.setFormaPgto(result.getString("Forma_Pagto"));
                reserva.setIdCliente(result.getInt("Cliente_ID"));
                reserva.setIdReserva(result.getInt("Reserva_ID"));
                reserva.setStatus(result.getString("Status"));
                reserva.setVendedor(result.getString("Vendedor"));
                reserva.setTicket(result.getString("Ticket"));
                //Retorna o resultado
                return reserva;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.reserva.classes.ReservaConsultar;
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
public class DaoConsultarReservas {
    public static List<ReservaConsultar> procurarReserva(String valor)
            throws SQLException, Exception {

        String sql = "CALL ConsultaReservas (?)";

        List<ReservaConsultar> listaReserva = null;
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

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaReserva == null) {
                    listaReserva = new ArrayList<>();
                }

                ReservaConsultar reserva = new ReservaConsultar();
                reserva.setReservaID(result.getInt("Reserva_ID"));
                reserva.setAssento(result.getString("Assento"));
                reserva.setDataPartida(result.getDate("Data_Voo"));
                reserva.setDataReserva(result.getDate("Data_Criado"));
                reserva.setDestino(result.getString("Aeroporto_Chegada"));
                reserva.setHoraVoo(result.getTime("HoraPartida"));
                reserva.setNome(result.getString("Primeiro_Nome"));
                reserva.setOrigem(result.getString("Aeroporto_Partida"));
                reserva.setSobrenome(result.getString("Ultimo_Nome"));
                reserva.setTicketCode(result.getString("Ticket"));
                reserva.setnVoo(result.getInt("Nr_Voo"));
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
    
    public static void deletarReserva(int reservaID) throws SQLException, Exception {
        //Monta a string de atualização do reserva no BD, utilizando
        //prepared statement
        String sql = "CALL deletarReserva (?)";
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
            
            preparedStatement.setInt(1, reservaID);

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
}

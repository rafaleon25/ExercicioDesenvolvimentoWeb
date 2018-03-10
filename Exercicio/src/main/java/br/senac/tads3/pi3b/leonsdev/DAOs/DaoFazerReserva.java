/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.cliente.classes.Cliente;
import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.login.classes.SingletonLogin;
import br.senac.tads3.pi3b.leonsdev.passageiros.classes.Passageiros;
import br.senac.tads3.pi3b.leonsdev.reserva.classes.Reserva;
import br.senac.tads3.pi3b.leonsdev.reserva.classes.TicketCode;
import br.senac.tads3.pi3b.leonsdev.servico.classes.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Josué
 */
public class DaoFazerReserva {

    public static String inserirVenda(Reserva reserva, Passageiros[] passageiro, Servico servico, Cliente cliente) throws SQLException, Exception {
        //Monta a string de inserção dos dados no BD,
        String sql1 = "insert into Reservas (Data_Criado, Status, Vendedor, Forma_Pagto, Custo_Total, Cliente_ID, Ativo, Ticket) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "insert into Passageiros (Primeiro_Nome, Ultimo_Nome, CPF, Dt_Nascimento, Email, Reserva_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        String sql3 = "insert into PassageirosVoos (Passageiro_ID, Voo_ID, Status, Assento) "
                + "VALUES (?, ?, ?, ?)";

        String sql4 = "insert into Servicos (Preco, Reserva_ID, ExtraBag, Ativo) "
                + "VALUES (?, ?, ?, ?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        String ticket = TicketCode.randomString(6);
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(2, "Confirmado");
            preparedStatement.setString(3, SingletonLogin.getInstance().getLogin());
            preparedStatement.setString(4, reserva.getFormaPgto());
            preparedStatement.setDouble(5, reserva.getCustoTotal());
            preparedStatement.setInt(6, cliente.getId());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, ticket);

            //Executa o comando no banco de dados
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < passageiro.length; i++) {
                preparedStatement2.setString(1, passageiro[i].getNome());
                preparedStatement2.setString(2, passageiro[i].getSobrenome());
                preparedStatement2.setString(3, passageiro[i].getCpf());
                Timestamp t = new Timestamp(passageiro[i].getDataNascimento().getTime());
                preparedStatement2.setTimestamp(4, t);
                preparedStatement2.setString(5, passageiro[i].getEmail());
                preparedStatement2.setInt(6, rs.getInt(1));
                preparedStatement2.execute();

                ResultSet rs2 = preparedStatement2.getGeneratedKeys();
                rs2.next();

                for (int j = 0; j < passageiro[i].getpassVoos().size(); j++) {
                    preparedStatement3 = connection.prepareStatement(sql3);
                    preparedStatement3.setInt(1, rs2.getInt(1));
                    preparedStatement3.setInt(2, passageiro[i].getpassVoos().get(j).getIdVoo());
                    preparedStatement3.setString(3, "Default");
                    preparedStatement3.setString(4, passageiro[i].getpassVoos().get(j).getAssento());
                    preparedStatement3.execute();
                }
            }

            preparedStatement4 = connection.prepareStatement(sql4);

            preparedStatement4.setDouble(1, servico.getPreco());
            preparedStatement4.setInt(2, rs.getInt(1));
            preparedStatement4.setString(3, servico.getExtraBag());
            preparedStatement4.setBoolean(4, true);
            preparedStatement4.execute();

        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (preparedStatement2 != null && !preparedStatement2.isClosed()) {
                preparedStatement2.close();
            }
            if (preparedStatement3 != null && !preparedStatement3.isClosed()) {
                preparedStatement3.close();
            }
            if (preparedStatement4 != null && !preparedStatement4.isClosed()) {
                preparedStatement4.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return ticket;
    }
    public static String inserirVendaComMilhas(Reserva reserva, Passageiros[] passageiro, Servico servico, Cliente cliente, Double milhas) throws SQLException, Exception {
        //Monta a string de inserção dos dados no BD,
        String sql1 = "insert into Reservas (Data_Criado, Status, Vendedor, Forma_Pagto, Custo_Total, Cliente_ID, Ativo, Ticket) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "insert into Passageiros (Primeiro_Nome, Ultimo_Nome, CPF, Dt_Nascimento, Email, Reserva_ID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        String sql3 = "insert into PassageirosVoos (Passageiro_ID, Voo_ID, Status, Assento) "
                + "VALUES (?, ?, ?, ?)";

        String sql4 = "insert into Servicos (Preco, Reserva_ID, ExtraBag, Ativo) "
                + "VALUES (?, ?, ?, ?)";
        
        String sql5 = "UPDATE Fidelidade set MilhasSaldo = MilhasSaldo - ? WHERE Cliente_ID = ?";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;

        String ticket = TicketCode.randomString(6);
        
        try {
            //Abre uma conexão com o banco de dados
            connection = ConnectionUtils.getConnection();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(2, "Confirmado");
            preparedStatement.setString(3, SingletonLogin.getInstance().getLogin());
            preparedStatement.setString(4, reserva.getFormaPgto());
            preparedStatement.setDouble(5, reserva.getCustoTotal());
            preparedStatement.setInt(6, cliente.getId());
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, ticket);

            //Executa o comando no banco de dados
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            preparedStatement2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < passageiro.length; i++) {
                preparedStatement2.setString(1, passageiro[i].getNome());
                preparedStatement2.setString(2, passageiro[i].getSobrenome());
                preparedStatement2.setString(3, passageiro[i].getCpf());
                Timestamp t = new Timestamp(passageiro[i].getDataNascimento().getTime());
                preparedStatement2.setTimestamp(4, t);
                preparedStatement2.setString(5, passageiro[i].getEmail());
                preparedStatement2.setInt(6, rs.getInt(1));
                preparedStatement2.execute();

                ResultSet rs2 = preparedStatement2.getGeneratedKeys();
                rs2.next();

                for (int j = 0; j < passageiro[i].getpassVoos().size(); j++) {
                    preparedStatement3 = connection.prepareStatement(sql3);
                    preparedStatement3.setInt(1, rs2.getInt(1));
                    preparedStatement3.setInt(2, passageiro[i].getpassVoos().get(j).getIdVoo());
                    preparedStatement3.setString(3, "Default");
                    preparedStatement3.setString(4, passageiro[i].getpassVoos().get(j).getAssento());
                    preparedStatement3.execute();
                }
            }

            preparedStatement4 = connection.prepareStatement(sql4);

            preparedStatement4.setDouble(1, servico.getPreco());
            preparedStatement4.setInt(2, rs.getInt(1));
            preparedStatement4.setString(3, servico.getExtraBag());
            preparedStatement4.setBoolean(4, true);
            preparedStatement4.execute();
            
            preparedStatement5 = connection.prepareStatement(sql5);
            preparedStatement5.setDouble(1, milhas);
            preparedStatement5.setInt(2, cliente.getId());
            preparedStatement5.execute();

        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (preparedStatement2 != null && !preparedStatement2.isClosed()) {
                preparedStatement2.close();
            }
            if (preparedStatement3 != null && !preparedStatement3.isClosed()) {
                preparedStatement3.close();
            }
            if (preparedStatement4 != null && !preparedStatement4.isClosed()) {
                preparedStatement4.close();
            }
            if (preparedStatement5 != null && !preparedStatement5.isClosed()) {
                preparedStatement5.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return ticket;
    }
}

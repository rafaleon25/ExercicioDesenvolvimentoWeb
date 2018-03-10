/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.relatorios.classes.RelatorioClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.senac.tads3.pi3b.leonsdev.relatorios.classes.RelatorioReservas;

/**
 *
 * @author Josué
 */
public class DaoRelatorios {
    public static List<RelatorioReservas> listarVendas(Date dataInicio, Date dataFim)
            throws SQLException, Exception {

        //Monta a string de listagem de vendas no banco, considerando
        //apenas as colunas usadas no relatório
        String sql = "CALL ReportReservas (?, ?)";
        /*
        String sql = "SELECT C.NOME nome, P.CATEGORIA categoria, P.DESCRICAO descricao, VP.QTD qtd, (VP.qtd*P.preco) total, VP.DATA data "
                + "FROM cliente C "
                + "INNER JOIN venda V ON V.CLIENTE_ID = C.CLIENTE_ID "
                + "INNER JOIN vendprod VP ON VP.VENDA_ID = V.VENDA_ID "
                + "INNER JOIN produto P ON P.PRODUTO_ID = VP.PRODUTO_ID "
                + "WHERE VP.DATA BETWEEN ? AND ?";*/
        //Lista de produtos de resultado
        List<RelatorioReservas> reservas = null;
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
            //Cria um preparedStatement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do PreparedStatement
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
            preparedStatement.setString(1, sdf.format(dataInicio));
            preparedStatement.setString(2, sdf1.format(dataFim));

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (reservas == null) {
                    reservas = new ArrayList<>();
                }
                //Cria uma instância de Venda e popula com os valores do BD
                RelatorioReservas venda = new RelatorioReservas();
                
                venda.setDataReserva(result.getDate("Data_Criado"));
                venda.setTicketCode(result.getString("Ticket"));
                venda.setPrimeiroNome_Passageiro(result.getString("Primeiro_Nome"));
                venda.setUltimoNome_Passageiro(result.getString("Ultimo_Nome"));
                venda.setDataPartida(result.getDate("Data_Voo"));
                venda.setNr_Voo(result.getInt("Nr_Voo"));
                venda.setHoraVoo(result.getTime("HoraPartida"));
                venda.setAeroportoOrigem(result.getString("Aeroporto_Partida"));
                venda.setAeroportoDestino(result.getString("Aeroporto_Chegada"));
                venda.setAssento(result.getString("Assento"));
                //Adiciona a instância na lista
                reservas.add(venda);
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o preparedStatement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        //Retorna a lista do banco de dados
        return reservas;

    }
 public static List<RelatorioClientes> listarClientes(Date dataInicio, Date dataFim)
            throws SQLException, Exception {

        //Monta a string de listagem de vendas no banco, considerando
        //apenas as colunas usadas no relatório
        String sql = "CALL ReportClientes (?, ?)";
        /*
        String sql = "SELECT C.NOME nome, P.CATEGORIA categoria, P.DESCRICAO descricao, VP.QTD qtd, (VP.qtd*P.preco) total, VP.DATA data "
                + "FROM cliente C "
                + "INNER JOIN venda V ON V.CLIENTE_ID = C.CLIENTE_ID "
                + "INNER JOIN vendprod VP ON VP.VENDA_ID = V.VENDA_ID "
                + "INNER JOIN produto P ON P.PRODUTO_ID = VP.PRODUTO_ID "
                + "WHERE VP.DATA BETWEEN ? AND ?";*/
        //Lista de produtos de resultado
        List<RelatorioClientes> clientes = null;
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
            //Cria um preparedStatement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do PreparedStatement
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
            preparedStatement.setString(1, sdf.format(dataInicio));
            preparedStatement.setString(2, sdf1.format(dataFim));

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (clientes == null) {
                    clientes = new ArrayList<>();
                }
                //Cria uma instância de Venda e popula com os valores do BD
                RelatorioClientes clientela = new RelatorioClientes();
                
                clientela.setDataCadastro(result.getDate("DataCadastro"));
                clientela.setNome(result.getString("Nome"));
                clientela.setSobrenome(result.getString("Sobrenome"));
                clientela.setCpf(result.getString("CPF"));
                clientela.setEmail(result.getString("Email"));
                clientela.setNivel(result.getString("Nivel"));
                clientela.setMilhasTotal(result.getInt("MilhasTotal"));
                clientela.setMilhasSaldo(result.getInt("MilhasSaldo"));
                clientela.setQtdReservas(result.getInt("TotalReservas"));
                clientela.setDataUltimaCompra(result.getDate("UltimaReserva"));
                //Adiciona a instância na lista
                clientes.add(clientela);
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o preparedStatement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        //Retorna a lista do banco de dados
        return clientes;

    }

}

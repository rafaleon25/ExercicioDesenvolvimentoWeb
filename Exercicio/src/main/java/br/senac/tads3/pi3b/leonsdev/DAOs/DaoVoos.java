/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.dbUtils.ConnectionUtils;
import br.senac.tads3.pi3b.leonsdev.voos.classes.Voos;
import java.sql.Connection;
import java.sql.Date;
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
public class DaoVoos {

    public static void inserir(Voos voos)
            throws SQLException, Exception {

        String sql = "INSERT INTO Voos (Nr_Voo,	Aeroporto_Partida, Aeroporto_Chegada, Data_Voo,	Operadora,"
                + " Aeronave_ID, Distancia_Milhas, HoraPartida,	HoraChegada, Ativo "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setInt(1, voos.getNrVoo());
            preparedStatement.setString(2, voos.getAeroportoPartida());
            preparedStatement.setString(3, voos.getAeroportoChegada());
            Timestamp t = new Timestamp(voos.getDataVoo().getTime());
            preparedStatement.setTimestamp(4, t);
            preparedStatement.setString(5, voos.getOperadora());
            preparedStatement.setInt(6, voos.getAeronave().getId());
            preparedStatement.setInt(7, voos.getDistanciaMilhas());
            preparedStatement.setTime(8, voos.getHoraPartida());
            preparedStatement.setTime(9, voos.getHoraChegada());
            preparedStatement.setBoolean(10, true);

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

    public static void atualizar(Voos voos)
            throws SQLException, Exception {
        String sql = "UPDATE Voos SET Nr_Voo=?,	Aeroporto_Partida=?, Aeroporto_Chegada=?, Data_Voo=?,	"
                + "Operadora=?,"
                + " Aeronave_ID=?, Distancia_Milhas=?, HoraPartida=?,	HoraChegada=?, Ativo=? "
                + "WHERE (Voo_ID=?)";
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
            preparedStatement.setInt(1, voos.getNrVoo());
            preparedStatement.setString(2, voos.getAeroportoPartida());
            preparedStatement.setString(3, voos.getAeroportoChegada());
            Timestamp t = new Timestamp(voos.getDataVoo().getTime());
            preparedStatement.setTimestamp(4, t);
            preparedStatement.setString(5, voos.getOperadora());
            preparedStatement.setInt(6, voos.getAeronave().getId());
            preparedStatement.setInt(7, voos.getDistanciaMilhas());
            preparedStatement.setTime(8, voos.getHoraPartida());
            preparedStatement.setTime(9, voos.getHoraChegada());
            preparedStatement.setBoolean(10, voos.getAtivo());
            preparedStatement.setInt(11, voos.getId());
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
        //Monta a string de atualização do voos no BD, utilizando
        //prepared statement
        String sql = "UPDATE Voos SET Ativo=? WHERE (Passageiro_ID=?)";
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

    public static List<Voos> listar()
            throws SQLException, Exception {
        String sql = "SELECT * FROM Voos WHERE (Ativo=?)";

        List<Voos> listaVoos = null;
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
                if (listaVoos == null) {
                    listaVoos = new ArrayList<>();
                }

                Voos voos = new Voos();
                voos.setId(result.getInt("Voo_ID"));
                voos.setAeronave_ID(result.getInt("Aeronave_ID"));
                voos.setAeroportoChegada(result.getString("Aeroporto_Chegada"));
                voos.setAeroportoPartida(result.getString("Aeroporto_Partida"));
                voos.setAtivo(result.getBoolean("Ativo"));
                voos.setDataVoo(result.getDate("Data_Voo"));
                voos.setDistanciaMilhas(result.getInt("Distancia_Milhas"));
                voos.setHoraChegada(result.getTime("HoraChegada"));
                voos.setHoraPartida(result.getTime("HoraPartida"));
                voos.setNrVoo(result.getInt("Nr_Voo"));
                voos.setOperadora(result.getString("Operadora"));
                voos.setTarifaMilhas(result.getDouble("TarifaPontos"));

                //Adiciona a instância na lista
                listaVoos.add(voos);
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
        //Retorna a lista de vooss do banco de dados
        return listaVoos;
    }

    public static List<Voos> procurar(String valor)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Voos WHERE Nr_Voo=? AND Ativo=?";

        List<Voos> listaVoos = null;
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
                if (listaVoos == null) {
                    listaVoos = new ArrayList<>();
                }

                Voos voos = new Voos();
                voos.setId(result.getInt("Voo_ID"));
                voos.setAeronave_ID(result.getInt("Aeronave_ID"));
                voos.setAeroportoChegada(result.getString("Aeroporto_Chegada"));
                voos.setAeroportoPartida(result.getString("Aeroporto_Partida"));
                voos.setAtivo(result.getBoolean("Ativo"));
                voos.setDataVoo(result.getDate("Data_Voo"));
                voos.setDistanciaMilhas(result.getInt("Distancia_Milhas"));
                voos.setHoraChegada(result.getTime("HoraChegada"));
                voos.setHoraPartida(result.getTime("HoraPartida"));
                voos.setNrVoo(result.getInt("Nr_Voo"));
                voos.setOperadora(result.getString("Operadora"));
                voos.setTarifaMilhas(result.getDouble("TarifaPontos"));
                //Adiciona a instância na lista
                listaVoos.add(voos);
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

        return listaVoos;
    }

    public static Voos obter(Integer id)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Voos WHERE Voo_ID=? AND Ativo=?";

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

                Voos voos = new Voos();
                voos.setId(result.getInt("Voo_ID"));
                voos.setAeronave_ID(result.getInt("Aeronave_ID"));
                voos.setAeroportoChegada(result.getString("Aeroporto_Chegada"));
                voos.setAeroportoPartida(result.getString("Aeroporto_Partida"));
                voos.setAtivo(result.getBoolean("Ativo"));
                voos.setDataVoo(result.getDate("Data_Voo"));
                voos.setDistanciaMilhas(result.getInt("Distancia_Milhas"));
                voos.setHoraChegada(result.getTime("HoraChegada"));
                voos.setHoraPartida(result.getTime("HoraPartida"));
                voos.setNrVoo(result.getInt("Nr_Voo"));
                voos.setOperadora(result.getString("Operadora"));
                voos.setTarifa(result.getDouble("Tarifa"));
                voos.setTarifaMilhas(result.getDouble("TarifaPontos"));
                //Retorna o resultado
                return voos;
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

    public static ArrayList<Voos> buscarVooEspecial(Date dataVoo, String AeroportoPartida, String AeroportoChegada)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Voos WHERE Data_Voo=? AND"
                + " Aeroporto_Partida=? AND"
                + " Aeroporto_Chegada=? AND"
                + " Ativo=?";

        ArrayList<Voos> listaVoos = null;
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
            Timestamp t = new Timestamp(dataVoo.getTime());
            preparedStatement.setTimestamp(1, t);
            preparedStatement.setString(2, AeroportoPartida);
            preparedStatement.setString(3, AeroportoChegada);
            preparedStatement.setBoolean(4, true);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Itera por cada item do resultado
            while (result.next()) {
                //Se a lista não foi inicializada, a inicializa
                if (listaVoos == null) {
                    listaVoos = new ArrayList<>();
                }

                Voos voos = new Voos();
                voos.setId(result.getInt("Voo_ID"));
                voos.setAeronave_ID(result.getInt("Aeronave_ID"));
                voos.setAeroportoChegada(result.getString("Aeroporto_Chegada"));
                voos.setAeroportoPartida(result.getString("Aeroporto_Partida"));
                voos.setAtivo(result.getBoolean("Ativo"));
                voos.setDataVoo(result.getDate("Data_Voo"));
                voos.setDistanciaMilhas(result.getInt("Distancia_Milhas"));
                voos.setHoraChegada(result.getTime("HoraChegada"));
                voos.setHoraPartida(result.getTime("HoraPartida"));
                voos.setNrVoo(result.getInt("Nr_Voo"));
                voos.setOperadora(result.getString("Operadora"));
                voos.setTarifa(result.getDouble("Tarifa"));
                voos.setTarifaMilhas(result.getDouble("TarifaPontos"));
                //Adiciona a instância na lista
                listaVoos.add(voos);
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

        return listaVoos;
    }

}

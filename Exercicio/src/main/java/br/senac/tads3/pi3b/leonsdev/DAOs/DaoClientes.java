/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.pi3b.leonsdev.DAOs;

import br.senac.tads3.pi3b.leonsdev.cliente.classes.Cliente;
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
public class DaoClientes {

    public static void inserir(Cliente cliente)
            throws SQLException, Exception {

        String sql = "INSERT INTO Clientes (Nome, Sobrenome, CPF, "
                + "Email, Celular, Data_Nascimento, Ativo, Estado,	"
                + "Sexo, Cidade, CEP, Endereco, DataCadastro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getCelular());
            Timestamp t = new Timestamp(cliente.getDataNascimento().getTime());
            preparedStatement.setTimestamp(6, t);
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, cliente.getEstado());
            preparedStatement.setString(9, cliente.getGenero());
            preparedStatement.setString(10, cliente.getCidade());
            preparedStatement.setString(11, cliente.getCep());
            preparedStatement.setString(12, cliente.getEndereco());
            preparedStatement.setDate(13, new java.sql.Date(System.currentTimeMillis()));

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

    public static void atualizar(Cliente cliente)
            throws SQLException, Exception {
        String sql = "UPDATE Clientes SET Nome=?, Sobrenome=?, CPF=?, "
                + "Email=?, Celular=?, Data_Nascimento=?, Ativo=?, Estado=?,	"
                + "Sexo=?, Cidade=?, CEP=?, Endereco=? "
                + "WHERE (Cliente_ID=?)";
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
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getCelular());
            Timestamp t = new Timestamp(cliente.getDataNascimento().getTime());
            preparedStatement.setTimestamp(6, t);
            preparedStatement.setBoolean(7, true);
            preparedStatement.setString(8, cliente.getEstado());
            preparedStatement.setString(9, cliente.getGenero());
            preparedStatement.setString(10, cliente.getCidade());
            preparedStatement.setString(11, cliente.getCep());
            preparedStatement.setString(12, cliente.getEndereco());
            preparedStatement.setInt(13, cliente.getId());

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
        //Monta a string de atualização do cliente no BD, utilizando
        //prepared statement
        String sql = "UPDATE Clientes SET Ativo=? WHERE (Cliente_ID=?)";
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

    public static List<Cliente> listar()
            throws SQLException, Exception {
        String sql = "SELECT C.*, F.Nivel, F.MilhasSaldo FROM Clientes C "
                + "INNER JOIN Fidelidade F ON F.Cliente_ID = C.Cliente_ID "
                + "WHERE (C.Ativo=?)";

        List<Cliente> listaClientes = null;
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
                if (listaClientes == null) {
                    listaClientes = new ArrayList<>();
                }

                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("Cliente_ID"));
                cliente.setCelular(result.getString("Celular"));
                cliente.setCep(result.getString("CEP"));
                cliente.setCidade(result.getString("Cidade"));
                cliente.setCpf(result.getString("CPF"));
                cliente.setDataNascimento(result.getDate("Data_Nascimento"));
                cliente.setEmail(result.getString("Email"));
                cliente.setEndereco(result.getString("Endereco"));
                cliente.setEstado(result.getString("Estado"));
                cliente.setGenero(result.getString("Sexo"));
                cliente.setNome(result.getString("Nome"));
                cliente.setSobrenome(result.getString("Sobrenome"));
                cliente.setDataCadastro(result.getDate("DataCadastro"));
                cliente.setNivel(result.getString("Nivel"));
                cliente.setPontos(result.getInt("MilhasSaldo"));

                //Adiciona a instância na lista
                listaClientes.add(cliente);
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
        //Retorna a lista de clientes do banco de dados
        return listaClientes;
    }

    public static Cliente procurar(String valor)
            throws SQLException, Exception {

        String sql = "SELECT C.*, F.Nivel, F.MilhasSaldo FROM Clientes C "
                + "INNER JOIN Fidelidade F ON F.Cliente_ID = C.Cliente_ID"
                + " WHERE C.CPF=? AND C.Ativo=? limit 1";

        Cliente cliente = new Cliente();
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
                cliente.setId(result.getInt("Cliente_ID"));
                cliente.setCelular(result.getString("Celular"));
                cliente.setCep(result.getString("CEP"));
                cliente.setCidade(result.getString("Cidade"));
                cliente.setCpf(result.getString("CPF"));
                cliente.setDataNascimento(result.getDate("Data_Nascimento"));
                cliente.setEmail(result.getString("Email"));
                cliente.setEndereco(result.getString("Endereco"));
                cliente.setEstado(result.getString("Estado"));
                cliente.setGenero(result.getString("Sexo"));
                cliente.setNome(result.getString("Nome"));
                cliente.setSobrenome(result.getString("Sobrenome"));
                cliente.setDataCadastro(result.getDate("DataCadastro"));
                cliente.setNivel(result.getString("Nivel"));
                cliente.setPontos(result.getInt("MilhasSaldo"));
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

        return cliente;
    }

    public static Cliente obter(Integer id)
            throws SQLException, Exception {

        String sql = "SELECT * FROM Clientes WHERE (Cliente_ID=? AND Ativo=?)";

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

                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("Cliente_ID"));
                cliente.setCelular(result.getString("Celular"));
                cliente.setCep(result.getString("CEP"));
                cliente.setCidade(result.getString("Cidade"));
                cliente.setCpf(result.getString("CPF"));
                cliente.setDataNascimento(result.getDate("Data_Nascimento"));
                cliente.setEmail(result.getString("Email"));
                cliente.setEndereco(result.getString("Endereco"));
                cliente.setEstado(result.getString("Estado"));
                cliente.setGenero(result.getString("Sexo"));
                cliente.setNome(result.getString("Nome"));
                cliente.setSobrenome(result.getString("Sobrenome"));
                cliente.setDataCadastro(result.getDate("DataCadastro"));
                //Retorna o resultado
                return cliente;
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

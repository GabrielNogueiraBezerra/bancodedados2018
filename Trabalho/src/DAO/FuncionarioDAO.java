package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Aluno;
import Models.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author Williana
 */
public class FuncionarioDAO {

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static FuncionarioDAO instancia;

    public static FuncionarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new FuncionarioDAO();
        }

        return instancia;
    }

    public void inserir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `funcionario`(`id`, `nome`, `contato`, `login`, `senha`) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getContato());
            stmt.setString(4, funcionario.getLogin());
            stmt.setString(5, funcionario.getSenha());

            stmt.executeUpdate();

            funcionario.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void alterar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `funcionario` SET `nome` = ?,`contato` = ?,`login` = ?, `senha` = ? WHERE `id` = ?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getContato());
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setInt(5, funcionario.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void excluir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM FUNCIONARIO WHERE ID = ?");
            stmt.setInt(1, funcionario.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void buscar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `contato`, `login`, `senha` FROM `funcionario` WHERE `id` = ?");
            stmt.setInt(1, funcionario.getId());
            result = stmt.executeQuery();

            while (result.next()) {
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setSenha(result.getString("senha"));
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
    }
    
    public ArrayList<Funcionario> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM FUNCIONARIO ORDER BY ID");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setNome(result.getString("nome"));
                funcionarios.add(funcionario);
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
            return funcionarios;
        }
    }

    public void validaFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `nome`, `contato`, `login`, `senha` FROM `funcionario` WHERE"
                    + " `login` = ? AND `senha` = ? ");
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            result = stmt.executeQuery();

            while (result.next()) {
                funcionario.setId(result.getInt("id"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setSenha(result.getString("senha"));
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
    }

    private int find() throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int resultado = 0;

        try {
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'funcionario' AND table_schema = 'bancoBD'");
            result = stmt.executeQuery();

            while (result.next()) {
                resultado = result.getInt("id");
            }

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
            return resultado - 1;
        }
    }

}

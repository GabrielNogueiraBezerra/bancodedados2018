package DAO;

import Conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.*;

/**
 *
 * @author Williana
 */
public class AlunoDAO {

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static AlunoDAO instancia;

    public static AlunoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AlunoDAO();
        }

        return instancia;
    }

    public void inserir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `aluno`(`id`, `nome`, `curso`, `contato`, `situacao`, `email`) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCurso());
            stmt.setString(4, aluno.getContato());
            stmt.setBoolean(5, aluno.isSituacao());
            stmt.setString(6, aluno.getEmail());

            stmt.executeUpdate();

            aluno.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void alterar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `aluno` SET `nome` = ?,`curso` = ?,`contato` = ?,`situacao` = ?, `email` = ? WHERE `id` = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCurso());
            stmt.setString(3, aluno.getContato());
            stmt.setBoolean(4, aluno.isSituacao());
            stmt.setString(5, aluno.getEmail());
            stmt.setInt(6, aluno.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void excluir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM ALUNO WHERE ID = ?");
            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void buscar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `curso`, `contato`, `situacao`, `email` FROM `aluno` WHERE `id` = ?");
            stmt.setInt(1, aluno.getId());
            result = stmt.executeQuery();

            while (result.next()) {
                aluno.setContato(result.getString("contato"));
                aluno.setCurso(result.getString("curso"));
                aluno.setEmail(result.getString("email"));
                aluno.setNome(result.getString("nome"));
                aluno.setSituacao(result.getBoolean("situacao"));
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
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'aluno' AND table_schema = 'bancoBD'");
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

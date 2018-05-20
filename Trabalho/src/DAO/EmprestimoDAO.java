package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Aluno;
import Models.Emprestimo;
import Models.Exemplar;
import Models.Funcionario;

/**
 *
 * @author Williana
 */
public class EmprestimoDAO {

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static EmprestimoDAO instancia;

    public static EmprestimoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EmprestimoDAO();
        }

        return instancia;
    }

    public void inserir(Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `emprestimo`(`id`, `exemplar`, `aluno`, `dataEmprestimo`, `dataPrevista`, `renovacoes`, `funcionario`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, emprestimo.getId());
            stmt.setInt(2, emprestimo.getExemplar().getId());
            stmt.setInt(3, emprestimo.getAluno().getId());
            stmt.setDate(4, (Date) emprestimo.getDataEmprestimo());
            stmt.setDate(5, (Date) emprestimo.getDataPrevista());
            stmt.setInt(6, emprestimo.getRenovacoes());
            stmt.setInt(7, emprestimo.getFuncionario().getId());

            stmt.executeUpdate();

            emprestimo.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void alterar(Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `emprestimo` SET `exemplar` = ?,`aluno` = ?,`datEmprestimo` = ?, `dataPrevista` = ?, `renovacoes` = ?, `funcionario` = ? WHERE `id` = ?");
            stmt.setInt(2, emprestimo.getExemplar().getId());
            stmt.setInt(3, emprestimo.getAluno().getId());
            stmt.setDate(4, (Date) emprestimo.getDataEmprestimo());
            stmt.setDate(5, (Date) emprestimo.getDataPrevista());
            stmt.setInt(6, emprestimo.getRenovacoes());
            stmt.setInt(7, emprestimo.getFuncionario().getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void excluir(Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM EMPRESTIMO WHERE ID = ?");
            stmt.setInt(1, emprestimo.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void buscar(Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `exemplar`, `aluno`, `dataEmprestimo`, `dataPrevista`, `renovacoes`, `funcionario` FROM `emprestimo` WHERE `id` = ?");
            stmt.setInt(1, emprestimo.getId());
            result = stmt.executeQuery();

            while (result.next()) {
                Aluno aluno = new Aluno();
                aluno.buscar(result.getInt("aluno"));
                emprestimo.setAluno(aluno);
                
                emprestimo.setDataEmprestimo(result.getDate("dataEmprestimo"));
                emprestimo.setDataPrevista(result.getDate("dataPrevista"));
                emprestimo.setRenovacoes(result.getInt("renovacoes"));
                
                Funcionario funcionario = new Funcionario();
                funcionario.buscar(result.getInt("funcionario"));
                emprestimo.setFuncionario(funcionario);
                
                Exemplar exemplar = new Exemplar();
                exemplar.buscar(result.getInt("exemplar"));
                
                emprestimo.setExemplar(exemplar);
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
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'emprestimo' AND table_schema = 'bancoBD'");
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

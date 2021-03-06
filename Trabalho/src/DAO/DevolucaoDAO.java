package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Devolucao;
import Models.Emprestimo;
import Models.Funcionario;
import java.sql.Date;

/**
 *
 * @author Williana
 */
public class DevolucaoDAO {

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static DevolucaoDAO instancia;

    public static DevolucaoDAO getInstancia() {
        if (instancia == null) {
            instancia = new DevolucaoDAO();
        }

        return instancia;
    }

    public void inserir(Devolucao devolucao) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `devolucao`(`id`, `emprestimo`, `funcionario`, `data`, `multa`) VALUES (?, ?, ?, ?, ?)");

            Date dataDevolucao = new Date(devolucao.getData().getYear(), devolucao.getData().getMonth(), devolucao.getData().getDay());

            stmt.setInt(1, devolucao.getId());
            stmt.setInt(2, devolucao.getEmprestimo().getId());
            stmt.setInt(3, devolucao.getFuncionario().getId());
            stmt.setDate(4, dataDevolucao);
            stmt.setFloat(5, devolucao.getMulta());

            stmt.executeUpdate();

            devolucao.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void alterar(Devolucao devolucao) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `devolucao` SET `emprestimo` = ?,`funcionario` = ?,`data` = ?, `multa` = ? WHERE `id` = ?");
            
            Date dataDevolucao = new Date(devolucao.getData().getYear(), devolucao.getData().getMonth(), devolucao.getData().getDay());
            
            stmt.setInt(1, devolucao.getEmprestimo().getId());
            stmt.setInt(2, devolucao.getFuncionario().getId());
            stmt.setDate(3, dataDevolucao);
            stmt.setFloat(4, devolucao.getMulta());
            stmt.setInt(5, devolucao.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void excluir(Devolucao devolucao) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM DEVOLUCAO WHERE ID = ?");
            stmt.setInt(1, devolucao.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void buscar(Devolucao devolucao) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `emprestimo`, `funcionario`, `data`, `multa` FROM `devolucao` WHERE `id` = ?");
            stmt.setInt(1, devolucao.getId());
            result = stmt.executeQuery();

            while (result.next()) {
                devolucao.setData(result.getDate("data"));

                Emprestimo emprestimo = new Emprestimo();
                emprestimo.buscar(result.getInt("emprestimo"));
                devolucao.setEmprestimo(emprestimo);

                Funcionario funcionario = new Funcionario();
                funcionario.buscar(result.getInt("emprestimo"));
                devolucao.setFuncionario(funcionario);

                devolucao.setMulta(result.getFloat("multa"));
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
    }

    public void buscar(Devolucao devolucao, Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `emprestimo`, `funcionario`, `data`, `multa` FROM `devolucao` WHERE `emprestimo` = ?   ");
            stmt.setInt(1, emprestimo.getId());
            result = stmt.executeQuery();

            while (result.next()) {
                
                devolucao.setId(result.getInt("id"));
                devolucao.setData(result.getDate("data"));

                devolucao.setEmprestimo(emprestimo);

                Funcionario funcionario = new Funcionario();
                funcionario.buscar(result.getInt("emprestimo"));
                devolucao.setFuncionario(funcionario);

                devolucao.setMulta(result.getFloat("multa"));
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
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'devolucao' AND table_schema = 'bancoBD'");
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

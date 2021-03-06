package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Exemplar;
import Models.Livro;
import java.util.ArrayList;

/**
 *
 * @author Williana
 */
public class ExemplarDAO {

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static ExemplarDAO instancia;

    public static ExemplarDAO getInstancia() {
        if (instancia == null) {
            instancia = new ExemplarDAO();
        }

        return instancia;
    }

    public void inserir(Exemplar exemplar) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `exemplar`(`id`, `livro`, `situacao`) VALUES (?, ?, ?)");
            stmt.setInt(1, exemplar.getId());
            stmt.setInt(2, exemplar.getLivro().getId());
            stmt.setBoolean(3, exemplar.isSituacao());

            stmt.executeUpdate();

            exemplar.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void alterar(Exemplar exemplar) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `exemplar` SET `livro` = ?,`situacao` = ? WHERE `id` = ?");
            stmt.setInt(1, exemplar.getLivro().getId());
            stmt.setBoolean(2, exemplar.isSituacao());
            stmt.setInt(3, exemplar.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void excluir(Exemplar exemplar) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM EXEMPLAR WHERE ID = ?");
            stmt.setInt(1, exemplar.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    
    public ArrayList<Exemplar> buscar(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Exemplar> exemplares = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `livro`, `situacao` FROM `exemplar` WHERE `livro` = ?");
            stmt.setInt(1, livro.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                Exemplar exemplar = new Exemplar();
                exemplar.setId(result.getInt("id"));
                exemplar.setSituacao(result.getBoolean("situacao"));
                exemplar.setLivro(livro);
                exemplares.add(exemplar);
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
            return exemplares;
        }
    }

    public void buscar(Exemplar exemplar) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `livro`, `situacao` FROM `exemplar` WHERE `id` = ?");
            stmt.setInt(1, exemplar.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                exemplar.setSituacao(result.getBoolean("situacao"));
                
                Livro livro = new Livro();
                livro.buscar(result.getInt("livro"));
                exemplar.setLivro(livro);
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
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'exemplar' AND table_schema = 'bancoBD'");
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

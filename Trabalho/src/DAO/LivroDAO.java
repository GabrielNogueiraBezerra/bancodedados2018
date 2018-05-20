package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Livro;

/**
 *
 * @author Williana
 */


public class LivroDAO {

	

    private ConnectionFactory dao = ConnectionFactory.getInstancia();
    private static LivroDAO instancia;
    
    public static LivroDAO getInstancia() {
        if (instancia == null) {
            instancia = new LivroDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `livro`(`id`, `autor`, `titulo`, `resenha`, `edicao`, `categoria`) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, livro.getId());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getTitulo());
            stmt.setString(4, livro.getResenha());
            stmt.setString(5, livro.getEdicao());
            stmt.setString(6, livro.getCategoria());
             
            stmt.executeUpdate();
            
            livro.setId(this.find());
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    
    public void alterar(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `livro` SET `autor` = ?,`titulo` = ?,`resenha` = ?, `edicao` = ?, `categoria` = ? WHERE `id` = ?");
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getTitulo());
            stmt.setString(4, livro.getResenha());
            stmt.setString(5, livro.getEdicao());
            stmt.setString(6, livro.getCategoria());
            stmt.setInt(7, livro.getId());
            
            
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    
    public void excluir(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM LIVRO WHERE ID = ?");
            stmt.setInt(1, livro.getId());
            stmt.executeUpdate();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    
    public Livro buscar(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `autor`, `titulo`, `resenha`, `edicao`, `categoria` FROM `livro` WHERE `id` = ?");
            stmt.setInt(1, livro.getId());
            stmt.executeQuery();
            return (Livro) stmt;
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }
    
    private int find() throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int resultado = 0;
        
        try {
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'livro' AND table_schema = 'bancoBD'");
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

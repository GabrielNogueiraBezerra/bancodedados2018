package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.ConnectionFactory;
import Models.Livro;
import Models.Exemplar;
import java.util.ArrayList;

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
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getResenha());
            stmt.setString(4, livro.getEdicao());
            stmt.setString(5, livro.getCategoria());
            stmt.setInt(6, livro.getId());
            
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
    
    public void buscar(Livro livro) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Exemplar> exemplares = null;
        try {
            stmt = conexao.prepareStatement("SELECT `autor`, `titulo`, `resenha`, `edicao`, `categoria` FROM `livro` WHERE `id` = ?");
            stmt.setInt(1, livro.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                livro.setAutor(result.getString("autor"));
                livro.setCategoria(result.getString("categoria"));
                livro.setEdicao(result.getString("edicao"));
                livro.setResenha(result.getString("resenha"));
                livro.setTitulo(result.getString("titulo"));
                
                exemplares = ExemplarDAO.getInstancia().buscar(livro);
                
                livro.setTotalExemplares(exemplares.size());
                
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
        }
    }
    
    public ArrayList<Livro> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConnection();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Exemplar> exemplares = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `autor`, `titulo`, `resenha`, `edicao`, `categoria` FROM `livro` order by id");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Livro livro = new Livro();
                livro.setId(result.getInt("id"));
                livro.setAutor(result.getString("autor"));
                livro.setCategoria(result.getString("categoria"));
                livro.setEdicao(result.getString("edicao"));
                livro.setResenha(result.getString("resenha"));
                livro.setTitulo(result.getString("titulo"));
                
                exemplares = ExemplarDAO.getInstancia().buscar(livro);
                
                livro.setTotalExemplares(exemplares.size());
                
                livros.add(livro);
            }
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, result);
            return livros;
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

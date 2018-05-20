package Models;

import DAO.ExemplarDAO;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 * @modificado Williana
 */
public class Exemplar implements InterfaceManter {

    private int id;
    private Livro livro;
    private boolean situacao;

    public Exemplar() {
        super();
    }

    public Exemplar(Livro livro, boolean situacao) {
        this.livro = livro;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        if (livro != null) {
            this.livro = livro;
        }
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
    @Override
    public void inserir() throws SQLException, ClassNotFoundException {
        if (this.livro != null) {
            if (this.id == 0) {
                ExemplarDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar()  throws SQLException, ClassNotFoundException{
        if (this.id > 0) {
        	ExemplarDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws SQLException, ClassNotFoundException {
        if (codigo > 0) {
            this.id = codigo;
            ExemplarDAO.getInstancia().buscar(this);
        }
    }

    @Override
    public void excluir() throws SQLException, ClassNotFoundException {
        if (this.id > 0) {
        	ExemplarDAO.getInstancia().excluir(this);
        }
    }


}

package Models;

import DAO.FuncionarioDAO;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 * @modificado Williana
 */
public class Funcionario implements InterfaceManter {

    private int id;
    private String nome;
    private String contato;
    private String login;
    private String senha;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String contato, String login, String senha) {
        this.setContato(contato);
        this.setLogin(login);
        this.setNome(nome);
        this.setSenha(senha);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        if (contato != null) {
            this.contato = contato;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null) {
            this.login = login;
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null) {
            this.senha = senha;
        }
    }

 
    @Override
    public void inserir()  throws ClassNotFoundException, SQLException{
        if (this.contato != null && this.login != null && this.nome != null && this.senha != null) {
            if (this.id == 0) {
                FuncionarioDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar()  throws ClassNotFoundException, SQLException{
        if (this.id > 0) {
        	FuncionarioDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo)  throws ClassNotFoundException, SQLException{
        if (codigo > 0) {
            this.id = codigo;
            FuncionarioDAO.getInstancia().buscar(this);
        }
       
    }

    @Override
    public void excluir()  throws ClassNotFoundException, SQLException{
        if (this.id > 0) {
           FuncionarioDAO.getInstancia().excluir(this);
        }
    }


}

package Models;

/**
 *
 * @author Gabriel
 */
public class Aluno implements InterfaceManter {

    private int id;
    private String nome;
    private String curso;
    private String contato;
    private boolean situacao;
    private String email;

    public Aluno() {
        super();
    }
    
    public Aluno(String nome){
        this();
        this.setNome(nome);
    }

    public Aluno(String nome, String curso, String contato, boolean situacao, String email) {
        this.setContato(contato);
        this.setCurso(curso);
        this.setEmail(email);
        this.setNome(nome);
        this.setSituacao(situacao);
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if (curso != null) {
            this.curso = curso;
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

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    @Override
    public void inserir() {
        if (this.contato != null && this.curso != null && this.email != null && this.nome != null) {
            if (this.id == 0) {
                // salvar no banco de dados
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() {
        if (this.id > 0) {
            // alterar no banco de dados
        }
    }

    @Override
    public void buscar(int codigo) {
        if (codigo > 0) {
            this.id = codigo;

            // buscar no banco de dados
        }
    }

    @Override
    public void excluir() {
        if (this.id > 0) {
            // deletar no banco de dados
        }
    }

}

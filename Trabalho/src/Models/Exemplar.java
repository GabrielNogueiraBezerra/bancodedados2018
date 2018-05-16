package Models;

/**
 *
 * @author Gabriel
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
    public void inserir() {
        if (this.livro != null) {
            if (this.id == 0) {
                // inserir no banco de dados
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
            // excluir no banco de dados
        }
    }

}

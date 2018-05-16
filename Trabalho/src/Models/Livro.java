package Models;

/**
 *
 * @author Gabriel
 */
public class Livro implements InterfaceManter {

    private int id;
    private String autor;
    private String titulo;
    private String resenha;
    private String edicao;
    private String categoria;

    public Livro() {
        super();
    }

    public Livro(String autor, String titulo, String resenha, String edicao, String categoria) {
        this.setAutor(autor);
        this.setCategoria(categoria);
        this.setEdicao(edicao);
        this.setResenha(resenha);
        this.setTitulo(titulo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void inserir() {
        if (this.autor != null && this.categoria != null && this.edicao != null && this.resenha != null && this.titulo != null) {
            if (this.id == 0) {
                // inserir no banco de dados;
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

            // buscar no banco de dados;
        }
    }

    @Override
    public void excluir() {
        if (this.id > 0) {
            // excluir no banco de dados
        }
    }

}

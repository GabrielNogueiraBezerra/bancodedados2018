package Models;

import DAO.AlunoDAO;
import DAO.EmprestimoDAO;
import DAO.ExemplarDAO;
import DAO.FuncionarioDAO;
import DAO.LivroDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Configuracao implements InterfaceObservable {

    private ArrayList<InterfaceObserver> observers;
    private ArrayList<Aluno> alunos;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Livro> livros;
    private ArrayList<Emprestimo> emprestimos;
    private Funcionario funcionario;

    public Configuracao() {
        if (this.observers == null) {
            this.observers = new ArrayList<>();
        }
    }

    @Override
    public void incluir(InterfaceObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    @Override
    public void excluir(InterfaceObserver observer) {
        if (observer != null) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void avisarObservers() {
        for (InterfaceObserver observer : this.observers) {
            if (observer != null) {
                observer.alterar();
            }
        }
    }

    public void salvaAluno(String nome, String curso, String contato, String email) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setContato(contato);
        aluno.setEmail(email);
        aluno.setCurso(curso);
        aluno.inserir();
    }

    public void salvaFuncionario(String contato, String nome, String login, String senha) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setContato(contato);
        funcionario.setLogin(login);
        funcionario.setNome(nome);
        funcionario.setSenha(senha);
        funcionario.inserir();
    }

    public void salvaLivro(String autor, String resenha, String edicao, String categoria, int totalExemplares) throws ClassNotFoundException, SQLException {
        Livro livro = new Livro();
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setEdicao(edicao);
        livro.setResenha(resenha);
        livro.setTitulo(edicao);
        livro.inserir();

        this.salvaExemplares(totalExemplares, livro);
    }

    private void salvaExemplares(int totalExemplares, Livro livro) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < totalExemplares; i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setLivro(livro);
            exemplar.setSituacao(false);
            exemplar.inserir();
        }
    }

    public void salvaEmprestimo(int codigoAluno, int codigoExemplar) throws ClassNotFoundException, SQLException {
        Emprestimo emprestimo = new Emprestimo();

        Aluno aluno = this.retornaAluno(codigoAluno);

        Exemplar exemplar = this.retornaExemplar(codigoExemplar);

        emprestimo.setAluno(aluno);

        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setDataPrevista(new Date());
        emprestimo.setExemplar(exemplar);
        emprestimo.setFuncionario(funcionario);
        emprestimo.setRenovacoes(0);
        emprestimo.inserir();
    }

    public void salvaDevolucao(int codigoEmprestimo) throws ClassNotFoundException, SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.buscar(codigoEmprestimo);

        Devolucao devolucao = new Devolucao();
        devolucao.setData(new Date());
        devolucao.setEmprestimo(emprestimo);
        devolucao.setFuncionario(this.funcionario);

        devolucao.setMulta((float) 1.5);
        devolucao.inserir();

    }

    public void alterarAluno(int matricula, String nome, String curso, String contato, String email) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(matricula);

        aluno.setNome(nome);
        aluno.setContato(contato);
        aluno.setEmail(email);
        aluno.setCurso(curso);

        aluno.alterar();
    }

    public void alterarFuncionario(int codigo, String contato, String nome, String login, String senha) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.buscar(codigo);

        funcionario.setContato(contato);
        funcionario.setLogin(login);
        funcionario.setNome(nome);
        funcionario.setSenha(senha);

        funcionario.alterar();
    }

    public void alterarLivro(int codigo, String autor, String resenha, String edicao, String categoria, int totalExemplares) throws ClassNotFoundException, SQLException {
        Livro livro = new Livro();
        livro.buscar(codigo);

        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livro.setEdicao(edicao);
        livro.setResenha(resenha);
        livro.setTitulo(edicao);
        livro.alterar();
    }

    public void alterarEmprestimo(int codigoEmprestimo, int codigoAluno, int codigoExemplar) throws ClassNotFoundException, SQLException {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.buscar(codigoEmprestimo);
        Aluno aluno = this.retornaAluno(codigoAluno);
        Exemplar exemplar = this.retornaExemplar(codigoExemplar);
        emprestimo.setAluno(aluno);
        // não posso alterar a data de emprestimo
        //emprestimo.setDataEmprestimo(new Date());
        // a unica opção que alterará a data prevista é a renovação
        //emprestimo.setDataPrevista(new Date());
        emprestimo.setExemplar(exemplar);
        // sem alterar o funcionario que cadastrou o emprestimo
        //emprestimo.setFuncionario(funcionario);
        // não posso alterar o numero de renovações para 0 pois não sei quantas houve
        //emprestimo.setRenovacoes(0);

        emprestimo.alterar();
    }

    public void renovarEmprestimo(int codigoEmprestimo) throws ClassNotFoundException, SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.buscar(codigoEmprestimo);

        emprestimo.setRenovacoes(emprestimo.getRenovacoes() + 1);
        emprestimo.setDataPrevista(new Date());

        emprestimo.alterar();
    }

    public void excluirAluno(int matricula) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(matricula);
        aluno.excluir();
    }

    public void excluirFuncionario(int codigo) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.buscar(codigo);
        funcionario.excluir();
    }

    public void excluirLivro(int codigo) throws ClassNotFoundException, SQLException {
        Livro livro = new Livro();
        livro.buscar(codigo);

        this.excluirExemplar(livro);

        livro.excluir();
    }

    private void excluirExemplar(Livro livro) throws SQLException, ClassNotFoundException {
        ArrayList<Exemplar> exemplares = ExemplarDAO.getInstancia().buscar(livro);
        for (Exemplar exemplar : exemplares) {
            exemplar.excluir();
        }
    }

    public void excluirEmprestimo(int codigo) throws ClassNotFoundException, SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.buscar(codigo);
        emprestimo.excluir();
    }

    public void buscaAlunos() throws SQLException, ClassNotFoundException {
        this.alunos = AlunoDAO.getInstancia().buscaTodos();
    }

    public void buscaFuncionarios() throws SQLException, ClassNotFoundException {
        this.funcionarios = FuncionarioDAO.getInstancia().buscaTodos();
    }

    public void buscarLivros() throws SQLException, ClassNotFoundException {
        this.livros = LivroDAO.getInstancia().buscaTodos();
    }

    public void buscarEmprestimos() throws SQLException, ClassNotFoundException {
        this.emprestimos = EmprestimoDAO.getInstancia().buscarTodos();
    }

    public void buscaAluno(int matricula) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(matricula);

        if (aluno.getNome() != null) {
            if (this.alunos == null) {
                this.alunos = new ArrayList<Aluno>();
            }

            this.alunos.add(aluno);
        }

    }

    public void buscaFuncionario(int codigo) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.buscar(codigo);

        if (funcionario.getNome() != null) {
            if (this.funcionarios == null) {
                this.funcionarios = new ArrayList<Funcionario>();
            }

            this.funcionarios.add(funcionario);
        }
    }

    public void buscaLivro(int codigo) throws ClassNotFoundException, SQLException {
        Livro livro = new Livro();
        livro.buscar(codigo);

        if (livro.getAutor() != null) {
            if (this.livros == null) {
                this.livros = new ArrayList<Livro>();
            }

            this.livros.add(livro);
        }
    }

    public void buscaEmprestimo(int codigo, int escolha) throws ClassNotFoundException, SQLException {

        Emprestimo emprestimo = new Emprestimo();

        switch (escolha) {
            case 0: {
                emprestimo.buscar(codigo);
                break;
            }
            case 1: {
                Aluno aluno = new Aluno();
                aluno.buscar(codigo);

                emprestimo.setAluno(aluno);

                emprestimo.buscar();
                break;
            }

            case 2: {
                Exemplar exemplar = new Exemplar();
                exemplar.buscar(codigo);

                emprestimo.setExemplar(exemplar);

                emprestimo.buscar();
                break;
            }
        }

        if (emprestimo.getAluno() != null && emprestimo.getExemplar() != null && emprestimo.getId() > 0) {
            if (this.emprestimos == null) {
                this.emprestimos = new ArrayList<>();
            }

            this.emprestimos.add(emprestimo);
        }
    }

    public Aluno retornaAluno(int matricula) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(matricula);
        return aluno;
    }

    public Funcionario retornaFuncionario(int codigo) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.buscar(codigo);
        return funcionario;
    }

    public Livro retornaLivro(int codigo) throws ClassNotFoundException, SQLException {
        Livro livro = new Livro();
        livro.buscar(codigo);
        return livro;
    }

    public Exemplar retornaExemplar(int codigo) throws SQLException, ClassNotFoundException {
        Exemplar exemplar = new Exemplar();
        exemplar.buscar(codigo);
        return exemplar;
    }

    public Emprestimo retornaEmprestimo(int codigo) throws SQLException, ClassNotFoundException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.buscar(codigo);
        return emprestimo;
    }

    public String retornaNomeAluno(int codigo) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(codigo);
        if (aluno.getNome() != null) {
            return "Aluno: " + aluno.getNome();
        } else {
            return "Aluno";
        }
    }

    public String retornaNomeExemplar(int codigo) throws SQLException, ClassNotFoundException {
        Exemplar exemplar = new Exemplar();
        exemplar.buscar(codigo);
        if (exemplar.getLivro() != null && !exemplar.isSituacao()) {
            return "Exemplar: " + exemplar.getLivro().getTitulo();
        } else {
            return "Exemplar";
        }
    }

    public void validaUsuario(String usuario, String senha) throws SQLException, ClassNotFoundException {
        if (this.funcionario == null) {
            this.funcionario = new Funcionario();
        }
        this.funcionario.setLogin(usuario);
        this.funcionario.setSenha(senha);
        FuncionarioDAO.getInstancia().validaFuncionario(this.funcionario);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public ArrayList<InterfaceObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<InterfaceObserver> observers) {
        this.observers = observers;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}

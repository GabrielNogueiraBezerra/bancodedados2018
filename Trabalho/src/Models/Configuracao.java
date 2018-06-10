package Models;

import DAO.AlunoDAO;
import DAO.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Configuracao implements InterfaceObservable {

    private ArrayList<InterfaceObserver> observers;
    private ArrayList<Aluno> alunos;
    private ArrayList<Funcionario> funcionarios;
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

    public void buscaAlunos() throws SQLException, ClassNotFoundException {
        AlunoDAO alunoDAO = new AlunoDAO();
        this.alunos = alunoDAO.buscaTodos();
    }

    public void buscaFuncionarios() throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        this.funcionarios = funcionarioDAO.buscaTodos();
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
            if(this.funcionarios == null){
                this.funcionarios = new ArrayList<Funcionario>();
            }
            
            this.funcionarios.add(funcionario);
        }
    }

    public Aluno retornaAluno(int matricula) throws ClassNotFoundException, SQLException {
        Aluno aluno = new Aluno();
        aluno.buscar(matricula);
        return aluno;
    }
    
    public Funcionario retornaFuncionario(int codigo) throws ClassNotFoundException, SQLException{
        Funcionario funcionario = new Funcionario();
        funcionario.buscar(codigo);
        return funcionario;
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

}

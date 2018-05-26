package Models;

import DAO.AlunoDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Configuracao implements InterfaceObservable {

    private ArrayList<InterfaceObserver> observers;
    private ArrayList<Aluno> alunos;

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

    public void buscaAlunos() throws SQLException, ClassNotFoundException {
        AlunoDAO alunoDAO = new AlunoDAO();
        this.alunos = alunoDAO.buscaTodos();
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

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

}

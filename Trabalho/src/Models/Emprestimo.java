package Models;

import DAO.EmprestimoDAO;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Gabriel
 * @modificado Williana
 */
public class Emprestimo implements InterfaceManter {

    private int id;
    private Exemplar exemplar;
    private Aluno aluno;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private int renovacoes;
    private Funcionario funcionario;

    public Emprestimo() {
    }

    public Emprestimo(Exemplar exemplar, Aluno aluno, Date dataEmprestimo, Date dataPrevista, int renovacoes, Funcionario funcionario) {
        this.setAluno(aluno);
        this.setDataEmprestimo(dataEmprestimo);
        this.setDataPrevista(dataPrevista);
        this.setExemplar(exemplar);
        this.setFuncionario(funcionario);
        this.setRenovacoes(renovacoes);
    }

    @Override
    public void inserir() throws ClassNotFoundException, SQLException {
        if (this.aluno != null && this.dataEmprestimo != null && this.dataPrevista != null && this.exemplar != null && this.funcionario != null
                && this.renovacoes >= 0) {
            if (this.id == 0) {
                EmprestimoDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if (this.id > 0) {
            EmprestimoDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if (codigo > 0) {
            this.id = codigo;
            EmprestimoDAO.getInstancia().buscar(this);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        if (this.id > 0) {
            EmprestimoDAO.getInstancia().excluir(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        if (exemplar != null) {
            this.exemplar = exemplar;
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        if (aluno != null) {
            this.aluno = aluno;
        }
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        if (dataEmprestimo != null) {
            this.dataEmprestimo = dataEmprestimo;
        }
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        if (dataPrevista != null) {
            this.dataPrevista = dataPrevista;
        }
    }

    public int getRenovacoes() {
        return renovacoes;
    }

    public void setRenovacoes(int renovacoes) {
        if (renovacoes >= 0) {
            this.renovacoes = renovacoes;
        }
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            this.funcionario = funcionario;
        }
    }
}

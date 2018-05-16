package Models;

import java.util.Date;

/**
 *
 * @author Gabriel
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
    public void inserir() {
        if (this.aluno != null && this.dataEmprestimo != null && this.dataPrevista != null && this.exemplar != null && this.funcionario != null
                && this.renovacoes >= 0) {
            if (this.id == 0) {
                // inserir no banco de dados
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() {
        if(this.id > 0){
            // alterar no banco de dados
        }
    }

    @Override
    public void buscar(int codigo) {
        if(codigo > 0){
            this.id = codigo;
            
            // buscar no banco de dados
        }
    }

    @Override
    public void excluir() {
        if(this.id > 0){
            // excluir no banco de dados
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

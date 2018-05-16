package Models;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Devolucao implements InterfaceManter {

    private int id;
    private Emprestimo emprestimo;
    private Funcionario funcionario;
    private Date data;
    private float multa;

    public Devolucao() {
        super();
    }

    public Devolucao(Emprestimo emprestimo, Funcionario funcionario, Date data, float multa) {
        this.setData(data);
        this.setEmprestimo(emprestimo);
        this.setFuncionario(funcionario);
        this.setMulta(multa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        if (emprestimo != null) {
            this.emprestimo = emprestimo;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        if (data != null) {
            this.data = data;
        }
    }

    public float getMulta() {
        return multa;
    }

    public void setMulta(float multa) {
        if (multa >= 0.0) {
            this.multa = multa;
        }
    }

    @Override
    public void inserir() {
        if (this.data != null && this.emprestimo != null && this.funcionario != null) {
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
            // excluir no banoc de dados
        }
    }

}

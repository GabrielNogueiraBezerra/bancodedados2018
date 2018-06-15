package Models;

import DAO.DevolucaoDAO;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Gabriel
 * @modificado Williana
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
    public void inserir() throws ClassNotFoundException, SQLException {
        if (this.data != null && this.emprestimo != null && this.funcionario != null) {
            if (this.id == 0) {
                DevolucaoDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if (this.id > 0) {
            DevolucaoDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if (codigo > 0) {
            this.id = codigo;
            DevolucaoDAO.getInstancia().buscar(this);
        }
    }

    public void buscar() throws SQLException, ClassNotFoundException {
        if (emprestimo != null) {
            DevolucaoDAO.getInstancia().buscar(this, emprestimo);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        if (this.id > 0) {
            DevolucaoDAO.getInstancia().excluir(this);
        }
    }

}

package Controllers;

import Models.Aluno;
import Models.Configuracao;
import Views.FrmCadastrarAluno;
import Models.InterfaceObserver;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel
 */
public class CadastrarAlunoController implements InterfaceObserver {

    private FrmCadastrarAluno view;
    private Configuracao model;

    public CadastrarAlunoController(FrmCadastrarAluno view, Configuracao model) {
        this.view = view;
        this.model = model;
        this.model.incluir(this);
    }

    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }

    public void evento(KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaAluno(this.view.getNome(), this.view.getCurso(), this.view.getContato(), this.view.getEmail());
                    this.view.mostraMensagem("Aluno cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível inserir o aluno. Mensagem retornada: " + ex.getMessage());
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if (this.view.getPesquisa().equals("")) {
                try {
                    this.model.buscaAlunos();
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os alunos. Mensagem retornada: " + ex.getMessage());
                }
            } else {
                try {
                    this.model.buscaAluno(Integer.parseInt(this.view.getPesquisa()));
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os alunos. Mensagem retornada: " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public void alterar() {
        ArrayList<Aluno> alunos = this.model.getAlunos();
        if (alunos != null) {
            this.view.limpaTableAlunos();
            String situacao = null;

            for (Aluno aluno : alunos) {
                if (aluno.isSituacao()) {
                    situacao = "OK";
                } else {
                    situacao = "Devedor";
                }

                String[] novaLinha = {String.valueOf(aluno.getId()), aluno.getNome(), aluno.getEmail(), situacao};
                ((DefaultTableModel) this.view.getTableAlunos().getModel()).addRow(novaLinha);
            }
        }

        this.model.setAlunos(new ArrayList<Aluno>());

    }
}

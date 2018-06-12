/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Funcionario;
import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmCadastrarFuncionario;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author willi
 */
public class CadastrarFuncionarioController implements InterfaceObserver {

    private Configuracao model;
    private FrmCadastrarFuncionario view;

    public CadastrarFuncionarioController(FrmCadastrarFuncionario view, Configuracao model) {
        this.model = model;
        this.view = view;
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

    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);
    }

    public void evento(MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int linha = this.view.getTableFuncionario().getSelectedRow();
            if (linha >= 0) {
                try {
                    this.view.preencheCamposAlteracao(this.model.retornaFuncionario(Integer.parseInt(
                            this.view.getTableFuncionario().getValueAt(linha, 0).toString())));

                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível selecionar funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
    }

    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaFuncionario(this.view.getContato(), this.view.getNome(), this.view.getLogin(), this.view.getSenha());
                    this.view.mostraMensagem("Funcionario cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Alterar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarFuncionario(Integer.parseInt(this.view.getCodigo()), this.view.getContato(), this.view.getNome(), this.view.getLogin(), this.view.getSenha());
                    this.view.mostraMensagem("Funcionario atualizado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível atualizar o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getCodigo().equals("")) {
                try {
                    this.model.excluirFuncionario(Integer.parseInt(this.view.getCodigo()));
                    this.view.mostraMensagem("Funcionario excluído com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if (this.view.getPesquisa().equals("")) {
                try {
                    this.model.buscaFuncionarios();
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os funcionarios. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            } else {
                try {
                    this.model.buscaFuncionario(Integer.parseInt(this.view.getPesquisa()));
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
    }

    @Override
    public void alterar() {
        ArrayList<Funcionario> funcionarios = this.model.getFuncionarios();
        if (funcionarios != null) {
            this.view.limpaTableFuncionarios();

            for (Funcionario funcionario : funcionarios) {
                String[] novaLinha = {String.valueOf(funcionario.getId()), funcionario.getNome(), funcionario.getContato()};
                ((DefaultTableModel) this.view.getTableFuncionario().getModel()).addRow(novaLinha);
            }
        }

        this.model.setFuncionarios(new ArrayList<Funcionario>());
    }

}

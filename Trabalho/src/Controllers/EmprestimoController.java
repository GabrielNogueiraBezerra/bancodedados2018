/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.DevolucaoDAO;
import Models.Configuracao;
import Models.Devolucao;
import Models.Emprestimo;
import Models.Funcionario;
import Models.InterfaceObserver;
import Views.FrmEmprestimo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author gabri
 */
public class EmprestimoController implements InterfaceObserver {

    private FrmEmprestimo view;
    private Configuracao model;

    public EmprestimoController(FrmEmprestimo view, Configuracao model) {
        this.view = view;
        this.model = model;
        this.model.incluir(this);
    }

    public void evento(KeyEvent evt) {
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public void evento(FocusEvent evt) {
        if (((JTextField) evt.getSource()).getToolTipText().equals("Aluno")) {
            try {
                this.view.addAluno(this.model.retornaNomeAluno(Integer.parseInt(this.view.getAluno())));
            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível buscar nome do aluno. Mensagem retornada: " + ex.getMessage());
                if (this.view.getAluno().equals("Aluno")) {
                    this.view.setAluno("");
                }
            }
        }
        if (((JTextField) evt.getSource()).getToolTipText().equals("Exemplar")) {
            try {
                this.view.addExemplar(this.model.retornaNomeExemplar(Integer.parseInt(this.view.getExemplar())));
                if (this.view.getLabelExemplar().equals("Exemplar")) {
                    this.view.mostraMensagem("Este exemplar já está emprestado.");
                    this.view.setExemplar("");
                    this.view.requestFocusExemplar();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível buscar nome do exemplar. Mensagem retornada: " + ex.getMessage());
            }
        }
    }

    public void evento(MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int linha = this.view.getTableEmprestimos().getSelectedRow();
            if (linha >= 0) {
                try {

                    if (this.view.getTableEmprestimos().getValueAt(linha, 6).equals("Devolvido")) {
                        this.view.mostraMensagem("Esse emprestimo já foi devolvido.");
                    } else {

                        this.view.preencheCamposAlteracao(this.model.retornaEmprestimo(Integer.parseInt(
                                this.view.getTableEmprestimos().getValueAt(linha, 0).toString())));
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível selecionar funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
    }

    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);
    }

    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }

    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaEmprestimo(Integer.parseInt(this.view.getAluno()), Integer.parseInt(this.view.getExemplar()));
                    this.view.mostraMensagem("Emprestimo cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o emprestimo. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Alterar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarEmprestimo(Integer.parseInt(this.view.getCodigo()), Integer.parseInt(this.view.getAluno()), Integer.parseInt(this.view.getExemplar()));
                    this.view.mostraMensagem("Emprestimo atualizado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível atualizar o emprestimo. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getCodigo().equals("")) {
                try {
                    this.model.excluirEmprestimo(Integer.parseInt(this.view.getCodigo()));
                    this.view.mostraMensagem("Emprestimo excluído com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir o emprestimo. Mensagem retornada: " + ex.getMessage());
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
                    this.model.buscarEmprestimos();
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os emprestimos. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            } else {
                try {
                    this.model.buscaEmprestimo(Integer.parseInt(this.view.getPesquisa()), this.view.getPesquisarPor().getSelectedIndex());

                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os emprestimos. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Devolver")) {
            try {
                this.model.salvaDevolucao(Integer.parseInt(this.view.getCodigo()));
                this.view.mostraMensagem("Devolução efetuada com sucesso.");
                this.view.limpaCampos();
            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível devolver o emprestimo. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Renovar")) {
            try {
                this.model.renovarEmprestimo(Integer.parseInt(this.view.getCodigo()));
                this.view.mostraMensagem("Renovação concluida com sucesso.");
                this.view.limpaCampos();
            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível renovar o emprestimo. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }
        }
    }

    private String formataData(Date data) {
        return data.getDay() + "/" + data.getMonth() + "/" + data.getYear();
    }

    @Override
    public void alterar() {
        try {
            ArrayList<Emprestimo> emprestimos = this.model.getEmprestimos();
            if (emprestimos != null) {
                this.view.limpaTableEmprestimos();
                for (Emprestimo emprestimo : emprestimos) {
                    String status = "";
                    Devolucao devolucao = new Devolucao();
                    devolucao.setEmprestimo(emprestimo);
                    devolucao.buscar();
                    if (devolucao.getId() > 0) {
                        status = "Devolvido";
                    } else {
                        status = "Não devolvido";
                    }
                    String[] novaLinha = {String.valueOf(emprestimo.getId()), emprestimo.getAluno().getNome(), this.formataData(emprestimo.getDataEmprestimo()), this.formataData(emprestimo.getDataPrevista()), emprestimo.getExemplar().getLivro().getTitulo(), String.valueOf(emprestimo.getRenovacoes()), status};
                    ((DefaultTableModel) this.view.getTableEmprestimos().getModel()).addRow(novaLinha);
                }
            }

            this.model.setEmprestimos(new ArrayList<Emprestimo>());
        } catch (Exception ex) {
            this.view.mostraMensagem("Não foi possível atualizar dados. Mensagem retornada: " + ex.getMessage());
        }
    }
}

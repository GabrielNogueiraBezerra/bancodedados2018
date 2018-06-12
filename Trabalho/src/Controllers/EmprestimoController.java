/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Configuracao;
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
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);
    }
    
    public void evento(MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int linha = this.view.getTabelaEmprestimo().getSelectedRow();
            if (linha >= 0) {
                try {
                    this.view.preencheCamposAlteracao(this.model.retornaEmprestimo(Integer.parseInt(
                            this.view.getTabelaEmprestimo().getValueAt(linha, 0).toString())));
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível selecionar funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
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
    }
    
    private String formataData(Date data) {
        return String.valueOf(data.getDay()) + "/" + String.valueOf(data.getMonth()) + "/" + String.valueOf(data.getYear());
    }
    
    @Override
    public void alterar() {
        try {
            ArrayList<Emprestimo> emprestimos = this.model.getEmprestimos();
            if (emprestimos != null) {
                this.view.limpaTableAlunos();
                
                for (Emprestimo emprestimo : emprestimos) {
                    String[] novaLinha = {String.valueOf(emprestimo.getId()), this.formataData(emprestimo.getDataEmprestimo()), emprestimo.getExemplar().getLivro().getTitulo(), "0", emprestimo.getAluno().getNome(), String.valueOf(emprestimo.getRenovacoes()), this.formataData(emprestimo.getDataPrevista())};
                    ((DefaultTableModel) this.view.getTabelaEmprestimo().getModel()).addRow(novaLinha);
                }
            }
            
            this.model.setEmprestimos(new ArrayList<Emprestimo>());
        } catch (Exception ex) {
            this.view.mostraMensagem("Não foi possível atualizar dados. Mensagem retornada: " + ex.getMessage());
        }
    }
}

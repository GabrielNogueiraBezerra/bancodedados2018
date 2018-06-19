
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Livro;
import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmCadastrarLivro;
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
 * @author gabri
 */
public class CadastrarLivroController implements InterfaceObserver {
    
    private FrmCadastrarLivro view;
    private Configuracao model;
    
    public CadastrarLivroController(FrmCadastrarLivro view, Configuracao model) {
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
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);
    }
    
    public void evento(MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int linha = this.view.getTableLivros().getSelectedRow();
            if (linha >= 0) {
                try {
                    this.view.preencheCamposAlteracao(this.model.retornaLivro(Integer.parseInt(
                            this.view.getTableLivros().getValueAt(linha, 0).toString())));
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível selecionar aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
    }
    
    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaLivro(this.view.getAutor(), this.view.getResenha(), this.view.getEdicao(), this.view.getCategoria(), Integer.parseInt(this.view.getQuantidadeExemplares()));
                    this.view.mostraMensagem("Livro cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível inserir o livro. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
        
        if (((JButton) evt.getSource()).getText().equals("Alterar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarLivro(Integer.parseInt(this.view.getCodigo()), this.view.getAutor(), this.view.getResenha(), this.view.getEdicao(), this.view.getCategoria(), Integer.parseInt(this.view.getQuantidadeExemplares()));
                    this.view.mostraMensagem("Livro alterado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível atualizar o livro. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
        
        if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getCodigo().equals("")) {
                try {
                    this.model.excluirLivro(Integer.parseInt(this.view.getCodigo()));
                    this.view.mostraMensagem("Livro excluído com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir o livro. Mensagem retornada: " + ex.getMessage());
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
                    this.model.buscarLivros();
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os livros. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            } else {
                try {
                    this.model.buscaLivro(Integer.parseInt(this.view.getPesquisa()));
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar o livro. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
    }
    
    @Override
    public void alterar() {
        
        ArrayList<Livro> livros = this.model.getLivros();
        if (livros != null) {
            this.view.limpaTableLivros();
            
            for (Livro livro : livros) {
                String[] novaLinha = {String.valueOf(livro.getId()), String.valueOf(livro.getTitulo()), livro.getAutor(), livro.getEdicao(), String.valueOf(livro.getTotalExemplares())};
                ((DefaultTableModel) this.view.getTableLivros().getModel()).addRow(novaLinha);
            }
        }
        
        this.model.setLivros(new ArrayList<Livro>());
    }
}

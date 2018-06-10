/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmCadastrarFuncionario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

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
    
    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaFuncionario(this.view.getContato(), this.view.getNome(), this.view.getLogin(), this.view.getSenha());
                    this.view.mostraMensagem("Funcionario cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o funcionario. Mensagem retornada: " + ex.getMessage());
                }
            }
        }
    }
    
    @Override
    public void alterar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

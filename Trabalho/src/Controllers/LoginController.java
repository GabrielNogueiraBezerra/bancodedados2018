package Controllers;

import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmLogin;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Gabriel
 */
public class LoginController implements InterfaceObserver {

    private FrmLogin view;
    private Configuracao model;

    public LoginController(FrmLogin view, Configuracao model) {
        this.view = view;
        this.model = model;
    }

    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }

    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Login")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.validaUsuario(this.view.getUsuario(), this.view.getSenha());
                    if (this.model.getFuncionario() != null) {
                        if (this.model.getFuncionario().getId() == 0) {
                            this.view.mensagem("Usuário ou senha não encontrados.");
                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mensagem("Não foi possível realizar a validação de usuário. Mensagem retornada: " + ex.getMessage());
                }
            }
        }
        if (((JButton) evt.getSource()).getText().equals("Sair")) {
            this.view.fechaTela();
        }
    }

    @Override
    public void alterar() {
        if (this.model.getFuncionario() != null) {
            if (this.model.getFuncionario().getId() > 0) {
                this.view.fechaTela();
            } else {
                this.model.setFuncionario(null);
            }
        }
    }
}

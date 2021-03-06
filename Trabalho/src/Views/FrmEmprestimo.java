/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.EmprestimoController;
import Models.Configuracao;
import Models.Emprestimo;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabri
 */
public class FrmEmprestimo extends javax.swing.JInternalFrame {

    private Configuracao model;
    private EmprestimoController controller;

    /**
     * Creates new form FrmEmprestimo
     */
    public FrmEmprestimo() {
        initComponents();
    }

    public FrmEmprestimo(Configuracao model) {
        this();
        this.model = model;
        this.controller = new EmprestimoController(this, model);
    }

    public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(this, mensagem);
        }
    }

    public boolean validaCampos() {
        if (this.txtAluno.getText().trim().equals("")) {
            this.mostraMensagem("Informe o aluno do emprestimo.");
            this.txtAluno.requestFocus();
            return false;
        }

        if (this.txtExemplar.getText().trim().equals("")) {
            this.mostraMensagem("Informe o exemplar do emprestimo.");
            this.txtAluno.requestFocus();
            return false;
        }

        return true;
    }

    public void limpaTableEmprestimos() {
        DefaultTableModel tabela = (DefaultTableModel) this.tableEmprestimo.getModel();
        tabela.setNumRows(0);
    }

    public JTable getTableEmprestimos() {
        return this.tableEmprestimo;
    }

    public String getAluno() {
        return this.txtAluno.getText().trim();
    }

    public String getExemplar() {
        return this.txtExemplar.getText().trim();
    }

    public String getLabelExemplar() {
        return this.lblExemplar.getText();
    }

    public String getLabelAluno() {
        return this.lblAluno.getText();
    }

    public String getCodigo() {
        return this.txtCodigo.getText().trim();
    }

    public String getPesquisa() {
        return this.txtPesquisa.getText().trim();
    }

    public JComboBox getPesquisarPor() {
        return this.cbPesquisa;
    }

    public void limpaCampos() {
        this.txtAluno.setText("");
        this.txtCodigo.setText("");
        this.txtExemplar.setText("");
        this.txtPesquisa.setText("");
        this.lblAluno.setText("Aluno");
        this.lblExemplar.setText("Exemplar");

        this.btnDevolver.setEnabled(false);
        this.btnExcluir.setEnabled(false);
        this.btnRenovar.setEnabled(false);
        this.btnSalvar.setText("Salvar");
    }

    public void addAluno(String titulo) {
        this.lblAluno.setText(titulo);
    }

    public void addExemplar(String titulo) {
        this.lblExemplar.setText(titulo);
    }

    public void preencheCamposAlteracao(Emprestimo emprestimo) {
        this.lblAluno.setText("Aluno: " + emprestimo.getAluno().getNome());
        this.lblExemplar.setText("Exemplar: " + emprestimo.getExemplar().getLivro().getTitulo());
        this.txtAluno.setText(String.valueOf(emprestimo.getAluno().getId()));
        this.txtExemplar.setText(String.valueOf(emprestimo.getExemplar().getId()));
        this.txtCodigo.setText(String.valueOf(emprestimo.getId()));
        this.btnDevolver.setEnabled(true);
        this.btnExcluir.setEnabled(true);
        this.btnRenovar.setEnabled(true);
        this.btnSalvar.setText("Alterar");
    }

    public void setExemplar(String exemplar) {
        if (exemplar != null) {
            this.txtExemplar.setText(exemplar);
        }
    }

    public void requestFocusExemplar() {
        this.txtExemplar.requestFocus();
    }

    public void setAluno(String aluno) {
        if (aluno != null) {
            this.txtAluno.setText(aluno);
        }
    }

    public JComboBox getComboCons() {
        return this.cbPesquisa;
    }

    public String getlblCons() {
        return this.lblCodigo.getText();
    }

    public void setlblCons(String texto) {
        if (texto != null) {
            this.lblCodigo.setText(texto);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbPesquisa = new javax.swing.JComboBox<>();
        lblCodigo = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtCodigo = new javax.swing.JTextField();
        lblAluno = new javax.swing.JLabel();
        txtAluno = new javax.swing.JTextField();
        lblExemplar = new javax.swing.JLabel();
        txtExemplar = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnRenovar = new javax.swing.JButton();
        btnDevolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmprestimo = new javax.swing.JTable();

        setClosable(true);
        setTitle("Emprestimos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel1.setText("Pesquisar por:");

        cbPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Aluno", "Livro" }));
        cbPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPesquisaItemStateChanged(evt);
            }
        });

        lblCodigo.setText("Codigo");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyTyped(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jLabel3.setText("Código");

        txtCodigo.setEditable(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        lblAluno.setText("Aluno");

        txtAluno.setToolTipText("Aluno");
        txtAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAlunoFocusLost(evt);
            }
        });
        txtAluno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAlunoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlunoKeyTyped(evt);
            }
        });

        lblExemplar.setText("Exemplar");

        txtExemplar.setToolTipText("Exemplar");
        txtExemplar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtExemplarFocusLost(evt);
            }
        });
        txtExemplar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExemplarKeyTyped(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnRenovar.setText("Renovar");
        btnRenovar.setEnabled(false);
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });

        btnDevolver.setText("Devolver");
        btnDevolver.setEnabled(false);
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        tableEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Aluno", "Data Emprestimo", "Data Prevista", "Livro", "Renovações", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmprestimoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmprestimo);
        if (tableEmprestimo.getColumnModel().getColumnCount() > 0) {
            tableEmprestimo.getColumnModel().getColumn(0).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(1).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(2).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(3).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(4).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(5).setResizable(false);
            tableEmprestimo.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(161, 161, 161)
                        .addComponent(lblCodigo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnOK))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(btnRenovar)
                        .addGap(7, 7, 7)
                        .addComponent(btnDevolver))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAluno)
                            .addComponent(txtAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblExemplar)
                            .addComponent(txtExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(128, 128, 128))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1))
                    .addComponent(lblCodigo))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRenovar)
                    .addComponent(btnDevolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAluno)
                        .addGap(6, 6, 6)
                        .addComponent(txtAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblExemplar)
                        .addGap(6, 6, 6)
                        .addComponent(txtExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyTyped
        this.controller.evento(evt);
    }//GEN-LAST:event_txtPesquisaKeyTyped

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnRenovarActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtAlunoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlunoKeyTyped
        this.controller.evento(evt);
    }//GEN-LAST:event_txtAlunoKeyTyped

    private void txtExemplarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExemplarKeyTyped
        this.controller.evento(evt);
    }//GEN-LAST:event_txtExemplarKeyTyped

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        this.controller.evento(evt);
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtAlunoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlunoKeyPressed
        this.controller.evento(evt);
    }//GEN-LAST:event_txtAlunoKeyPressed

    private void tableEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmprestimoMouseClicked
        this.controller.evento(evt);
    }//GEN-LAST:event_tableEmprestimoMouseClicked

    private void txtAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAlunoFocusLost
        this.controller.evento(evt);
    }//GEN-LAST:event_txtAlunoFocusLost

    private void txtExemplarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExemplarFocusLost
        this.controller.evento(evt);
    }//GEN-LAST:event_txtExemplarFocusLost

    private void cbPesquisaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPesquisaItemStateChanged
        this.controller.evento(evt);
    }//GEN-LAST:event_cbPesquisaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRenovar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAluno;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblExemplar;
    private javax.swing.JTable tableEmprestimo;
    private javax.swing.JTextField txtAluno;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtExemplar;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador.Especialidades;


import BLL.EspecialidadeJpaController;
import DAL.Especialidade;
import Main.Clinica;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nuno
 */
public class AtualizarEspecialidade extends javax.swing.JFrame {

    
    DefaultTableModel tabela = new DefaultTableModel();
    
    
    public AtualizarEspecialidade() {
        initComponents();
        ImageIcon ic = new ImageIcon(getClass().getResource("/Images/hospital.png"));
        this.setIconImage(ic.getImage());
        setLocationRelativeTo(null);
        
        Object colunas[] = {"Nome", "Preço"};
        tabela.setColumnIdentifiers(colunas);
        TabelaLista.setModel(tabela);
        
        loadData();
    }
    
    
    private void loadData(){
        
        EspecialidadeJpaController espController = new EspecialidadeJpaController();
        Boolean encontrou = false;
        Object obj[] = new Object[2];
        
        for(Especialidade e : espController.findEspecialidadeEntities()){
            obj[0] = e.getNome();
            obj[1] = e.getPreco();
            tabela.addRow(obj);
            encontrou = true;
        }
        
        if (!encontrou){
            JOptionPane.showMessageDialog(null, "Não existem especialidade registadas.");
            GerirEspecialidades gm = new GerirEspecialidades();
            gm.setVisible(true);
            dispose();
        }
        
        
    }
    
    private void loadData(String nome){
        
        EspecialidadeJpaController espController = new EspecialidadeJpaController();
        Boolean encontrou = false;
        Object obj[] = new Object[2];
        
        for(Especialidade e : espController.findEspecialidadeEntities(nome)){
            obj[0] = e.getNome();
            obj[1] = e.getPreco();
            tabela.addRow(obj);
            encontrou = true;
        }
        
        if (!encontrou)
            JOptionPane.showMessageDialog(null, "Não existem especialidade registadas com o nome desejado.");            
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Logout = new javax.swing.JLabel();
        Retroceder = new javax.swing.JLabel();
        Procurar = new javax.swing.JPanel();
        ProcurarLabel = new javax.swing.JLabel();
        NomeField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaLista = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Atualizar Especialidade");

        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cross.png"))); // NOI18N
        Logout.setToolTipText("Encerrar Sessão");
        Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });

        Retroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/backs.png"))); // NOI18N
        Retroceder.setToolTipText("Retroceder");
        Retroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Retroceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RetrocederMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(209, 209, 209)
                .addComponent(Retroceder)
                .addGap(18, 18, 18)
                .addComponent(Logout)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Retroceder)
                    .addComponent(Logout))
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Procurar.setBackground(new java.awt.Color(0, 204, 204));
        Procurar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Procurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProcurarMouseClicked(evt);
            }
        });

        ProcurarLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        ProcurarLabel.setForeground(new java.awt.Color(255, 255, 255));
        ProcurarLabel.setText("Procurar");
        ProcurarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProcurarLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ProcurarLayout = new javax.swing.GroupLayout(Procurar);
        Procurar.setLayout(ProcurarLayout);
        ProcurarLayout.setHorizontalGroup(
            ProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProcurarLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(ProcurarLabel)
                .addGap(19, 19, 19))
        );
        ProcurarLayout.setVerticalGroup(
            ProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProcurarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProcurarLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NomeField.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        NomeField.setForeground(new java.awt.Color(0, 204, 204));
        NomeField.setToolTipText("Nome");
        NomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeFieldActionPerformed(evt);
            }
        });
        NomeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NomeFieldKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Nome da Especialidade:");

        TabelaLista.setAutoCreateRowSorter(true);
        TabelaLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaLista.setColumnSelectionAllowed(true);
        TabelaLista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabelaLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaLista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        Clinica c = new Clinica();
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void RetrocederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrocederMouseClicked
        GerirEspecialidades ge = new GerirEspecialidades();
        ge.setVisible(true);
        dispose();
    }//GEN-LAST:event_RetrocederMouseClicked

    private void ProcurarLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProcurarLabelMouseClicked
        String nome = NomeField.getText();
        if(!nome.isEmpty()) {
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadData(nome);
        }
        else JOptionPane.showMessageDialog(null, "Insira um nome.");

    }//GEN-LAST:event_ProcurarLabelMouseClicked

    private void ProcurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProcurarMouseClicked
        String nome = NomeField.getText();
        if(!nome.isEmpty()) {
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadData(nome);
        }
        else JOptionPane.showMessageDialog(null, "Insira um nome.");

    }//GEN-LAST:event_ProcurarMouseClicked

    private void NomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeFieldActionPerformed

    private void NomeFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NomeFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeFieldKeyPressed

    private void TabelaListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaListaMouseClicked
        int row =  TabelaLista.getSelectedRow();
        String nomeEsp = (TabelaLista.getValueAt(row, 0)).toString();
        
        EspecialidadeJpaController eC = new EspecialidadeJpaController();
           
        Especialidade esp = eC.findEspecialidade(nomeEsp);
        AtualizarEspecialidade1 a1 = new AtualizarEspecialidade1();
        a1.setEsp(esp);
        a1.setVisible(true);
        dispose();

    }//GEN-LAST:event_TabelaListaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AtualizarEspecialidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarEspecialidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarEspecialidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarEspecialidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarEspecialidade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout;
    private javax.swing.JTextField NomeField;
    private javax.swing.JPanel Procurar;
    private javax.swing.JLabel ProcurarLabel;
    private javax.swing.JLabel Retroceder;
    private javax.swing.JTable TabelaLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

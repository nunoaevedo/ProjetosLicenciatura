/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador.MedicoAdmin;


import Administrador.Especialidades.*;
import BLL.MedicoJpaController;
import DAL.Medico;
import DAL.Utilizador;
import Main.Clinica;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nuno
 */
public class ProcurarMedico extends javax.swing.JFrame {

    DefaultTableModel tabela = new DefaultTableModel();
    Medico med =  new Medico();
    
    String nome;
    
    
    Boolean encontrou = false;
    Boolean showPass = false;
    Boolean search = false;
    
    
    
    public ProcurarMedico() {
        initComponents();
        ImageIcon ic = new ImageIcon(getClass().getResource("/Images/hospital.png"));
        this.setIconImage(ic.getImage());
        setLocationRelativeTo(null);
        
        Object colunas[] = {"Nome", "Apelido", "Data de Nascimento", "NIF", "Sexo", "Email", "Telefone", "Utilizador", "Senha"};
        tabela.setColumnIdentifiers(colunas);
        TabelaLista.setModel(tabela);
        
        loadData();
        
        
    }
    
    private void loadData(){
        MedicoJpaController medController = new MedicoJpaController();
        encontrou = false;
        Object obj[] = new Object[9];
        
        for(Object o : medController.findMedicoEntities()){
            obj[0] = ((Medico)o).getNome();
            obj[1] = ((Medico)o).getApelido(); 
            obj[2] = ((Medico)o).getDataNasc();
            obj[3] = ((Medico)o).getNif();
            if(null == ((Medico)o).getSexo()) obj[4] = "";
            else switch (((Medico)o).getSexo()) {
                case 0:
                    obj[4] = "Masculino";
                    break;
                case 1:
                    obj[4] = "Feminino";
                    break;
                default:
                    obj[4] = "";
                    break;
            }
            obj[5] = ((Medico)o).getEmail();
            obj[6] = ((Medico)o).getTelefone();    
            obj[7] = ((Medico)o).getIdUser().getUsername();
            
            tabela.addRow(obj);
            encontrou = true;
        }
        if (!encontrou){
            JOptionPane.showMessageDialog(null, "Não existem médicos registados.");
            GerirMedico gm = new GerirMedico();
            gm.setVisible(true);
            dispose();
        }
            
        
        
    }
    
    
    
    
    private void loadData(String nome){
        MedicoJpaController medController = new MedicoJpaController();
        
        Object obj[] = new Object[9];
        encontrou = false;
        for(Object o : medController.ProcuraMed(nome)){
            obj[0] = ((Medico)o).getNome();
            obj[1] = ((Medico)o).getApelido(); 
            obj[2] = ((Medico)o).getDataNasc();
            obj[3] = ((Medico)o).getNif();
            if(null == ((Medico)o).getSexo()) obj[4] = "";
            else switch (((Medico)o).getSexo()) {
                case 0:
                    obj[4] = "Masculino";
                    break;
                case 1:
                    obj[4] = "Feminino";
                    break;
                default:
                    obj[4] = "";
                    break;
            }
            obj[5] = ((Medico)o).getEmail();
            obj[6] = ((Medico)o).getTelefone();    
            obj[7] = ((Medico)o).getIdUser().getUsername();
            encontrou = true;
            tabela.addRow(obj);
        }
        
        if (!encontrou)
            JOptionPane.showMessageDialog(null, "Não existem médicos com o nome indicado.");
    }
    
    private void loadDataPassword(){
        MedicoJpaController medController = new MedicoJpaController();
        
        Object obj[] = new Object[9];
        encontrou = false;
        for(Object o : medController.findMedicoEntities()){
            obj[0] = ((Medico)o).getNome();
            obj[1] = ((Medico)o).getApelido();
            obj[2] = ((Medico)o).getDataNasc();
            obj[3] = ((Medico)o).getNif();
            if(null == ((Medico)o).getSexo()) obj[4] = "";
            else switch (((Medico)o).getSexo()) {
                case 0:
                    obj[4] = "Masculino";
                    break;
                case 1:
                    obj[4] = "Feminino";
                    break;
                default:
                    obj[4] = "";
                    break;
            }
            obj[5] = ((Medico)o).getEmail();
            obj[6] = ((Medico)o).getTelefone();    
            obj[7] = ((Medico)o).getIdUser().getUsername();
            obj[8] = ((Medico)o).getIdUser().getPassword();
            
            tabela.addRow(obj);
            encontrou = true;
        }
        if (!encontrou){
            JOptionPane.showMessageDialog(null, "Não existem médicos registados.");
            GerirMedico gm = new GerirMedico();
            gm.setVisible(true);
            dispose();
        }
        
    }
    
    private void loadDataPassword(String nome){
        MedicoJpaController medController = new MedicoJpaController();
        
        Object obj[] = new Object[9];
        encontrou = false;
        for(Object o : medController.ProcuraMed(nome)){
            obj[0] = ((Medico)o).getNome();
            obj[1] = ((Medico)o).getApelido();
            obj[2] = ((Medico)o).getDataNasc();
            obj[3] = ((Medico)o).getNif();
            if(null == ((Medico)o).getSexo()) obj[4] = "";
            else switch (((Medico)o).getSexo()) {
                case 0:
                    obj[4] = "Masculino";
                    break;
                case 1:
                    obj[4] = "Feminino";
                    break;
                default:
                    obj[4] = "";
                    break;
            }
            obj[5] = ((Medico)o).getEmail();
            obj[6] = ((Medico)o).getTelefone();    
            obj[7] = ((Medico)o).getIdUser().getUsername();
            obj[8] = ((Medico)o).getIdUser().getPassword();
            
            tabela.addRow(obj);
            encontrou = true;
        }
        if (!encontrou)
            JOptionPane.showMessageDialog(null, "Não existem médicos com o nome indicado.");
        
        
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
        PassToggle = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaLista = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Procurar Médico");

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
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(260, 260, 260)
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
                .addGap(26, 26, 26)
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
        jLabel2.setText("Nome do Médico:");

        PassToggle.setText("Mostrar Senhas");
        PassToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassToggleActionPerformed(evt);
            }
        });

        TabelaLista.setAutoCreateRowSorter(true);
        TabelaLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(PassToggle)
                        .addGap(440, 440, 440)))
                .addContainerGap(7, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PassToggle)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        Clinica c = new Clinica();
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void RetrocederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrocederMouseClicked
        GerirMedico gm = new GerirMedico();
        gm.setVisible(true);
        dispose();                
    }//GEN-LAST:event_RetrocederMouseClicked

    private void ProcurarLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProcurarLabelMouseClicked
        nome = NomeField.getText();
        if(!nome.isEmpty() && !showPass) {
            search = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadData(nome);
        }
        else if(showPass){
            search = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadDataPassword(nome);
        }
        else JOptionPane.showMessageDialog(null, "Insira um nome válido.");
         
        
    }//GEN-LAST:event_ProcurarLabelMouseClicked

    private void ProcurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProcurarMouseClicked
        nome = NomeField.getText();
        if(!nome.isEmpty() && !showPass) {
            search = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadData(nome);
        }
        else if(showPass){
            search = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadDataPassword(nome);
        }
        else JOptionPane.showMessageDialog(null, "Insira um nome válido.");
        
        
        
    }//GEN-LAST:event_ProcurarMouseClicked

    private void NomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeFieldActionPerformed

    private void NomeFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NomeFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeFieldKeyPressed

    private void PassToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassToggleActionPerformed
        if(PassToggle.isSelected() && !search){
            PassToggle.setText("Esconder Senhas");
            showPass = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadDataPassword();
        }
        else if(PassToggle.isSelected() && search){
            PassToggle.setText("Esconder Senhas");
            showPass = true;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadDataPassword(nome);
        }
        else  if(!PassToggle.isSelected() && search){
            PassToggle.setText("Mostrar Senhas");
            showPass = false;
            tabela.getDataVector().removeAllElements();
            tabela.fireTableDataChanged();
            loadData(nome);
        }
        else{
            PassToggle.setText("Mostrar Senhas");
            tabela.getDataVector().removeAllElements();
            showPass = false;
            tabela.fireTableDataChanged();
            loadData();
        }
    }//GEN-LAST:event_PassToggleActionPerformed

    private void TabelaListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaListaMouseClicked
        int row =  TabelaLista.getSelectedRow();
        String user = (TabelaLista.getValueAt(row, 7)).toString();
        MedicoJpaController medController = new MedicoJpaController();
        med = medController.findMedByUser(user);
        
        ProcurarMedico1 p1 = new ProcurarMedico1();
        p1.setMed(med);
        p1.setVisible(true);
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
            java.util.logging.Logger.getLogger(ProcurarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcurarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcurarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcurarMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ProcurarMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logout;
    private javax.swing.JTextField NomeField;
    private javax.swing.JToggleButton PassToggle;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rececao.Consulta;


import Administrador.MedicoAdmin.GerirMedico;
import BLL.EspecialidadeJpaController;
import BLL.MedicoJpaController;
import Rececao.Paciente.*;
import BLL.PacienteJpaController;
import DAL.Consulta;
import DAL.Especialidade;
import DAL.Paciente;
import Main.Clinica;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nuno
 */
public class AtualizarConsulta1 extends javax.swing.JFrame {

    DefaultTableModel tabela = new DefaultTableModel();
    
    String nome;
    
    Boolean encontrou = false;
    Paciente pac = new Paciente();
    Consulta cons = new Consulta();
    
    
    public AtualizarConsulta1() {
        initComponents();
        ImageIcon ic = new ImageIcon(getClass().getResource("/Images/hospital.png"));
        this.setIconImage(ic.getImage());
        setLocationRelativeTo(null);
        
        Boolean existe = false;
        EspecialidadeJpaController espController = new EspecialidadeJpaController();
        for(Object o : espController.findEspecialidadeEntities()){
            EspecialidadeBox.addItem(((Especialidade)o).getNome());
            existe = true;
        }
        if(!existe){
            JOptionPane.showMessageDialog(null, "Registe especialidades primeiro.");
            MenuConsulta gm = new MenuConsulta();
            gm.setVisible(true);
            dispose();
            
        }
        
        
    }
    
    private Boolean verCampos(){
        return DataField.getDate().toString().isEmpty() || EspecialidadeBox.getSelectedIndex() == 0 || Hora.getSelectedIndex() == 0 || Minuto.getSelectedIndex() == 0;
    }
    
    private void preencheCampos(){
        DataField.setDate(cons.getData());
        EspecialidadeBox.setSelectedItem(cons.getIdMed().getIdEsp().getNome());
        Hora.setSelectedItem(String.valueOf(cons.getData().getHours()));
        Minuto.setSelectedItem(String.valueOf(cons.getData().getMinutes()));
        
    }
    
    public void setCons(Consulta cons){
        this.cons = cons;
        preencheCampos();
        
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        DataField = new org.jdesktop.swingx.JXDatePicker();
        EspecialidadeBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        Hora = new javax.swing.JComboBox<>();
        Minuto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Atualizar Consulta");

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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Especialidade:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Data:");

        EspecialidadeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a especialidade" }));

        jButton1.setText("Escolher Médico");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        Hora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hora", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));

        Minuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minuto", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(336, 336, 336)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(DataField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Minuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(EspecialidadeBox, 0, 186, Short.MAX_VALUE))
                .addGap(345, 359, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(396, 396, 396))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EspecialidadeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Minuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        Clinica c = new Clinica();
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void RetrocederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrocederMouseClicked
        MenuConsulta gm = new MenuConsulta();
        gm.setVisible(true);
        dispose();                
    }//GEN-LAST:event_RetrocederMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        if(!verCampos()){
            Date d = new Date();
            Date d1 = DataField.getDate();
            d1.setHours(Integer.parseInt(Hora.getSelectedItem().toString()));
            d1.setMinutes(Integer.parseInt(Minuto.getSelectedItem().toString()));

            if(d1.after(d)){
                EspecialidadeJpaController ec = new EspecialidadeJpaController();
                Especialidade e = ec.findEspecialidade(EspecialidadeBox.getSelectedItem().toString());
                MedicoJpaController mc = new MedicoJpaController();
                if(mc.existeMedComEsp(e)){
                    cons.setData(d1);
                    AtualizarConsulta2 nc = new AtualizarConsulta2();
                    nc.setInfo(cons, e);
                    nc.setVisible(true);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "Não existem médicos registados com a especialidade selecionada.");
            }
            else if(d1.before(d))
                JOptionPane.showMessageDialog(null, "Selecione uma data válida.");   
        }
        else JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
        
        
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(AtualizarConsulta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarConsulta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarConsulta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarConsulta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new AtualizarConsulta1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker DataField;
    private javax.swing.JComboBox<String> EspecialidadeBox;
    private javax.swing.JComboBox<String> Hora;
    private javax.swing.JLabel Logout;
    private javax.swing.JComboBox<String> Minuto;
    private javax.swing.JLabel Retroceder;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

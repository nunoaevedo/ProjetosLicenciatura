/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador.MedicoAdmin;



import BLL.CodpostalJpaController;
import BLL.EspecialidadeJpaController;
import BLL.MedicoJpaController;
import BLL.UtilizadorJpaController;
import DAL.Codpostal;
import DAL.Especialidade;
import DAL.Medico;
import DAL.Utilizador;
import Main.Clinica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Nuno
 */
public class AtualizarMedico1 extends javax.swing.JFrame {

    private Medico med;
    
    
    
    public AtualizarMedico1() {
        
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
        if(existe == false){
            JOptionPane.showMessageDialog(null, "Registe especialidades primeiro.");
            GerirMedico gm = new GerirMedico();
            gm.setVisible(true);
            dispose();
        } 
        
    }
    
    
    private void preencheCampos(){
        
        
        NomeField.setText(med.getNome());
        ApelidoField.setText(med.getApelido());
        EmailField.setText(med.getEmail());
        RuaField.setText(med.getMorada());
        UtilizadorField.setText(med.getIdUser().getUsername());
        SenhaField.setText(med.getIdUser().getPassword());
        CodPostalField.setText(med.getCodPostal().getCodPostal());
        CidadeField.setText(med.getCodPostal().getCidade());
        DataNascField.setDate(med.getDataNasc());
        NIFField.setText(med.getNif().toString());        
        TelefoneField.setText(med.getTelefone().toString());
        EspecialidadeBox.setSelectedItem(med.getIdEsp().getNome());
        SexoBox.setSelectedIndex(med.getSexo()+1);
                
                
    }
    
    
    public void setMed(Medico m){
        med = m;
        preencheCampos();
    }
    
    
    private Boolean verCampos(){
        return NomeField.getText().isEmpty() || ApelidoField.getText().isEmpty() || EmailField.getText().isEmpty() || RuaField.getText().isEmpty() || UtilizadorField.getText().isEmpty()
                || CodPostalField.getText().isEmpty() || CidadeField.getText().isEmpty() || DataNascField.getDate().toString().isEmpty() || NIFField.getText().isEmpty() || String.valueOf(SenhaField.getPassword()).isEmpty()
                || TelefoneField.getText().isEmpty() || EspecialidadeBox.getSelectedIndex() == 0 || SexoBox.getSelectedIndex() == 0;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        NomeField = new javax.swing.JTextField();
        ApelidoField = new javax.swing.JTextField();
        EmailField = new javax.swing.JTextField();
        RuaField = new javax.swing.JTextField();
        UtilizadorField = new javax.swing.JTextField();
        Atualizar = new javax.swing.JButton();
        CodPostalField = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        CidadeField = new javax.swing.JTextField();
        DataNascField = new org.jdesktop.swingx.JXDatePicker();
        NIFField = new javax.swing.JFormattedTextField();
        SenhaField = new javax.swing.JPasswordField();
        EspecialidadeBox = new javax.swing.JComboBox<>();
        TelefoneField = new javax.swing.JFormattedTextField();
        SexoBox = new javax.swing.JComboBox<>();
        Limpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");
        setSize(new java.awt.Dimension(1000, 550));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Atualizar Médico");

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
                .addContainerGap(379, Short.MAX_VALUE)
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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Apelido:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Data Nascimento:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("NIF:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Rua:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Código Postal:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Sexo:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Telefone:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Email:");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Especialidade:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Utilizador:");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 153, 153));
        jLabel13.setText("Senha:");

        NomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeFieldActionPerformed(evt);
            }
        });

        ApelidoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApelidoFieldActionPerformed(evt);
            }
        });

        EmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailFieldActionPerformed(evt);
            }
        });

        RuaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RuaFieldActionPerformed(evt);
            }
        });

        UtilizadorField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UtilizadorFieldActionPerformed(evt);
            }
        });

        Atualizar.setText("Atualizar");
        Atualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtualizarMouseClicked(evt);
            }
        });

        try {
            CodPostalField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        CodPostalField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CodPostalFieldMouseExited(evt);
            }
        });
        CodPostalField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodPostalFieldKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 153));
        jLabel15.setText("Cidade:");

        CidadeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CidadeFieldActionPerformed(evt);
            }
        });

        NIFField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));

        EspecialidadeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a especialidade" }));

        TelefoneField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));

        SexoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o sexo", "Masculino", "Feminino" }));

        Limpar.setText("Limpar");
        Limpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LimparMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RuaField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(ApelidoField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(NomeField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(CodPostalField)
                            .addComponent(CidadeField)
                            .addComponent(DataNascField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NIFField)))
                    .addComponent(Limpar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Atualizar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UtilizadorField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(EmailField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(SenhaField)
                            .addComponent(EspecialidadeBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TelefoneField)
                            .addComponent(SexoBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(SexoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ApelidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(TelefoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DataNascField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(NIFField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EspecialidadeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UtilizadorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(RuaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel7)
                    .addComponent(CodPostalField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SenhaField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(CidadeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Atualizar)
                    .addComponent(Limpar))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        Clinica c = new Clinica();
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_LogoutMouseClicked

    private void RetrocederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RetrocederMouseClicked
        AtualizarMedico gm = new AtualizarMedico();
        gm.setVisible(true);
        dispose();                
    }//GEN-LAST:event_RetrocederMouseClicked

    private void NomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeFieldActionPerformed

    private void ApelidoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApelidoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApelidoFieldActionPerformed

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailFieldActionPerformed

    private void RuaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RuaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RuaFieldActionPerformed

    private void UtilizadorFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtilizadorFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UtilizadorFieldActionPerformed

    private void CidadeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CidadeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CidadeFieldActionPerformed

    private void CodPostalFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodPostalFieldKeyTyped
        
        
    }//GEN-LAST:event_CodPostalFieldKeyTyped

    private void CodPostalFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CodPostalFieldMouseExited
        String codPostal = CodPostalField.getText();
        CodpostalJpaController cpController = new CodpostalJpaController();
        
        if(!cpController.existeCodpostal(codPostal)){
            CidadeField.setText("");
            CidadeField.setEditable(true);
        }else{
            CidadeField.setText(cpController.findCodpostal(codPostal).getCidade());
            CidadeField.setEditable(false);
        }
    }//GEN-LAST:event_CodPostalFieldMouseExited

    private void AtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtualizarMouseClicked
        UtilizadorJpaController uController = new UtilizadorJpaController();
        MedicoJpaController medController = new MedicoJpaController();
        if(verCampos()) JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
        else if(!med.getIdUser().getUsername().equals(UtilizadorField.getText()) && med.getIdUser().getId() != medController.findMedByUser(UtilizadorField.getText()).getId())
            JOptionPane.showMessageDialog(null, "Nome de utilizador já existente.");
        else{
            CodpostalJpaController cpController = new CodpostalJpaController();
            Codpostal cp = new Codpostal();
            cp.setCodPostal(CodPostalField.getText());
            cp.setCidade(CidadeField.getText());
            if(!cpController.existeCodpostal(CodPostalField.getText())){
                try {
                    cpController.create(cp);
                } catch (Exception ex) {
                    Logger.getLogger(AtualizarMedico1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            EspecialidadeJpaController espController = new EspecialidadeJpaController();
            Especialidade esp = new Especialidade();
            
            esp = espController.findEspecialidade(EspecialidadeBox.getSelectedItem().toString());
                     

            Utilizador u = med.getIdUser();
            u.setUsername(UtilizadorField.getText());
            u.setPassword(String.valueOf(SenhaField.getPassword()));
           
            
            try {
                uController.edit(u);
                Integer temp = SexoBox.getSelectedIndex()-1;            
                short sexo = temp.shortValue();
                med.setNome(NomeField.getText());
                med.setApelido(ApelidoField.getText());
                med.setEmail(EmailField.getText());
                med.setMorada(RuaField.getText());            
                med.setCodPostal(cp);           
                med.setIdUser(u);          
                med.setDataNasc(DataNascField.getDate());
                med.setNif(Integer.parseInt(NIFField.getText()));
                med.setTelefone(Integer.parseInt(TelefoneField.getText()));           
                med.setSexo(sexo);           
                med.setIdEsp(esp);


                try {
                    medController.edit(med);
                    JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso.");
                    GerirMedico gm = new GerirMedico();
                    gm.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Houve um problema a editar o médico.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Houve um problema a editar o nome de utilizador do médico.");
            }
            
            
            
            
            
            
            
            
        }
        
        
    }//GEN-LAST:event_AtualizarMouseClicked

    private void LimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LimparMouseClicked
        
        preencheCampos();
        
    }//GEN-LAST:event_LimparMouseClicked

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
            java.util.logging.Logger.getLogger(AtualizarMedico1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtualizarMedico1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtualizarMedico1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtualizarMedico1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>       
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AtualizarMedico1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApelidoField;
    private javax.swing.JButton Atualizar;
    private javax.swing.JTextField CidadeField;
    private javax.swing.JFormattedTextField CodPostalField;
    private org.jdesktop.swingx.JXDatePicker DataNascField;
    private javax.swing.JTextField EmailField;
    private javax.swing.JComboBox<String> EspecialidadeBox;
    private javax.swing.JButton Limpar;
    private javax.swing.JLabel Logout;
    private javax.swing.JFormattedTextField NIFField;
    private javax.swing.JTextField NomeField;
    private javax.swing.JLabel Retroceder;
    private javax.swing.JTextField RuaField;
    private javax.swing.JPasswordField SenhaField;
    private javax.swing.JComboBox<String> SexoBox;
    private javax.swing.JFormattedTextField TelefoneField;
    private javax.swing.JTextField UtilizadorField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.reservations;

import fr.villalem.admin.ITadmin;
import fr.villalem.labd.ITconnexion;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.table.*;
import static gestionagenda.GestionAgenda.rq;

/**
 *
 * @author Villalemons
 */
public class ITagenda extends javax.swing.JFrame {

    /**
     * Creates new form ITagenda
     */
    public ITagenda() {
        initComponents();
               ResultSet rs = rq.getReservations();
 
         try {
             
             //To remove previously added rows
             while(jTable1.getRowCount() > 0) 
             {
                 ((DefaultTableModel) jTable1.getModel()).removeRow(0);
             }
             int columns = rs.getMetaData().getColumnCount();
             while(rs.next())
            {  
                Object[] row = new Object[columns];
                for (int i = 1; i <= columns; i++)
                {  
                    row[i - 1] = rs.getObject(i);
                }
                ((DefaultTableModel) jTable1.getModel()).insertRow(rs.getRow()-1,row);
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(ITagenda.class.getName()).log(Level.SEVERE, null, ex);
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAdmin = new javax.swing.JButton();
        btnDeconnexion = new javax.swing.JButton();
        txtWelcome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuParamAdmin = new javax.swing.JMenuItem();
        jMenuDeconnexion = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuChercherConflit = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ICI C LACCUEIL :DDDDDD");

        btnAdmin.setText("Paramètres administrateur");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        btnDeconnexion.setText("Deconnexion");
        btnDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeconnexionActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Début", "Fin", "Personnes", "Validé"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        jMenu1.setText("Fichier");

        jMenuParamAdmin.setText("Paramètres administrateur");
        jMenuParamAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuParamAdminActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuParamAdmin);

        jMenuDeconnexion.setText("Déconnexion");
        jMenuDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDeconnexionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuDeconnexion);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Test");

        jMenuChercherConflit.setText("Chercher confli réservation");
        jMenuChercherConflit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuChercherConflitActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuChercherConflit);

        jMenuItem1.setText("agenda visuel");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDeconnexion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdmin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(206, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeconnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        ITadmin itadmin = new ITadmin();
        itadmin.setVisible(true);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeconnexionActionPerformed
        ITconnexion itco = new ITconnexion();
        this.dispose();
        itco.setVisible(true);
    }//GEN-LAST:event_btnDeconnexionActionPerformed

    private void jMenuDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDeconnexionActionPerformed
        ITconnexion itco = new ITconnexion();
        this.dispose();
        itco.setVisible(true);
    }//GEN-LAST:event_jMenuDeconnexionActionPerformed

    private void jMenuParamAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuParamAdminActionPerformed
        ITadmin itadmin = new ITadmin();
        itadmin.setVisible(true);
    }//GEN-LAST:event_jMenuParamAdminActionPerformed

    private void jMenuChercherConflitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuChercherConflitActionPerformed
        chercherConfliResa chercherConflit = new chercherConfliResa();
        chercherConflit.setVisible(true);
    }//GEN-LAST:event_jMenuChercherConflitActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        testAgenda t = new testAgenda();
        t.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    //Méthodes
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnDeconnexion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuChercherConflit;
    private javax.swing.JMenuItem jMenuDeconnexion;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuParamAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAdmin() {return btnAdmin;}
    public JLabel getTxtWelcome() {return txtWelcome;}

}

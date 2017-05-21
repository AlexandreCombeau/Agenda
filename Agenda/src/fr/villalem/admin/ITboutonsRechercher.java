/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.admin;

import static gestionagenda.GestionAgenda.rq;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ITboutonsRechercher extends javax.swing.JFrame {

    /**
     * Creates new form ITboutonsRechercher
     */
    public ITboutonsRechercher() {
        initComponents();
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
        btnClient = new javax.swing.JButton();
        btnDevis = new javax.swing.JButton();
        btnFacture = new javax.swing.JButton();
        btnReservation = new javax.swing.JButton();
        btnQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Que voulez vous rechercher?");

        btnClient.setText("Client");
        btnClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientActionPerformed(evt);
            }
        });

        btnDevis.setText("Devis");
        btnDevis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevisActionPerformed(evt);
            }
        });

        btnFacture.setText("Facture");
        btnFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFactureActionPerformed(evt);
            }
        });

        btnReservation.setText("Reservation");
        btnReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservationActionPerformed(evt);
            }
        });

        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDevis, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnClient, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnQuitter))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel1)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClient, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDevis, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservationActionPerformed
        try {
            
            String[] clients = rq.getClients();
            String[] noms = rq.getnomsClients();
            String[] prenoms = rq.getprenomsClients();
            String client = "";
            client = (String)JOptionPane.showInputDialog(null, "Veuillez sélectionner un client","Rechercher un client",JOptionPane.QUESTION_MESSAGE, null,clients,clients[0]);
            int longueur = noms.length;
            String[] client2 = new String[longueur];
            String leNom = "";
            String lePrenom = "";
            for(int i = 0; i<longueur; i++){
                client2[i] = prenoms[i]+" "+noms[i];
                if(client2[i] == null ? client == null : client2[i].equals(client)){
                    leNom = noms[i];
                    lePrenom = prenoms[i];
                }
            }
            int idClient = rq.getIdClient(leNom, lePrenom);
            String[] dates = rq.getDatesReservations(idClient);
            String date = "";
            if(client!=null){
                if (!"".equals(dates[0])){
                    date = (String)JOptionPane.showInputDialog(null, "Veuillez sélectionner une date de début de réservation","Rechercher une date",JOptionPane.QUESTION_MESSAGE, null,dates,dates[0]);
                    int idReservation = rq.getIdReservation(idClient, date);
                    String[] lesInfos = rq.getInfosReservation(idReservation);
                    if (date!=null){
                        JOptionPane.showMessageDialog(null, lesInfos, "Informations sur la réservation",JOptionPane.INFORMATION_MESSAGE);
                    }
                } 
                else{
                    JOptionPane.showMessageDialog(null, "Il n'y a pas de reservation pour ce client", "information",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ITboutonsRechercher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReservationActionPerformed

    private void btnClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientActionPerformed
        
        try {
            String[] clients = rq.getClients();
            String[] noms = rq.getnomsClients();
            String[] prenoms = rq.getprenomsClients();
         
            String client = "";
            client = (String)JOptionPane.showInputDialog(null, "Veuillez sélectionner un client","Rechercher un client",JOptionPane.QUESTION_MESSAGE, null,clients,clients[0]);
            
            int longueur = noms.length;
            String[] client2 = new String[longueur];
            String leNom = "";
            String lePrenom = "";
            for(int i = 0; i<longueur; i++){
               client2[i] = prenoms[i]+" "+noms[i];
               if(client2[i] == null ? client == null : client2[i].equals(client)){
                   leNom = noms[i];
                   lePrenom = prenoms[i];
               }
            }
            if(client != null){
                int idClient = rq.getIdClient(leNom, lePrenom);
                String[] lesInfos = rq.getInfosClient(idClient);
                JOptionPane.showMessageDialog(null, lesInfos, "Informations sur le client",JOptionPane.INFORMATION_MESSAGE);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(ITboutonsRechercher.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_btnClientActionPerformed

    private void btnDevisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevisActionPerformed
        
    }//GEN-LAST:event_btnDevisActionPerformed

    private void btnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterActionPerformed
        
        this.dispose();
        
    }//GEN-LAST:event_btnQuitterActionPerformed

    private void btnFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFactureActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFactureActionPerformed

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
            java.util.logging.Logger.getLogger(ITboutonsRechercher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ITboutonsRechercher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ITboutonsRechercher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ITboutonsRechercher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ITboutonsRechercher().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClient;
    private javax.swing.JButton btnDevis;
    private javax.swing.JButton btnFacture;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JButton btnReservation;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

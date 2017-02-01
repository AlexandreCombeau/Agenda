/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.admin;

import static gestionagenda.GestionAgenda.rq;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Stagiaire
 */
public class ITmodif extends javax.swing.JFrame {

    /**
     * Creates new form ITmodif
     */
    public ITmodif() {
        initComponents();
    }
    
    /*
    ============================================================================
    /!\ MANQUE LES FONCTIONS POUR CHANGER LE COMMENTAIRE ET LA SUPERFICIE /!\
    ============================================================================
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btnNom = new javax.swing.JButton();
        btnCouleur = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnQuitter = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lbNomSalle = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("jCheckBox2");

        jCheckBox3.setText("jCheckBox3");

        jCheckBox4.setText("jCheckBox4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNom.setText("Changer le nom");
        btnNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNomActionPerformed(evt);
            }
        });

        btnCouleur.setText("Changer la couleur");
        btnCouleur.setBorderPainted(false);
        btnCouleur.setOpaque(true);
        btnCouleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCouleurActionPerformed(evt);
            }
        });

        jButton3.setText("Changer le commentaire");

        jButton4.setText("Changer la superficie");

        txtTitle.setFont(new java.awt.Font("Malayalam MN", 1, 18)); // NOI18N
        txtTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCouleur))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(lbNomSalle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNom)
                                    .addComponent(jButton3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1)
                            .addComponent(btnQuitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCouleur, btnNom, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCouleur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(lbNomSalle))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCouleurActionPerformed
        Color couleur = JColorChooser.showDialog(null, "Code couleur de la salle", btnCouleur.getBackground());
        //Transcription du code couleur RGB en HEX
        if(couleur == null){
        }
        else{
            String hexa = "#"+Integer.toHexString(couleur.getRGB()).substring(2);
            try {
                //Faire une insertion dans la BD du nouveau code couleur
                if(rq.MAJcodeCouleur("Salle", lbNomSalle.getText(), hexa)){
                    JOptionPane.showMessageDialog(null, "Nouvelle couleure pour la salle : "+lbNomSalle.getText());
                    btnCouleur.setBackground(couleur);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Cette couleur existe déjà pour une autre salle / tâche");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ITmodif.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCouleurActionPerformed

    private void btnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnQuitterActionPerformed

    private void btnNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNomActionPerformed
        String nouveauNom = "";
        boolean b = true;
        while(nouveauNom.equals("")){
            nouveauNom = (String)JOptionPane.showInputDialog(null, "Le nom actuel est : "+lbNomSalle.getText()+".\nQuel sera le nouveau nom ?", "Modification de la salle : "+lbNomSalle.getText(), JOptionPane.QUESTION_MESSAGE);
            System.out.println("LE NOUVEAU NOM EST : "+nouveauNom);
            if(nouveauNom == null){
                b = false;
                break;
            }
            else if(nouveauNom != null && !(nouveauNom.equals(""))){
                try {
                    if(rq.checkErreurModif("Salle", nouveauNom) == false){
                        JOptionPane.showMessageDialog(null, "Ce nom de salle existe déjà");
                        nouveauNom = "";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ITmodif.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(nouveauNom != null && nouveauNom.equals("")){
                JOptionPane.showMessageDialog(null, "Veuillez rentrer un nom");
            }
        }
        if(b = true){
            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau nom de salle sera : "+nouveauNom+".\nConfirmer ?", "Modification de la salle : "+lbNomSalle.getText(), JOptionPane.YES_NO_OPTION);
            System.out.println("ICIIIIIIII LE CHOIXX : "+choix); // OK = 0 // REFUSER = 1
            if(choix == 0){
                //UPDATE SUR LA BD
                rq.MAJnom("Salle", lbNomSalle.getText(), nouveauNom);
                this.setTxtTitle(nouveauNom);
                this.setLbNomSalle(nouveauNom);
            }
        }
    }//GEN-LAST:event_btnNomActionPerformed

    
    public JLabel getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txt) {
        this.txtTitle.setText(txt);
    }
    
    public JLabel getLbNomSalle() {
        return lbNomSalle;
    }

    public void setLbNomSalle(String txt) {
        this.lbNomSalle.setText(txt);
    }

    public JButton getBtnCouleur() {
        return btnCouleur;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCouleur;
    private javax.swing.JButton btnNom;
    private javax.swing.JButton btnQuitter;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbNomSalle;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
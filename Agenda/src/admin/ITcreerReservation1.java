/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static gestionagenda.GestionAgenda.rq;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JCalendar;
import javax.swing.JTextField;

/**
 *
 * @author Stagiaire
 */
public class ITcreerReservation1 extends javax.swing.JFrame {
	public Ioperation Io;
    /**
     * Creates new form ITgenerationDevis
     */
    public ITcreerReservation1(Ioperation Io) {
    	this.Io=Io;
    	
        initComponents();
        try {
            //On initialise les tableaux avec les données de la base de données.
            lesSalles = rq.getSalleTacheEntiteFormule("salle", "Salle");
            lesFormules = rq.getSalleTacheEntiteFormule("formule", "Formule");
            lesDispositions = rq.getSalleTacheEntiteFormule("disposition", "Disposition");
            lesOptions = rq.getOptionService("option");
            lesServices = rq.getOptionService("service");
            lesClients= rq.getClients();
            

            //On enlève tout les items associés aux combo box par défaut.
            cbDisposition.removeAllItems();
            cbFormule.removeAllItems();
            cbSalle1.removeAllItems();
            cbSalle2.removeAllItems();
            cbOption1.removeAllItems();
            cbOption2.removeAllItems();
            cbOption3.removeAllItems();
            cbOption4.removeAllItems();
            cbOption5.removeAllItems();
            cbOption6.removeAllItems();
            cbService1.removeAllItems();
            cbService2.removeAllItems();
            cbService3.removeAllItems();
            cbService4.removeAllItems();
            cbService5.removeAllItems();
            cbService6.removeAllItems();
            cbHeureDebut.removeAllItems();
            cbHeureFin.removeAllItems();
            
            //On ajoute l'item "Aucune".
            cbSalle2.addItem("Aucune");
            cbOption1.addItem("Aucune");
            cbOption2.addItem("Aucune");
            cbOption3.addItem("Aucune");
            cbOption4.addItem("Aucune");
            cbOption5.addItem("Aucune");
            cbOption6.addItem("Aucune");
            
            //On initialise les combo box avec les données récupérées plus haut.
            for (String heures : heures){
            	cbHeureDebut.addItem(heures);
            	cbHeureFin.addItem(heures);
            }
            
            for (String lesSalle : lesSalles) {
                cbSalle1.addItem(lesSalle);
                cbSalle2.addItem(lesSalle);
            }
            
            for (String lesDisposition : lesDispositions) {
                cbDisposition.addItem(lesDisposition);
            }
            for (String lesClient : lesClients) {
                cbClient.addItem(lesClient);
            }
            
            for (String lesFormule : lesFormules) {
                cbFormule.addItem(lesFormule);
            }
            
            for (String lesOption : lesOptions) {
                cbOption1.addItem(lesOption);
                cbOption2.addItem(lesOption);
                cbOption3.addItem(lesOption);
                cbOption4.addItem(lesOption);
                cbOption5.addItem(lesOption);
                cbOption6.addItem(lesOption);
            }
            
            for (String lesService : lesServices) {
                cbService1.addItem(lesService);
                cbService2.addItem(lesService);
                cbService3.addItem(lesService);
                cbService4.addItem(lesService);
                cbService5.addItem(lesService);
                cbService6.addItem(lesService);
            }
            
            //On le choisis par défaut pour ne pas faire apparaitre les données relatives à une deuxième salle qu'on ne souhaite pas avoir.
            cbOption1.setSelectedIndex(5);
            cbOption2.setSelectedIndex(6);
            cbOption3.setSelectedIndex(4);
            cbService1.setSelectedIndex(7);
            cbService2.setSelectedIndex(6);
            cbService3.setSelectedIndex(5);
            cbService4.setSelectedIndex(2);
            cbService5.setSelectedIndex(3);
            cbService6.setSelectedIndex(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(ITcreerReservation1.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        labelTitre = new javax.swing.JLabel();
        labelAttention = new javax.swing.JLabel();
        labelDisposition = new javax.swing.JLabel();
        cbDisposition = new javax.swing.JComboBox<>();
        labelFormule = new javax.swing.JLabel();
        cbFormule = new javax.swing.JComboBox<>();
        labelNbHeures = new javax.swing.JLabel();
        labelNbParticipants = new javax.swing.JLabel();
        txtNombreParticipants = new javax.swing.JTextField();
        labelSalle1 = new javax.swing.JLabel();
        cbSalle1 = new javax.swing.JComboBox<>();
        labelDateDebut = new javax.swing.JLabel();
        labelHoraire = new javax.swing.JLabel();
        labelA = new javax.swing.JLabel();
        labelSalle2 = new javax.swing.JLabel();
        btnEnregistrer = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();
        labelOptions = new javax.swing.JLabel();
        cbOption1 = new javax.swing.JComboBox<>();
        cbOption2 = new javax.swing.JComboBox<>();
        cbOption3 = new javax.swing.JComboBox<>();
        cbOption4 = new javax.swing.JComboBox<>();
        cbOption5 = new javax.swing.JComboBox<>();
        cbOption6 = new javax.swing.JComboBox<>();
        labelServices = new javax.swing.JLabel();
        cbService1 = new javax.swing.JComboBox<>();
        cbService2 = new javax.swing.JComboBox<>();
        cbService3 = new javax.swing.JComboBox<>();
        cbService4 = new javax.swing.JComboBox<>();
        cbService5 = new javax.swing.JComboBox<>();
        cbService6 = new javax.swing.JComboBox<>();
        cbHeureDebut = new javax.swing.JComboBox<>();
        cbHeureFin = new javax.swing.JComboBox<>();
        cbClient = new javax.swing.JComboBox();
        labelClient = new javax.swing.JLabel();
        labelDateFin = new javax.swing.JLabel();
        cbSalle2 = new javax.swing.JComboBox<>();
        calendarDebut = new JCalendar();
        calendarFin = new JCalendar();

        jLabel18.setText("Date du :");

        jLabel19.setText("jLabel19");

        jLabel25.setText("jLabel25");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitre.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitre.setText("                                             Création d'une nouvelle réservation");

        labelAttention.setText("Veuillez remplir tous les champs pour créer votre nouvelle réservation !");

        labelDisposition.setText("Disposition :");

        cbDisposition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbDisposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDispositionActionPerformed(evt);
            }
        });

        labelFormule.setText("Formule : ");

        cbFormule.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelNbHeures.setText("Nombre d'heures :");

        labelNbParticipants.setText("Nombre de participants :");

        labelSalle1.setText("Salle 1 :");

        cbSalle1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelDateDebut.setText("Date de debut: ");

        labelHoraire.setText("Horaire de : (HH:MM)");

        labelA.setText("à :");

        labelSalle2.setText("Salle 2 :");

        btnEnregistrer.setText("Enregistrer réservation");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        labelOptions.setText("Options :");

        cbOption1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption1ActionPerformed(evt);
            }
        });

        cbOption2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption2ActionPerformed(evt);
            }
        });

        cbOption3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption3ActionPerformed(evt);
            }
        });

        cbOption4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption4ActionPerformed(evt);
            }
        });

        cbOption5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption5ActionPerformed(evt);
            }
        });

        cbOption6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbOption6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOption6ActionPerformed(evt);
            }
        });

        labelServices.setText("Services :");

        cbService1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService1ActionPerformed(evt);
            }
        });

        cbService2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService2ActionPerformed(evt);
            }
        });

        cbService3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService3ActionPerformed(evt);
            }
        });

        cbService4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService4ActionPerformed(evt);
            }
        });

        cbService5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService5ActionPerformed(evt);
            }
        });

        cbService6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbService6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbService6ActionPerformed(evt);
            }
        });

        labelClient.setText("Client :");

        labelDateFin.setText("Date de fin : ");

        cbSalle2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        cbHeureDebut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        cbHeureFin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelDisposition)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbDisposition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelClient)
        					.addGap(18)
        					.addComponent(cbClient, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))))
        		.addComponent(labelTitre, GroupLayout.PREFERRED_SIZE, 1237, GroupLayout.PREFERRED_SIZE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(5)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelSalle2)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(cbSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addComponent(labelAttention)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(cbOption2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelOptions)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(cbOption1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addComponent(cbOption3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(cbOption4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(cbOption5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(cbOption6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addGap(53)
        					.addComponent(labelServices)
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(cbService1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(cbService3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(cbService2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(cbService4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(cbService5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(cbService6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(labelDateDebut)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(calendarDebut, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelSalle1)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(cbSalle1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(labelFormule)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(cbFormule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        								.addComponent(labelHoraire))))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(cbHeureDebut, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(labelA)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(cbHeureFin, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(labelNbHeures))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelNbParticipants)
        							.addGap(14)
        							.addComponent(txtNombreParticipants, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(labelDateFin)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(calendarFin, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))))))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(129)
        			.addComponent(btnEnregistrer, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addGap(84)
        			.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(labelTitre, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
        			.addGap(22)
        			.addComponent(labelAttention)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(labelClient)
        				.addComponent(cbClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(52)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(labelDisposition)
        				.addComponent(cbDisposition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(labelOptions)
        						.addComponent(cbOption1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(labelServices)
        						.addComponent(cbService1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbOption2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbOption3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbOption4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbOption5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbOption6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(cbService2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbService3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbService4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbService5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cbService6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(36)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(labelSalle1)
        				.addComponent(cbSalle1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelHoraire)
        				.addComponent(cbHeureDebut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelA)
        				.addComponent(cbHeureFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelNbHeures))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(labelFormule)
        				.addComponent(cbFormule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelNbParticipants)
        				.addComponent(txtNombreParticipants, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(labelDateDebut)
        						.addComponent(calendarDebut, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        					.addGap(8)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(labelSalle2)
        						.addComponent(cbSalle2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(labelDateFin)
        				.addComponent(calendarFin, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnEnregistrer, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
        			.addGap(116))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
       
        this.dispose();
        
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
       try {
    	    
            //SALLE 1
    	   if(!getTitle().equals("")){
    	    Io.recevoirId(Integer.parseInt(getTitle()));
    	   }
            String nomSalle1 = (String)cbSalle1.getSelectedItem();
            String formule = (String)cbFormule.getSelectedItem();
            String horaireDebut = (String)cbHeureDebut.getSelectedItem();
            String horaireFin = (String)cbHeureFin.getSelectedItem();
            String disposition = (String)cbDisposition.getSelectedItem();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String dateDebut = format1.format(calendarDebut.getDate());
            String dateFin = format1.format(calendarFin.getDate());
            System.out.println(dateDebut);
            System.out.println(dateFin);
            String nbParticipants = txtNombreParticipants.getText();
            
            if(comparerheures(horaireDebut, horaireFin)){
            	JOptionPane.showMessageDialog(null, "L'heure de d�but doit preceder l'heure de fin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(comparerdates(dateDebut,dateFin))
            	JOptionPane.showMessageDialog(null, "La date de d�but doit preceder la date de fin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            
            else{
            //CLIENT
            double nbHeures = Double.parseDouble(horaireFin.split(":")[0])-Double.parseDouble(horaireDebut.split(":")[0])+(Double.parseDouble(horaireFin.split(":")[1])-Double.parseDouble(horaireDebut.split(":")[1]))/60;
            String[] clients;
            clients = rq.getClients();
            String[] noms = rq.getElementByIdFromTable("client", "Client", "nom");
            String[] prenoms = rq.getElementByIdFromTable("client", "Client", "prenom");
            String client = (String)cbClient.getSelectedItem();
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
            int idFormule = rq.getIdFormule(formule);
            
            Io.operationResa(dateDebut,dateFin,horaireDebut+":00",horaireFin+":00",Integer.parseInt(nbParticipants),nbHeures, idClient, idFormule);
            
            int idReservation = rq.getIdReservation(idClient, dateDebut);
            int idsalle = rq.getIdSalle(nomSalle1);
            int iddispo = rq.getIdDisposition(disposition);
            int idinfosalle= rq.getIdInfoSalle(idsalle,iddispo);
            Io.operationSalleResa(idReservation, idinfosalle);
            List<Integer>listeOS= new ArrayList<>();
            //LES OPTIONS
            String o1 = (String)cbOption1.getSelectedItem();
            String o2 = (String)cbOption2.getSelectedItem();
            String o3 = (String)cbOption3.getSelectedItem();
            String o4 = (String)cbOption4.getSelectedItem();
            String o5 = (String)cbOption5.getSelectedItem();
            String o6 = (String)cbOption6.getSelectedItem();
            if (!"Aucune".equals(o1)){
            	listeOS.add(rq.getIdOptionService(o1)); 
                
            }
            if (!"Aucune".equals(o2)){
            	listeOS.add(rq.getIdOptionService(o2)); 
            	
            }
            if (!"Aucune".equals(o3)){
            	listeOS.add(rq.getIdOptionService(o3)); 
            	
            }
            if (!"Aucune".equals(o4)){
            	listeOS.add(rq.getIdOptionService(o4)); 
            	
            }
            if (!"Aucune".equals(o5)){
            	listeOS.add(rq.getIdOptionService(o5)); 
            	
            }
            if (!"Aucune".equals(o6)){
            	listeOS.add(rq.getIdOptionService(o6)); 
            	
            }
            

            //LES SERVICES
            String s1 = (String)cbService1.getSelectedItem();
            String s2 = (String)cbService2.getSelectedItem();
            String s3 = (String)cbService3.getSelectedItem();
            String s4 = (String)cbService4.getSelectedItem();
            String s5 = (String)cbService5.getSelectedItem();
            String s6 = (String)cbService6.getSelectedItem();
            
            if (!"Aucune".equals(s1)){
            	listeOS.add(rq.getIdOptionService(s1)); 
            	
            }
            if (!"Aucune".equals(s2)){
            	listeOS.add(rq.getIdOptionService(s2)); 
            	
            }
            if (!"Aucune".equals(s3)){
            	listeOS.add(rq.getIdOptionService(s3)); 
            	
            }
            if (!"Aucune".equals(s4)){
            	listeOS.add(rq.getIdOptionService(s4)); 
            	
            }
            if (!"Aucune".equals(s5)){
            	listeOS.add(rq.getIdOptionService(s5)); 
            	
            }
            if (!"Aucune".equals(s6)){
            	listeOS.add(rq.getIdOptionService(s6)); 
            	
            }
            Io.operationChoix(idReservation, listeOS);
            //SI IL EXISTE UNE DEUXIEME SALLE
            String existe = (String)cbSalle2.getSelectedItem();
            if(!(existe.equals("Aucune"))){
               String nomSalle2 = (String)cbSalle2.getSelectedItem();
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ITcreerReservation1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void cbOption1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption1ActionPerformed

    }//GEN-LAST:event_cbOption1ActionPerformed

    private void cbOption2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption2ActionPerformed

    }//GEN-LAST:event_cbOption2ActionPerformed

    private void cbOption3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption3ActionPerformed

    }//GEN-LAST:event_cbOption3ActionPerformed

    private void cbOption4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption4ActionPerformed

    }//GEN-LAST:event_cbOption4ActionPerformed

    private void cbOption5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption5ActionPerformed

    }//GEN-LAST:event_cbOption5ActionPerformed

    private void cbOption6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOption6ActionPerformed

    }//GEN-LAST:event_cbOption6ActionPerformed

    private void cbService1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService1ActionPerformed

    }//GEN-LAST:event_cbService1ActionPerformed

    private void cbService2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService2ActionPerformed

    }//GEN-LAST:event_cbService2ActionPerformed

    private void cbService3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService3ActionPerformed

    }//GEN-LAST:event_cbService3ActionPerformed

    private void cbService4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService4ActionPerformed

    }//GEN-LAST:event_cbService4ActionPerformed

    private void cbService5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService5ActionPerformed

    }//GEN-LAST:event_cbService5ActionPerformed

    private void cbService6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbService6ActionPerformed

    }//GEN-LAST:event_cbService6ActionPerformed

    private void cbDispositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDispositionActionPerformed

    }//GEN-LAST:event_cbDispositionActionPerformed
    
    
    public Boolean comparerheures (String horaireDebut, String horaireFin){
    	String heureDebut=horaireDebut.split(":")[0];
    	String heureFin=horaireFin.split(":")[0];
    	String minDebut=horaireDebut.split(":")[1];
    	String minFin=horaireFin.split(":")[1];
    	if(Integer.parseInt(heureDebut) > Integer.parseInt(heureFin)){
    		 return true;
    	}
    	if(Integer.parseInt(heureDebut) > Integer.parseInt(heureFin) && Integer.parseInt(minDebut) > Integer.parseInt(minFin)){
    		 return true;
    	}
    	return false;
    }
    
    public Boolean comparerdates (String dateDebut, String dateFin){
    	String jourDebut = dateDebut.split("-")[0];
    	String jourFin = dateFin.split("-")[0];
    	String moisDebut = dateDebut.split("-")[1];
    	String moisFin = dateFin.split("-")[1];
    	String anDebut = dateDebut.split("-")[2];
    	String anFin = dateFin.split("-")[2];
    	if(Integer.parseInt(anFin)<Integer.parseInt(anDebut)){
    		return true;
    	}
    	if(Integer.parseInt(moisFin)<Integer.parseInt(moisDebut) && Integer.parseInt(anFin)==Integer.parseInt(anDebut)){
    		return true;
    	}
    	if(Integer.parseInt(moisFin)==Integer.parseInt(moisDebut) && Integer.parseInt(anFin)==Integer.parseInt(anDebut) && Integer.parseInt(jourFin)<Integer.parseInt(jourDebut)){
    		return true;
    	}
    	return false;
    }
    
    public void setClient (String nom){
    	for(int i=0; i<cbClient.getItemCount();++i){
    		
    		if(cbClient.getItemAt(i).toString().equals(nom)){
    			cbClient.setSelectedIndex(i);
    		}
    	}
    }
    
    public void setSalle (String nom){
    	for(int i=0; i<cbSalle1.getItemCount();++i){
    		
    		if(cbSalle1.getItemAt(i).toString().equals(nom)){
    			cbSalle1.setSelectedIndex(i);
    		}
    	}
    }
    
    public void setDisposition (String nom){
    	for(int i=0; i<cbDisposition.getItemCount();++i){
    		
    		if(cbDisposition.getItemAt(i).toString().equals(nom)){
    			cbDisposition.setSelectedIndex(i);
    		}
    	}
    }
    
    public void setFormule (String nom){
    	for(int i=0; i<cbFormule.getItemCount();++i){
    		
    		if(cbFormule.getItemAt(i).toString().equals(nom)){
    			cbFormule.setSelectedIndex(i);
    		}
    	}
    }
    
    public void setHeureFin (String heure){
    	cbHeureFin.setSelectedItem(heure);
    }
    
    public void setHeureDebut (String heure){
    	cbHeureDebut.setSelectedItem(heure);
    }
    
    public void setNbPersonne (int nb){
    	txtNombreParticipants.setText(Integer.toString(nb));
    }
    
    public void setDateDebut (String dateStr){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	Date date;
		try {
			date = formatter.parse(dateStr);
			calendarDebut.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setDateFin (String dateStr){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	Date date;
		try {
			date = formatter.parse(dateStr);
			calendarFin.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setOptions(String[] options){
    	
    	JComboBox[] Optn = new JComboBox[6];
    	Optn[0]=cbOption1;Optn[1]=cbOption2;Optn[2]=cbOption3;Optn[3]=cbOption4;Optn[4]=cbOption5;Optn[5]=cbOption6;
    	for (int i=0;i<options.length;++i){
    		
    		Optn[i].setSelectedItem(options[i]);
    	}	
    }
    
    public void setServices(String[] services){
    	JComboBox[] Srv = new JComboBox[6];
    	Srv[0]=cbService1;Srv[1]=cbService2;Srv[2]=cbService3;Srv[3]=cbService4;Srv[4]=cbService5;Srv[5]=cbService6;
    	for (int i=0;i<services.length;++i){
    		Srv[i].setSelectedItem(services[i]);
    	}	
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JComboBox<String> cbDisposition;
    private javax.swing.JComboBox<String> cbFormule;
    private javax.swing.JComboBox<String> cbOption1;
    private javax.swing.JComboBox<String> cbOption2;
    private javax.swing.JComboBox<String> cbOption3;
    private javax.swing.JComboBox<String> cbOption4;
    private javax.swing.JComboBox<String> cbOption5;
    private javax.swing.JComboBox<String> cbOption6;
    private javax.swing.JComboBox<String> cbSalle1;
    private javax.swing.JComboBox<String> cbSalle2;
    private javax.swing.JComboBox<String> cbService1;
    private javax.swing.JComboBox<String> cbService2;
    private javax.swing.JComboBox<String> cbService3;
    private javax.swing.JComboBox<String> cbService4;
    private javax.swing.JComboBox<String> cbService5;
    private javax.swing.JComboBox<String> cbService6;
    private javax.swing.JComboBox<String> cbHeureDebut;
    private javax.swing.JComboBox<String> cbHeureFin;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelAttention;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelDateDebut;
    private javax.swing.JLabel labelDateFin;
    private javax.swing.JLabel labelDisposition;
    private javax.swing.JLabel labelFormule;
    private javax.swing.JLabel labelHoraire;
    private javax.swing.JLabel labelNbHeures;
    private javax.swing.JLabel labelNbParticipants;
    private javax.swing.JLabel labelOptions;
    private javax.swing.JLabel labelSalle1;
    private javax.swing.JLabel labelSalle2;
    private javax.swing.JLabel labelServices;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JComboBox cbClient;
    private javax.swing.JTextField txtNombreParticipants;
    private JCalendar calendarDebut;
    private JCalendar calendarFin;
    // End of variables declaration//GEN-END:variables
    private String[] lesSalles = null;
    private String[] lesFormules = null;
    private String[] lesDispositions = null;
    private String[] lesOptions = null;
    private String[] lesServices = null;
    private String[] lesClients = null;
    private String[] heures= {"09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00"};
}
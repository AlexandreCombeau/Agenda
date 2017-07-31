/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static gestionagenda.GestionAgenda.rq;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import database.operationAjout;
import database.operationModif;

import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 *
 * @author Stagiaire
 */
public abstract class AbstractPlanning extends javax.swing.JFrame {
	
	int id;
	int currentSalle;
	int currentDate;
	int nbDates=1;
    /**
     * Creates new form ITgenerationDevis
     */
	Igeneration Ig;
	
    public AbstractPlanning() {
        initComponents();
        try {
            //On initialise les tableaux avec les données de la base de données.
            lesSalles = rq.getSalleTacheEntiteFormule("salle", "Salle");
            lesFormules = rq.getSalleTacheEntiteFormule("formule", "Formule");
            lesDispositions = rq.getSalleTacheEntiteFormule("disposition", "Disposition");
            lesOptions = rq.getOptionService("option");
            lesServices = rq.getOptionService("service");
            lesClients= rq.getClients();
            currentSalle=0;
            String[] jean= new String[lesSalles.length-1];
            int la=0;
            for(int i=0; i<lesSalles.length;++i){
            	if(!lesSalles[i].equals("privatisation totale")){
            		jean[la]=lesSalles[i];
            		la+=1;
            	}
            }
            lesSalles=jean;
            //On enlève tout les items associés aux combo box par défaut.
            cbDisposition.removeAllItems();
            cbFormule.removeAllItems();
            cbSalle1.removeAllItems();
            cbDate.removeAllItems();
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
            
            
            cbNoSalle.addItem("salle 1");
            cbNoSalle.addItem("salle 2");
            cbNoSalle.addItem("salle 3");
            cbNoSalle.addItem("privatisation");
            //On ajoute l'item "Aucune".
            cbSalle1.addItem("Aucune");
            //cbDate.addItem("Aucune");
            cbOption1.addItem("Aucune");
            cbOption2.addItem("Aucune");
            cbOption3.addItem("Aucune");
            cbOption4.addItem("Aucune");
            cbOption5.addItem("Aucune");
            cbOption6.addItem("Aucune");
            cbService1.addItem("Aucune");
            cbService2.addItem("Aucune");
            cbService3.addItem("Aucune");
            cbService4.addItem("Aucune");
            cbService5.addItem("Aucune");
            cbService6.addItem("Aucune");
            for(int i=0;i<3;++i){salles[0][i][0]="Aucune";}
            for(int i=0;i<3;++i){for(int j=0;j<7;++j){for(int k=0;k<365;++k){salles[j][i][k]="Aucune";}}}
            for(int i=0;i<3;++i){for(int j=0;j<12;++j){for(int k=0;k<365;++k){OSTab[j][i][k]="Aucune";}}}
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            cbDate.addItem(formatter.format(new Date()));
            //On initialise les combo box avec les données récupérées plus haut.
            for (String heures : heures){
            	cbHeureDebut.addItem(heures);
            	cbHeureFin.addItem(heures);
            }
            
            for (String lesSalle : lesSalles) {
                cbSalle1.addItem(lesSalle);
                //cbDate.addItem(lesSalle);
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
            
           
            
            calendarFin.addPropertyChangeListener(new PropertyChangeListener() {
            	public void propertyChange(PropertyChangeEvent e) {
            		CalendarChange(e);
            	}
            });
            
            calendarDebut.addPropertyChangeListener(new PropertyChangeListener() {
            	public void propertyChange(PropertyChangeEvent e) {
            		CalendarChange(e);
            	}
            });
            
            cbNoSalle.addItemListener(new ItemListener() {
            	public void itemStateChanged(ItemEvent e) {
            		try {
						SalleChange(e);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            });
            
            cbDate.addItemListener(new ItemListener() {
            	public void itemStateChanged(ItemEvent e) {
            		try {
						DateChange(e);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            });
            currentDate=0;
            currentSalle=0;
            SalleChange();
            currentSalle=1;
            SalleChange();
            currentSalle=2;
            SalleChange();
            currentSalle=0;
        } catch (SQLException ex) {
            //Logger.getLogger(ITcreerReservation1.class.getName()).log(Level.SEVERE, null, ex);
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
        labelDate = new javax.swing.JLabel();
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
        cbDate = new javax.swing.JComboBox<>();
        cbNoSalle = new javax.swing.JComboBox();
        cbNoSalle.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		btnNoSalleActionPerformed (arg0);
        	}
        });
        
        calendarDebut = new JCalendar();
        
        calendarFin = new JCalendar();
        
        btnNouveauClient = new JButton();

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

        labelSalle1.setText("Salle");

        cbSalle1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelDateDebut.setText("Date de debut: ");

        labelHoraire.setText("Horaire de : (HH:MM)");

        labelA.setText("à :");

        labelDate.setText("Date");

        btnEnregistrer.setText("Enregistrer réservation");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnEnregistrerActionPerformed(evt);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

        cbDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        cbHeureDebut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        cbHeureFin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        btnNouveauClient.setText("Nouveau Client");
        btnNouveauClient.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		btnNouveauClientActionPerformed(evt);
        	}
        });
        
        
        
        
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(labelClient)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(cbNoSalle, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        						.addComponent(labelDisposition)
        						.addPreferredGap(ComponentPlacement.RELATED)
        						.addComponent(cbDisposition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(cbClient, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
        		.addComponent(labelTitre, GroupLayout.PREFERRED_SIZE, 1237, GroupLayout.PREFERRED_SIZE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(5)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(labelDate)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(cbDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        							.addComponent(cbService6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addComponent(btnNouveauClient)))
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
        				.addComponent(cbClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNouveauClient))
        			.addGap(12)
        			.addComponent(cbNoSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
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
        						.addComponent(labelDate)
        						.addComponent(cbDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
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
    
    private void btnNouveauClientActionPerformed(ActionEvent evt) {
		ITajoutClient ajout = new ITajoutClient(new operationAjout());
		ajout.setVisible(true);
	}
    
    private void privat(){
    	currentSalle=0;
    	cbNoSalle.setSelectedItem("privatisation");
		cbSalle1.addItem("privatisation totale");
		cbSalle1.setSelectedItem("privatisation totale");
		cbDisposition.setSelectedItem("salle_vide");
		cbDisposition.setEnabled(false);
		cbDate.setEnabled(false);
		cbSalle1.setEnabled(false);
		System.out.println("CA MARCHE YOUPI");
    }
    
    private void btnNoSalleActionPerformed(java.awt.event.ActionEvent evt) {
    	if(cbNoSalle.getSelectedItem()== "privatisation"){
    		privat();
    	}
    	else{
    		cbSalle1.removeItem("privatisation totale");
    		cbDisposition.setEnabled(true);
    		cbDate.setEnabled(true);
    		cbSalle1.setEnabled(true);
    		System.out.println("CA MARCHE PLUS");
    	}
    }
    
    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) throws NumberFormatException, FileNotFoundException, IOException {//GEN-FIRST:event_btnEnregistrerActionPerformed
       try {
    	    
            //SALLE 1
    	   
           
           if(cbNoSalle.getSelectedIndex()==3){
        	   //int cs=0;
        	   System.out.println("JEAN LASSALE "+salles[0][0][currentDate]);
               //cbNoSalle.setSelectedIndex(cs);
               SalleChange();
        	   
        	   String nomSalle = (String)cbSalle1.getSelectedItem();
           		String disposition = (String)cbDisposition.getSelectedItem();
           		String nbParticipants = txtNombreParticipants.getText();
           		int idsalle = rq.getIdByName("salle", "idSalle", nomSalle, "libelle");
           		salles[0][0][currentDate]=nomSalle;
           		salles[1][0][currentDate]=disposition;
           		salles[2][0][currentDate]=nbParticipants;
           		salles[3][0][currentDate]=rq.getStrById("salle", "idSalle", "descriptif", idsalle);
           		salles[4][0][currentDate]=(String)cbHeureDebut.getSelectedItem();
            	salles[5][0][currentDate]=(String)cbHeureFin.getSelectedItem();
            	salles[6][0][currentDate]=(String)cbFormule.getSelectedItem();
           		
        	   for(int i=0;i<nbDates;++i){
        		   salles[0][0][i]=salles[0][0][currentDate];
        		   salles[1][0][i]=salles[1][0][currentDate];
        		   salles[2][0][i]=salles[2][0][currentDate];
        		   salles[3][0][i]=salles[3][0][currentDate];
        		   salles[4][0][i]=salles[4][0][currentDate];
        		   salles[5][0][i]=salles[5][0][currentDate];
        		   salles[6][0][i]=salles[6][0][currentDate];
        		   for(int j=1;j<3;++j){
        			   salles[0][j][i]="Aucune";
            		   salles[1][j][i]="Aucune";
            		   salles[2][j][i]="Aucune";
            		   salles[3][j][i]="Aucune";
            		   salles[4][j][i]="Aucune";
            		   salles[5][j][i]="Aucune";
            		   salles[6][j][i]="Aucune";
        		   }
        		   for(int j=0;j<12;++j){
        			   OSTab[j][0][i]=OSTab[j][0][currentDate];
        			   for(int k=1;k<3;++k){
        				   OSTab[j][k][i]="Aucune";
        			   }
        		   }
        		   
        	   }
           }
           else{
        	   int cs=currentSalle;
        	   System.out.println(salles[0][0][currentDate]);
               cbNoSalle.setSelectedIndex(cs);
               SalleChange();
               for(int i=0;i<3;++i){
            	   for(int j=0;j<nbDates;++j){
            		   if(salles[0][i][j].equals("Aucune")){
            			   salles[0][i][j]="Aucune";
            			   salles[1][i][j]="Aucune";
            			   salles[2][i][j]="Aucune";
            			   salles[3][i][j]="Aucune";
            		   };
            	   }
               }
           }
           //int cs=currentSalle;
           //cbNoSalle.setSelectedIndex(0);
           //SalleChange();
           //int cs=currentSalle;
           //cbNoSalle.setSelectedIndex(1);
           //SalleChange();
           //int cs=currentSalle;
           //cbNoSalle.setSelectedIndex(2);
           //SalleChange();
           //cbNoSalle.setSelectedIndex(cs);
           //SalleChange();
           
           //currentSalle=cs;
           System.out.println("BORDEL DE PUTAIN DE SALOPERIE DE MERDE");
           for(int i=0; i<12;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!OSTab[i][j][k].equals("Aucune"))System.out.println(OSTab[i][j][k]);
			
           
            String nomSalle1 = (String)cbSalle1.getSelectedItem();
            String formule = (String)cbFormule.getSelectedItem();
            String horaireD = (String)cbHeureDebut.getSelectedItem();
            String horaireF = (String)cbHeureFin.getSelectedItem();
            String disposition = (String)cbDisposition.getSelectedItem();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String[] date = new String[nbDates];
            String Debut=format1.format(calendarDebut.getDate());
            String Fin = format1.format(calendarFin.getDate());
            
            String Current=Debut;
            for(int i=0;i<nbDates;++i){
            	System.out.println(Current);
            	date[i]=Current;
            	GregorianCalendar dat = new GregorianCalendar();
            	dat.setTime(format1.parse(Current));
            	dat.add(Calendar.DAY_OF_YEAR, 1);
            	Current=format1.format(dat.getTime());
            	System.out.println(date[i]);
            }
            
            System.out.println(Debut);
            System.out.println(Fin);
            System.out.println(date[nbDates-1]);
            String nbParticipants = txtNombreParticipants.getText();
            
            int idFormule = rq.getIdByName("formule", "idFormule", formule, "libelle");
            
            //int iddispo = rq.getIdByName("disposition", "idDisposition", disposition, "libelle");
            int[][] idinfosalle = new int [3][nbDates];
            for(int i=0;i<3;++i){
            	for(int k=0; k<nbDates;++k){
            		if(!salles[0][i].equals("Aucune")){
            			int idsalle = rq.getIdByName("salle", "idSalle", salles[0][i][k], "libelle");
            			int iddispo = rq.getIdByName("disposition", "idDisposition", salles[1][i][k], "libelle");
            			idinfosalle[i][k]=rq.getIdInfoSalle(idsalle,iddispo);
            		}
            		else{
            			idinfosalle[i][k]=0;
            		}
            	}
            }
            
            if("".equals(nbParticipants)){
                nbParticipants="0";
            }
            if(comparerheures(horaireD, horaireF, Debut, Fin)){
            	JOptionPane.showMessageDialog(null, "L'heure de debut doit preceder l'heure de fin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(comparerdates(Debut,Fin)){
            	JOptionPane.showMessageDialog(null, "La date de debut doit preceder la date de fin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }
            //else if(Integer.parseInt(nbParticipants)>rq.getCapacite(idinfosalle[0])){
            	//JOptionPane.showMessageDialog(null, "La salle dans cette disposition est de capacite insuffisante", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            //}
            else{
            //CLIENT
            //double nbHeures = Double.parseDouble(horaireFin.split(":")[0])-Double.parseDouble(horaireDebut.split(":")[0])+(Double.parseDouble(horaireFin.split(":")[1])-Double.parseDouble(horaireDebut.split(":")[1]))/60;
            //final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
            
            //double nbHeures = (calendarFin.getDate().getTime()-calendarDebut.getDate().getTime())/(1000*60*60*24)*14+Double.parseDouble(horaireFin.split(":")[0])-Double.parseDouble(horaireDebut.split(":")[0])+(Double.parseDouble(horaireFin.split(":")[1])-Double.parseDouble(horaireDebut.split(":")[1]))/60;
            
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
            
            int resadispo=0;
            int current=1;
            for (int i=0;i<3;++i){
            	for(int k=0; k<nbDates;++k){
            		if(!salles[0][i].equals("Aucune")){
            			int idsalle = rq.getIdByName("salle", "idSalle", salles[0][i][k], "libelle");
            			resadispo=rq.checkResa(idsalle , Debut, Fin, horaireD, horaireF);
            			if(resadispo==0){
            				current=resadispo;
            			}
            		}
            	}
            }
            if(current==0){
            	resadispo=0;
            }
            //resadispo=rq.checkResa(idsalle,dateDebut, dateFin, horaireDebut, horaireFin);
            
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
            
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            
            int[][] nbPersonnes = new int[3][nbDates];
            double[][] nbHeures=new double[3][nbDates];
            int[][]formules=new int[3][nbDates];
            String[][] horaireDebut = new String[3][nbDates];
            String[][] horaireFin = new String[3][nbDates];
            for(int i=0;i<3;++i){
            	for(int k=0; k<nbDates;++k){
            		System.out.println(i+" "+k+" "+nbDates);
            		if(!salles[0][i][k].equals("Aucune")){
            			horaireDebut[i][k]=salles[4][i][k];
            			horaireFin[i][k]=salles[5][i][k];
            			nbPersonnes[i][k]=Integer.parseInt(salles[2][i][k]);
            			nbHeures[i][k]=(sdf.parse(horaireFin[i][k]).getTime()-sdf.parse(horaireDebut[i][k]).getTime())/(1000*60*60*24)*14+Double.parseDouble(horaireFin[i][k].split(":")[0])-Double.parseDouble(horaireDebut[i][k].split(":")[0])+(Double.parseDouble(horaireFin[i][k].split(":")[1])-Double.parseDouble(horaireDebut[i][k].split(":")[1]))/60;
            			formules[i][k]=rq.getIdByName("formule", "idFormule", salles[6][i][k], "libelle");
            			//System.out.println("nb personnes "+nbPersonnes[i][k]);
            		}
            		else{
            			nbPersonnes[i][k]=0;
            		}
            	}
            }
            //MAJ BDD
            
            
            
        	validation(resadispo, date, horaireDebut, horaireFin, nbParticipants, nbHeures, idClient, formules, idinfosalle, OSTab, nbPersonnes);
        	
        	//GENERATION 
        	String adresse=rq.getStrById("client", "idClient", "adresseFacturation", idClient);
        	String [] clientInfo= new String[7];
        	clientInfo[0]=adresse.split(",")[0];
        	clientInfo[1]=adresse.split(",")[1];
        	clientInfo[2]=adresse.split(",")[1].split(" ")[1];
        	clientInfo[5]=client;
        	clientInfo[3]=rq.getStrById("client", "idClient", "eMail", idClient);
        	clientInfo[4]=rq.getStrById("client", "idClient", "telephone", idClient);
        	clientInfo[6]=Integer.toString(idClient);
        	
        	//String commentSalle= rq.getStrById("salle", "idSalle", "descriptif", idsalle);
        	//String[] equipements = {o1, o2, o3, o4, o5, o6, disposition};
            //String[] services = {s1,  s2,   s3,   s4,   s5,   s6};
            String []services = trierTab(FusionServices());
            String []equipements = trierTab(FusionOptions());
            
            String commentS1 = rq.getCommentService(services[0]);
            String commentS2 = rq.getCommentService(services[1]);
           	String commentS3 = rq.getCommentService(services[2]);
           	String commentS4 = rq.getCommentService(services[3]);
           	String commentS5 = rq.getCommentService(services[4]);
           	String commentS6 = rq.getCommentService(services[5]);
            
            String[] commentairesServices = {commentS1, commentS2, commentS3, commentS4, commentS5, commentS6};
            //String[] salle1 = {nomSalle1, formule, Double.toString(nbHeures), nbParticipants, dateDebut, horaireDebut, horaireFin, commentSalle, dateFin};
            String[] infos = {formule, Double.toString(nbHeures[0][0]), nbParticipants, date[0], horaireD, horaireF, date[nbDates-1]};
            String[] salle2 = {(String)cbDate.getSelectedItem(), "", "", "", "", "", "", "", ""};
            if(Ig!=null){
            	Ig.generer(clientInfo, salles, equipements, services, commentairesServices, OSTab);
            }
        	
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ITcreerReservation1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    
    
    public String[] trierTab(String [][] Tab){
    	String [] Cleaned = new String [12];
    	int i=0;
    		for (int j=0;j<6;++j){
    			for(int k=0; k<nbDates; ++k){
    			if(!Tab[j][k].equals("Aucune")){
    					Cleaned[i]=Tab[j][k];
    					Cleaned[i+6]=Tab[j+6][k];
    					i=i+1;
    				}
    			}
    		}
    	for(int j=i;j<6;++j){
    		Cleaned[j]="Aucune";
    		Cleaned[j+6]="0";
    	}
    	return Cleaned;
    }
    
    public String[][] FusionServices(){
    	String[][] Services=new String[12][nbDates];
    	for(int n=0; n<nbDates; ++n){
    		for(int i=0;i<6;++i){
    			Services[i][n]="Aucune";
    		}
    		for(int i=6;i<12;++i){
    			Services[i][n]="0";
    		}
    		int a=0;
    	
    		for(int j=0;j<3;++j){
    			for(int k=6;k<12;++k){
    				int m=0;
    				for (int l=0;l<6;++l){
    					if (OSTab[k][j].equals(Services[l][n])&&!(Services[l+6][n]).equals("") && !(salles[2][j][n]).equals("")){
    						Services[l+6][n]=Integer.toString(Integer.parseInt(Services[l+6][n])+Integer.parseInt(salles[2][j][n]));
    						m=1;
    					}
    				}
    				if(m==0 && a<6){
    					
    					Services[a][n]=OSTab[k][j][n];
    					Services[a+6][n]=salles[2][j][n];
    					//System.out.println(Services[a][n]);
    					a+=1;
    				}
    			}
    		}
    	}
    	return Services;
    }
    
    
    public String[][] FusionOptions(){
        String[][] Options=new String[12][nbDates];
        for(int n=0; n<nbDates; ++n){
        	for(int i=0;i<6;++i){
        		Options[i][n]="Aucune";
        	}
        	for(int i=6;i<12;++i){
        		Options[i][n]="0";
        	}
        	int a=0;
        	
        	for(int j=0;j<3;++j){
        		for(int k=6;k<12;++k){
        			int m=0;
        			for (int l=0;l<6;++l){
        				if (OSTab[k][j].equals(Options[l][n])&&!(Options[l+6][n]).equals("") && !(salles[2][j][n]).equals("")){
        					Options[l+6][n]=Integer.toString(Integer.parseInt(Options[l+6][n])+Integer.parseInt(salles[2][j][n]));
        					m=1;
        				}
        			}
        			if(m==0 && a<6){
        				
        				Options[a][n]=OSTab[k][j][n];
        				Options[a+6][n]=salles[2][j][n];
        				//System.out.println(Options[a][n]);
        				a+=1;
        			}
        		}
       		}
       	}
        return Options;
    }
    
    public void DateChange(ItemEvent e) throws SQLException{
    	DateChange();
    }
    
    public void DateChange() throws SQLException{
    	String nomSalle = (String)cbSalle1.getSelectedItem();
    	String disposition = (String)cbDisposition.getSelectedItem();
    	String nbParticipants = txtNombreParticipants.getText();
    	int idsalle = rq.getIdByName("salle", "idSalle", nomSalle, "libelle");
    	salles[0][currentSalle][currentDate]=nomSalle;
    	salles[1][currentSalle][currentDate]=disposition;
    	salles[2][currentSalle][currentDate]=nbParticipants;
    	salles[3][currentSalle][currentDate]=rq.getStrById("salle", "idSalle", "descriptif", idsalle);
    	salles[4][currentSalle][currentDate]=(String)cbHeureDebut.getSelectedItem();
    	salles[5][currentSalle][currentDate]=(String)cbHeureFin.getSelectedItem();
    	salles[6][currentSalle][currentDate]=(String)cbFormule.getSelectedItem();
    	
    	String o1 = (String)cbOption1.getSelectedItem();
        String o2 = (String)cbOption2.getSelectedItem();
        String o3 = (String)cbOption3.getSelectedItem();
        String o4 = (String)cbOption4.getSelectedItem();
        String o5 = (String)cbOption5.getSelectedItem();
        String o6 = (String)cbOption6.getSelectedItem();
        
        String s1 = (String)cbService1.getSelectedItem();
        String s2 = (String)cbService2.getSelectedItem();
        String s3 = (String)cbService3.getSelectedItem();
        String s4 = (String)cbService4.getSelectedItem();
        String s5 = (String)cbService5.getSelectedItem();
        String s6 = (String)cbService6.getSelectedItem();
        
        OSTab[0][currentSalle][currentDate]=o1;
        OSTab[1][currentSalle][currentDate]=o2;
        OSTab[2][currentSalle][currentDate]=o3;
        OSTab[3][currentSalle][currentDate]=o4;
        OSTab[4][currentSalle][currentDate]=o5;
        OSTab[5][currentSalle][currentDate]=o6;
        OSTab[6][currentSalle][currentDate]=s1;
        OSTab[7][currentSalle][currentDate]=s2;
        OSTab[8][currentSalle][currentDate]=s3;
        OSTab[9][currentSalle][currentDate]=s4;
        OSTab[10][currentSalle][currentDate]=s5;
        OSTab[11][currentSalle][currentDate]=s6;
        //currentDate=0;
        //currentSalle=0;
        if(cbDate.getSelectedIndex()!=-1){
        	currentDate=cbDate.getSelectedIndex();
        	
        	System.out.println(currentSalle+" "+currentDate+" "+cbDate.getSelectedIndex());
        	cbOption1.setSelectedItem(OSTab[0][currentSalle][currentDate]);
        	cbOption2.setSelectedItem(OSTab[1][currentSalle][currentDate]);
        	cbOption3.setSelectedItem(OSTab[2][currentSalle][currentDate]);
        	cbOption4.setSelectedItem(OSTab[3][currentSalle][currentDate]);
        	cbOption5.setSelectedItem(OSTab[4][currentSalle][currentDate]);
        	cbOption6.setSelectedItem(OSTab[5][currentSalle][currentDate]);
        	cbService1.setSelectedItem(OSTab[6][currentSalle][currentDate]);
        	cbService2.setSelectedItem(OSTab[7][currentSalle][currentDate]);
        	cbService3.setSelectedItem(OSTab[8][currentSalle][currentDate]);
        	cbService4.setSelectedItem(OSTab[9][currentSalle][currentDate]);
        	cbService5.setSelectedItem(OSTab[10][currentSalle][currentDate]);
        	cbService6.setSelectedItem(OSTab[11][currentSalle][currentDate]);
        
        	cbSalle1.setSelectedItem(salles[0][currentSalle][currentDate]);
        	cbDisposition.setSelectedItem(salles[1][currentSalle][currentDate]);
        	txtNombreParticipants.setText(salles[2][currentSalle][currentDate]);
        	cbHeureDebut.setSelectedItem(salles[4][currentSalle][currentDate]);
            cbHeureFin.setSelectedItem(salles[5][currentSalle][currentDate]);
            cbFormule.setSelectedItem(salles[6][currentSalle][currentDate]);
        	for(int i=0;i<3;++i)if(salles[0][i][currentDate].equals("privatisation totale"))privat();
        
        	System.out.println(salles[0][0][currentDate]);
        	System.out.println(salles[0][1][currentDate]);
        	System.out.println(salles[0][2][currentDate]);
        }
    }
    
    public void SalleChange(ItemEvent e) throws SQLException{
    	SalleChange();
    }
    
    public void SalleChange() throws SQLException{
    	String nomSalle = (String)cbSalle1.getSelectedItem();
    	String disposition = (String)cbDisposition.getSelectedItem();
    	String nbParticipants = txtNombreParticipants.getText();
    	int idsalle = rq.getIdByName("salle", "idSalle", nomSalle, "libelle");
    	salles[0][currentSalle][currentDate]=nomSalle;
    	salles[1][currentSalle][currentDate]=disposition;
    	salles[2][currentSalle][currentDate]=nbParticipants;
    	salles[3][currentSalle][currentDate]=rq.getStrById("salle", "idSalle", "descriptif", idsalle);
    	salles[4][currentSalle][currentDate]=(String)cbHeureDebut.getSelectedItem();
    	salles[5][currentSalle][currentDate]=(String)cbHeureFin.getSelectedItem();
    	salles[6][currentSalle][currentDate]=(String)cbFormule.getSelectedItem();
    	
    	String o1 = (String)cbOption1.getSelectedItem();
        String o2 = (String)cbOption2.getSelectedItem();
        String o3 = (String)cbOption3.getSelectedItem();
        String o4 = (String)cbOption4.getSelectedItem();
        String o5 = (String)cbOption5.getSelectedItem();
        String o6 = (String)cbOption6.getSelectedItem();
        
        String s1 = (String)cbService1.getSelectedItem();
        String s2 = (String)cbService2.getSelectedItem();
        String s3 = (String)cbService3.getSelectedItem();
        String s4 = (String)cbService4.getSelectedItem();
        String s5 = (String)cbService5.getSelectedItem();
        String s6 = (String)cbService6.getSelectedItem();
        
        OSTab[0][currentSalle][currentDate]=o1;
        OSTab[1][currentSalle][currentDate]=o2;
        OSTab[2][currentSalle][currentDate]=o3;
        OSTab[3][currentSalle][currentDate]=o4;
        OSTab[4][currentSalle][currentDate]=o5;
        OSTab[5][currentSalle][currentDate]=o6;
        OSTab[6][currentSalle][currentDate]=s1;
        OSTab[7][currentSalle][currentDate]=s2;
        OSTab[8][currentSalle][currentDate]=s3;
        OSTab[9][currentSalle][currentDate]=s4;
        OSTab[10][currentSalle][currentDate]=s5;
        OSTab[11][currentSalle][currentDate]=s6;
        if(cbNoSalle.getSelectedIndex()==3){currentSalle=0;}
        else{currentSalle=cbNoSalle.getSelectedIndex();}
        System.out.println(currentSalle+" "+currentDate+" "+cbDate.getSelectedIndex());
        cbOption1.setSelectedItem(OSTab[0][currentSalle][currentDate]);
        cbOption2.setSelectedItem(OSTab[1][currentSalle][currentDate]);
        cbOption3.setSelectedItem(OSTab[2][currentSalle][currentDate]);
        cbOption4.setSelectedItem(OSTab[3][currentSalle][currentDate]);
        cbOption5.setSelectedItem(OSTab[4][currentSalle][currentDate]);
        cbOption6.setSelectedItem(OSTab[5][currentSalle][currentDate]);
        cbService1.setSelectedItem(OSTab[6][currentSalle][currentDate]);
        cbService2.setSelectedItem(OSTab[7][currentSalle][currentDate]);
        cbService3.setSelectedItem(OSTab[8][currentSalle][currentDate]);
        cbService4.setSelectedItem(OSTab[9][currentSalle][currentDate]);
        cbService5.setSelectedItem(OSTab[10][currentSalle][currentDate]);
        cbService6.setSelectedItem(OSTab[11][currentSalle][currentDate]);
        
        cbSalle1.setSelectedItem(salles[0][currentSalle][currentDate]);
        cbDisposition.setSelectedItem(salles[1][currentSalle][currentDate]);
        txtNombreParticipants.setText(salles[2][currentSalle][currentDate]);
        cbHeureDebut.setSelectedItem(salles[4][currentSalle][currentDate]);
        cbHeureFin.setSelectedItem(salles[5][currentSalle][currentDate]);
        cbFormule.setSelectedItem(salles[6][currentSalle][currentDate]);
        System.out.println(salles[0][0][currentDate]+" SALLE 1");
        System.out.println(salles[0][1][currentDate]+" SALLE 2");
        System.out.println(salles[0][2][currentDate]+" SALLE 3");
    }
    
    public void CalendarChange(PropertyChangeEvent e){
    	try {
			SalleChange();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String horaireDebut = (String)cbHeureDebut.getSelectedItem();
        String horaireFin = (String)cbHeureFin.getSelectedItem();
        double nbHeures = (calendarFin.getDate().getTime()-calendarDebut.getDate().getTime())/(1000*60*60*24)*14+Double.parseDouble(horaireFin.split(":")[0])-Double.parseDouble(horaireDebut.split(":")[0])+(Double.parseDouble(horaireFin.split(":")[1])-Double.parseDouble(horaireDebut.split(":")[1]))/60;
    	labelNbHeures.setText("nombre d'heures: "+Double.toString(nbHeures));
    	
    	nbDates = (int)((calendarFin.getDate().getTime()-calendarDebut.getDate().getTime())/(1000*60*60*24)+1);
    	cbDate.removeAllItems();
    	SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String Current=format1.format(calendarDebut.getDate());
        
    	
    
        for(int i=0;i<nbDates;++i){
        	System.out.println(Current);
        	cbDate.addItem(Current);
        	GregorianCalendar dat = new GregorianCalendar();
        	try {
				dat.setTime(format1.parse(Current));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	dat.add(Calendar.DAY_OF_YEAR, 1);
        	Current=format1.format(dat.getTime());
        	
        }
    	//cbDate.addItem();
    	System.out.println(nbDates);
    	for(int k=0;k<nbDates;++k){
    		//for(int j=0;j<3;++j){
    			/*for(int i=0;i<4;++i){
    				salles[i][currentSalle][k]=salles[i][currentSalle][0];
    				System.out.println(salles[i][currentSalle][k]);
    			}
    			for(int i=0;i<12;++i){
    				OSTab[i][currentSalle][k]=OSTab[i][currentSalle][0];
    				System.out.println(OSTab[i][currentSalle][k]);
    			}*/
    		//}
    	}
    	
    }
    
    public static Boolean comparerheures (String horaireDebut, String horaireFin, String dateDebut, String dateFin){
    	String heureDebut=horaireDebut.split(":")[0];
    	String heureFin=horaireFin.split(":")[0];
    	String minDebut=horaireDebut.split(":")[1];
    	String minFin=horaireFin.split(":")[1];
    	if(Integer.parseInt(heureDebut) > Integer.parseInt(heureFin) && dateDebut.equals(dateFin)){
    		 return true;
    	}
    	if(Integer.parseInt(heureDebut) > Integer.parseInt(heureFin) && Integer.parseInt(minDebut) > Integer.parseInt(minFin) && dateDebut.equals(dateFin)){
    		 return true;
    	}
    	return false;
    }
    
    public static Boolean comparerdates (String dateDebut, String dateFin){
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
    
    public void setSalle (String nom[]){
    	for(int i=0; i<cbSalle1.getItemCount();++i){
    		
    		if(cbSalle1.getItemAt(i).toString().equals(nom[0])){
    			cbSalle1.setSelectedIndex(i);
    		}
    	}
    	for(int i=0; i<nom.length;++i){
    		salles[0][i][0]=nom[i];
    	}
    }
    
    public void setDisposition (String[] nom){
    	
    	for(int i=0; i<cbDisposition.getItemCount();++i){
    		
    		if(cbDisposition.getItemAt(i).toString().equals(nom[0])){
    			cbDisposition.setSelectedIndex(i);
    		}
    	}
    	for(int i=0; i<nom.length;++i){
    		salles[1][i][0]=nom[i];
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
    
    public void setNbPersonne (int[] nb){
    	txtNombreParticipants.setText(Integer.toString(nb[0]));
    	for(int i=0; i<nb.length;++i){
    		salles[2][i][0]=Integer.toString(nb[i]);
    	}
    }
    
    public void setDateDebut (String dateStr){
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	Date date;
		try {
			date = formatter.parse(dateStr);
			calendarDebut.setDate(date);
			//cbDate.setSelectedItem(dateStr);
			currentDate=0;
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
    
    public void setOptions(String[][] options){
    	
    	JComboBox[] Optn = new JComboBox[6];
    	Optn[0]=cbOption1;Optn[1]=cbOption2;Optn[2]=cbOption3;Optn[3]=cbOption4;Optn[4]=cbOption5;Optn[5]=cbOption6;
    	for (int i=0;i<6;++i){
    		
    		Optn[i].setSelectedItem(options[i][0]);
    		OSTab[i][0][0]=options[i][0];
    		OSTab[i][1][0]=options[i][1];
    		OSTab[i][2][0]=options[i][2];
    		System.out.println(OSTab[i][0]);
    	}	
    }
    
    public void setServices(String[][] services){
    	JComboBox[] Srv = new JComboBox[6];
    	Srv[0]=cbService1;Srv[1]=cbService2;Srv[2]=cbService3;Srv[3]=cbService4;Srv[4]=cbService5;Srv[5]=cbService6;
    	for (int i=6;i<12;++i){
    		Srv[i-6].setSelectedItem(services[i-6][0]);
    		OSTab[i][0][0]=services[i-6][0];
    		OSTab[i][1][0]=services[i-6][1];
    		OSTab[i][2][0]=services[i-6][2];
    		System.out.println(OSTab[i][0]);
    	}	
    }
    
    public void setTab(String[][][] salles){
    	this.salles=salles;
    	cbSalle1.setSelectedItem(salles[0][0][0]);
    	cbDisposition.setSelectedItem(salles[1][0][0]);
    	txtNombreParticipants.setText(salles[2][0][0]);
    	cbHeureDebut.setSelectedItem(salles[4][0][0]);
    	cbHeureFin.setSelectedItem(salles[5][0][0]);
    	cbFormule.setSelectedItem(salles[6][0][0]);
    	
    	currentDate=0;
    	currentSalle=0;
    	System.out.println("CA A MARCHE LOL");
    	for(int i=0;i<6;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!this.salles[i][j][k].equals("Aucune"))System.out.println(this.salles[i][j][k]);
    	for(int i=0;i<6;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!salles[i][j][k].equals("Aucune"))System.out.println(salles[i][j][k]);
    	for(int i=0;i<6;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(this.salles[i][j][k].equals("privatisation totale"))privat();
    }
    
    public void setOS(String[][][] OS){
    	this.OSTab=OS;
    	
    	cbOption1.setSelectedItem(OSTab[0][0][0]);
    	cbOption2.setSelectedItem(OSTab[1][0][0]);
    	cbOption3.setSelectedItem(OSTab[2][0][0]);
    	cbOption4.setSelectedItem(OSTab[3][0][0]);
    	cbOption5.setSelectedItem(OSTab[4][0][0]);
    	cbOption6.setSelectedItem(OSTab[5][0][0]);
    	cbService1.setSelectedItem(OSTab[6][0][0]);
    	cbService2.setSelectedItem(OSTab[7][0][0]);
    	cbService3.setSelectedItem(OSTab[8][0][0]);
    	cbService4.setSelectedItem(OSTab[9][0][0]);
    	cbService5.setSelectedItem(OSTab[10][0][0]);
    	cbService6.setSelectedItem(OSTab[11][0][0]);
    	currentDate=0;
    	currentSalle=0;
    	System.out.println("CA A MARCHE LOL");
    	for(int i=0;i<4;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!this.OSTab[i][j][k].equals("Aucune"))System.out.println(this.OSTab[i][j][k]);
    	for(int i=0;i<4;++i)for(int j=0;j<3;++j)for(int k=0;k<365;++k)if(!OS[i][j][k].equals("Aucune"))System.out.println(OS[i][j][k]);
    }
    
    
    
    public void setIg(Igeneration Ig){
    	this.Ig=Ig;
    }
    
	public void validation(int resadispo, String[] date, String[][] horaireDebut, String[][] horaireFin, String nbParticipants, double[][] nbHeures, int idClient, int[][] formules, int[][] idinfosalle, String[][][] OS, int[][] nbPersonnes) throws SQLException{
	
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
    private javax.swing.JComboBox<String> cbDate;
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
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelServices;
    private javax.swing.JLabel labelTitre;
    private javax.swing.JComboBox cbClient;
    private javax.swing.JComboBox cbNoSalle;
    private javax.swing.JTextField txtNombreParticipants;
    private javax.swing.JButton btnNouveauClient;
    private JCalendar calendarDebut;
    private JCalendar calendarFin;
    // End of variables declaration//GEN-END:variables
    private String[] lesSalles = null;
    private String[] lesFormules = null;
    private String[] lesDispositions = null;
    private String[] lesOptions = null;
    private String[] lesServices = null;
    private String[] lesClients = null;
    private String[][][] salles= new String[7][3][365];
    private String[][][] OSTab= new String[12][3][365];
    private String[] heures= {"09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00"};
}

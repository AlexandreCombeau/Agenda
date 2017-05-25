package admin;

import static admin.ITajoutUtilisateur.checkEmail;
import static gestionagenda.GestionAgenda.rq;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ITmodifClient extends javax.swing.JFrame{
	public ITmodifClient (){
		initComponents();
	}
	
	public void initComponents(){
		separator = new JSeparator();
		
		btnNewButton = new JButton("Changer nom");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNomActionPerformed(arg0);
			}
		});
		
		btnChangerPrenom = new JButton("Changer prenom");
		btnChangerPrenom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrenomActionPerformed(e);
			}
		});
		
		btnChangerEmail = new JButton("Changer email");
		btnChangerEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEmailActionPerformed(e);
			}
		});
		
		btnChangerAdresse = new JButton("Changer adresse");
		btnChangerAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdresseActionPerformed(e);
			}
		});
		
		btnNewButton_1 = new JButton("Changer entite");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEntiteActionPerformed(e);
			}
		});
		
		btnChangerTelephone = new JButton("Changer telephone");
		btnChangerTelephone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTelActionPerformed(e);
			}
		});
		
		btnNewButton_2 = new JButton("Editer commentaire");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnComActionPerformed(e);
			}
		});
		
		separator_1 = new JSeparator();
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitActionPerformed(e);
			}
		});
		
		label = new JLabel();
		
		label_1 = new JLabel();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(380, 380);
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(groupLayout);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnChangerEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
										.addComponent(separator, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnChangerTelephone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnChangerAdresse, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnChangerPrenom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106,106,106)
							.addComponent(btnQuitter, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(144,144,144)
							.addComponent(label)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24,24,24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(2,2,2)
							.addComponent(label)
							.addGap(18,18,18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnChangerPrenom))
					.addGap(18,18,18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChangerEmail)
						.addComponent(btnChangerAdresse))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnChangerTelephone))
					.addGap(18,18,18)
					.addComponent(btnNewButton_2)
					.addGap(27,27,27)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuitter)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	

	private void btnNomActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauNom = "";
	        //String[] nomUsager = this.getNomPrenom(lbUser.getText());
	        nouveauNom = (String)JOptionPane.showInputDialog(null, "L'ancien prenom de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"nom", "client")+"\nQuel sera le nouveau prenom ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauNom != null && !(nouveauNom.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau prenom pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauNom+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "nom", nouveauNom, id, "idClient");
	            	label_1.setText(nouveauNom);
	            	JOptionPane.showMessageDialog(null, "Le nouveau prenom de "+label.getText()+" "+label_1.getText()+" est : "+nouveauNom);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
		
	}	
	

	private void btnPrenomActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauPrenom = "";
	        //String[] nomUsager = this.getNomPrenom(lbUser.getText());
	        nouveauPrenom = (String)JOptionPane.showInputDialog(null, "L'ancien prenom de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"prenom", "client")+"\nQuel sera le nouveau prenom ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauPrenom != null && !(nouveauPrenom.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau prenom pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauPrenom+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "prenom", nouveauPrenom, id, "idClient");
	            	label.setText(nouveauPrenom);
	            	JOptionPane.showMessageDialog(null, "Le nouveau prenom de "+label.getText()+" "+label_1.getText()+" est : "+nouveauPrenom);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
		
	}
	

	private void btnEmailActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauMail = "";
	        //String[] nomUsager = this.getNomPrenom(lbUser.getText());
	        nouveauMail = (String)JOptionPane.showInputDialog(null, "L'ancien mail de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(), "eMail", "client")+"\nQuel sera le nouveau mail ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        if(!(rq.checkUtilisateurEmail(nouveauMail))){
	            JOptionPane.showMessageDialog(null, "Ce mail est déjà utlisé !\nVeuillez en choisir un autre");
	        }
	        else if(!(checkEmail(nouveauMail))){
	            JOptionPane.showMessageDialog(null, "Ce mail n'est pas valide !\nVeuillez en choisir un autre");
	        }
	        else if(nouveauMail != null && !(nouveauMail.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau mail pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauMail+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "eMail", nouveauMail, id, "idClient");
	            	JOptionPane.showMessageDialog(null, "Le nouveau mail de "+label.getText()+" "+label_1.getText()+" est : "+nouveauMail);
	            }
	        }
	        }catch (SQLException ex) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	

	private void btnAdresseActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauAdresse = "";
	        //String[] nomUsager = this.getNomPrenom(lbUser.getText());
	        nouveauAdresse = (String)JOptionPane.showInputDialog(null, "L'ancienne adresse de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"adresseFacturation", "client")+"\nQuel sera la nouvelle adresse ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauAdresse != null && !(nouveauAdresse.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "La nouvelle adresse pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauAdresse+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "adresseFacturation", nouveauAdresse, id, "idClient");
	                JOptionPane.showMessageDialog(null, "La nouvelle adresse de "+label.getText()+" "+label_1.getText()+" est : "+nouveauAdresse);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
	}
	

	private void btnTelActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauTelephone = "";
	        //String[] nomUsager = this.getNomTelephone(lbUser.getText());
	        nouveauTelephone = (String)JOptionPane.showInputDialog(null, "L'ancien Telephone de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"telephone", "client")+"\nQuel sera le nouveau Telephone ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauTelephone != null && !(nouveauTelephone.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau Telephone pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauTelephone+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJIntfromId("client", "commentaire", Integer.parseInt(nouveauTelephone), id, "idClient");
	            	JOptionPane.showMessageDialog(null, "Le nouveau Telephone de "+label.getText()+" "+label_1.getText()+" est : "+nouveauTelephone);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
		
	}
	
	
	private void btnComActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauCommentaire = "";
	        //String[] nomUsager = this.getNomCommentaire(lbUser.getText());
	        nouveauCommentaire = (String)JOptionPane.showInputDialog(null, "L'ancien Commentaire de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"commentaire", "client")+"\nQuel sera le nouveau Commentaire ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauCommentaire != null && !(nouveauCommentaire.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau Commentaire pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauCommentaire+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "commentaire", nouveauCommentaire, id, "idClient");
	            	JOptionPane.showMessageDialog(null, "Le nouveau Commentaire de "+label.getText()+" "+label_1.getText()+" est : "+nouveauCommentaire);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
	}
	

	private void btnEntiteActionPerformed(ActionEvent arg0) {
		try{
			int id = Integer.parseInt(rq.getElementByPrenomNom(label.getText(),label_1.getText(),"idClient","client"));
	        String nouveauentite = "";
	        //String[] nomUsager = this.getNomentite(lbUser.getText());
	        nouveauentite = (String)JOptionPane.showInputDialog(null, "L'ancien entite de "+label.getText()+" "+label_1.getText()+" est : "+rq.getElementByPrenomNom(label.getText(),label_1.getText(),"entite", "client")+"\nQuel sera le nouveau entite ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.QUESTION_MESSAGE);
	        
	        if(nouveauentite != null && !(nouveauentite.equals(""))){
	            int choix = (int)JOptionPane.showConfirmDialog(null, "Le nouveau entite pour "+label.getText()+" "+label_1.getText()+" sera : "+nouveauentite+".\nConfirmer ?", "Modification de l'utilisateur : "+label.getText()+" "+label_1.getText(), JOptionPane.YES_NO_OPTION);
	            if(choix == 0){
	            	rq.MAJStrfromId("client", "entite", nouveauentite, id, "idClient");
	            	JOptionPane.showMessageDialog(null, "Le nouveau entite de "+label.getText()+" "+label_1.getText()+" est : "+nouveauentite);
	            }
	        
	        }
			}catch (Exception e) {
	                Logger.getLogger(ITmodifUtilisateur.class.getName()).log(Level.SEVERE, null, e);
	        }
		
	}
	
	private void btnExitActionPerformed(ActionEvent e) {
		this.dispose();
		
	}
	
	public void setlabelprenom(String txt) {
        this.label.setText(txt);
    }
	
	public void setlabelnom(String txt) {
        this.label_1.setText(txt);
    }
	
	private javax.swing.JButton btnNewButton;
    private javax.swing.JButton btnChangerPrenom;
    private javax.swing.JButton btnChangerEmail;
    private javax.swing.JButton btnChangerAdresse;
    private javax.swing.JButton btnNewButton_1;
    private javax.swing.JButton btnChangerTelephone;
    private javax.swing.JButton btnNewButton_2;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JSeparator separator_1;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label_1;
}

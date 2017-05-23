/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usager;

/**
 *
 * @author Villalemons
 */
public class Usager {
    
    /*
    VARIABLES
    */  
    private int id, admin;
    private String nom, prenom;

    /**
     * CONSTRUCTEUR
     * 
     * @param id
     * @param admin
     * @param nom
     * @param prenom 
     */
    public Usager(int id, int admin, String nom, String prenom) {
        this.id = id;
        this.admin = admin;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Usager{" + "id=" + id + ", admin=" + admin + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    /*
    GETTERS / SETTERS
    */
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getAdmin() {return admin;}

    public void setAdmin(int admin) {this.admin = admin;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;} 
    
}

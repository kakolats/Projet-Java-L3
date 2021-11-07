/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */
public class Medecin extends User {
    private String nci;
    private String specialite;
    private final String ROLE="ROLE_MEDECIN";
    
    public Medecin(){
        
    }

    public Medecin(int id, String nomComplet) {
        super(id, nomComplet);
    }
    

    //Insertion
    public Medecin(String nci, String specialite, String login, String password, String role, String nomComplet) {
        super(login, password, role, nomComplet);
        this.nci = nci;
        this.specialite = specialite;
    }

    //Mise a jour
    public Medecin(String nci, String specialite, int id, String login, String password, String role, String nomComplet) {
        super(id, login, password, role, nomComplet);
        this.nci = nci;
        this.specialite = specialite;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return this.nomComplet;
    }
    
    
    
}

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
public class Patient extends User {
    private String antecedents;

    public Patient() {
    }

    public Patient(int id) {
        super(id);
    }

    public Patient(int id, String nomComplet) {
        super(id, nomComplet);
    }
    
    

    public Patient(String antecedents, String nomComplet, String login, String password, String role) {
        super(nomComplet, login, password, role);
        this.antecedents = antecedents;
    }

    public Patient(String antecedents, int id, String nomComplet, String login, String password, String role) {
        super(id, nomComplet, login, password, role);
        this.antecedents = antecedents;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return nomComplet; //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}

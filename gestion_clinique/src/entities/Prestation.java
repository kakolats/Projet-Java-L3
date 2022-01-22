/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Prestation {
    private int id;
    private Date date;
    private String etat;
    private String resultats;
    private TypeService type;
    private Patient patient;
    private int consultation_id;

    public Prestation(){
        
    }

    public Prestation(TypeService type, Patient patient, int consultation_id) {
        this.type = type;
        this.patient = patient;
        this.consultation_id = consultation_id;
    }
    
    

    public Prestation(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Prestation(int id, String resultats) {
        this.id = id;
        this.resultats = resultats;
    }

    
    
    
    public Prestation(Date date, String etat,TypeService type) {
        this.date = date;
        this.etat = etat;
        this.type = type;
    }

    public Prestation(String etat, TypeService type, Patient patient) {
        this.etat = etat;
        this.type = type;
        this.patient = patient;
    }
      
    
    
    public Prestation(Date date, TypeService type) {
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getResultats() {
        return resultats;
    }

    public void setResultats(String resultats) {
        this.resultats = resultats;
    }

    public TypeService getType() {
        return type;
    }

    public void setType(TypeService type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    
    
    
    
    
}

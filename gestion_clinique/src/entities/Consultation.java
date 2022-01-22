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
public class Consultation {
    
    private int id;
    private Date date;
    private Patient patient;
    private Medecin medecin;
    private Ordonnance ordonnance;
    private int specialite_id;
    private String libelle;
    private String statut;
    private TypeService type;
    private String constantes;
    
    public Consultation(){
        
    }

    public Consultation(Date date, String statut, TypeService type) {
        this.date = date;
        this.statut = statut;
        this.type = type;
    }
    
    

    public Consultation(int id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Consultation(Date date, Patient patient, Medecin medecin, int specialite_id) {
        this.date = date;
        this.patient = patient;
        this.medecin = medecin;
        this.specialite_id = specialite_id;
    }

    public Consultation(Date date, String statut, int specialite_id) {
        this.date = date;
        this.statut = statut;
        this.specialite_id = specialite_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    
    
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.ordonnance = ordonnance;
    }

    public int getSpecialite_id() {
        return specialite_id;
    }

    public void setSpecialite_id(int specialite_id) {
        this.specialite_id = specialite_id;
    }

    public TypeService getType() {
        return type;
    }

    public void setType(TypeService type) {
        this.type = type;
    }

    public String getConstantes() {
        return constantes;
    }

    public void setConstantes(String constantes) {
        this.constantes = constantes;
    }
    
    
    
    
    
}

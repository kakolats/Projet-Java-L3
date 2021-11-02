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
public class RendezVous {
    private int id;
    private int statut;
    private Date date;
    private TypeConsultation typeC;
    private TypePrestation typeP;
    private Patient patient;

    public RendezVous() {
    }

    public RendezVous(int statut, Date date, TypeConsultation typeC, Patient patient) {
        this.statut = statut;
        this.date = date;
        this.typeC = typeC;
        this.patient = patient;
    }

    public RendezVous(int statut, Date date, TypePrestation typeCo, Patient patient) {
        this.statut = statut;
        this.date = date;
        this.typeP = typeCo;
        this.patient = patient;
    }

    public RendezVous(int id, int statut, Date date, TypePrestation typeCo, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.typeP = typeCo;
        this.patient = patient;
    }

    public RendezVous(int id, int statut, Date date, TypeConsultation typeC, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.typeC = typeC;
        this.patient = patient;
    }

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public TypeConsultation getTypeC() {
        return typeC;
    }

    public void setTypeC(TypeConsultation typeC) {
        this.typeC = typeC;
    }

    public TypePrestation getTypeP() {
        return typeP;
    }

    public void setTypeP(TypePrestation typeP) {
        this.typeP = typeP;
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
    
    
}

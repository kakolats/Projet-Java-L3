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
    private String statut;
    private Date date;
    private TypeConsultation typeC;
    private TypePrestation typeP;
    private TypeService typeS;
    private Patient patient;

    public RendezVous() {
    }

    public RendezVous(int id, String statut, Date date, TypeService typeS, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.typeS = typeS;
        this.patient = patient;
    }

    public RendezVous(String statut, Date date,TypeService typeS, Patient patient) {
        this.statut = statut;
        this.date = date;
        this.typeS = typeS;
        this.patient = patient;
    }


    
    
    public RendezVous(String statut, Date date, TypeConsultation typeC, Patient patient) {
        this.statut = statut;
        this.date = date;
        this.typeC = typeC;
        this.patient = patient;
    }

    public RendezVous(String statut, Date date, TypePrestation typeCo, Patient patient) {
        this.statut = statut;
        this.date = date;
        this.typeP = typeCo;
        this.patient = patient;
    }

    public RendezVous(int id, String statut, Date date, TypePrestation typeCo, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.typeP = typeCo;
        this.patient = patient;
    }

    public RendezVous(int id, String statut, Date date, TypeConsultation typeC, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.date = date;
        this.typeC = typeC;
        this.patient = patient;
    }

    public RendezVous(Date date, TypeConsultation typeC, Patient patient) {
        this.date = date;
        this.typeC = typeC;
        this.patient = patient;
    }

    public RendezVous(Date date, TypePrestation typeP, Patient patient) {
        this.date = date;
        this.typeP = typeP;
        this.patient = patient;
    }

    public RendezVous(String statut, Date date) {
        this.statut = statut;
        this.date = date;
    }

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
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

    public TypeService getTypeS() {
        return typeS;
    }

    public void setTypeS(TypeService typeS) {
        this.typeS = typeS;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "statut=" + statut + ", patient=" + patient.getId() + '}';
    }
    
    
}

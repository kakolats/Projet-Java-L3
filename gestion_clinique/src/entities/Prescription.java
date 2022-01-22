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
public class Prescription {
    private int id;
    private Medicament medicament;
    private int consultation_id;
    private String posologie;

    public Prescription() {
    }

    public Prescription(Medicament medicament, int consultation_id, String posologie) {
        this.medicament = medicament;
        this.consultation_id = consultation_id;
        this.posologie = posologie;
    }

    public Prescription(int id, Medicament medicament, String posologie) {
        this.id = id;
        this.medicament = medicament;
        this.posologie = posologie;
    }

    public Prescription(Medicament medicament, String posologie) {
        this.medicament = medicament;
        this.posologie = posologie;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }
    
    
}

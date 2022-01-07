/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.TypeConsultation;
import entities.TypePrestation;
import entities.TypeService;
import entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IService {
    
    public User login(String login,String password);
    public int addPatient(Patient user);
    public int addConsultation(Consultation consultation);
    public List<TypeConsultation> showAllTypeConsultation();
    public List<TypePrestation> showAllTypePrestation();
    public int addRendezVous(RendezVous rdv);
    public int updateRendezVous(String statut,int idRdv);
    public List<RendezVous> searchRendezVousByPatient(int idPatient);
    public List<RendezVous> showAllRendezVousEnCours();
    public List<Medecin> showMedecinsByTypeConsultation(TypeService type);
    public int showConsutationsNumberByMedecin(int idMedecin,Date date);
    public int showPrestationNumberByDate(int idPrestation,Date date);
    public int addPrestation(Prestation prestation);
}

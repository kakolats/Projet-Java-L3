/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Consultation;
import entities.Medecin;
import entities.Medicament;
import entities.Patient;
import entities.Prescription;
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
    public List<Consultation> showAllConsultationsByPatient(int idPatient);
    public List<Prestation> showAllPrestationByPatient(int idPatient);
    public List<Prestation> showAllPrestationByConsultation(int idConsultation);
    public int showPrestationNumberByDate(int idPrestation,Date date);
    public int addPrestation(Prestation prestation);
    public int addPrestationWithoutDate(Prestation prestation);
    public int addPrescription(Prescription prescription);
    public List<Prescription> showPrescriptionsByConsultation(int consultationId);
    public int finaliserConsultation(Consultation consultation);
    public List<Prestation> showAllPrestationsByEtat(String etat);
    
    public List<Consultation> showAllConsultationsByEtat(String statut,Medecin medecin);
    public List<Consultation> showAllConsultationsByDateDoctorAndEtat(String statut,Medecin medecin,Date date);
    
    public List<Prestation> showAllPrestationsWithoutDate();
    public int setDatePrestation(Prestation prestation);
    public int setAnnulationPrestation(Prestation prestation);
    public int setResultatsPrestation(Prestation prestation);
    public List<Medicament> showAllMedicaments();
    public Medicament findMedicamentById(int idMedicament);
    public List<Patient> findAllPatients();
    
    public int annulerConsultation(Consultation consultation);
    
    public List<User> showAllUsers();
    
    public User findUserByLogin(String login);
    
    public int addUser(User user);
    public int addUserMedecin(User user,int spe_id);
    
    
}

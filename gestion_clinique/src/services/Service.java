/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ConsultationDao;
import dao.MedecinDao;
import dao.MedicamentDao;
import dao.PatientDao;
import dao.PrescriptionDao;
import dao.PrestationDao;
import dao.RendezVousDao;
import dao.TypeConsultationDao;
import dao.TypePrestationDao;
import dao.UserDao;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Service implements IService {
    
    UserDao daoUser=new UserDao();
    MedecinDao daoMedecin=new MedecinDao();
    PatientDao daoPatient=new PatientDao();
    RendezVousDao daoRendezVous=new RendezVousDao();
    TypePrestationDao daoTypePrestation=new TypePrestationDao();
    TypeConsultationDao daoTypeConsultation=new TypeConsultationDao();
    ConsultationDao daoConsultation=new ConsultationDao();
    PrestationDao daoPrestation=new PrestationDao();
    MedicamentDao daoMedicament=new MedicamentDao();
    PrescriptionDao daoPrescription=new PrescriptionDao();

    @Override
    public User login(String login, String password) {
        return daoUser.findUserLoginAndPassword(login, password);
    }

    @Override
    public int addPatient(Patient user) {
        return daoPatient.insert(user);
    }

    @Override
    public List<TypeConsultation> showAllTypeConsultation() {
        return daoTypeConsultation.findAll();
    }

    @Override
    public List<TypePrestation> showAllTypePrestation() {
        return daoTypePrestation.findAll();
    }

    @Override
    public int addRendezVous(RendezVous rdv) {
        return daoRendezVous.insert(rdv);
    }

    @Override
    public List<RendezVous> searchRendezVousByPatient(int idPatient) {
        List<RendezVous> rendezVous=daoRendezVous.findByIdPatient(idPatient);
        for(RendezVous rdv:rendezVous){
            if(rdv.getTypeS().getType().equals("Consultation")){
                String libelle=daoTypeConsultation.findLibelleById(rdv.getTypeS().getId());
                rdv.getTypeS().setLibelle(libelle);
            }
            if(rdv.getTypeS().getType().equals("Prestation")){
                String libelle=daoTypePrestation.findLibelleById(rdv.getTypeS().getId());
                rdv.getTypeS().setLibelle(libelle);
            }
        }
        
        return rendezVous;
    }

    @Override
    public List<RendezVous> showAllRendezVousEnCours() {
        List<RendezVous> rendezVous=daoRendezVous.findAll();
        for(RendezVous rdv:rendezVous){
            if(rdv.getTypeS().getType().equals("Consultation")){
                String libelle=daoTypeConsultation.findLibelleById(rdv.getTypeS().getId());
                rdv.getTypeS().setLibelle(libelle);
            }
            if(rdv.getTypeS().getType().equals("Prestation")){
                String libelle=daoTypePrestation.findLibelleById(rdv.getTypeS().getId());
                rdv.getTypeS().setLibelle(libelle);
            }
        }
        return rendezVous;
    }

    @Override
    public List<Medecin> showMedecinsByTypeConsultation(TypeService type) {
        return daoMedecin.findByTypeConsultation(type);
    }

    @Override
    public int showConsutationsNumberByMedecin(int idMedecin,Date date) {
        return daoConsultation.countByMedecin(idMedecin,date);
    }

    @Override
    public int showPrestationNumberByDate(int idPrestation, Date date) {
        return daoPrestation.countByType(idPrestation,date);
    }

    @Override
    public int addConsultation(Consultation consultation) {
        return daoConsultation.insert(consultation);
    }

    @Override
    public int updateRendezVous(String statut,int idRdv) {
        return daoRendezVous.update(statut,idRdv);
    }

    @Override
    public int addPrestation(Prestation prestation) {
        return daoPrestation.insert(prestation);
    }

    @Override
    public List<Consultation> showAllConsultationsByPatient(int idPatient) {
        List<Consultation> consultations=daoConsultation.findByIdPatient(idPatient);
        for(Consultation consul:consultations){
            String libelle=daoTypeConsultation.findLibelleById(consul.getSpecialite_id());
            consul.setLibelle(libelle);
            Medecin med=daoMedecin.findById(consul.getMedecin().getId());
            consul.getMedecin().setNomComplet(med.getNomComplet());
        }
        return consultations;
    }

    @Override
    public List<Prestation> showAllPrestationByPatient(int idPatient) {
        List<Prestation> prestations=daoPrestation.findByIdPatient(idPatient);
        for(Prestation presta:prestations){
            String libelle=daoTypePrestation.findLibelleById(presta.getType().getId());
            presta.getType().setLibelle(libelle);
        } 
        return prestations;
    }

    @Override
    public List<Prestation> showAllPrestationsByEtat(String etat) {
        List<Prestation> prestations= daoPrestation.selectAllByEtat(etat);
        for(Prestation presta:prestations){
            Patient pat=daoPatient.findById(presta.getPatient().getId());
            
            presta.getPatient().setNomComplet(pat.getNomComplet());
            String libelle=daoTypePrestation.findLibelleById(presta.getType().getId());
            presta.getType().setLibelle(libelle);
            //System.out.println("this bitch got me"+presta.getId());
        }
        return prestations;
    }

    @Override
    public List<Prestation> showAllPrestationsWithoutDate() {
        List<Prestation> prestations= daoPrestation.selectAllWithoutDate();
        for(Prestation presta:prestations){
            Patient pat=daoPatient.findById(presta.getPatient().getId());
            
            presta.getPatient().setNomComplet(pat.getNomComplet());
            String libelle=daoTypePrestation.findLibelleById(presta.getType().getId());
            presta.getType().setLibelle(libelle);
            //System.out.println("this bitch got me"+presta.getId());
        }
        return prestations;
    }

    @Override
    public int setDatePrestation(Prestation prestation) {
        return daoPrestation.updateDate(prestation);
    }

    @Override
    public int setAnnulationPrestation(Prestation prestation) {
        return daoPrestation.updateAnnuler(prestation);
    }

    @Override
    public int setResultatsPrestation(Prestation prestation) {
        return daoPrestation.update(prestation);
    }

    @Override
    public List<Consultation> showAllConsultationsByEtat(String statut, Medecin medecin) {
        List<Consultation> consultations=daoConsultation.selectAllByEtat(statut, medecin);
        for(Consultation consul:consultations){
            Patient pat=daoPatient.findById(consul.getPatient().getId());
            consul.getPatient().setNomComplet(pat.getNomComplet());
            String libelle=daoTypeConsultation.findLibelleById(consul.getType().getId());
            consul.getType().setLibelle(libelle);  
        }
        return consultations;
    }

    @Override
    public List<Medicament> showAllMedicaments() {
        return daoMedicament.findAll();
    }

    @Override
    public int addPrestationWithoutDate(Prestation prestation) {
        return daoPrestation.insertWithoutDate(prestation);
    }

    @Override
    public int addPrescription(Prescription prescription) {
        return daoPrescription.insert(prescription);
    }

    @Override
    public int finaliserConsultation(Consultation consultation) {
        return daoConsultation.update(consultation);
    }

    @Override
    public List<Prestation> showAllPrestationByConsultation(int idConsultation) {
        List<Prestation> prestations=daoPrestation.findByIdConsultation(idConsultation);
        for(Prestation p:prestations){
            String libelle=daoTypePrestation.findLibelleById(p.getType().getId());
            p.getType().setLibelle(libelle);
        }
        return prestations;
    }

    @Override
    public List<Prescription> showPrescriptionsByConsultation(int consultationId) {
        List<Prescription> prescriptions=daoPrescription.findByConsultation(consultationId);
        for(Prescription p:prescriptions){
            Medicament medoc=daoMedicament.findById(p.getMedicament().getId());
            p.getMedicament().setLibelle(medoc.getLibelle());
        }
        return prescriptions;
    }

    @Override
    public Medicament findMedicamentById(int idMedicament) {
        return daoMedicament.findById(idMedicament);
    }

    @Override
    public List<Patient> findAllPatients() {
        return daoPatient.findAll();
    }

    @Override
    public int annulerConsultation(Consultation consultation) {
        return daoConsultation.updateAnnuler(consultation);
    }

    @Override
    public List<User> showAllUsers() {
        return daoUser.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return daoUser.findUserByLogin(login);
    }

    @Override
    public int addUser(User user) {
        return daoUser.insert(user);
    }

    @Override
    public int addUserMedecin(User user, int spe_id) {
        return daoUser.insertUser(user, spe_id);
    }

    @Override
    public List<Consultation> showAllConsultationsByDateDoctorAndEtat(String statut, Medecin medecin, Date date) {
        List<Consultation> consultations=daoConsultation.selectByDateDoctorAndEtat(statut, medecin, date);
        for(Consultation consul:consultations){
            Patient pat=daoPatient.findById(consul.getPatient().getId());
            consul.getPatient().setNomComplet(pat.getNomComplet());
            String libelle=daoTypeConsultation.findLibelleById(consul.getType().getId());
            consul.getType().setLibelle(libelle);  
        }
        return consultations;
    }
}

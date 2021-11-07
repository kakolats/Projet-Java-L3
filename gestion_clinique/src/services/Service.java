/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MedecinDao;
import dao.PatientDao;
import dao.RendezVousDao;
import dao.TypeConsultationDao;
import dao.TypePrestationDao;
import dao.UserDao;
import entities.Medecin;
import entities.Patient;
import entities.RendezVous;
import entities.TypeConsultation;
import entities.TypePrestation;
import entities.TypeService;
import entities.User;
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
    
}

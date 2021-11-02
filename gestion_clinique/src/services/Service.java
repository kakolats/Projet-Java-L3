/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PatientDao;
import dao.RendezVousDao;
import dao.TypeConsultationDao;
import dao.TypePrestationDao;
import dao.UserDao;
import entities.Patient;
import entities.RendezVous;
import entities.TypeConsultation;
import entities.TypePrestation;
import entities.User;
import java.util.List;

/**
 *
 * @author HP
 */
public class Service implements IService {
    
    UserDao daoUser=new UserDao();
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
    
}

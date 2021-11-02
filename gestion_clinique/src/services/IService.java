/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public interface IService {
    
    public User login(String login,String password);
    public int addPatient(Patient user);
    public List<TypeConsultation> showAllTypeConsultation();
    public List<TypePrestation> showAllTypePrestation();
    public int addRendezVous(RendezVous rdv);
}

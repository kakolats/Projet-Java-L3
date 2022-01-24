/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.RendezVous;
import entities.TypeService;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ConsultationDao implements IDao<Consultation> {
    
    private final Database database=new Database();
    private final String SQL_ALL_BY_ID_PATIENT="select * from consultation where patient_id=?";
    private final String SQL_COUNT_BY_MEDECIN="select count(*) from consultation where medecin_id=? and date=? and statut='EN ATTENTE'";
    private final String SQL_INSERT="insert into consultation (date,patient_id,medecin_id,specialite_id,statut) values(?,?,?,?,"
            + "'EN ATTENTE')";
    private final String SQL_ALL_BY_ETAT="select * from consultation where statut=? and medecin_id=?";
    private final String SQL_UPDATE="update consultation set constantes=?,statut='TERMINE' where id=?";
    private final String SQL_UPDATE_ANNULE="update consultation set statut='ANNULE' where id=?";
    private final String SQL_ALL_BY_DATE="select * from consultation where statut=? and medecin_id=? and date=?";
    
    
    public int countByMedecin(int idMedecin,Date date){
        int count=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_COUNT_BY_MEDECIN);
        try {
            database.getPs().setInt(1,idMedecin);
            database.getPs().setDate(2,date);
            ResultSet rs=database.executeSelect(SQL_COUNT_BY_MEDECIN);
            if(rs.next()){
                count=rs.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return count;
    }
    
    @Override
    public int insert(Consultation consultation) {
        int id=0;
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setDate(1,consultation.getDate());
            database.getPs().setInt(2,consultation.getPatient().getId());
            database.getPs().setInt(3,consultation.getMedecin().getId());
            database.getPs().setInt(4,consultation.getSpecialite_id());
            database.executeUpdate(SQL_INSERT);
            
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return id;
    }

    public List<Consultation> findByIdPatient(int idPatient){
        List<Consultation> consultations= new ArrayList<Consultation>();
        Patient patient=new Patient(idPatient);
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ID_PATIENT);
            database.getPs().setInt(1,idPatient);
            ResultSet rs= database.executeSelect(SQL_ALL_BY_ID_PATIENT);
            while(rs.next()){
                Consultation consul=new Consultation(rs.getDate("date"),rs.getString("statut"),rs.getInt("specialite_id"));
                Medecin med=new Medecin();
                med.setId(rs.getInt("medecin_id"));
                if(consul.getStatut().equals("TERMINE")){
                    consul.setConstantes(rs.getString("constantes"));
                }
                consul.setMedecin(med);
                consultations.add(consul);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }                  
        
        return consultations;
    }
    
    public List<Consultation> selectAllByEtat(String statut,Medecin medecin){
        List<Consultation> consultations= new ArrayList<Consultation>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ETAT);
            database.getPs().setString(1,statut);
            database.getPs().setInt(2,medecin.getId());
            ResultSet rs=database.executeSelect(SQL_ALL_BY_ETAT);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("specialite_id"));
                Consultation consultation=new Consultation(rs.getDate("date"),rs.getString("statut"),type);
                Patient patient=new Patient(rs.getInt("patient_id"));
                if(consultation.getStatut().equals("TERMINE")){
                    consultation.setConstantes(rs.getString("constantes"));  
                }
                consultation.setSpecialite_id(rs.getInt("specialite_id"));
                consultation.setPatient(patient);
                consultation.setId(rs.getInt("id"));
                consultations.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return consultations;
    }
    
    public List<Consultation> selectByDateDoctorAndEtat(String statut,Medecin medecin,Date date){
        List<Consultation> consultations= new ArrayList<Consultation>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_DATE);
            database.getPs().setString(1,statut);
            database.getPs().setInt(2,medecin.getId());
            database.getPs().setDate(3,date);
            ResultSet rs=database.executeSelect(SQL_ALL_BY_DATE);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("specialite_id"));
                Consultation consultation=new Consultation(rs.getDate("date"),rs.getString("statut"),type);
                Patient patient=new Patient(rs.getInt("patient_id"));
                if(consultation.getStatut().equals("TERMINE")){
                    consultation.setConstantes(rs.getString("constantes"));  
                }
                consultation.setSpecialite_id(rs.getInt("specialite_id"));
                consultation.setPatient(patient);
                consultation.setId(rs.getInt("id"));
                consultations.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return consultations;
    }
    
    @Override
    public int update(Consultation consultation) {
        int id=0;
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE);
            database.getPs().setString(1,consultation.getConstantes());
            database.getPs().setInt(2,consultation.getId());
            database.executeUpdate(SQL_UPDATE);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return id;
    }

    public int updateAnnuler(Consultation consultation){
        int id=0;
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE_ANNULE);
            database.getPs().setInt(1,consultation.getId());
            id=database.executeUpdate(SQL_UPDATE_ANNULE);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return id;
    }
    
    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

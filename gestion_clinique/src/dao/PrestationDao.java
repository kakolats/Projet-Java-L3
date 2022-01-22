/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import entities.Prestation;
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
public class PrestationDao implements IDao<Prestation> {
    private final Database database=new Database();
    private final String SQL_COUNT_BY_TYPE="select count(*) from prestation where type_id=? and date=? and etat='EN ATTENTE'";
    private final String SQL_ALL_BY_ETAT="select * from prestation where etat=? and date IS NOT NULL";
    private final String SQL_ALL_WITHOUT_DATE="select * from prestation where date IS NULL";
    private final String SQL_ALL_BY_ID_PATIENT="select * from prestation where patient_id=?";
    private final String SQL_ALL_BY_ID_CONSULTATION="select * from prestation where consultation_id=?";
    private final String SQL_INSERT="insert into prestation (date,etat,type_id,patient_id) values(?,'EN ATTENTE',?,?)";
    private final String SQL_INSERT_WITHOUT_DATE="insert into prestation (etat,type_id,patient_id,consultation_id) values('EN ATTENTE',?,?,?)";
    private final String SQL_UPDATE="update prestation SET etat='TERMINE',resultats=? where id=?";
    private final String SQL_UPDATE_DATE="update prestation set date=? where id=?";
    private final String SQL_UPDATE_ANNULE="update prestation set etat=ANNULE where id=?";
    
    
    public int countByType(int idType,Date date){
        int count=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_COUNT_BY_TYPE);
        try {
            database.getPs().setInt(1,idType);
            database.getPs().setDate(2,date);
            ResultSet rs=database.executeSelect(SQL_COUNT_BY_TYPE);
            if(rs.next()){
                count=rs.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return count;
    }

    @Override
    public int insert(Prestation prestation) {
        int id=0;  
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setDate(1,prestation.getDate());
            database.getPs().setInt(2,prestation.getType().getId());
            database.getPs().setInt(3,prestation.getPatient().getId());
            
            database.executeUpdate(SQL_INSERT);
            
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           database.closeConnexion(); 
        }
        
        return id;
    }
    
    public int insertWithoutDate(Prestation prestation){
        int id=0;  
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT_WITHOUT_DATE);
            database.getPs().setInt(1,prestation.getType().getId());
            database.getPs().setInt(2,prestation.getPatient().getId());
            database.getPs().setInt(3,prestation.getConsultation_id());
            database.executeUpdate(SQL_INSERT_WITHOUT_DATE);
            
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           database.closeConnexion(); 
        }
        
        return id;
    }
    
    public List<Prestation> findByIdPatient(int idPatient){
        List<Prestation> prestations= new ArrayList<Prestation>();
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ID_PATIENT);
            database.getPs().setInt(1,idPatient);
            
            ResultSet rs=database.executeSelect(SQL_ALL_BY_ID_PATIENT);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("type_id"));
                Prestation prestation=new Prestation(rs.getDate("date"),rs.getString("etat"),type);
                Patient patient=new Patient(rs.getInt("patient_id"));
                if(prestation.getEtat().equals("TERMINE")){
                    prestation.setResultats(rs.getString("resultats"));
                }
                prestation.setPatient(patient);
                prestations.add(prestation);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return prestations;
    }
    
    public List<Prestation> findByIdConsultation(int idConsultation){
        List<Prestation> prestations= new ArrayList<Prestation>();
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ID_CONSULTATION);
            database.getPs().setInt(1,idConsultation);
            
            ResultSet rs=database.executeSelect(SQL_ALL_BY_ID_CONSULTATION);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("type_id"));
                Prestation prestation=new Prestation(rs.getDate("date"),rs.getString("etat"),type);
                Patient patient=new Patient(rs.getInt("patient_id"));
                prestation.setPatient(patient);
                if(prestation.getEtat().equals("TERMINE")){
                    prestation.setResultats(rs.getString("resultats"));
                }
                prestations.add(prestation);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return prestations;
    }

    public List<Prestation> selectAllByEtat(String etat){
        List<Prestation> prestations= new ArrayList<Prestation>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ETAT);
            database.getPs().setString(1,etat);
            ResultSet rs=database.executeSelect(SQL_ALL_BY_ETAT);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("type_id"));
                Prestation prestation=new Prestation(rs.getDate("date"),rs.getString("etat"),type);
                Patient patient=new Patient(rs.getInt("patient_id"));
                prestation.setPatient(patient);
                prestation.setId(rs.getInt("id"));
                if(etat.equals("TERMINE")){
                    prestation.setResultats(rs.getString("resultats"));
                }
                prestations.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return prestations;
    }
    
    public List<Prestation> selectAllWithoutDate(){
        List<Prestation> prestations= new ArrayList<Prestation>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_WITHOUT_DATE);
            ResultSet rs=database.executeSelect(SQL_ALL_WITHOUT_DATE);
            while(rs.next()){
                TypeService type=new TypeService(rs.getInt("type_id"));
                Patient patient=new Patient(rs.getInt("patient_id"));
                Prestation prestation=new Prestation(rs.getString("etat"),type,patient);
                prestation.setId(rs.getInt("id"));
                prestations.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return prestations;
    }
    
    
    @Override
    public int update(Prestation prestation) {
        int id=0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE);
            database.getPs().setString(1,prestation.getResultats());
            database.getPs().setInt(2,prestation.getId());
            database.executeUpdate(SQL_UPDATE);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return id;
    }
    
    public int updateDate(Prestation prestation) {
        int id=0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE_DATE);
            database.getPs().setDate(1,prestation.getDate());
            database.getPs().setInt(2,prestation.getId());
            database.executeUpdate(SQL_UPDATE_DATE);
            /*
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return id;
    }
    
    public int updateAnnuler(Prestation prestation) {
        int id=0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE_ANNULE);
            database.getPs().setInt(1,prestation.getId());
            database.executeUpdate(SQL_UPDATE_ANNULE);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return id;
    }
    
    

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }
    
}

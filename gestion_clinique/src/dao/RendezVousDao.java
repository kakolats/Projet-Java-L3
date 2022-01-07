/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import entities.RendezVous;
import entities.TypeService;
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
public class RendezVousDao implements IDao<RendezVous> {
    
    private final Database database=new Database();
    private final String SQL_ALL_EN_COURS="select * from rendez_vous where statut='En Cours'";
    private final String SQL_ALL_BY_ID_PATIENT="select * from rendez_vous where patient_id=?";
    private final String SQL_INSERT_PRESTATION="insert into rendez_vous (date,prestation_type_id,patient_id) values(?,?,?)";
    private final String SQL_INSERT_CONSULTATION="insert into rendez_vous (date,specialite_id,patient_id) values(?,?,?)";
    private final String SQL_DELETE="delete * from rendz_vous where id=?";
    private final String SQL_UPDATE="update rendez_vous SET statut=? where id=?";

    @Override
    public int insert(RendezVous rdv) {
        int id=0;
         
        try {
         
        database.openConnexion();
        if(rdv.getTypeC()==null){
            database.initPrepareStatement(SQL_INSERT_PRESTATION);
            database.getPs().setDate(1,rdv.getDate());
            database.getPs().setInt(2,rdv.getTypeP().getId());
            database.getPs().setInt(3,rdv.getPatient().getId());
            database.executeUpdate(SQL_INSERT_PRESTATION);
        }else{
            database.initPrepareStatement(SQL_INSERT_CONSULTATION);
            database.getPs().setDate(1,rdv.getDate());
            database.getPs().setInt(2,rdv.getTypeC().getId());
            database.getPs().setInt(3,rdv.getPatient().getId());
            database.executeUpdate(SQL_INSERT_CONSULTATION);
        }
        
         
        ResultSet rs=database.getPs().getGeneratedKeys();
        if(rs.next()){
            id = rs.getInt(1);
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        
        return id;
    }
    
    
    

    @Override
    public int update(RendezVous ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int update(String statut,int idRdv){
        int id=0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_UPDATE);
            database.getPs().setString(1,statut);
            database.getPs().setInt(2,idRdv);
            id=database.executeUpdate(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<RendezVous> findAll() {
        List<RendezVous> rendezVous= new ArrayList<RendezVous>();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL_EN_COURS);
        ResultSet rs= database.executeSelect(SQL_ALL_EN_COURS);
        try {
            while(rs.next()){
                RendezVous rdv=new RendezVous(rs.getString("statut"),rs.getDate("date"));
                TypeService typeS;
                if(rs.getInt("specialite_id")!=0){
                    typeS=new TypeService(rs.getInt("specialite_id"));
                    typeS.setType("Consultation");
                    rdv.setTypeS(typeS);
                }
                if(rs.getInt("prestation_type_id")!=0){
                    typeS=new TypeService(rs.getInt("prestation_type_id"));
                    typeS.setType("Prestation");
                    rdv.setTypeS(typeS);
                }
                Patient patient=new Patient(rs.getInt("patient_id"));
                rdv.setPatient(patient);
                rdv.setId(rs.getInt("id"));
                rendezVous.add(rdv);
                
            }  
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);  
        }
        database.closeConnexion();
        return rendezVous;
    }

    @Override
    public RendezVous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<RendezVous> findByIdPatient(int idPatient){
        List<RendezVous> rendezVous= new ArrayList<RendezVous>();
        Patient patient=new Patient(idPatient);
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_ID_PATIENT);
            database.getPs().setInt(1,idPatient);
            ResultSet rs= database.executeSelect(SQL_ALL_BY_ID_PATIENT);
            while(rs.next()){
                RendezVous rdv=new RendezVous(rs.getString("statut"),rs.getDate("date"));
                TypeService typeS;
                if(rs.getInt("specialite_id")!=0){
                    typeS=new TypeService(rs.getInt("specialite_id"));
                    typeS.setType("Consultation");
                    rdv.setTypeS(typeS);
                }
                if(rs.getInt("prestation_type_id")!=0){
                    typeS=new TypeService(rs.getInt("prestation_type_id"));
                    typeS.setType("Prestation");
                    rdv.setTypeS(typeS);
                }
                
                rendezVous.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }                  
        
        return rendezVous;
    }
    
    
}

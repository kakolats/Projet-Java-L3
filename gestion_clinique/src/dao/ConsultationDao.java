/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Consultation;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ConsultationDao implements IDao<Consultation> {
    
    private final Database database=new Database();
    private final String SQL_COUNT_BY_MEDECIN="select count(*) from consultation where medecin_id=? and date=?";
    private final String SQL_INSERT="insert into consultation (date,patient_id,medecin_id,specialite_id) values(?,?,?,?)";
    
    
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

    @Override
    public int update(Consultation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

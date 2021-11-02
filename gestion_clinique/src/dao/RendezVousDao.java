/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.RendezVous;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class RendezVousDao implements IDao<RendezVous> {
    
    private final Database database=new Database();
    private final String SQL_INSERT_PRESTATION="insert into rendez_vous (date,prestation_type_id,patient_id) values(?,?,?)";
    private final String SQL_INSERT_CONSULTATION="insert into rendez_vous (date,specialite_id,patient_id) values(?,?,?)";

    @Override
    public int insert(RendezVous rdv) {
        int id=0;
        if(rdv.getTypeC()==null){  
            try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT_PRESTATION);
            database.getPs().setDate(1,rdv.getDate());
            database.getPs().setInt(2,rdv.getTypeP().getId());
            database.getPs().setInt(3,rdv.getPatient().getId());
            database.executeUpdate(SQL_INSERT_PRESTATION);
         
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                database.closeConnexion();
            }
        }else{
            try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT_CONSULTATION);
            database.getPs().setDate(1,rdv.getDate());
            database.getPs().setInt(2,rdv.getTypeC().getId());
            database.getPs().setInt(3,rdv.getPatient().getId());
            database.executeUpdate(SQL_INSERT_CONSULTATION);
         
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(RendezVousDao.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                database.closeConnexion();
            }
        }
        
        return id;
    }
    
    
    

    @Override
    public int update(RendezVous ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RendezVous> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RendezVous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

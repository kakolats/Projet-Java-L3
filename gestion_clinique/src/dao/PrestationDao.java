/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Prestation;
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
public class PrestationDao implements IDao<Prestation> {
    private final Database database=new Database();
    private final String SQL_COUNT_BY_TYPE="select count(*) from prestation where type_id=? and date=?";
    private final String SQL_INSERT="insert into prestation (date,etat,type_id) values(?,'EN ATTENTE',?)";
    
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

    @Override
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

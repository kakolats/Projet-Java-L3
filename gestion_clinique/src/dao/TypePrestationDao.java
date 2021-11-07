/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TypePrestation;
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
public class TypePrestationDao implements IDao<TypePrestation> {
    
    private Database database=new Database();
    private final String SQL_ALL="select * from prestation_type";
    private final String SQL_BY_ID="select libelle from prestation_type where id=?";

    @Override
    public int insert(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypePrestation> findAll() {
        List<TypePrestation> types=new ArrayList<>();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_ALL);
            ResultSet rs =database.executeSelect(SQL_ALL);
            while(rs.next()){
                TypePrestation type=new TypePrestation(rs.getInt("id"),rs.getString("libelle"));
                types.add(type);
            }      
        } catch (SQLException ex) {
            Logger.getLogger(TypeConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return types;
    }

    @Override
    public TypePrestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String findLibelleById(int id){
        String libelle="";
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_BY_ID);
            database.getPs().setInt(1,id);
            ResultSet rs=database.executeSelect(SQL_BY_ID);
            if(rs.next()){
                libelle=rs.getString("libelle");
            }
                        
        } catch (SQLException ex) {
            Logger.getLogger(TypeConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return libelle;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
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
public class MedecinDao implements IDao<Medecin> {

    private final Database database=new Database();
    private final String SQL_ALL_BY_CONSULTATION="select * from user where specialite_id=?";
    private final String SQL_BY_ID="select * from user where id=?";
    @Override
    public int insert(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medecin findById(int id) {
        Medecin medecin=new Medecin();
        try {
            
            database.openConnexion();
            database.initPrepareStatement(SQL_BY_ID);
            database.getPs().setInt(1,id);
            ResultSet rs=database.executeSelect(SQL_BY_ID);
            if(rs.next()){
                medecin.setNomComplet(rs.getString("nom_complet"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return medecin;
    }
    
    public List<Medecin> findByTypeConsultation(TypeService type){
        List<Medecin> listeMedecins= new ArrayList<Medecin>();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_CONSULTATION);
            database.getPs().setInt(1,type.getId());
            
            ResultSet rs=database.executeSelect(SQL_ALL_BY_CONSULTATION);
            
            while(rs.next()){
                
                Medecin med=new Medecin(rs.getInt("id"),rs.getString("nom_complet"));
                listeMedecins.add(med);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        
        return listeMedecins;
    }
    
}

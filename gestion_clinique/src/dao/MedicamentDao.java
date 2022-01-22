/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medicament;
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
public class MedicamentDao implements IDao<Medicament> {
    
    private final Database database=new Database();
    private final String SQL_ALL="select * from medicament";
    private final String SQL_BY_ID="select * from medicament where id=?";

    @Override
    public int insert(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medicaments=new ArrayList<Medicament>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL);
            ResultSet rs=database.executeSelect(SQL_ALL);
            while(rs.next()){
                Medicament medicament=new Medicament(rs.getInt("id"),rs.getString("libelle"));
                medicaments.add(medicament);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }        
        return medicaments;
    }

    @Override
    public Medicament findById(int id) {
        Medicament medoc=new Medicament();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=database.executeSelect(SQL_BY_ID);
            if(rs.next()){
                medoc.setLibelle(rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return medoc;
    }
    
}

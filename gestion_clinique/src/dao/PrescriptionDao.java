/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medicament;
import entities.Prescription;
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
public class PrescriptionDao implements IDao<Prescription> {
    
    private final Database database=new Database();
    private final String SQL_ALL_BY_CONSULTATION="select * from prescription where consultation_id=?";
    private final String SQL_INSERT="insert into prescription (medicament_id,consultation_id,posologie) "
            + "values (?,?,?)";

    @Override
    public int insert(Prescription presta) {
        int id=0;

        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setInt(1,presta.getMedicament().getId());
            database.getPs().setInt(2,presta.getConsultation_id());
            database.getPs().setString(3,presta.getPosologie());
            database.executeUpdate(SQL_INSERT);
            
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return id;
    }

    @Override
    public int update(Prescription ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prescription> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prescription findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Prescription> findByConsultation(int consultation_id){
        List<Prescription> prescriptions=new ArrayList<Prescription>();
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL_BY_CONSULTATION);
            database.getPs().setInt(1,consultation_id);
            ResultSet rs=database.executeSelect(SQL_ALL_BY_CONSULTATION);
            while(rs.next()){
                Medicament medi=new Medicament(rs.getInt("medicament_id"));
                Prescription prescri=new Prescription(medi,rs.getString("posologie"));
                prescriptions.add(prescri);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return prescriptions;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
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
public class PatientDao implements IDao<Patient> {
    
    
    
    private final String SQL_INSERT = "INSERT INTO `user` "
            + " ( `login`, `password`, `nom_complet`,`antecedents`,`role`) "
            + " VALUES ( ?,?,?,?,'ROLE_PATIENT')";
    private final String SQL_BY_ID="select * from user where id=?";
    private final String SQL_ALL="select * from user where role='ROLE_PATIENT'";
    private final Database database=new Database();
    
    @Override
    public int insert(Patient user) {
        int id=0;
        database.openConnexion();
        try {
            
            database.initPrepareStatement(SQL_INSERT);
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1,user.getLogin());
            database.getPs().setString(2, user.getPassword());
            database.getPs().setString(3, user.getNomComplet());
            database.getPs().setString(4, user.getAntecedents());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return id;
    }

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients=new ArrayList<Patient>();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
        ResultSet rs=database.executeSelect(SQL_ALL);
        try {
            while(rs.next()){
                Patient pat=new Patient(rs.getInt("id"),rs.getString("nom_complet"));
                patients.add(pat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient patient=new Patient(id);
        System.out.println(id);
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_BY_ID);
            database.getPs().setInt(1,id);
            ResultSet rs=database.executeSelect(SQL_BY_ID);
            if(rs.next()){
                patient.setNomComplet(rs.getString("nom_complet"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return patient;         
    }
    
}

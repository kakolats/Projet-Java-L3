/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
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
public class UserDao implements IDao<User> {
    
    private final String  SQL_LOGIN = "SELECT * FROM user WHERE login =  ? AND password = ? ";
    private final String SQL_ALL="SELECT * from user where role!='ROLE_PATIENT' and role!='ROLE_ADMIN'";
    private final String SQL_BY_LOGIN="SELECT * from user where login=?";
    private final String SQL_INSERT="insert into user (login,password,nom_complet,role) values(?,?,?,?)";
    private final String SQL_INSERT_USER="insert into user (login,password,nomComplet,role,specialite_id) values(?,?,?,?,?)";
    private final Database database= new Database();

    @Override
    public int insert(User user) {
        int id=0;
        database.openConnexion();
        try {
            
            database.initPrepareStatement(SQL_INSERT);
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1,user.getLogin());
            database.getPs().setString(2, user.getPassword());
            database.getPs().setString(3, user.getNomComplet());
            database.getPs().setString(4, user.getRole());
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
    
    public int insertUser(User user,int spe_id){
        int id=0;
        database.openConnexion();
        try {
            
            database.initPrepareStatement(SQL_INSERT);
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1,user.getLogin());
            database.getPs().setString(2, user.getPassword());
            database.getPs().setString(3, user.getNomComplet());
            database.getPs().setString(4, user.getRole());
            database.getPs().setInt(5,spe_id);
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
    public int update(User ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        List<User> users=new ArrayList<User>();
        
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_ALL);
            ResultSet rs=database.executeSelect(SQL_ALL);
            while(rs.next()){
                User user =new User();
                user.setId(rs.getInt("id"));
                user.setNomComplet(rs.getString("nom_complet"));
                user.setLogin(rs.getString("login"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        
        return users;
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User findUserLoginAndPassword(String login,String password){
        User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_LOGIN);
        try {
            database.getPs().setString(1, login);
            database.getPs().setString(2, password);
            ResultSet rs = database.executeSelect(SQL_LOGIN);
        
            if(rs.next())
            {
                    user = new User(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("role") 
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public User findUserByLogin(String login){
        User user = null;
        database.openConnexion();
        database.initPrepareStatement(SQL_BY_LOGIN);
        try {
            database.getPs().setString(1, login);
            ResultSet rs = database.executeSelect(SQL_BY_LOGIN);
        
            if(rs.next())
            {
                    user = new User(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("role") 
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
}

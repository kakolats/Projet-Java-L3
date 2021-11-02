/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CreationCompteController implements Initializable {

    private final Service service=new Service();
    @FXML
    private TextField txtfPassword;
    @FXML
    private TextField txtfLogin;
    @FXML
    private TextField txtfNomComplet;
    @FXML
    private TextArea txtaAntecedents;
    @FXML
    private Text txtError;
    
    private CreationCompteController ctrl;
    private Patient user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl=this;
    }    

    @FXML
    private void handleAnnuler(ActionEvent event) {
        txtError.setVisible(false);
        txtfLogin.clear();
        txtfPassword.clear();
        txtfNomComplet.clear();
        txtaAntecedents.clear();
    }

    @FXML
    private void handleInscription(ActionEvent event) {
        String login = txtfLogin.getText().trim();
        String password=txtfPassword.getText().trim();
        String nomComplet=txtfNomComplet.getText().trim();
        String antecedents=txtaAntecedents.getText().trim();
        user=new Patient(antecedents,nomComplet,login,password,"ROLE_PATIENT");
        if(login.isEmpty() || password.isEmpty()||nomComplet.isEmpty()||antecedents.isEmpty()){
            txtError.setText("Tous les champs sont obligatoires");
            txtError.setVisible(true);
        }else{
            
            if(service.login(login,password)!=null){
                txtError.setText("Ce compte existe deja");
                txtError.setVisible(true);
            }else{
                if(service.addPatient(user)!=0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Inscription");
                    alert.setContentText("Inscription reussie ");
                    alert.show();
                }
            }
        }
    }

    public CreationCompteController getCtrl() {
        return ctrl;
    }

    public void setCtrl(CreationCompteController ctrl) {
        this.ctrl = ctrl;
    }

    public Patient getUser() {
        return user;
    }

    public void setUser(Patient user) {
        this.user = user;
    }
    
    
}

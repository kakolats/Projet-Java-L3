/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConnexionController implements Initializable {
    
    

    @FXML
    private TextField txtfLogin;
    @FXML
    private TextField txtfPasword;
    @FXML
    private Label txtError;
    
    private static ConnexionController ctrl;
    
    private User user;
    private final Service service=new Service();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl=this;
        // TODO
    }    

    @FXML
    private void handleAnnuler(ActionEvent event) {
        txtError.setVisible(false);
        txtfLogin.clear();
        txtfPasword.clear();        
    }

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login = txtfLogin.getText().trim();
        String password = txtfPasword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
          user = service.login(login, password);
          if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              //Cache la fénétre de connexion
              this.txtError.getScene().getWindow().hide();
              AnchorPane root = null;
              
              try {
                  root = FXMLLoader.load(getClass().getResource("/views/v_home.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
    }

    @FXML
    private void handleRedirectionCreation(ActionEvent event) {
        this.txtError.getScene().getWindow().hide();
        AnchorPane root = null;           
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_CreationCompte.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public static ConnexionController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(ConnexionController ctrl) {
        ConnexionController.ctrl = ctrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
    
    
}

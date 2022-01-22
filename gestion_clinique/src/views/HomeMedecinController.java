/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeMedecinController implements Initializable {

    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Button btnDeconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadView("v_listeAllConsultation");
    }    
    
    private void loadView(String view){
        try {
            AnchorPane root;
            root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
            anchorContent.getChildren().clear();
            anchorContent.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
        AnchorPane root;
        try
        {
            Stage stage = (Stage) btnDeconnexion.getScene().getWindow();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 =  new Stage();
            stage1.setScene(scene);
            stage1.show();
                   
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleDossierMedicaux(ActionEvent event) {
        loadView("v_dossierMedical");
    }

    @FXML
    private void handleListConsultations(ActionEvent event) {
        loadView("v_listeAllConsultation");
    }
    
}

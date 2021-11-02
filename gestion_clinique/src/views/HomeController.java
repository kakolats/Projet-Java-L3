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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadView("v_demande");
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
    private void handleLoadViewDemande(ActionEvent event) {
        loadView("v_demande");
    }

    @FXML
    private void handleLoadViewListeRdv(ActionEvent event) {
        loadView("v_listeConsultation");
    }

    @FXML
    private void handleLoadViewListeConsultations(ActionEvent event) {
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.RendezVous;
import entities.TypeConsultation;
import entities.TypePrestation;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DemandeController implements Initializable {

    private Service service=new Service();
    ObservableList<TypePrestation> prestations;
    ObservableList<TypeConsultation> consultations;
    
    @FXML
    private DatePicker dfDate;
    @FXML
    private ComboBox<String> cboType;
    @FXML
    private ComboBox<TypePrestation> cboTypePrestation;
    @FXML
    private ComboBox<TypeConsultation> cboTypeConsultation;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadComboType(cboType);
        loadComboBoxTypePrestation(cboTypePrestation);
        loadComboBoxTypeConsultation(cboTypeConsultation); 
        dateHandler(dfDate);
    }  
    
    private void loadComboBoxTypePrestation(ComboBox<TypePrestation> cbo){
        prestations=FXCollections.observableArrayList(service.showAllTypePrestation());
        cbo.setItems(prestations);
        cbo.setVisible(false);
    }
    
    private void loadComboBoxTypeConsultation(ComboBox<TypeConsultation> cbo){
        consultations=FXCollections.observableArrayList(service.showAllTypeConsultation());
        cbo.setItems(consultations);
        cbo.setVisible(false);
    }
    
    
    
    
    @FXML
    private void handleDemande(ActionEvent event) {
        TypePrestation typeP=cboTypePrestation.getSelectionModel().getSelectedItem();
        TypeConsultation typeC=cboTypeConsultation.getSelectionModel().getSelectedItem();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.valueOf(dfDate.getValue());
        Patient patient=new Patient(ConnexionController.getCtrl().getUser().getId());
        
        if(typeP!=null){            
            RendezVous rdv=new RendezVous(0,date,typeP,patient);
            int id=service.addRendezVous(rdv); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande de rendez-vous ajoutee avec succes");
            alert.setContentText("Demande reussie ");
            alert.show();
        }else{
            if(typeC!=null){
                RendezVous rdv=new RendezVous(0,date,typeC,patient);
                int id=service.addRendezVous(rdv); 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Demande de rendez-vous ajoutee avec succes");
                alert.setContentText("Demande reussie ");
                alert.show();
            }
        }
        
        
    }
    
    private void loadComboType(ComboBox<String> cboType){ 
        cboType.getItems().add("Consultation");
        cboType.getItems().add("Prestation");
    }

    @FXML
    private void handleTransition(ActionEvent event) {
        String type=cboType.getSelectionModel().getSelectedItem();
        if(type.equals("Consultation")){
            cboTypePrestation.setDisable(true);
            cboTypePrestation.setVisible(false);
            cboTypeConsultation.setDisable(false);
            cboTypeConsultation.setVisible(true);
        }
        if(type.equals("Prestation")){
            cboTypeConsultation.setDisable(true);
            cboTypeConsultation.setVisible(false);
            cboTypePrestation.setDisable(false);
            cboTypePrestation.setVisible(true);
        }
    }

    @FXML
    private void handleAnnulation(ActionEvent event) {
    }
    
    private void dateHandler(DatePicker dp){
        dp.setDayCellFactory(picker -> new DateCell() 
        { 
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
        });  
    }
    
}

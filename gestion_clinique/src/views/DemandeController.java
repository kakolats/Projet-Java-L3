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
import entities.TypeService;
import entities.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DemandeController implements Initializable {

    private Service service=new Service();
    ObservableList<TypeService> prestations;
    ObservableList<TypeService> consultations;
    
    @FXML
    private DatePicker dfDate;
    @FXML
    private ComboBox<String> cboType;
    @FXML
    private ComboBox<TypeService> cboTypeService;
    @FXML
    private Text txtError;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadComboType(cboType);
        loadComboBoxTypeConsultation(cboTypeService);
        dateHandler(dfDate);
        //System.out.println(Date.valueOf(dfDate.getValue())==null);
    }  
    
    private void loadComboBoxTypePrestation(ComboBox<TypeService> cbo){
        prestations=FXCollections.observableArrayList(service.showAllTypePrestation());
        cbo.setItems(prestations);
        cbo.getSelectionModel().selectFirst();    }
    
    private void loadComboBoxTypeConsultation(ComboBox<TypeService> cbo){
        consultations=FXCollections.observableArrayList(service.showAllTypeConsultation());
        cbo.setItems(consultations);
        cbo.getSelectionModel().selectFirst();    }
    
    
    
    
    
    @FXML
    private void handleDemande(ActionEvent event) {
        if(dfDate.getValue()==null){
            Util.makeAlert("Veuillez selectionner une date");
        }else{
            String type=cboType.getSelectionModel().getSelectedItem();
            TypeService typeS=cboTypeService.getSelectionModel().getSelectedItem();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date date = Date.valueOf(dfDate.getValue());
            Patient patient=new Patient(ConnexionController.getCtrl().getUser().getId());
            if(type.equals("Consultation")){
            TypeConsultation typeC=new TypeConsultation(typeS.getId(),typeS.getLibelle());
            RendezVous rdv=new RendezVous(date,typeC,patient);
            service.addRendezVous(rdv);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande");
            alert.setContentText("Demande effectuee avec succes");
            alert.show();
            }else if(type.equals("Prestation")){
                TypePrestation typeP=new TypePrestation(typeS.getId(),typeS.getLibelle());
                RendezVous rdv=new RendezVous(date,typeP,patient);
                service.addRendezVous(rdv);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Demande");
                alert.setContentText("Demande effectuee avec succes");
                alert.show();
            }
        }
        
        
        }
        
        
    
    
    private void loadComboType(ComboBox<String> cboType){ 
        cboType.getItems().add("Consultation");
        cboType.getItems().add("Prestation");
        cboType.getSelectionModel().selectFirst();
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
        //dp.setValue(LocalDate.now());
    }

    @FXML
    private void handleTransition(Event event) {
        String type=cboType.getSelectionModel().getSelectedItem();
        if(type.equals("Consultation")){
            loadComboBoxTypeConsultation(cboTypeService);
        }
        if(type.equals("Prestation")){
            loadComboBoxTypePrestation(cboTypeService);
        }
    }
    
}

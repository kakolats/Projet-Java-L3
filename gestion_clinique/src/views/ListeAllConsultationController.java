/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.TypeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeAllConsultationController implements Initializable {

    private Service service = new Service();
    private static ListeAllConsultationController ctrl;
    private Consultation consultationSelected;
    Medecin medecin=new Medecin(ConnexionController.getCtrl().getUser().getId());
    
    ObservableList<Consultation> obvConsultation;
    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation,Date> tblcDate;
    @FXML
    private TableColumn<Consultation,String> tblcStatut;
    @FXML
    private TableColumn<Consultation,Patient> tblcPatient;
    @FXML
    private TableColumn<Consultation,TypeService> tblcType;
    @FXML
    private ComboBox<String> cboFiltre;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Button btnDetails;
    @FXML
    private Button btnMakeConsultation;
    @FXML
    private Button btnAnnulation;
    @FXML
    private DatePicker dateFilter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadComboBox(cboFiltre);
        loadTableView(service.showAllConsultationsByEtat("EN ATTENTE",medecin));
        btnDetails.setDisable(true);
        btnMakeConsultation.setDisable(false);
        ctrl=this;
        
        //loadTableView(service.)
    }    
    
    public void loadTableView(List<Consultation> consultations){
        obvConsultation=FXCollections.observableArrayList(consultations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvConsultations.setItems(obvConsultation);
    }


    @FXML
    private void handleFiltre(ActionEvent event) {
        if(cboFiltre.getSelectionModel().getSelectedItem().equals("EN ATTENTE")){
            loadTableView(service.showAllConsultationsByEtat("EN ATTENTE",medecin));
            btnDetails.setDisable(true);
            btnMakeConsultation.setDisable(false);
            
        }else{
            loadTableView(service.showAllConsultationsByEtat("TERMINE",medecin));
            btnDetails.setDisable(false);
            btnMakeConsultation.setDisable(true);
        }
    }
    
    
    private void loadComboBox(ComboBox<String> cboType){ 
        cboType.getItems().add("EN ATTENTE");
        cboType.getItems().add("TERMINE");
        cboType.getSelectionModel().selectFirst();
    }

    public static ListeAllConsultationController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(ListeAllConsultationController ctrl) {
        ListeAllConsultationController.ctrl = ctrl;
    }

    public Consultation getConsultationSelected() {
        return consultationSelected;
    }

    public void setConsultationSelected(Consultation consultationSelected) {
        this.consultationSelected = consultationSelected;
    }
    

    @FXML
    private void handleSelectConsultation(MouseEvent event) {
        consultationSelected=tblvConsultations.getSelectionModel().getSelectedItem();
        //System.out.println(consultationSelected.getId());
    }

    @FXML
    private void handleSelectMakeConsultation(ActionEvent event) {
        if(consultationSelected!=null){
            loadView("v_makeConsultation");
        }
        
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
    private void handleDetails(ActionEvent event) {
        if(consultationSelected!=null){
            loadView("v_detailsConsultation");
        }
    }

    @FXML
    private void handleAnnulation(ActionEvent event) {
        if(consultationSelected!=null){
            service.annulerConsultation(consultationSelected);
            Util.makeAlert("Consultation annulee avec succes");
        }
    }

    @FXML
    private void handleSearchByDate(ActionEvent event) {
        LocalDate date=dateFilter.getValue();
        if(date==null){
            Util.makeAlert("Veuillez entrer une date");
        }else{
            String etat=cboFiltre.getSelectionModel().getSelectedItem();
            loadTableView(service.showAllConsultationsByDateDoctorAndEtat(etat, medecin,Date.valueOf(date)));
         
        }
    }
    
    
}

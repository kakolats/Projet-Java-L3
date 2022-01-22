/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Patient;
import entities.Prestation;
import entities.TypeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DossierMedicalController implements Initializable {

    private Service service=new Service();
    Patient patientSelected;
    ObservableList<Patient> obvPatients;
    ObservableList<Prestation> obvPrestations;
    ObservableList<Consultation> obvConsultations;
    private static DossierMedicalController ctrl;
    private Consultation consultationSelected;
    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation,Date> tblcDate;
    @FXML
    private TableColumn<Consultation,String> tblcStatut;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,Date> tblcDateP;
    @FXML
    private TableColumn<Prestation,Date> tblcStatutP;
    @FXML
    private Button btnDetails;
    @FXML
    private TableColumn<Patient,Integer> tblcId;
    @FXML
    private TableColumn<Patient,String> tblcNomComplet;
    @FXML
    private TableView<Patient> tblvPatients;
    @FXML
    private TableColumn<Consultation,TypeService> tblcType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableViewPatients(service.findAllPatients());
        ctrl=this;
    }    

    @FXML
    private void makeDetailsConsultation(ActionEvent event) {
    }
    
    private void loadTableViewPatients(List<Patient> patients){
        obvPatients=FXCollections.observableArrayList(patients);
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblvPatients.setItems(obvPatients);
    }
    
    private void loadTableViewConsultations(List<Consultation> consultations){
        obvConsultations=FXCollections.observableArrayList(consultations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvConsultations.setItems(obvConsultations);
    }
    
    private void loadTableViewPrestations(List<Prestation> prestations){
        obvPrestations=FXCollections.observableArrayList(prestations);
        tblcDateP.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatutP.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblvPrestations.setItems(obvPrestations);
    }

    @FXML
    private void handleSelectPatient(MouseEvent event) {
        patientSelected=tblvPatients.getSelectionModel().getSelectedItem();
        if(patientSelected!=null){
            loadTableViewConsultations(service.showAllConsultationsByPatient(patientSelected.getId()));
            loadTableViewPrestations(service.showAllPrestationByPatient(patientSelected.getId()));
        }
    }

    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        Prestation presta=tblvPrestations.getSelectionModel().getSelectedItem();
        if(presta.getEtat().equals("TERMINE")){
            Util.makeAlert(presta.getResultats());
        }
        
    }

    @FXML
    private void handleSelectConsultation(MouseEvent event) {
        consultationSelected = tblvConsultations.getSelectionModel().getSelectedItem();
        if(consultationSelected.getStatut().equals("TERMINE")){
            loadView("v_detailsConsultation");
        }
        
    }

    public static DossierMedicalController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(DossierMedicalController ctrl) {
        DossierMedicalController.ctrl = ctrl;
    }

    public Consultation getConsultationSelected() {
        return consultationSelected;
    }

    public void setConsultationSelected(Consultation consultationSelected) {
        this.consultationSelected=consultationSelected;        
    }
    private void loadView(String view){
        try {
            AnchorPane root = null;
            root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

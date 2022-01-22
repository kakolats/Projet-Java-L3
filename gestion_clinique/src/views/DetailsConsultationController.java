/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Medicament;
import entities.Prescription;
import entities.Prestation;
import entities.TypeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DetailsConsultationController implements Initializable {

    private Service service=new Service();
    ObservableList<Prescription> obvPrescriptions;
    ObservableList<Prestation> obvPrestations;
    @FXML
    private TableView<Prescription> tblvPrescriptions;
    @FXML
    private TableColumn<Prescription,Medicament> tblcNom;
    @FXML
    private TableColumn<Prescription,String> tblcPosologie;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,TypeService> tblcLibelle;
    @FXML
    private TableColumn<Prestation,String> tblcStatut;
    @FXML
    private TextArea txtAConstantes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        if(ListeAllConsultationController.getCtrl().getConsultationSelected()!=null){
            Consultation cns=ListeAllConsultationController.getCtrl().getConsultationSelected();
            txtAConstantes.setText(cns.getConstantes());
            txtAConstantes.setDisable(true);
            loadTableViewPrestations(service.showAllPrestationByConsultation(cns.getId()));
            loadTableViewPrescriptions(service.showPrescriptionsByConsultation(cns.getId()));
        }else{
            if(DossierMedicalController.getCtrl().getConsultationSelected()!=null){
                Consultation cna=DossierMedicalController.getCtrl().getConsultationSelected();
                txtAConstantes.setText(cna.getConstantes());
                txtAConstantes.setDisable(true);
                loadTableViewPrestations(service.showAllPrestationByConsultation(cna.getId()));
                loadTableViewPrescriptions(service.showPrescriptionsByConsultation(cna.getId()));
            }else{
                if(ListeConsultationController.getCtrl().getConsultationSelected()!=null){
                    Consultation cnx=ListeConsultationController.getCtrl().getConsultationSelected();
                    txtAConstantes.setText(cnx.getConstantes());
                    txtAConstantes.setDisable(true);
                    loadTableViewPrestations(service.showAllPrestationByConsultation(cnx.getId()));
                    loadTableViewPrescriptions(service.showPrescriptionsByConsultation(cnx.getId()));
                }
            }
        
        }
        
    }    
    
    private void loadTableViewPrestations(List<Prestation> prestations){
        obvPrestations=FXCollections.observableArrayList(prestations);
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvPrestations.setItems(obvPrestations);
    }
    
    private void loadTableViewPrescriptions(List<Prescription> prescriptions){
        obvPrescriptions=FXCollections.observableArrayList(prescriptions);
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("medicament"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvPrescriptions.setItems(obvPrescriptions);
    }

    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        Prestation presta=tblvPrestations.getSelectionModel().getSelectedItem();
        if(presta.getEtat().equals("TERMINE")){
            Util.makeAlert(presta.getResultats());
        }
    }
    
}

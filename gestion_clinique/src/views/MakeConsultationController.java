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
import entities.TypePrestation;
import entities.TypeService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MakeConsultationController implements Initializable {

    private Service service=new Service();
    private List<Prescription> listPrescriptions=new ArrayList<Prescription>();
    private List<Prestation> listPrestations=new ArrayList<Prestation>();
    
    ObservableList<Prescription> obvListPrescriptions;
    ObservableList<Prestation> obvListPrestas;
    
    ObservableList<Medicament> medicaments;
    ObservableList<TypePrestation> prestations;
    @FXML
    private TextField txtfTemp;
    @FXML
    private TextField txtfMasse;
    @FXML
    private TextField txtfTension;
    @FXML
    private ComboBox<Medicament> cboMedicaments;
    @FXML
    private TableView<Prescription> tblvMedocs;
    @FXML
    private ComboBox<TypePrestation> cboPrestations;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,TypeService> tblcLibelle;
    @FXML
    private TableColumn<Medicament,String> tblcNom;
    @FXML
    private TextArea txtfPosologie;
    @FXML
    private TableColumn<Prescription,String> tblcPosologie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadComboBoxMedicaments(cboMedicaments);
        loadComboBoxPrestations(cboPrestations);
    }    

    @FXML
    private void handleAddMedicament(ActionEvent event) {
        Medicament medoc=cboMedicaments.getSelectionModel().getSelectedItem();
        if(txtfPosologie.getText().equals("")){
            Util.makeAlert("Veuillez Entrez une posologie");
        }else{
            Prescription prescri=new Prescription();
            prescri.setMedicament(medoc);
            prescri.setPosologie(txtfPosologie.getText());
            prescri.setConsultation_id(ListeAllConsultationController.getCtrl().getConsultationSelected().getId());
            listPrescriptions.add(prescri);
            loadTableViewMedicament();
        }
    }
    
    private void loadComboBoxMedicaments(ComboBox<Medicament> cbo){
        medicaments=FXCollections.observableArrayList(service.showAllMedicaments());
        cbo.setItems(medicaments);
        cbo.getSelectionModel().selectFirst();
    }
    private void loadComboBoxPrestations(ComboBox<TypePrestation> cbo){
        prestations=FXCollections.observableArrayList(service.showAllTypePrestation());
        cbo.setItems(prestations);
        cbo.getSelectionModel().selectFirst();
    }
    
    private void loadTableViewMedicament(){
        obvListPrescriptions=FXCollections.observableArrayList(listPrescriptions);
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("medicament"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvMedocs.setItems(obvListPrescriptions);
    }
    
    private void loadTableViewPrestation(){
        obvListPrestas=FXCollections.observableArrayList(listPrestations);
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvPrestations.setItems(obvListPrestas);
    }

    @FXML
    private void handleAddPrestation(ActionEvent event) {
        TypePrestation type=cboPrestations.getSelectionModel().getSelectedItem();
        Prestation presta=new Prestation(type,ListeAllConsultationController.getCtrl().getConsultationSelected().getPatient(),
        ListeAllConsultationController.getCtrl().getConsultationSelected().getId());
        listPrestations.add(presta);
        loadTableViewPrestation();       
        
    }

    @FXML
    private void handleFinishConsultation(ActionEvent event) {
        if(txtfTemp.getText().isEmpty()||txtfMasse.getText().isEmpty()||txtfTension.getText().isEmpty()){
            Util.makeAlert("Veuillez remplir tous les constantes");
        }else{
            String constantes="Temperature: "+txtfTemp.getText()+"/n"+
                    "Masse: "+txtfMasse.getText()+"/n"+
                    "Tension: "+txtfTension.getText();
            Consultation consul=ListeAllConsultationController.getCtrl().getConsultationSelected();
            consul.setConstantes(constantes);
            if(listPrestations.isEmpty()==false){
                for(Prestation presta:listPrestations){
                    service.addPrestationWithoutDate(presta);
                }
            }
            if(listPrescriptions.isEmpty()==false){
                for(Prescription pres:listPrescriptions){
                    service.addPrescription(pres);
                }
            }
            service.finaliserConsultation(consul);
        }
    }
    
    
        
    
    
}

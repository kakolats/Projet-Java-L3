/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.Prestation;
import entities.TypeService;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeAllPrestationController implements Initializable {

    private Service service = new Service();
    private Prestation prestationSelected=new Prestation();
    ObservableList<Prestation> obvPrestation;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,Date> tblcDate;
    @FXML
    private TableColumn<Prestation,String> tblcStatut;
    @FXML
    private TableColumn<Prestation,Patient> tblcPatient;
    @FXML
    private TableColumn<Prestation,TypeService> tblcType;
    @FXML
    private ComboBox<String> cboFiltre;
    @FXML
    private TextArea txtResultats;
    @FXML
    private TextField txtPatient;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnAnnulation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView(service.showAllPrestationsByEtat("EN ATTENTE"));
        loadComboBox(cboFiltre);
        btnSave.setDisable(true);
        btnAnnulation.setDisable(true);
        txtPatient.setDisable(false);
    }    
    
    
    
    public void loadTableView(List<Prestation> prestations){
        obvPrestation=FXCollections.observableArrayList(prestations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvPrestations.setItems(obvPrestation);
    }
    
    private void loadComboBox(ComboBox<String> cboType){ 
        cboType.getItems().add("EN ATTENTE");
        cboType.getItems().add("TERMINE");
        cboType.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleFiltre(ActionEvent event) {
        if(cboFiltre.getSelectionModel().getSelectedItem().equals("EN ATTENTE")){
            loadTableView(service.showAllPrestationsByEtat("EN ATTENTE"));
        }else{
            loadTableView(service.showAllPrestationsByEtat("TERMINE"));
        }
    }

    @FXML
    private void handleMakePrestation(ActionEvent event) {
        int id=prestationSelected.getId();
        System.out.println(id);
        
        if(txtResultats.textProperty().getValue().isEmpty()){
            Util.makeAlert("Veuillez entrer des resultats");
        }else{
            //System.out.println(txtResultats.textProperty().getValue());
            Prestation prestation=new Prestation(id,txtResultats.textProperty().getValue());
            System.out.println("Ceci est l'id "+prestation.getId());
            service.setResultatsPrestation(prestation);
            reboot();
            loadTableView(service.showAllPrestationsByEtat("EN ATTENTE"));
            Util.makeAlert("Resultats enregistres avec succes");
        }
        
    }

    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        prestationSelected=tblvPrestations.getSelectionModel().getSelectedItem();
        if(prestationSelected!=null){
            txtPatient.setText(String.valueOf(prestationSelected.getPatient().getNomComplet()));
            if(prestationSelected.getEtat().equals("EN ATTENTE")){
                btnSave.setDisable(false);
                btnAnnulation.setDisable(false);
            }else{
                if(prestationSelected.getEtat().equals("TERMINE")){
                    txtResultats.setText(prestationSelected.getResultats());
                }
            }
        }
        
        
    }

    @FXML
    private void handleAnnulation(ActionEvent event) {
        service.setAnnulationPrestation(prestationSelected);
        btnSave.setDisable(true);
        btnAnnulation.setDisable(true);
        loadTableView(service.showAllPrestationsByEtat("EN ATTENTE"));
        Util.makeAlert("Prestation annulee avec succes");
    }
    
    private void reboot(){
        prestationSelected=null;
        btnSave.setDisable(true);
        btnAnnulation.setDisable(true);
        txtPatient.setText("");
        txtResultats.setText("");
    }
}

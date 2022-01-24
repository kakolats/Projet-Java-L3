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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PrestationDatationController implements Initializable {

    private Service service = new Service();
    private Prestation prestationSelected;
    int countP;
    Date dateSelected;
    ObservableList<Prestation> obvPrestation;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,String> tblcStatut;
    @FXML
    private TableColumn<Prestation,Patient> tblcPatient;
    @FXML
    private TableColumn<Prestation,TypeService> tblcType;
    @FXML
    private TextField numberRdv;
    @FXML
    private Button btnPlanifier;
    @FXML
    private DatePicker datePick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView(service.showAllPrestationsWithoutDate());
        numberRdv.setDisable(true);
        btnPlanifier.setDisable(true);
        Util.dateHandler(datePick);
        datePick.setValue(LocalDate.now());
        numberRdv.setVisible(false);
    }   
    
    public void loadTableView(List<Prestation> prestations){
        obvPrestation=FXCollections.observableArrayList(prestations);
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvPrestations.setItems(obvPrestation);
    }

    @FXML
    private void handleDatation(ActionEvent event) {
        dateSelected=Date.valueOf(datePick.getValue());
        //int id=prestationSelected.getId();
        //System.out.println(id); 
        countP=service.showPrestationNumberByDate(prestationSelected.getId(),dateSelected);
        
        if(countP==5){
            
        }else{
            Prestation presta=new Prestation(prestationSelected.getId(),dateSelected);
            service.setDatePrestation(presta);
            Util.makeAlert("Prestation datée avec succès");
        }
    }

    @FXML
    private void handlePrestationSelection(MouseEvent event) {
        prestationSelected=tblvPrestations.getSelectionModel().getSelectedItem();
        btnPlanifier.setDisable(false);
    }

    /*
    private void handleDatePicking(MouseEvent event) {
        if(datePick.getValue()!=null){
            dateSelected=Date.valueOf(datePick.getValue());
            System.out.println(dateSelected);
            System.out.println(prestationSelected.getType().getId());
            //countP=service.showPrestationNumberByDate(prestationSelected.getType().getId(),dateSelected);
            //numberRdv.setText(String.valueOf(countP));
        }
        
    }*/

    @FXML
    private void handleDatePicking(InputMethodEvent event) {
        if(datePick.getValue()!=null){
            dateSelected=Date.valueOf(datePick.getValue());
            countP=service.showPrestationNumberByDate(prestationSelected.getId(),dateSelected);
            numberRdv.setText(String.valueOf(countP));
        }
    }
    
}

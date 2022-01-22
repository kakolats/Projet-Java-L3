/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Prestation;
import entities.TypeService;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListePrestationController implements Initializable {

    private Service service = new Service();
    ObservableList<Prestation> obvPrestation;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation,Date> tblcDate;
    @FXML
    private TableColumn<Prestation,String> tblcStatut;
    @FXML
    private TableColumn<Prestation,TypeService> tblcType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView(service.showAllPrestationByPatient(ConnexionController.getCtrl().getUser().getId()));
    }    
    
    public void loadTableView(List<Prestation> prestations){
        obvPrestation=FXCollections.observableArrayList(prestations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblvPrestations.setItems(obvPrestation);
        
    }
}

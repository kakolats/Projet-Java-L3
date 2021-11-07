/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.RendezVous;
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
public class ListeRendezVousController implements Initializable {

    
    private Service service = new Service();
    ObservableList<RendezVous> obvRdv;
    @FXML
    private TableView<RendezVous> tblvRdv;
    @FXML
    private TableColumn<RendezVous,Date> tblcDate;
    @FXML
    private TableColumn<RendezVous,String> tblcStatut;
    @FXML
    private TableColumn<RendezVous,TypeService> tblcService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<RendezVous> listeRdv=service.searchRendezVousByPatient(ConnexionController.getCtrl().getUser().getId());
        loadTableViewRdv(listeRdv);
    }    
    
    public void loadTableViewRdv(List<RendezVous> listeRdv){
        obvRdv=FXCollections.observableArrayList(listeRdv);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcService.setCellValueFactory(new PropertyValueFactory<>("typeS"));
         
        tblvRdv.setItems(obvRdv);
    }
    
}

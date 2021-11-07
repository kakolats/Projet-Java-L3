/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Medecin;
import entities.RendezVous;
import entities.TypeService;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ValidationRdvController implements Initializable {

    private Service service = new Service();
    ObservableList<RendezVous> obvRdv;
    ObservableList<Medecin> obvMedecins;
    @FXML
    private TableView<RendezVous> tblvRdv;
    @FXML
    private TableColumn<RendezVous,Date> tblcDate;
    @FXML
    private TableColumn<RendezVous,String> tblcStatut;
    @FXML
    private TableColumn<RendezVous,TypeService> tblcService;
    @FXML
    private TextField txtfDate;
    @FXML
    private ComboBox<Medecin> cboMedecin;
    @FXML
    private TextField txtfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<RendezVous> listeRdv=service.showAllRendezVousEnCours();
        loadTableViewRdv(listeRdv);
        txtfDate.setDisable(true);
        txtfType.setDisable(true);
        cboMedecin.setDisable(true);
        
    }    
    
    public void loadTableViewRdv(List<RendezVous> listeRdv){
        obvRdv=FXCollections.observableArrayList(listeRdv);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcService.setCellValueFactory(new PropertyValueFactory<>("typeS"));
         
        tblvRdv.setItems(obvRdv);
    }

    @FXML
    private void handleAnnulation(ActionEvent event) {
    }

    @FXML
    private void handleValidation(ActionEvent event) {
    }

    @FXML
    private void handleRdvSelection(MouseEvent event) {
        RendezVous rdvSelected=tblvRdv.getSelectionModel().getSelectedItem();
        List<Medecin> listeMedecins=new ArrayList<Medecin>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        txtfDate.setText(dateFormat.format(rdvSelected.getDate()));
        txtfType.setText(rdvSelected.getTypeS().getType()+" "+rdvSelected.getTypeS().getLibelle());
        if(rdvSelected.getTypeS().getType()=="Consultation"){
            cboMedecin.setDisable(false);
            TypeService type=rdvSelected.getTypeS();
            listeMedecins=service.showMedecinsByTypeConsultation(type);
            loadTableViewMedecin(listeMedecins);
        }
    }
    
    private void loadTableViewMedecin(List<Medecin> listeMedecins){
        obvMedecins=FXCollections.observableArrayList(listeMedecins);
        cboMedecin.setItems(obvMedecins);
        cboMedecin.getSelectionModel().selectFirst(); 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Medecin;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeConsultationController implements Initializable {

    private Service service = new Service();
    private static ListeConsultationController ctrl;
    private Consultation consultationSelected=new Consultation();
    ObservableList<Consultation> obvConsuls;
    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation,Date> tblcDate;
    @FXML
    private TableColumn<Consultation,String> tblcStatut;
    @FXML
    private TableColumn<Consultation,Medecin> tblcMedecin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView(service.showAllConsultationsByPatient(ConnexionController.getCtrl().getUser().getId()));
        ctrl=this;
    }

    public void loadTableView(List<Consultation> consultations){
        obvConsuls=FXCollections.observableArrayList(consultations);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcMedecin.setCellValueFactory(new PropertyValueFactory<>("medecin"));
        tblvConsultations.setItems(obvConsuls);
    }

    @FXML
    private void handleSelectConsultation(MouseEvent event) {
        consultationSelected=tblvConsultations.getSelectionModel().getSelectedItem();
        if(consultationSelected.getStatut().equals("TERMINE")){
            loadView("v_detailsConsultation");
        }
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

    public static ListeConsultationController getCtrl() {
        return ctrl;
    }

    public static void setCtrl(ListeConsultationController ctrl) {
        ListeConsultationController.ctrl = ctrl;
    }

    public Consultation getConsultationSelected() {
        return consultationSelected;
    }

    public void setConsultationSelected(Consultation consultationSelected) {
        this.consultationSelected = consultationSelected;
    }
    
    
    
    
}

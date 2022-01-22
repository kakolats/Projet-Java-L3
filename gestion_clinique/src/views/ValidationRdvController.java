/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.Prestation;
import entities.RendezVous;
import entities.TypeService;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    Date dateSelected;
    private RendezVous rdvSelected;
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
    @FXML
    private TextField numberRdv;

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
        service.updateRendezVous("REFUSE",rdvSelected.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RendezVous");
        alert.setContentText("Rendez-Vous Refuse avec succes");
        alert.show();
        List<RendezVous> listeRdv=service.showAllRendezVousEnCours();
        loadTableViewRdv(listeRdv);   
    }

    @FXML
    private void handleValidation(ActionEvent event) throws ParseException {
        String date1=txtfDate.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date2 = format.parse(date1); 
        Date date = new Date(date2.getTime()); 
        //System.out.println(txtfType.getText());
        int typeId=rdvSelected.getTypeS().getId();
        Patient patient=new Patient(rdvSelected.getPatient().getId());
        if(txtfType.getText().startsWith("Consultation")){
            //System.out.println(rdvSelected.getPatient().getId());
            Medecin medecin=cboMedecin.getSelectionModel().getSelectedItem();
            Consultation consultation=new Consultation(date,patient,medecin,typeId);
            service.addConsultation(consultation);
            service.updateRendezVous("VALIDE",rdvSelected.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RendezVous");
            alert.setContentText("Rendez-Vous valide avec succes");
            alert.show();
            List<RendezVous> listeRdv=service.showAllRendezVousEnCours();
            loadTableViewRdv(listeRdv);
        }else{
            TypeService type=new TypeService(rdvSelected.getTypeS().getId());
            Prestation prestation=new Prestation(date,type);
            prestation.setPatient(patient);
            service.addPrestation(prestation);
            service.updateRendezVous("VALIDE",rdvSelected.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RendezVous");
            alert.setContentText("Rendez-Vous valide avec succes");
            alert.show();
            List<RendezVous> listeRdv=service.showAllRendezVousEnCours();
            loadTableViewRdv(listeRdv);
        }
         
    }
    
    private void handleNumberRdv(String type,int idPrestation){
        if(type=="Consultation"){
            Medecin med=cboMedecin.getSelectionModel().getSelectedItem();
            if(med==null){
                numberRdv.setText("0");
            }else{
                int count=service.showConsutationsNumberByMedecin(med.getId(),dateSelected);
                numberRdv.setText(String.valueOf(count));
            }
        }else{
            int countP=service.showPrestationNumberByDate(idPrestation,dateSelected);
            numberRdv.setText(String.valueOf(countP));
        }
        
    }

    @FXML
    private void handleRdvSelection(MouseEvent event) {
        rdvSelected=tblvRdv.getSelectionModel().getSelectedItem();
        //System.out.println(rdvSelected.getId());
        List<Medecin> listeMedecins=new ArrayList<Medecin>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        txtfDate.setText(dateFormat.format(rdvSelected.getDate()));
        txtfType.setText(rdvSelected.getTypeS().getType()+" "+rdvSelected.getTypeS().getLibelle());
        if(rdvSelected.getTypeS().getType()=="Consultation"){
            cboMedecin.setDisable(false);
            TypeService type=rdvSelected.getTypeS();
            listeMedecins=service.showMedecinsByTypeConsultation(type);
            cboMedecin.setVisible(true);
            loadComboBoxMedecin(listeMedecins);
            dateSelected=rdvSelected.getDate();
            handleNumberRdv(rdvSelected.getTypeS().getType(),0);
        }else{
            cboMedecin.setVisible(false);
            dateSelected=rdvSelected.getDate();
            handleNumberRdv(rdvSelected.getTypeS().getType(),rdvSelected.getTypeS().getId());
        }
    }
    
    private void loadComboBoxMedecin(List<Medecin> listeMedecins){
        obvMedecins=FXCollections.observableArrayList(listeMedecins);
        cboMedecin.setItems(obvMedecins);
        cboMedecin.getSelectionModel().selectFirst(); 
    }

    @FXML
    private void handleMedecinChange(ActionEvent event) {
        handleNumberRdv("Consultation",0);
    }
}

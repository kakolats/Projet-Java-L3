/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.TypeConsultation;
import entities.TypeService;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Service;
import utils.Util;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminController implements Initializable {

    
    private Service service=new Service();
    ObservableList<TypeConsultation> obvType;
    ObservableList<User> obvUser;
    @FXML
    private ComboBox<String> cboRole;
    @FXML
    private TextField txtfNomComplet;
    @FXML
    private TextField txtfLogin;
    @FXML
    private ComboBox<TypeConsultation> cboSpecialite;
    @FXML
    private PasswordField txtfPassword;
    @FXML
    private PasswordField txtfPasswordC;
    @FXML
    private TableView<User> tblvUsers;
    @FXML
    private TableColumn<User,Integer> tblcId;
    @FXML
    private TableColumn<User,String> tblcLogin;
    @FXML
    private TableColumn<User,String> tblcNomComplet;
    @FXML
    private TableColumn<User,String> tblcRole;
    @FXML
    private Button btnDeconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadComboRole(cboRole);
        loadTableViewUser(service.showAllUsers());
        loadComboBoxType();
        cboSpecialite.setDisable(false);
    }    
    
    private void loadComboRole(ComboBox<String> cbo){
        cbo.getItems().add("ROLE_MEDECIN");
        cbo.getItems().add("ROLE_SECRETAIRE");
        cbo.getItems().add("ROLE_RP");
        cbo.getSelectionModel().selectFirst();
    }
    
    private void loadComboBoxType(){
        obvType=FXCollections.observableArrayList(service.showAllTypeConsultation());
        cboSpecialite.setItems(obvType);
        cboSpecialite.getSelectionModel().selectFirst();
    }
    
    private void loadTableViewUser(List<User> users){
        obvUser=FXCollections.observableArrayList(users);
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblcLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tblvUsers.setItems(obvUser);
    }

    @FXML
    private void handleDeconnexion(ActionEvent event) throws IOException {
        AnchorPane root;
        try
        {
            Stage stage = (Stage) btnDeconnexion.getScene().getWindow();
            stage.hide();
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage1 =  new Stage();
            stage1.setScene(scene);
            stage1.show();
                   
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void handleAddUser(ActionEvent event) {
        String login = txtfLogin.getText().trim();
        String password=txtfPassword.getText().trim();
        String nomComplet=txtfNomComplet.getText().trim();
        String role=cboRole.getSelectionModel().getSelectedItem();
        User user=new User(nomComplet,login,password,role);
        if(login.isEmpty() || password.isEmpty()||nomComplet.isEmpty()){
            Util.makeAlert("Tous les champs sont obligatoires");
        }else{
            if(service.findUserByLogin(login)!=null){
              Util.makeAlert("Ce login est deja pris");  
            }else{
                if(role.equals("ROLE_MEDECIN")){
                    
                }else{
                    service.addUser(user);
                }
                
            }
        }
    }

    @FXML
    private void handleActionRole(ActionEvent event) {
        String role=cboRole.getSelectionModel().getSelectedItem();
        if(role.equals("ROLE_MEDECIN")){
            cboSpecialite.setDisable(false);
        }else{
            cboSpecialite.setDisable(true);
        }
    }
}

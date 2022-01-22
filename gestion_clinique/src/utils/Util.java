/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import views.HomeController;

/**
 *
 * @author HP
 */
public class Util {
    
    private void loadView(String view,AnchorPane anchorContent){
        try {
            AnchorPane root;
            root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
            anchorContent.getChildren().clear();
            anchorContent.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void makeAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("alerte");
            alert.setContentText(message);
            alert.show();
    }
    
    public static void dateHandler(DatePicker dp){
        dp.setDayCellFactory(picker -> new DateCell() 
        { 
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
        }); 
        //dp.setValue(LocalDate.now());
    }
}

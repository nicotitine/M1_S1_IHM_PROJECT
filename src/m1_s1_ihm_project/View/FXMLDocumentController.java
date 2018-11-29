/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.View;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    
    
    @FXML 
    private TabPane tabPane;
    
    
    private JFXButton button;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<Magazines> magazines = Database.getMagazines();
         button.setText(magazines.get(0).getTitle());
         System.out.println(magazines.get(0).getTitle());
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        tabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        //magazinesTab.set(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        
    }    
    
}

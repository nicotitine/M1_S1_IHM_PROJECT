/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.View;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

/**
 *
 * @author Nico
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private HBox hb;
    
    @FXML 
    private TabPane tabPane;
    
    @FXML
    private Tab magazinesTab;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        tabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        //magazinesTab.set(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        
    }    
    
}

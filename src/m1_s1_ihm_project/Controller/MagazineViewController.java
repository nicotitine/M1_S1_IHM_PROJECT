/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 * FXML Controller class
 *
 * @author Nico
 */
public class MagazineViewController implements Initializable {

    @FXML private JFXButton exitMag;
    private ScreenController screenController;
    private Magazines thisMag;
    
    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(exitMag)) {
            screenController.activateMag("magazines", thisMag, screenController);
            System.out.println("exit clicked");
        }
    }
    
     public void setStageAndSetupListeners(Scene scene, Magazines mag, ScreenController SC) {
         screenController = SC;
         thisMag = mag;
     }

   
    @Override public void initialize(URL url, ResourceBundle rb) {
       
    }
}

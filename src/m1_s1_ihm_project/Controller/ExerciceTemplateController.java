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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import m1_s1_ihm_project.Model.Exercices.Exercices;

public class ExerciceTemplateController implements Initializable {
    
    @FXML private Text exerciceTitle;
    @FXML private ImageView exerciceImageView;
    @FXML private Label exerciceType;
    @FXML private Label exerciceDuration;
    @FXML private Text exerciceText;
    @FXML private JFXButton exerciceConsult;
    
    private ScreenController screenController;
    private int id_database;
    private int textMaxLength;
    
    public void setStageAndSetupListeners(Exercices exe, ScreenController SC, int id_data) {
        screenController = SC;
        id_database = id_data;
        exerciceTitle.setText(exe.getTitle());
        exerciceType.setText(exe.getType().toUpperCase());
        exerciceImageView.setImage(new Image(exe.getImageUrl()));
        if(exe.getDescription().length() > textMaxLength)
            exerciceText.setText(exe.getDescription().substring(0, 300) + "...");
        else 
            exerciceText.setText(exe.getDescription());
        
        exerciceText.setWrappingWidth(340);
        exerciceDuration.setText("Durée : " + exe.getDuration());
    }
    
    @FXML public void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        textMaxLength = 300;
    }    
    
}

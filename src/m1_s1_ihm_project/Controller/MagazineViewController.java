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
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 * FXML Controller class
 *
 * @author Nico
 */
public class MagazineViewController implements Initializable {

     @FXML
    private FlowPane magazineFlowPane;

    @FXML
    private VBox magazineVBox;

    @FXML
    private MediaView magazineMediaView;

    @FXML
    private JFXButton exitMag;

    @FXML
    private Label title;

    @FXML
    private Label date;

    @FXML
    private Label type;

    @FXML
    private Text description;
    
    @FXML
    private HBox header;

    @FXML
    private JFXButton backBtn;
    private ScreenController screenController;
    private Magazines thisMag;
    private double windowWidth;
    private Scene thisScene;
    private Stage thisStage;
    
    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(exitMag) || event.getSource().equals(backBtn)) {
            screenController.activateMag("magazines", thisMag, screenController);
            System.out.println("exit clicked");
        }
    }
    
    @FXML private void hoverFlatBtn() {
        exitMag.setTextFill(Color.web("#104672"));
    }
    @FXML private void unHoverFlatBtn() {
        exitMag.setTextFill(Color.web("#1B75BC"));
    }
    
     public void setStageAndSetupListeners(Scene scene, Magazines mag, ScreenController SC) {
         thisScene = scene;
         thisStage = (Stage)scene.getWindow();
         windowWidth = scene.getWidth();
         magazineFlowPane.setPrefWidth(windowWidth);
         magazineVBox.setPrefWidth(windowWidth);
         screenController = SC;
         thisMag = mag;
         title.setText(mag.getTitle());
         description.setText(mag.getDescription());
         date.setText("Date de publication : " + mag.getPublishDate().toString());
         switch(mag.getType()) {
             case "book" :
                 type.setText("Type : Livre");
         }
         
         thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazineFlowPane.setPrefWidth((double)newVal);
            magazineVBox.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
            if(windowWidth < 1200) {
                header.setSpacing(200);
            }
            if(windowWidth < 800) {
                header.setSpacing(100);
            }
            
        });
     }

   
    @Override public void initialize(URL url, ResourceBundle rb) {
       
    }
}

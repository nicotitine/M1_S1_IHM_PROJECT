/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 * FXML Controller class
 *
 * @author Nico
 */
public class MagazinesViewController implements Initializable {

    @FXML private FlowPane magazinesMP;
    @FXML private Label magazineTitle1;
    @FXML private ImageView magazineImageView1;
    @FXML private Text magazineText1;
    @FXML private JFXButton magazineConsult1;
    @FXML private Label magazineTitle2;
    @FXML private ImageView magazineImageView2;
    @FXML private Text magazineText2;
    @FXML private JFXButton magazineConsult2;
    @FXML private Label magazineTitle3;
    @FXML private ImageView magazineImageView3;
    @FXML private Text magazineText3;
    @FXML private JFXButton magazineConsult3;
    @FXML private Label magazineTitle4;
    @FXML private ImageView magazineImageView4;
    @FXML private Text magazineText4;
    @FXML private JFXButton magazineConsult4;
    @FXML private Label magazineTitle5;
    @FXML private ImageView magazineImageView5;
    @FXML private Text magazineText5;
    @FXML private JFXButton magazineConsult5;
    @FXML private ScrollPane magazinesScrollPane;
    @FXML private JFXTabPane tabPane;
    @FXML private Tab magazinesTab;
    
    private double windowWidth;
    private Scene thisScene;
    private Stage thisStage;
    private ScreenController screenController;
    
    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(magazineConsult1)) {
            Magazines mag = Database.getMagazine("1");
            screenController.setMagazineData(mag);
            screenController.activateMag("magazine", mag, screenController);
        }
        if(event.getSource().equals(magazineConsult2)) {
            Magazines mag = Database.getMagazine("2");
            screenController.setMagazineData(mag);
            screenController.activateMag("magazine", mag, screenController);
        }
         if(event.getSource().equals(magazineConsult3)) {
            Magazines mag = Database.getMagazine("3");
            screenController.setMagazineData(mag);
            screenController.activateMag("magazine", mag, screenController);
        }
         if(event.getSource().equals(magazineConsult4)) {
            Magazines mag = Database.getMagazine("4");
            screenController.setMagazineData(mag);
            screenController.activateMag("magazine", mag, screenController);
        }
    }
   
    
     public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        screenController = SC;
        thisScene = scene;
        thisStage = (Stage)scene.getWindow();
        windowWidth = thisStage.getWidth();
        magazinesScrollPane.setPrefWidth(windowWidth);
        tabPane.setPrefWidth(windowWidth);
        magazinesMP.setPrefWidth(windowWidth);
        
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazinesScrollPane.setPrefWidth((double)newVal);
            tabPane.setPrefWidth((double)newVal);
            magazinesMP.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
        });
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Magazines> magazines = Database.getMagazines();
        
        ObservableList<Label> magazineTitles = FXCollections.observableArrayList();
        ObservableList<Text> magazineTexts = FXCollections.observableArrayList();
        ObservableList<ImageView> magazineImages = FXCollections.observableArrayList();
        
        magazineTitles.add(magazineTitle1);
        magazineTitles.add(magazineTitle2);
        magazineTitles.add(magazineTitle3);
        magazineTitles.add(magazineTitle4);
        magazineTitles.add(magazineTitle5);
        magazineTexts.add(magazineText1);
        magazineTexts.add(magazineText2);
        magazineTexts.add(magazineText3);
        magazineTexts.add(magazineText4);
        magazineTexts.add(magazineText5);
        magazineImages.add(magazineImageView1);
        magazineImages.add(magazineImageView2);
        magazineImages.add(magazineImageView3);
        magazineImages.add(magazineImageView4);
        magazineImages.add(magazineImageView5);
        
        int maxSize;
        if(magazineTitles.size() < magazines.size()) {
            maxSize = magazineTitles.size();
        } else {
            maxSize = magazines.size();
        }

        for(int i = 0; i < maxSize; i++) {
            int descLength = magazines.get(i).getDescription().length();
            String finalDesc;
            if (descLength > 300) {
                finalDesc = magazines.get(i).getDescription().substring(0, 300) + " ...".replace("13", "\n");
            } else {
                finalDesc = magazines.get(i).getDescription();
            }
            magazineTitles.get(i).setText(magazines.get(i).getTitle());
            magazineTexts.get(i).setText(finalDesc);
            magazineImages.get(i).setImage(new Image(magazines.get(i).getImageUrl()));
            magazineTexts.get(i).setWrappingWidth(260);
        }
    }
}

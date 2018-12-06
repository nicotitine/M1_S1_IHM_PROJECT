/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import m1_s1_ihm_project.View.FXMLMagazineTestController;

/**
 * FXML Controller class
 *
 * @author Nico
 */
public class MagazinesViewController implements Initializable {

    @FXML private FlowPane magazinesMP;
    @FXML private ScrollPane magazinesScrollPane;
    @FXML private JFXTabPane tabPane;
    @FXML private Tab magazinesTab;
    
    private double windowWidth;
    private Stage thisStage;
    private ScreenController screenController;
    private ObservableList<Magazines> magazines;
    private boolean firstLaunch;
    
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        screenController = SC;
        thisStage = (Stage)scene.getWindow();
        windowWidth = thisStage.getWidth();
        magazinesScrollPane.setPrefWidth(windowWidth);
        tabPane.setPrefWidth(windowWidth);
        magazinesMP.setPrefWidth(windowWidth);
        
        if(firstLaunch) {
            magazinesMP.getChildren().clear();
            for(int i = 0; i < magazines.size(); i++) {
                try {
                    FXMLLoader magLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/FXMLMagazineTest.fxml"));
                    magazinesMP.getChildren().add(magLoader.load());
                    FXMLMagazineTestController controller = magLoader.getController();
                    controller.setStageAndSetupListeners(magazines.get(i), screenController, i+1);
                } catch (IOException ex) {
                    Logger.getLogger(MagazinesViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        firstLaunch = false;
        
        // Resize event
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazinesScrollPane.setPrefWidth((double)newVal);
            tabPane.setPrefWidth((double)newVal);
            magazinesMP.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
        });
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        magazines = Database.getMagazines();
        firstLaunch = true;
    }
}

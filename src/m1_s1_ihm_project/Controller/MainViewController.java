package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercices.Exercices;
import m1_s1_ihm_project.Model.Magazines.Magazines;

public class MainViewController implements Initializable {

    @FXML private FlowPane magazinesMP;
    @FXML private ScrollPane magazinesScrollPane;
    @FXML private FlowPane exercicesMP;
    @FXML private ScrollPane exercicesScrollPane;
    @FXML private FlowPane toolsMP;
    @FXML private ScrollPane toolsScrollPane;
    @FXML private JFXTabPane tabPane;
    @FXML private JFXComboBox translateToList;
    @FXML private JFXComboBox translateFromList;
    @FXML private JFXButton exchangeBtn;
    
    private double windowWidth;
    private Stage thisStage;
    private ScreenController screenController;
    private ObservableList<Magazines> magazines;
    private ObservableList<Exercices> exercices;
    private boolean firstLaunch;
    private TraductionController traducteurController;
    
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        // Initialize the window with the window width (screen width as fullscreen)
        screenController = SC;
        thisStage = (Stage)scene.getWindow();
        windowWidth = thisStage.getWidth();
        magazinesScrollPane.setPrefWidth(windowWidth);
        magazinesMP.setPrefWidth(windowWidth);
        exercicesScrollPane.setPrefWidth(windowWidth);
        exercicesMP.setPrefWidth(windowWidth);
        toolsScrollPane.setPrefWidth(windowWidth);
        toolsMP.setPrefWidth(windowWidth);
        tabPane.setPrefWidth(windowWidth);
        
        if(firstLaunch) {
            // Add all magazines to the magazines pane
            magazinesMP.getChildren().clear();
            magazinesMP.setAlignment(Pos.TOP_CENTER);
            for(int i = 0; i < magazines.size(); i++) {
                try {
                    FXMLLoader magLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineTemplate.fxml"));
                    magazinesMP.getChildren().add(magLoader.load());
                    MagazineTemplateController controller = magLoader.getController();
                    controller.setStageAndSetupListeners(magazines.get(i), screenController, i+1);
                } catch (IOException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Add all exercices to the exercices pane
            exercicesMP.setAlignment(Pos.CENTER);
            for(int i = 0; i < exercices.size(); i++) {
                try {
                    FXMLLoader exeLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/ExerciceTemplate.fxml"));
                    exercicesMP.getChildren().add(exeLoader.load());
                    ExerciceTemplateController controller = exeLoader.getController();
                    controller.setStageAndSetupListeners(exercices.get(i), screenController, i);
                } catch (IOException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // Initialize the tools tab
            traducteurController = new TraductionController();
            translateToList.setItems(traducteurController.getNameList());
            translateToList.getSelectionModel().selectFirst();
            translateFromList.setItems(traducteurController.getNameList());
            translateFromList.getSelectionModel().select(1);
        }
        
        firstLaunch = false;
        
        // Resize event
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazinesScrollPane.setPrefWidth((double)newVal);
            magazinesMP.setPrefWidth((double)newVal);
            exercicesScrollPane.setPrefWidth((double)newVal);
            exercicesMP.setPrefWidth((double)newVal);
            toolsScrollPane.setPrefWidth((double) newVal);
            toolsMP.setPrefWidth((double) newVal);
            tabPane.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
        });
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Get all magazines and exercices from database
        magazines = Database.getMagazines();
        exercices = Database.getExercices();
        firstLaunch = true;
    }
    
    @FXML public void handleButtonAction(ActionEvent event) {
        // For google translate exchange button event
        if(event.getSource().equals(exchangeBtn)) {
            if(translateFromList.getValue() == "ENGLISH") {
                translateFromList.getSelectionModel().select(1);
                translateToList.getSelectionModel().select(0);
            } else {
                translateFromList.getSelectionModel().select(0);
                translateToList.getSelectionModel().select(1);
            }
        }
    }
}
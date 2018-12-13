package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.utils.JFXHighlighter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercices.Exercices;
import m1_s1_ihm_project.Model.Magazines.Magazines;
import m1_s1_ihm_project.Model.Tools.EnglishNumbers;
import m1_s1_ihm_project.Model.Tools.EnglishTime;

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
    @FXML private JFXTreeTableView<EnglishTime> timesTable;
    @FXML private JFXTreeTableView<EnglishNumbers> numbersTable;
    @FXML private JFXButton clearMode;
    @FXML private JFXButton darkMode;
    @FXML private JFXButton clearModeEx;
    @FXML private JFXButton darkModeEx;
    @FXML private JFXButton clearModeTool;
    @FXML private JFXButton darkModeTool;
    @FXML private HBox headerHBox;
    @FXML private HBox headerHBoxEx;
    @FXML private HBox headerHBoxTool;
    @FXML private JFXButton exitBtn;
    @FXML private JFXButton exitBtnEx;
    @FXML private JFXButton exitBtnTool;
    @FXML private JFXTextField searchFieldNumbers;
    @FXML private JFXTextField searchFieldTimes;
    
    private double windowWidth;
    private double windowHeight;
    private Stage thisStage;
    private ScreenController screenController;
    private ObservableList<Magazines> magazines;
    private ObservableList<Exercices> exercices;
    private ObservableList<EnglishTime> times;
    private ObservableList<EnglishNumbers> numbers;
    private TraductionController traducteurController;
    
    private final String theme1Url = getClass().getResource("/m1_s1_ihm_project/View/customCss.css").toExternalForm();
    private final String theme2Url = getClass().getResource("/m1_s1_ihm_project/View/customCss_1.css").toExternalForm();
    
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        // Initialize the window with the window width (screen width as fullscreen)
        screenController = SC;
        thisStage = (Stage)scene.getWindow();
        windowWidth = thisStage.getWidth();
        windowHeight = thisStage.getHeight() - 197;
        magazinesScrollPane.setPrefSize(windowWidth, windowHeight);
        magazinesMP.setPrefSize(windowWidth, windowHeight);
        exercicesScrollPane.setPrefSize(windowWidth, windowHeight);
        exercicesMP.setPrefSize(windowWidth, windowHeight);
        toolsScrollPane.setPrefSize(windowWidth, windowHeight);
        toolsMP.setPrefSize(windowWidth, windowHeight);
        tabPane.setPrefSize(windowWidth, windowHeight);
        headerHBox.setPrefWidth(windowWidth);
        headerHBoxEx.setPrefWidth(windowWidth);
        headerHBoxTool.setPrefWidth(windowWidth);
        
        // Add all magazines to the magazines pane
        magazinesMP.getChildren().clear();
        magazinesMP.setAlignment(Pos.TOP_CENTER);
        exercicesMP.setAlignment(Pos.TOP_CENTER);
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
        exercicesMP.getChildren().clear();
        for(int i = 0; i < exercices.size(); i++) {
            try {
                FXMLLoader exeLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/ExerciceTemplate.fxml"));
                exercicesMP.getChildren().add(exeLoader.load());
                ExerciceTemplateController controller = exeLoader.getController();
                controller.setStageAndSetupListeners(exercices.get(i), screenController, i+1);
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
        times = Database.getTimes();
        JFXTreeTableColumn<EnglishTime, String> timeCol = new JFXTreeTableColumn("Temps");
        JFXTreeTableColumn<EnglishTime, String> exampleCol = new JFXTreeTableColumn("Exemple");
        JFXTreeTableColumn<EnglishTime, String> explenationCol = new JFXTreeTableColumn("Explication");
        timeCol.setPrefWidth(250);
        exampleCol.setPrefWidth(280);
        explenationCol.setPrefWidth(1094 - 250 - 310);
        timeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getTitle()));
        exampleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getExample()));
        explenationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getExplenation()));
        timeCol.setStyle("-fx-alignment: center; -fx-font-weight: bold;");
            
        final TreeItem<EnglishTime> root = new RecursiveTreeItem<>(times, RecursiveTreeObject::getChildren);
        timesTable.getColumns().setAll(timeCol, exampleCol, explenationCol);
        timesTable.setRoot(root);
        timesTable.setShowRoot(false);
        searchFieldTimes.textProperty().addListener(setupSearchFieldTimes(timesTable));
        
        numbers = Database.getNumbers();
        JFXTreeTableColumn<EnglishNumbers, String> numberCol = new JFXTreeTableColumn("Nombre");
        JFXTreeTableColumn<EnglishNumbers, String> numberEnCol = new JFXTreeTableColumn("Nombre écrit");
        JFXTreeTableColumn<EnglishNumbers, String> ordinalCol = new JFXTreeTableColumn("Ordinal");
        JFXTreeTableColumn<EnglishNumbers, String> ordinalEnCol = new JFXTreeTableColumn("Ordinal écrit");
        numberCol.setPrefWidth(240);
        numberEnCol.setPrefWidth(240);
        ordinalCol.setPrefWidth(240);
        ordinalEnCol.setPrefWidth(240);
        numberCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getNumber()));
        numberEnCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getNumberEn()));
        ordinalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getOrdinal()));
        ordinalEnCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getOrdinalEn()));
        numberCol.setStyle("-fx-alignment: center; -fx-font-weight: bold;");
        
        final TreeItem<EnglishNumbers> rootNumbers = new RecursiveTreeItem<>(numbers, RecursiveTreeObject::getChildren);
        numbersTable.getColumns().setAll(numberCol, numberEnCol, ordinalCol, ordinalEnCol);
        numbersTable.setRoot(rootNumbers);
        numbersTable.setShowRoot(false);
        searchFieldNumbers.textProperty().addListener(setupSearchFieldNumbers(numbersTable));
 
        // Resize event
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazinesScrollPane.setPrefWidth((double)newVal);
            magazinesMP.setPrefWidth((double)newVal);
            exercicesScrollPane.setPrefWidth((double)newVal);
            exercicesMP.setPrefWidth((double)newVal);
            toolsScrollPane.setPrefWidth((double) newVal);
            toolsMP.setPrefWidth((double) newVal);
            tabPane.setPrefWidth((double)newVal);
            headerHBox.setPrefWidth((double)newVal);
            headerHBoxEx.setPrefWidth((double)newVal);
            headerHBoxTool.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
        });
        
        thisStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            windowHeight = (double)newVal - 197;
            magazinesScrollPane.setPrefHeight(windowHeight);
            magazinesMP.setPrefHeight(windowHeight);
            exercicesScrollPane.setPrefHeight(windowHeight);
            exercicesMP.setPrefHeight(windowHeight);
            toolsScrollPane.setPrefHeight(windowHeight);
            toolsMP.setPrefHeight(windowHeight);
            tabPane.setPrefHeight(windowHeight);
            
        });
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Get all magazines and exercices from database
        magazines = Database.getMagazines();
        exercices = Database.getExercices();
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
        if(event.getSource().equals(darkMode) || event.getSource().equals(darkModeEx) || event.getSource().equals(darkModeTool)) {
            screenController.setDarkMode();
        }
        
        if(event.getSource().equals(clearMode) || event.getSource().equals(clearModeEx) ||event.getSource().equals(clearModeTool)) {
           screenController.setClearMode();
        }
        
        if(event.getSource().equals(exitBtn) || event.getSource().equals(exitBtnEx) ||event.getSource().equals(exitBtnTool)) {
            Runtime.getRuntime().exit(0);
        }
    }
    
    private ChangeListener<String> setupSearchFieldTimes(final JFXTreeTableView<EnglishTime> tableView) {
        return (o, oldVal, newVal) ->
            tableView.setPredicate(timeProp -> {
                final EnglishTime time = timeProp.getValue();
                return time.getTitle().contains(newVal)
                    || time.getExample().contains(newVal)
                     || time.getExplenation().contains(newVal);
            });
    }
    
    private ChangeListener<String> setupSearchFieldNumbers(final JFXTreeTableView<EnglishNumbers> tableView) {
        return (o, oldVal, newVal) ->
            tableView.setPredicate(numberProp -> {
                final EnglishNumbers number = numberProp.getValue();
                return number.getNumber().contains(newVal)
                    || number.getNumberEn().contains(newVal)
                     || number.getOrdinal().contains(newVal)
                         || number.getOrdinalEn().contains(newVal);
            });
    }
}
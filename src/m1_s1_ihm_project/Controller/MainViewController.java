package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercice.Exercice;
import m1_s1_ihm_project.Model.Magazine.Magazine;
import m1_s1_ihm_project.Model.Tools.EnglishNumber;
import m1_s1_ihm_project.Model.Tools.EnglishTime;
import m1_s1_ihm_project.View.DatabaseErrorView;

public class MainViewController implements Initializable {

    @FXML private FlowPane magazinesMP, exercicesMP, toolsMP;
    @FXML private ScrollPane magazinesScrollPane, exercicesScrollPane, toolsScrollPane;
    @FXML private JFXTabPane tabPane;
    @FXML private JFXComboBox translateToList, translateFromList;
    @FXML private JFXButton exchangeBtn, clearMode, darkMode, clearModeEx, darkModeEx, clearModeTool, darkModeTool, exitBtn, exitBtnEx, exitBtnTool;
    @FXML private JFXTreeTableView<EnglishTime> timesTable;
    @FXML private JFXTreeTableView<EnglishNumber> numbersTable;
    @FXML private HBox headerHBox, headerHBoxEx, headerHBoxTool;
    @FXML private JFXTextField searchFieldNumbers, searchFieldTimes;
    @FXML private JFXTextArea textTranslated, textToTranslate;
    @FXML private Region regionLeftMagazine, regionRightMagazine, regionLeftExercices, regionRightExercices;
    @FXML private Tab magazinesTab;
    
    private double windowWidth, windowHeight;
    private Stage thisStage;
    private ScreenController screenController;
    private ObservableList<Magazine> magazines;
    private ObservableList<Exercice> exercices;
    private ObservableList<EnglishTime> times;
    private ObservableList<EnglishNumber> numbers;
    private TraductionController traducteurController;
    private DatabaseErrorView magazinesError, exercicesError, toolsError;
    private Region[] regions;
    
    public void showError() {
        magazinesError.show();
        exercicesError.show();
        toolsError.show();
    }
        
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        // Initialize the window with the window width (screen width as fullscreen)
        screenController = SC;
        thisStage = (Stage)scene.getWindow();
        windowWidth = thisStage.getWidth();
        windowHeight = thisStage.getHeight() - 191;
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
        if(screenController.getIsDatabaseConnected()) {
            for(int i = 0; i < magazines.size(); i++) {
                try {
                    FXMLLoader magLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineTemplate.fxml"));
                    magazinesMP.getChildren().add(magLoader.load());
                    MagazineTemplateController controller = magLoader.getController();
                    controller.setStageAndSetupListeners(magazines.get(i), screenController, i);
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
            times = Database.getTimes();
            JFXTreeTableColumn<EnglishTime, String> timeCol = new JFXTreeTableColumn("Temps");
            JFXTreeTableColumn<EnglishTime, String> exampleCol = new JFXTreeTableColumn("Exemple");
            JFXTreeTableColumn<EnglishTime, String> explenationCol = new JFXTreeTableColumn("Explication");
            timeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getTitle()));
            timeCol.setStyle("-fx-alignment: center; -fx-font-weight: bold;");
            timeCol.setContextMenu(null);
            exampleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getExample()));
            exampleCol.setContextMenu(null);
            explenationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getExplenation()));
            explenationCol.setContextMenu(null);
            final TreeItem<EnglishTime> root = new RecursiveTreeItem<>(times, RecursiveTreeObject::getChildren);
            timesTable.getColumns().setAll(timeCol, exampleCol, explenationCol);
            timesTable.setRoot(root);
            timesTable.setShowRoot(false);
            searchFieldTimes.textProperty().addListener(setupSearchFieldTimes(timesTable));
            timesTable.setColumnResizePolicy(JFXTreeTableView.CONSTRAINED_RESIZE_POLICY);

            numbers = Database.getNumbers();
            JFXTreeTableColumn<EnglishNumber, String> numberCol = new JFXTreeTableColumn("Nombre");
            JFXTreeTableColumn<EnglishNumber, String> numberEnCol = new JFXTreeTableColumn("Nombre écrit");
            JFXTreeTableColumn<EnglishNumber, String> ordinalCol = new JFXTreeTableColumn("Ordinal");
            JFXTreeTableColumn<EnglishNumber, String> ordinalEnCol = new JFXTreeTableColumn("Ordinal écrit");
            numberCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getNumber()));
            numberCol.setStyle("-fx-alignment: center; -fx-font-weight: bold;");
            numberCol.setContextMenu(null);
            numberEnCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getNumberEn()));
            numberEnCol.setContextMenu(null);
            ordinalCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getOrdinal()));
            ordinalCol.setContextMenu(null);
            ordinalEnCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valueProperty().get().getOrdinalEn()));
            ordinalEnCol.setContextMenu(null);  
            final TreeItem<EnglishNumber> rootNumbers = new RecursiveTreeItem<>(numbers, RecursiveTreeObject::getChildren);
            numbersTable.getColumns().setAll(numberCol, numberEnCol, ordinalCol, ordinalEnCol);
            numbersTable.setRoot(rootNumbers);
            numbersTable.setShowRoot(false);
            searchFieldNumbers.textProperty().addListener(setupSearchFieldNumbers(numbersTable));  
            numbersTable.setColumnResizePolicy(JFXTreeTableView.CONSTRAINED_RESIZE_POLICY);
        } else {
            magazinesMP.getChildren().clear();
            exercicesMP.getChildren().clear();
            toolsMP.getChildren().clear();
            magazinesError = new DatabaseErrorView("Impossible de se connecter à la base de données...", Database.getErrorConnect());
            exercicesError = new DatabaseErrorView("Impossible de se connecter à la base de données...", Database.getErrorConnect());
            toolsError = new DatabaseErrorView("Impossible de se connecter à la base de données...", Database.getErrorConnect());
            magazinesMP.getChildren().add(magazinesError);
            exercicesMP.getChildren().add(exercicesError);
            toolsMP.getChildren().add(toolsError);            
            magazinesMP.setAlignment(Pos.CENTER);
            exercicesMP.setAlignment(Pos.CENTER);
            toolsMP.setAlignment(Pos.CENTER);
        }
 
        // Resize events
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            windowWidth = newVal.doubleValue();
            this.resizeWidth(windowWidth);
        });
        
        thisStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            windowHeight = newVal.doubleValue() - 197;
            this.resizeHeight(windowHeight);
        });
        
        thisStage.maximizedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if(!t1) {
                this.thisStage.setWidth(1200);
                this.thisStage.setHeight(700);
            }
        });
    }
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        // Get all magazines and exercices from database
        magazines = Database.getMagazines();
        exercices = Database.getExercices();
        regions = new Region[4];
        regions[0] = regionLeftMagazine;
        regions[1] = regionRightMagazine;
        regions[2] = regionLeftExercices;
        regions[3] = regionRightExercices;
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
            String temp = textTranslated.getText();
            textTranslated.setText(textToTranslate.getText());
            textToTranslate.setText(temp);
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
    
    
    // Searching functions
    private ChangeListener<String> setupSearchFieldTimes(final JFXTreeTableView<EnglishTime> tableView) {
        return (o, oldVal, newVal) ->
            tableView.setPredicate(timeProp -> {
                final EnglishTime time = timeProp.getValue();
                return time.getTitle().contains(newVal)
                    || time.getExample().contains(newVal)
                     || time.getExplenation().contains(newVal);
            });
    }
    
    private ChangeListener<String> setupSearchFieldNumbers(final JFXTreeTableView<EnglishNumber> tableView) {
        return (o, oldVal, newVal) ->
            tableView.setPredicate(numberProp -> {
                final EnglishNumber number = numberProp.getValue();
                return number.getNumber().contains(newVal)
                    || number.getNumberEn().contains(newVal)
                     || number.getOrdinal().contains(newVal)
                         || number.getOrdinalEn().contains(newVal);
            });
    }
    
    public void resizeWidth(double windowWidth) {
        magazinesScrollPane.setPrefWidth(windowWidth);
        magazinesMP.setPrefWidth(windowWidth);
        exercicesScrollPane.setPrefWidth(windowWidth);
        exercicesMP.setPrefWidth(windowWidth);
        toolsScrollPane.setPrefWidth(windowWidth);
        toolsMP.setPrefWidth(windowWidth);
        tabPane.setPrefWidth(windowWidth);
        headerHBox.setPrefWidth(windowWidth);
        headerHBoxEx.setPrefWidth(windowWidth);
        headerHBoxTool.setPrefWidth(windowWidth);
        if(windowWidth < 1455 && windowWidth >= 1155) {
            for (Region region : regions)
                region.setPrefWidth(50);
            
        }
        if(windowWidth < 1155) {
            for (Region region : regions)
                region.setVisible(false);
        } else {
            for (Region region : regions)
                region.setVisible(false);
        }
    }
    
    public void resizeHeight(double windowHeight) {
        System.out.println(windowHeight);
        magazinesScrollPane.setPrefHeight(windowHeight);
        magazinesMP.setPrefHeight(windowHeight);
        exercicesScrollPane.setPrefHeight(windowHeight);
        exercicesMP.setPrefHeight(windowHeight);
        toolsScrollPane.setPrefHeight(windowHeight);
        toolsMP.setPrefHeight(windowHeight);
        tabPane.setPrefHeight(windowHeight);
    }
}
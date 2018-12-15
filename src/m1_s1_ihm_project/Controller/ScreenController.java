package m1_s1_ihm_project.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercice.Exercice;
import m1_s1_ihm_project.Model.Magazine.Magazine;

public final class ScreenController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private Magazine magazineData;
    private Exercice exerciceData;
    private FXMLLoader magazineLoader, exerciceLoader, mainLoader, welcomeLoader;
    private final String lightThemeUrl = getClass().getResource("/m1_s1_ihm_project/View/lightTheme.css").toExternalForm();
    private final String darkThemeUrl = getClass().getResource("/m1_s1_ihm_project/View/darkTheme.css").toExternalForm();
    private String pseudo;
    private boolean firstLaunch = true;
    
    public ScreenController(Stage stage) {
        try {
            magazineLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            exerciceLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/ExerciceView.fxml"));
            mainLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MainView.fxml"));
            welcomeLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/WelcomeView.fxml"));
            screenMap.put("magazine", magazineLoader.load());
            screenMap.put("exercice", exerciceLoader.load());
            screenMap.put("main", mainLoader.load());
            screenMap.put("welcome", welcomeLoader.load());
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font.loadFont(
            getClass().getResourceAsStream("/m1_s1_ihm_project/View/Resources/Raleway-Regular.ttf"), 
            72
        );
        Font.loadFont(
            getClass().getResourceAsStream("/m1_s1_ihm_project/View/Resources/Raleway-Bold.ttf"), 
            72
        );
        Parent root = (Parent)screenMap.get("welcome");
        WelcomeViewController controller = (WelcomeViewController)welcomeLoader.getController();
        main = new Scene(root);
        main.getStylesheets().add(darkThemeUrl);
        stage.setScene(main);
        stage.setMaximized(true);
        stage.setTitle("Traveler Companion");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/m1_s1_ihm_project/View/Resources/icon.png")));
        stage.show();
        controller.setStageAndSetupListeners(this.getMain(), this.getInstance());
    }
    
    public void loadHome(ScreenController SC) {
        Platform.runLater(() -> {
            MainViewController mainController = (MainViewController)mainLoader.getController();
            mainController.setStageAndSetupListeners(main, SC);
            firstLaunch = false;
        });
    }

    public void activateMag(String name, Magazine mag, ScreenController SC){
        switch(name) {
            case "magazine":
                MagazineViewController controller = (MagazineViewController)magazineLoader.getController();
                main.setRoot( screenMap.get(name) );
                controller.setStageAndSetupListeners(main, magazineData, SC);
            break;
            case "main":
                if(firstLaunch) {
                    MainViewController magazinesController = (MainViewController)mainLoader.getController();
                    main.setRoot( screenMap.get(name) );
                    magazinesController.setStageAndSetupListeners(main, SC);
                    firstLaunch = false;
                } else {
                    main.setRoot(screenMap.get(name));
                }
            break;
        }
    }
    
    public void activateExe(String name, Exercice exe, ScreenController SC) {
        switch(name) {
            case "exercice" :
                ExerciceViewController controller = (ExerciceViewController)exerciceLoader.getController();
                main.setRoot(screenMap.get(name));
                controller.setStageAndSetupListeners(main, exerciceData, SC);
            break;
            case "main":
                if(firstLaunch) {
                    MainViewController magazinesController = (MainViewController)mainLoader.getController();
                    main.setRoot( screenMap.get(name) );
                    magazinesController.setStageAndSetupListeners(main, SC);
                    firstLaunch = false;
                } else {
                    main.setRoot(screenMap.get(name));
                }
            break;
        }
    }
    
    public void setMagazineData(Magazine mag) {
        this.magazineData = mag;
    }
    
    public Magazine getMagazineData(){
        return this.magazineData;
    }
    
    public void setExerciceData(Exercice exe) {
        this.exerciceData = exe;
    }
    
    public Exercice getExerciceData() {
        return this.exerciceData;
    }
    
    public Scene getMain() {
        return this.main;
    }
    
    public void setDarkMode() {
        main.getStylesheets().remove(lightThemeUrl);
            if(!main.getStylesheets().contains(darkThemeUrl))
                main.getStylesheets().add(darkThemeUrl);
    }
    
    public void setClearMode() {
        main.getStylesheets().remove(darkThemeUrl);
            if(!main.getStylesheets().contains(lightThemeUrl))
                main.getStylesheets().add(lightThemeUrl);
    }
    
    public String getPseudo() {
        return this.pseudo;
    }
    
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    
    private ScreenController getInstance() {
        return this;
    }
}
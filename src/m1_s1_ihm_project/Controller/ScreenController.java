package m1_s1_ihm_project.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercices.Exercices;
import m1_s1_ihm_project.Model.Magazines.Magazines;

public final class ScreenController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private Magazines magazineData;
    private FXMLLoader magazineLoader;
    private Exercices exerciceData;
    private FXMLLoader exerciceLoader;
    private FXMLLoader mainLoader;
    private FXMLLoader welcomeLoader;
    private final String lightThemeUrl = getClass().getResource("/m1_s1_ihm_project/View/customCss.css").toExternalForm();
    private final String darkThemeUrl = getClass().getResource("/m1_s1_ihm_project/View/customCss_1.css").toExternalForm();
    private String pseudo;
    
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
        Parent root = (Parent)screenMap.get("welcome");
        WelcomeViewController controller = (WelcomeViewController)welcomeLoader.getController();
        main = new Scene(root);
        main.getStylesheets().add(darkThemeUrl);
        stage.setScene(main);
        stage.setMaximized(true);
        stage.setTitle("Traveler Companion");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/m1_s1_ihm_project/View/Ressources/icon.png")));
        stage.show();
        controller.setStageAndSetupListeners(this.getMain(), this);
    }

    public void activateMag(String name, Magazines mag, ScreenController SC){
        switch(name) {
            case "magazine":
                MagazineViewController controller = (MagazineViewController)magazineLoader.getController();
                main.setRoot( screenMap.get(name) );
                controller.setStageAndSetupListeners(main, magazineData, SC);
            break;
            case "main":
                MainViewController magazinesController = (MainViewController)mainLoader.getController();
                main.setRoot( screenMap.get(name) );
                magazinesController.setStageAndSetupListeners(main, SC);
        }
    }
    
    public void activateExe(String name, Exercices exe, ScreenController SC) {
        switch(name) {
            case "exercice" :
                ExerciceViewController controller = (ExerciceViewController)exerciceLoader.getController();
                main.setRoot(screenMap.get(name));
                controller.setStageAndSetupListeners(main, exerciceData, SC);
            break;
            case "main":
                MainViewController magazinesController = (MainViewController)mainLoader.getController();
                main.setRoot( screenMap.get(name) );
                magazinesController.setStageAndSetupListeners(main, SC);
            break;
        }
    }
    
    public void setMagazineData(Magazines mag) {
        this.magazineData = mag;
    }
    
    public Magazines getMagazineData(){
        return this.magazineData;
    }
    
    public void setExerciceData(Exercices exe) {
        this.exerciceData = exe;
    }
    
    public Exercices getExerciceData() {
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
}
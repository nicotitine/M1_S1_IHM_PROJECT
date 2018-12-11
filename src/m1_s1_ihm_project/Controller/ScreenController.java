package m1_s1_ihm_project.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import m1_s1_ihm_project.Model.Magazines.Magazines;

public class ScreenController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private Magazines magazineData;
    private FXMLLoader magazineLoader;
    private FXMLLoader mainLoader;
    private final String theme1Url = getClass().getResource("/m1_s1_ihm_project/View/customCss.css").toExternalForm();
    private final String theme2Url = getClass().getResource("/m1_s1_ihm_project/View/customCss_1.css").toExternalForm();
    private boolean isDarkMode;
    
    public ScreenController(Scene main) {
        this.main = main;
        isDarkMode = false;
        try {
            magazineLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            mainLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MainView.fxml"));
            screenMap.put("magazine", magazineLoader.load());
            screenMap.put("main", mainLoader.load());
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void setMagazineData(Magazines mag) {
        magazineData = mag;
    }
    public Magazines getMagazineData(){
        return magazineData;
    }
    public Scene getMain() {
        return this.main;
    }
    
    public void setDarkMode() {
        this.isDarkMode = true;
        main.getStylesheets().remove(theme1Url);
            if(!main.getStylesheets().contains(theme2Url))
                main.getStylesheets().add(theme2Url);
    }
    
    public void setClearMode() {
        this.isDarkMode = false;
        main.getStylesheets().remove(theme2Url);
            if(!main.getStylesheets().contains(theme1Url))
                main.getStylesheets().add(theme1Url);
    }
}
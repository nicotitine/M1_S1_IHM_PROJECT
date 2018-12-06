/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class ScreenController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;
    private Magazines magazineData;
    private FXMLLoader magazineLoader;
    private FXMLLoader magazinesLoader;
    
    public ScreenController(Scene main) {
        this.main = main;
        try {
            magazineLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            magazinesLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazinesView.fxml"));
            screenMap.put("magazine", magazineLoader.load());
            screenMap.put("magazines", magazinesLoader.load());
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
            case "magazines":
                MagazinesViewController magazinesController = (MagazinesViewController)magazinesLoader.getController();
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
}

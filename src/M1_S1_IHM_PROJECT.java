/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.MagazinesViewController;
import m1_s1_ihm_project.Controller.ScreenController;

/**
 *
 * @author Nico
 */
public class M1_S1_IHM_PROJECT extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Database.connect("localhost", 1527, "IHM_Project", "root", "root");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("m1_s1_ihm_project/View/MagazinesView.fxml"));
        Parent root = (Parent)loader.load();
        MagazinesViewController controller = (MagazinesViewController)loader.getController();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("m1_s1_ihm_project/View/customCss.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Learn English");
        stage.show();
        ScreenController screenController = new ScreenController(scene);
        controller.setStageAndSetupListeners(scene, screenController); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}

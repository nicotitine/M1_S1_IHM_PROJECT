/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.View;

import java.sql.Connection;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.MagazinesViewController;
import m1_s1_ihm_project.Controller.ScreenController;
import m1_s1_ihm_project.Controller.ToDelete;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class M1_S1_IHM_PROJECT extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Database.connect("localhost", 1527, "IHM_Project", "root", "root");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MagazinesView.fxml"));
        Parent root = (Parent)loader.load();
        MagazinesViewController controller = (MagazinesViewController)loader.getController();
        
        //Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("customCss.css").toExternalForm());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Learn English");
        stage.show();
        ScreenController screenController = new ScreenController(scene);
        controller.setStageAndSetupListeners(scene, screenController);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        launch(args);
    }
}

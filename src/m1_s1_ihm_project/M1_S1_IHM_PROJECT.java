/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Magazines.Book;

/**
 *
 * @author Nico
 */
public class M1_S1_IHM_PROJECT extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Learn English");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        try {
            Connection connection = java.sql.DriverManager.getConnection("jdbc:derby://localhost:1527/IHM_Project", "root", "root");
            System.out.println("Connexion à la base de données réussie !");              
        } catch(java.sql.SQLException e) {
            System.err.println(e.getMessage());
            Runtime.getRuntime().exit(1);
        }
    }
    
}

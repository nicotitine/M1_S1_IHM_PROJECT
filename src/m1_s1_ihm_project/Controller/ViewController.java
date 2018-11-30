/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javax.imageio.ImageIO;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class ViewController implements Initializable {
    
    private Database database;
    
    private Label label;
    
    
    @FXML 
    private TabPane tabPane;
    
    
    @FXML private JFXMasonryPane magazinesMP;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        database = new Database("localhost", 1527, "IHM_Project", "root", "root");
        ObservableList<Magazines> magazines = database.getMagazines();
        
        for (Magazines magazine : magazines) {
            VBox vbox = new VBox();
            vbox.setPrefSize(300, 400);            
            vbox.getChildren().add(new Label(magazine.getTitle()));
            vbox.getChildren().add(ImageViewBuilder.create().image(new Image("https://nicolashincelin.fr/test.jpg")).build());
            Text text = new Text(magazine.getDescription());
            text.setWrappingWidth(300);
            vbox.getChildren().add(text);
            vbox.setStyle("-fx-border-color: black;");
            vbox.setStyle("-fx-background-color: white;");
            magazinesMP.getChildren().add(vbox);
        }
        
        System.out.println(magazines.get(0).getTitle());
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        tabPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        //magazinesTab.set(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        
    }    
    
}

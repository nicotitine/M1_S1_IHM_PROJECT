/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class ToDelete implements Initializable {
    
     
    
    @FXML
    private JFXTabPane tabPane;

    @FXML
    private Tab magazinesTab;

    @FXML
    private FlowPane magazinesMP;

    @FXML
    private Label magazineTitle1;

    @FXML
    private ImageView magazineImageView1;

    @FXML
    private Text magazineText1;

    @FXML
    private JFXButton magazineConsult1;

    @FXML
    private Label magazineTitle2;

    @FXML
    private ImageView magazineImageView2;

    @FXML
    private Text magazineText2;

    @FXML
    private JFXButton magazineConsult2;

    @FXML
    private Label magazineTitle3;

    @FXML
    private ImageView magazineImageView3;

    @FXML
    private Text magazineText3;

    @FXML
    private JFXButton magazineConsult3;

    @FXML
    private Label magazineTitle4;

    @FXML
    private ImageView magazineImageView4;

    @FXML
    private Text magazineText4;

    @FXML
    private JFXButton magazineConsult4;

    @FXML
    private Label magazineTitle5;

    @FXML
    private ImageView magazineImageView5;

    @FXML
    private Text magazineText5;

    @FXML
    private JFXButton magazineConsult5;
    
    @FXML
    private ScrollPane magazinesScrollPane;
    
    private double windowWidth;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //state = FXCollections.observableArrayList();
        
        if(event.getSource().equals(magazineConsult1)) {
            magazinesMP.getChildren().clear();
            Magazines mag = Database.getMagazine("1");
            System.out.println(magazinesMP.getChildren().size());
            //showMagazine(mag);
            try {
            System.out.println(mag.getTitle());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            VBox loadedVbox = loader.load();
            JFXButton btn = (JFXButton)loader.getNamespace().get("exitMag");
            loadedVbox.setPrefWidth(windowWidth);
            magazinesMP.getChildren().add(loadedVbox);
            System.out.println("seems to be added " + loadedVbox.getChildren().size());
            btn.setOnAction((ActionEvent e) -> {System.out.println(btn.getText() + " wtf");
                magazinesMP.getChildren().clear();
                FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/View.fxml"));
                try {
                    backLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ToDelete.class.getName()).log(Level.SEVERE, null, ex);
                }
                FlowPane mp = (FlowPane) backLoader.getNamespace().get("magazinesMP");
                System.out.println(mp.getChildren().size());
                magazinesMP.getChildren().addAll(mp.getChildren());
                
            });
        } catch (IOException ex) {
            Logger.getLogger(ToDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            System.out.println("other event");
        }
        if(event.getSource().equals(magazineConsult2)) {
            
        }
    }
    
    
    private void showMagazine(Magazines mag) {
        try {
            System.out.println(mag.getTitle());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            VBox loadedVbox = loader.load();
            JFXButton btn = (JFXButton)loader.getNamespace().get("exitMag");
            loadedVbox.setPrefWidth(windowWidth);
            magazinesMP.getChildren().add(loadedVbox);
            magazinesMP.setPadding(new Insets(-20, 0, 0, 0));
            btn.setOnAction((ActionEvent event) -> {
                magazinesMP.getChildren().clear();
            });
        } catch (IOException ex) {
            Logger.getLogger(ToDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    public void setStageAndSetupListeners(Scene scene) {
        ScreenController screenController = new ScreenController(scene);        
        Stage myStage = (Stage)scene.getWindow();
        windowWidth = scene.getWidth();
        myStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazinesScrollPane.setPrefWidth((double)newVal);
            tabPane.setPrefWidth((double)newVal);
            magazinesMP.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}

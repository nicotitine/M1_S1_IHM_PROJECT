/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 * FXML Controller class
 *
 * @author Nico
 */
public class MagazinesViewController implements Initializable {

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
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(magazineConsult1)) {
            
            magazinesMP.getChildren().clear();
            Magazines mag = Database.getMagazine("1");
            System.out.println(magazinesMP.getChildren().size());
            //showMagazine(mag);
            try {
            System.out.println(mag.getTitle());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazineView.fxml"));
            FlowPane loadedVbox = loader.load();
            JFXButton btn = (JFXButton)loader.getNamespace().get("exitMag");
            //loadedVbox.setPrefWidth(windowWidth);
            magazinesMP.getChildren().add(loadedVbox);
            System.out.println("seems to be added " + loadedVbox.getChildren().size());
            btn.setOnAction((ActionEvent e) -> {System.out.println(btn.getText() + " wtf");
                magazinesMP.getChildren().clear();
                FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/m1_s1_ihm_project/View/MagazinesView.fxml"));
                FlowPane loadedFlowPane;
                try {
                    backLoader.load();
                } catch (IOException ex) {
                    Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                FlowPane mp = (FlowPane) backLoader.getNamespace().get("magazinesMP");
                System.out.println(mp.getChildren().size());
                System.out.println(magazinesMP.getParent());
                
                
                
            });
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            System.out.println("other event");
        }
        if(event.getSource().equals(magazineConsult2)) {
            
        }
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Magazines> magazines = Database.getMagazines();
        
        ObservableList<Label> magazineTitles = FXCollections.observableArrayList();
        ObservableList<Text> magazineTexts = FXCollections.observableArrayList();
        ObservableList<ImageView> magazineImages = FXCollections.observableArrayList();
        
        magazineTitles.add(magazineTitle1);
        magazineTitles.add(magazineTitle2);
        magazineTitles.add(magazineTitle3);
        magazineTitles.add(magazineTitle4);
        magazineTitles.add(magazineTitle5);
        magazineTexts.add(magazineText1);
        magazineTexts.add(magazineText2);
        magazineTexts.add(magazineText3);
        magazineTexts.add(magazineText4);
        magazineTexts.add(magazineText5);
        magazineImages.add(magazineImageView1);
        magazineImages.add(magazineImageView2);
        magazineImages.add(magazineImageView3);
        magazineImages.add(magazineImageView4);
        magazineImages.add(magazineImageView5);
        int maxSize;
        if(magazineTitles.size() < magazines.size()) {
            maxSize = magazineTitles.size();
        } else {
            maxSize = magazines.size();
        }
        
        for(int i = 0; i < maxSize; i++) {
            magazineTitles.get(i).setText(magazines.get(i).getTitle());
            magazineTexts.get(i).setText(magazines.get(i).getDescription());
            magazineImages.get(i).setImage(new Image(magazines.get(i).getImageUrl()));
        }
    }    
    
}

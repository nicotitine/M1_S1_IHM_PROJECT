package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Magazines.Audio;
import m1_s1_ihm_project.Model.Magazines.Magazines;
import m1_s1_ihm_project.Model.Magazines.Video;

public class MagazineViewController implements Initializable {

    @FXML private FlowPane magazineFlowPane;
    @FXML private VBox magazineVBox;
    @FXML private ScrollPane scrollPaneMedia;
    @FXML private JFXButton exitMag;
    @FXML private Label title;
    @FXML private Label date;
    @FXML private Label type;
    @FXML private Text description;
    @FXML private JFXButton backBtn;
    @FXML private Label secondaryTitle;
    @FXML private HBox subHeaderHBox;
    @FXML private HBox btnGroupBuyShare;
    @FXML private JFXButton buyBook;
    @FXML private JFXButton shareBook;
    
    private ScreenController screenController;
    private Magazines thisMag;
    private double windowWidth;
    private Stage thisStage;
    private ImageView imageMediaView;
    private WebView videoMediaView;
    private WebView audioMediaView;
    
    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(exitMag) || event.getSource().equals(backBtn)) {
            screenController.activateMag("main", thisMag, screenController);
            if(videoMediaView != null) {
                videoMediaView.getEngine().load(null);
            }
            if(audioMediaView != null) {
                audioMediaView.getEngine().load(null);
            }
        }
        if(event.getSource().equals(buyBook)) {
            try {
                Desktop.getDesktop().browse(new URI(thisMag.getBrowsingUrl()));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(MagazineViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML private void hoverFlatBtn() {
        exitMag.setTextFill(Color.web("#104672"));
    }
    @FXML private void unHoverFlatBtn() {
        exitMag.setTextFill(Color.web("#1B75BC"));
    }
    
    public void setStageAndSetupListeners(Scene scene, Magazines mag, ScreenController SC) {
        thisStage = (Stage)scene.getWindow();
        windowWidth = scene.getWidth();
        magazineFlowPane.setPrefWidth(windowWidth);
        magazineVBox.setPrefWidth(windowWidth);
        scrollPaneMedia.setPrefWidth(windowWidth);
        subHeaderHBox.setPrefWidth(windowWidth);
        secondaryTitle.setPrefWidth(windowWidth/2);
        btnGroupBuyShare.setPrefWidth(windowWidth/2);
        screenController = SC;
        thisMag = mag;
        title.setText(mag.getTitle());
        description.setText(mag.getDescription().replace("\\n", "\n"));
        date.setText("Date de publication : " + new SimpleDateFormat("dd/MM/yyyy").format(mag.getPublishDate()));
        switch(mag.getType()) {
            case "book" :
                type.setText("Type : Livre");
                imageMediaView = new ImageView();
                imageMediaView.setImage(new Image(mag.getImageUrl()));
                imageMediaView.setFitHeight(300*3);
                imageMediaView.setFitWidth(windowWidth);
                scrollPaneMedia.setContent(imageMediaView);
                scrollPaneMedia.setPrefHeight(350);
                secondaryTitle.setText("Synopsis : ");
                buyBook.setText("Acheter ce livre dans votre navigateur");
                shareBook.setText("Partager ce livre");
            break;
            case "video" :
                Video vid = (Video)mag;
                type.setText("Type : Vidéo");
                videoMediaView = new WebView();
                videoMediaView.getEngine().load(vid.getMediaUrl());
                videoMediaView.setPrefSize(480, 700);
                scrollPaneMedia.setContent(videoMediaView);
                secondaryTitle.setText("Description : ");
                scrollPaneMedia.setPrefHeight(700);
                subHeaderHBox.setPrefHeight(100);
                buyBook.setText("Ouvrir la vidéo dans votre navigateur");
                shareBook.setText("Partager cette vidéo");
            break;
            case "audio" :
                Audio audio = (Audio)mag;
                type.setText("Type : Document audio");
                audioMediaView = new WebView();
                audioMediaView.getEngine().load(audio.getMediaUrl());
                audioMediaView.setPrefSize(windowWidth, 350);
                scrollPaneMedia.setContent(audioMediaView);
                buyBook.setText("Ouvrir dans votre navigateur");
                shareBook.setText("Partager ce document audio");
            break;
        }
        
        description.setWrappingWidth(windowWidth/2);
         
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            magazineFlowPane.setPrefWidth((double)newVal);
            magazineVBox.setPrefWidth((double)newVal);
            windowWidth = (double)newVal;
            if(imageMediaView != null)
                imageMediaView.setFitWidth(windowWidth);
            if(videoMediaView != null) {
                videoMediaView.setPrefWidth(windowWidth);
            }
            description.setWrappingWidth(windowWidth/2);
        });
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
       
    }
}
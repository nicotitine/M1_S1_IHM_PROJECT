package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;

public class WelcomeViewController implements Initializable {

    @FXML private StackPane pane;
    private JFXButton buttonStart;
    private JFXTextField pseudoField;
    
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        Platform.runLater(() -> {
            // Load the main view while the user is on the welcome screen.
            // On "new Image()", adding the parameter "backgroundLoading" as true removes any loading time !!!
            SC.loadHome(SC);
        });
        
        buttonStart.setOnAction((ActionEvent event) -> {
            SC.setPseudo(pseudoField.getText());
            if(!Database.getIsDatabaseConnected()) {
               SC.activateMag("error", null, SC);
            } else 
                SC.activateMag("main", null, SC);
            
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/m1_s1_ihm_project/View/Resources/welcomeVideo.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer); 
        VBox mainBox = new VBox();
        VBox secondaryBox = new VBox();
        Text title = new Text("Bienvenu sur Traveler Companion");
        Text description = new Text("Apprenez rapidement et efficacement l'anglais avec cet assistant !\n\nEt le tout sans débourser un centime !");
        Text toStart = new Text("Pour commencer : ");
        VBox pseudoBox = new VBox();
        Text pseudoLabel = new Text("Entrez un pseudo : (Not working)");
        pseudoField = new JFXTextField();
        buttonStart = new JFXButton("Commencer !");
        VBox buttonWrapper = new VBox();
        
        mediaPlayer.play();
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  
        mediaPlayer.setAutoPlay(true);
        mediaView.setFitWidth(Screen.getPrimary().getBounds().getWidth());
        mediaView.setFitHeight(Screen.getPrimary().getBounds().getHeight());
        title.getStyleClass().addAll("medium-title", "title");
        description.setStyle("-fx-font-size: 14px; -fx-fill: #313131;");
        description.setWrappingWidth(380);
        toStart.getStyleClass().addAll("title", "small-title");
        pseudoLabel.setStyle("-fx-font-size: 14px;");
        pseudoLabel.setWrappingWidth(230);
        pseudoField.setPromptText("Ex : Fab");
        pseudoField.setPrefWidth(230);
        pseudoField.setStyle("-jfx-focus-color: #1b75bc");
        pseudoBox.getChildren().addAll(pseudoLabel, pseudoField);
        pseudoBox.setSpacing(10);
        pseudoBox.setAlignment(Pos.CENTER);
        pseudoBox.setPrefWidth(400);
        pseudoBox.setFillWidth(false);
        buttonStart.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-background-color:#1b75bc");
        buttonStart.setButtonType(JFXButton.ButtonType.RAISED);
        buttonStart.setCursor(Cursor.HAND);
        buttonWrapper.setAlignment(Pos.CENTER);
        buttonWrapper.getChildren().add(buttonStart);
        buttonWrapper.setPrefWidth(400);
        secondaryBox.setPrefSize(400 , 400);
        secondaryBox.getChildren().addAll(title, description, new VBox(),toStart, pseudoBox, new VBox(), buttonWrapper);
        secondaryBox.setSpacing(20);
        secondaryBox.setStyle("-fx-background-color: white; -fx-background-radius: 7px; -fx-border-radius: 7px; -fx-padding: 20px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0);");
        secondaryBox.setFillWidth(false);
        mainBox.setFillWidth(false);
        mainBox.getChildren().add(secondaryBox);
        pane.getChildren().add(mediaView);
        pane.getChildren().add(mainBox);
        mainBox.setAlignment(Pos.CENTER);
        
        // Cut a video bug (camera became crazy : https://youtu.be/AmJrybOE9bs?t=75 )
        mediaPlayer.currentTimeProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.toSeconds() > 69 && newVal.toSeconds() < 70) {
                mediaPlayer.seek(new Duration(newVal.toMillis() + 11000));
            }
        });
    }
}
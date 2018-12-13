package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import javafx.util.Duration;

public class WelcomeViewController implements Initializable {

    @FXML private StackPane pane;
    private JFXButton buttonStart;
    private JFXTextField pseudoField;
    
    public void setStageAndSetupListeners(Scene scene, ScreenController SC) {
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                SC.setPseudo(pseudoField.getText());
                SC.activateMag("main", null, SC);
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File file = new File("src/m1_s1_ihm_project/View/ressources/welcomeVideo.mp4");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setMute(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer); 
        
        VBox mainBox = new VBox();
        VBox secondaryBox = new VBox();
        Text title = new Text("Bienvenu sur Traveler Companion");
        Text description = new Text("Apprenez rapidement l'anglais avec cet assistant et obtenez un dipome reconnu par l'état.\n\nEt le tout sans débourser un centime !");
        Text toStart = new Text("Pour commencer : ");
        VBox pseudoBox = new VBox();
        Text pseudoLabel = new Text("Entrez un pseudo :");
        pseudoField = new JFXTextField();
        buttonStart = new JFXButton("Commencer !");
        VBox buttonWrapper = new VBox();
        
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        description.setStyle("-fx-font-size: 14px; -fx-fill: #313131;");
        description.setWrappingWidth(380);
        toStart.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pseudoLabel.setStyle("-fx-font-size: 14px;");
        pseudoLabel.setWrappingWidth(190);
        pseudoField.setPromptText("Ex : Fab");
        pseudoField.setPrefWidth(190);
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
        
        // Cut a video bug (camera became crazy)
        mediaPlayer.currentTimeProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.toSeconds() > 69 && newVal.toSeconds() < 70) {
                mediaPlayer.seek(new Duration(newVal.toMillis() + 11000));
            }
        });
        
        mainBox.getChildren().add(secondaryBox);
        pane.getChildren().add(mediaView);
        pane.getChildren().add(mainBox);
        mainBox.setAlignment(Pos.CENTER);
    }
}
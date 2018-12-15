package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import m1_s1_ihm_project.Model.Exercice.Exercice;

public class ExerciceTemplateController implements Initializable {
    
    @FXML private Text exerciceTitle, exerciceText;
    @FXML private ImageView exerciceImageView;
    @FXML private Label exerciceType, exerciceDuration;
    @FXML private JFXButton exerciceConsult;
    
    private ScreenController screenController;
    private int id_database;
    private int textMaxLength;

    public void setStageAndSetupListeners(Exercice exe, ScreenController SC, int id_data) {
        screenController = SC;
        id_database = id_data;
        exerciceTitle.setText(exe.getTitle());
        exerciceType.setText(exe.getType().toUpperCase());
        exerciceImageView.setImage(new Image(exe.getImageUrl(), true));
        if(exe.getDescription().length() > textMaxLength)
            exerciceText.setText(exe.getDescription().substring(0, 300) + "...");
        else
            exerciceText.setText(exe.getDescription());

        exerciceText.setWrappingWidth(340);
        exerciceDuration.setText("Durée : " + exe.getDuration());
    }

    @FXML public void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(exerciceConsult)) {
            Exercice exe = Database.getExercice(id_database);
            screenController.setExerciceData(exe);
            screenController.activateExe("exercice", exe, screenController);
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        textMaxLength = 300;
    }
}

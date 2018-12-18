package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import m1_s1_ihm_project.Model.Magazine.Magazine;

public class MagazineTemplateController implements Initializable {

    @FXML private Text magazineTitle, magazineText;
    @FXML private ImageView magazineImageView;
    @FXML private Label magazineType, magazineDate;
    @FXML private JFXButton magazineConsult;
    
    private int textMaxLength;
    private int id_database;
    private ScreenController screenController;
    
    public void setStageAndSetupListeners(Magazine mag, ScreenController SC, int id_data) {
        screenController = SC;
        id_database = id_data;
        magazineTitle.setText(mag.getTitle());
        magazineDate.setText("Publié le : " + new SimpleDateFormat("dd/MM/yyyy").format(mag.getPublishDate()));
        magazineImageView.setImage(new Image(mag.getImageUrl(), true));
        if(mag.getDescription().length() > textMaxLength)
            magazineText.setText(mag.getDescription().substring(0, 300) + "...");
        else
            magazineText.setText(mag.getDescription());
        
        magazineText.setWrappingWidth(340);
        switch(mag.getType()) {
            case "book":
                magazineType.setText("Livre");
            break;
            case "video":
                magazineType.setText("Vidéo");
            break;
            case "audio":
                magazineType.setText("Audio");
            break;
            case "document":
                magazineType.setText("Document");
            break;
        }
    }

    @Override public void initialize(URL url, ResourceBundle rb) {
        textMaxLength = 300;
    }    

    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(magazineConsult)) {
            Magazine mag = Database.getMagazine(id_database);
            screenController.setMagazineData(mag);
            screenController.activateMag("magazine", mag, screenController);
        }
    }
}
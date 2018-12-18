package m1_s1_ihm_project.View;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DatabaseErrorView extends StackPane{
    
    private JFXDialog dialog;
    
    public DatabaseErrorView(String message, String error) {
            ImageView imageView = new ImageView(new Image("/m1_s1_ihm_project/View/Resources/errorIcon.png"));
            JFXDialogLayout content= new JFXDialogLayout();
            Text header = new Text("Erreur !");
            Text body = new Text(message);
            Text answersText = new Text(error);
            Label errorLabel = new Label("Description de l'erreur :");
            errorLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");
            body.setWrappingWidth(450);
            answersText.setWrappingWidth(450);
            body.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            header.setStyle("-fx-font-size : 24px");
            body.setStyle("-fx-font-size : 18px");
            answersText.setStyle("-fx-font-size: 14px");
            VBox test = new VBox();
            HBox imageBox = new HBox(imageView);
            imageBox.setAlignment(Pos.CENTER);
            test.getChildren().addAll(body, imageBox, errorLabel, answersText);
            test.setSpacing(30);
            content.setHeading(header);
            content.setBody(test);
            dialog = new JFXDialog(this, content, JFXDialog.DialogTransition.CENTER);
    }
    
    public void show() {
        dialog.show();
    }
}

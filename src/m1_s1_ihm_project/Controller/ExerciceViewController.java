package m1_s1_ihm_project.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import m1_s1_ihm_project.Model.Exercice.Exercice;

public class ExerciceViewController implements Initializable {

    @FXML private FlowPane exerciceFlowPane;
    @FXML private VBox exerciceVBox, checkboxVB;
    @FXML private ScrollPane scrollPaneMedia, exerciceScrollPane;
    @FXML private JFXButton exitMag, backBtn, validateBtn;
    @FXML private Label title, type, secondaryTitle;
    @FXML private Text description;
    @FXML private HBox subHeaderHBox;
    @FXML private StackPane stackpane;
    
    private ScreenController screenController;
    private Exercice thisExe;
    private double windowWidth;
    private double windowHeight;
    private Stage thisStage;
    private ImageView imageMediaView;
    private WebView videoMediaView;
    private WebView audioMediaView;
    private JFXDialog dialog;
    
    @FXML private void handleButtonAction(ActionEvent event) {
        if(event.getSource().equals(exitMag) || event.getSource().equals(backBtn)) {
            screenController.activateExe("main", thisExe, screenController);
            if(videoMediaView != null)
                videoMediaView.getEngine().load(null);
            if(audioMediaView != null) 
                audioMediaView.getEngine().load(null);
        }
        
        if(event.getSource().equals(validateBtn)) {
            List<String> answers = new ArrayList<>();
            switch(thisExe.getType()) {
                case "qcm": 
                    for(int i = 0; i < checkboxVB.getChildren().size(); i++) {
                        JFXCheckBox tempQcm = (JFXCheckBox)checkboxVB.getChildren().get(i);
                        if(tempQcm.isSelected()) {
                            answers.add(tempQcm.getText());
                        }
                    }
                break;
                case "tat":
                    Set<Node> textFields= checkboxVB.lookupAll("JFXTextField");
                    for (Iterator<Node> it = textFields.iterator(); it.hasNext(); ) {
                        JFXTextField f = (JFXTextField)it.next();
                        answers.add(f.getText());
                    }
                break;
            }
            
            boolean isExeValid = true;
            boolean isEnd = false;
            int index = 0;
            if(answers.size() != thisExe.getAnswers().length) 
                isExeValid = false;
            while(isExeValid == true && isEnd == false) {
                if(!answers.get(index).equals(thisExe.getAnswers()[index]))
                    isExeValid = false;
                index++;
                if(index >= answers.size())
                    isEnd = true;
            }
            String bodyString;
            ImageView imageView;
            if(isExeValid) {
                bodyString = "Bravo, l'exercice a été complété sans erreur !\n";
                imageView = new ImageView(new Image("/m1_s1_ihm_project/View/Resources/okIcon.png"));
            } else {
                bodyString = "Dommage, toutes les réponses ne sont pas correctes.\n";
                imageView = new ImageView(new Image("/m1_s1_ihm_project/View/Resources/errorIcon.png"));
            }
            String answersString = "";
            for (String answer : thisExe.getAnswers()) {
                answersString += "\t- " + answer + "\n";
            }
            JFXDialogLayout content= new JFXDialogLayout();
            Text header = new Text("Résultat de l'exercice");
            Text body = new Text(bodyString);
            Text answersText = new Text("\nLes réponses étaient :");
            Text answersTextBold = new Text(answersString);
            answersTextBold.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXButton closeBtn = new JFXButton("Fermer");
            closeBtn.setStyle("-fx-font-size: 18px; -fx-text-fill: #1b75bc");
            header.setStyle("-fx-font-size : 24px");
            body.setStyle("-fx-font-size : 18px");
            answersText.setStyle("-fx-font-size: 18px");
            VBox test = new VBox();
            HBox imageBox = new HBox(imageView);
            HBox closeBox = new HBox(closeBtn);
            closeBox.setAlignment(Pos.CENTER_RIGHT);
            imageBox.setAlignment(Pos.CENTER);
            test.getChildren().addAll(body, imageBox, answersText, answersTextBold, closeBox);
            content.setHeading(header);
            content.setBody(test);
            dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
            dialog.show();
            closeBtn.setCursor(Cursor.HAND);
            closeBtn.setOnAction((ActionEvent event1) -> {
                dialog.close();
            });
        }
    }
    
    @FXML private void hoverFlatBtn() {
        exitMag.setTextFill(Color.web("#104672"));
    }
    @FXML private void unHoverFlatBtn() {
        exitMag.setTextFill(Color.web("#1B75BC"));
    }
    
    public void setStageAndSetupListeners(Scene scene, Exercice exe, ScreenController SC) {
        thisStage = (Stage)scene.getWindow();
        windowWidth = scene.getWidth();
        windowHeight = scene.getHeight() - 2;
        exerciceFlowPane.setPrefSize(windowWidth, windowHeight);
        exerciceScrollPane.setPrefSize(windowWidth, windowHeight);
        exerciceVBox.setPrefSize(windowWidth, windowHeight);
        scrollPaneMedia.setPrefWidth(windowWidth);
        subHeaderHBox.setPrefWidth(windowWidth);
        secondaryTitle.setPrefWidth(windowWidth/2);
        screenController = SC;
        thisExe = exe;
        title.setText(exe.getTitle());
        description.setText(exe.getDescription().replace("\\n", "\n"));
        checkboxVB.getChildren().clear();
        
        switch(exe.getType()) {
            case "qcm" :
                type.setText("Type : QCM");
                imageMediaView = new ImageView();
                imageMediaView.setImage(new Image(exe.getImageUrl()));
                imageMediaView.setFitHeight(300*3);
                imageMediaView.setFitWidth(windowWidth);
                scrollPaneMedia.setContent(imageMediaView);
                scrollPaneMedia.setPrefHeight(350);
                secondaryTitle.setText("Consigne : ");
                for (String question : thisExe.getQuestions()) {
                    JFXCheckBox checkBox = new JFXCheckBox(question);
                    checkBox.setStyle("-fx-font-size: 18px");
                    checkBox.getStyleClass().add("checkbox");
                    checkboxVB.getChildren().add(checkBox);
                }
            break;
            case "tat" :
                type.setText("Type : Texte à trous");
                imageMediaView = new ImageView();
                imageMediaView.setImage(new Image(exe.getImageUrl()));
                imageMediaView.setFitHeight(300*3);
                imageMediaView.setFitWidth(windowWidth);
                scrollPaneMedia.setContent(imageMediaView);
                scrollPaneMedia.setPrefHeight(350);
                secondaryTitle.setText("Consigne : ");
                scrollPaneMedia.setPrefHeight(400);
                subHeaderHBox.setPrefHeight(100);
                for (String question : thisExe.getQuestions()) {
                    String[] splitedQuestion = question.split("%");
                    HBox box = new HBox();
                    box.setSpacing(20);
                    Label firstPart = new Label(splitedQuestion[0]);
                    Label secondPart = new Label(splitedQuestion[1]);
                    firstPart.setStyle("-fx-font-size: 18px");
                    firstPart.getStyleClass().add("classicText");
                    secondPart.setStyle("-fx-font-size: 18px;");
                    secondPart.getStyleClass().add("classicText");
                    JFXTextField field = new JFXTextField();
                    field.setPromptText("votre réponse ...");
                    field.setStyle("-fx-font-size: 18px");
                    field.setCursor(Cursor.HAND);
                    field.getStyleClass().addAll("classicText", "searchInput");
                    box.getChildren().addAll(firstPart, field, secondPart);
                    box.setAlignment(Pos.CENTER_LEFT);
                    checkboxVB.getChildren().add(box);
                }
            break;
        }
        
        description.setWrappingWidth(windowWidth/2);
        thisStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            exerciceFlowPane.setPrefWidth((double)newVal);
            exerciceVBox.setPrefWidth((double)newVal);
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
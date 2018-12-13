import javafx.application.Application;
import javafx.stage.Stage;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.ScreenController;

public class M1_S1_IHM_PROJECT extends Application {
        
    @Override
    public void start(Stage stage) throws Exception {
        Database.connect("localhost", 1527, "IHM_Project", "root", "root");
        ScreenController screenController = new ScreenController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
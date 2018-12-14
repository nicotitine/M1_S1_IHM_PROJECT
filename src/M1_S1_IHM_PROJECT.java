import javafx.application.Application;
import javafx.stage.Stage;
import m1_s1_ihm_project.Controller.Database;
import m1_s1_ihm_project.Controller.ScreenController;

public class M1_S1_IHM_PROJECT extends Application {
    
    // MODIFY HERE IF YOU CREATED YOUR OWN DATABSE
    private final String serverName = "localhost";
    private final int serverPort = 1527;
    private final String dbName = "IHM_Project";
    private final String dbUser = "root";
    private final String dbPassword = "root";
    ///////////////////////////////////////////////
        
    @Override
    public void start(Stage stage) throws Exception {
        Database.connect(serverName, serverPort, dbName, dbUser, dbPassword);
        ScreenController screenController = new ScreenController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package m1_s1_ihm_project.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import m1_s1_ihm_project.Model.Exercice.Exercice;
import m1_s1_ihm_project.Model.Magazine.Audio;
import m1_s1_ihm_project.Model.Magazine.Book;
import m1_s1_ihm_project.Model.Magazine.Document;
import m1_s1_ihm_project.Model.Magazine.Magazine;
import m1_s1_ihm_project.Model.Magazine.Video;
import m1_s1_ihm_project.Model.Tools.EnglishNumber;
import m1_s1_ihm_project.Model.Tools.EnglishTime;

public class Database {
    
    private static Connection connection;
    private static ObservableList<Magazine> magazinesList;
    private static ObservableList<Exercice> exercicesList;
    private static ObservableList<EnglishTime> timesList;
    private static ObservableList<EnglishNumber> numbersList;
    private static String errorConnect;
    private static boolean isDatabaseConnected;
        
    public static boolean connect(String host, int port, String databaseName, String user, String password) {
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:derby://" + host + ":" + port + "/" + databaseName, user, password);
            System.out.println("Connexion à la base de données réussie !");
            magazinesList = FXCollections.observableArrayList();
            exercicesList = FXCollections.observableArrayList();
            timesList = FXCollections.observableArrayList();
            numbersList = FXCollections.observableArrayList();
            Database.retrieveMagazinesFromDatabse();
            Database.retrieveExercicesFromDatabase();
            Database.retrieveTimesFromDatabase();
            Database.retrieveNumbersFromDatabase();
            connection.close();
            isDatabaseConnected =  true;
        } catch(java.sql.SQLException e) {
            errorConnect = e.getMessage();
            System.err.println(e.getMessage());
            isDatabaseConnected = false;
        }
        return isDatabaseConnected;
    }
    
    private static void retrieveMagazinesFromDatabse() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet magazinesResultSet = stmt.executeQuery("SELECT * FROM MAGAZINES");
            while(magazinesResultSet.next()){
                String title = magazinesResultSet.getString("TITLE");
                String description = magazinesResultSet.getString("DESCRIPTION").replace("\\n", "\n");
                String imageUrl = magazinesResultSet.getString("IMAGEURL");
                Date publishDate = magazinesResultSet.getDate("PUBLISHDATE");
                String type = magazinesResultSet.getString("TYPE");
                String mediaUrl = magazinesResultSet.getString("MEDIAURL");
                String browsingUrl = magazinesResultSet.getString("BROWSINGURL");
                switch(type) {
                    case "book":
                        magazinesList.add(new Book(title, description, imageUrl, publishDate, type, browsingUrl));
                    break;
                    case "audio":
                        magazinesList.add(new Audio(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl));
                    break;
                    case "document" :
                        magazinesList.add(new Document(title, description, imageUrl, publishDate, type, browsingUrl));
                    break;
                    case "video" :
                        magazinesList.add(new Video(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl));
                    break;
                    default: 
                        magazinesList.add(new Magazine(title, description, imageUrl, publishDate, type, browsingUrl));
                }
            }
            magazinesResultSet.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void retrieveExercicesFromDatabase() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet exercicesResultSet = stmt.executeQuery("SELECT * FROM EXERCICES");
            while(exercicesResultSet.next()){
                String title = exercicesResultSet.getString("TITLE");
                String description = exercicesResultSet.getString("DESCRIPTION");
                String[] questions = exercicesResultSet.getString("QUESTIONS").split(", ");
                String[] answers = exercicesResultSet.getString("ANSWERS").split(", ");
                String duration = exercicesResultSet.getString("DURATION");
                String imageUrl = exercicesResultSet.getString("IMAGEURL");
                String type = exercicesResultSet.getString("TYPE");
                exercicesList.add(new Exercice(title, description, questions, answers, duration, imageUrl, type));
            }
            exercicesResultSet.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void retrieveTimesFromDatabase() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet timesResultSet = stmt.executeQuery("SELECT * FROM TIMES");
            while(timesResultSet.next()){
                String time = timesResultSet.getString("TIME");
                String example = timesResultSet.getString("EXAMPLE");
                String explenation = timesResultSet.getString("EXPLENATION");
                timesList.add(new EnglishTime(time, example, explenation));
            }
            timesResultSet.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void retrieveNumbersFromDatabase() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet numbersResultSet = stmt.executeQuery("SELECT * FROM NUMBERS");
            while(numbersResultSet.next()) {
                String number = numbersResultSet.getString("NUMBER");
                String numberEn = numbersResultSet.getString("NUMBEREN");
                String ordinal = numbersResultSet.getString("ORDINAL");
                String ordinalEn = numbersResultSet.getString("ORDINALEN");
                numbersList.add(new EnglishNumber(number, numberEn, ordinal, ordinalEn));
            }
            numbersResultSet.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ObservableList<Magazine> getMagazines() {
        return magazinesList;
    }
    
    public static ObservableList<Exercice> getExercices() {
        return exercicesList;
    }
    
    public static ObservableList<EnglishTime> getTimes() {
        return timesList;
    }
    
    public static ObservableList<EnglishNumber> getNumbers() {
        return numbersList;
    }
    
    public static Magazine getMagazine(int index) {
        return magazinesList.get(index);
    }
    
    public static Exercice getExercice(int index) {
        return exercicesList.get(index);
    }
    
    public static String getErrorConnect() {
        return errorConnect;
    }
    
    public static boolean getIsDatabaseConnected() {
        return isDatabaseConnected;
    }
}
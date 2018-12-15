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
import m1_s1_ihm_project.Model.Exercices.Exercices;
import m1_s1_ihm_project.Model.Magazines.Audio;
import m1_s1_ihm_project.Model.Magazines.Book;
import m1_s1_ihm_project.Model.Magazines.Document;
import m1_s1_ihm_project.Model.Magazines.Magazines;
import m1_s1_ihm_project.Model.Magazines.Video;
import m1_s1_ihm_project.Model.Tools.EnglishNumbers;
import m1_s1_ihm_project.Model.Tools.EnglishTime;

public class Database {
    
    private static Connection connection;
    private static ObservableList<Magazines> magazinesList;
    private static ObservableList<Exercices> exercicesList;
    private static ObservableList<EnglishTime> timesList;
    private static ObservableList<EnglishNumbers> numbersList;
        
    public static void connect(String host, int port, String databaseName, String user, String password) {
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
        } catch(java.sql.SQLException e) {
            System.err.println(e.getMessage());
            Runtime.getRuntime().exit(1);
        }
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
                        magazinesList.add(new Magazines(title, description, imageUrl, publishDate, type, browsingUrl));
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
                exercicesList.add(new Exercices(title, description, questions, answers, duration, imageUrl, type));
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
                numbersList.add(new EnglishNumbers(number, numberEn, ordinal, ordinalEn));
            }
            numbersResultSet.close();
            stmt.close();
        } catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ObservableList<Magazines> getMagazines() {
        return magazinesList;
    }
    
    public static ObservableList<Exercices> getExercices() {
        return exercicesList;
    }
    
    public static ObservableList<EnglishTime> getTimes() {
        return timesList;
    }
    
    public static ObservableList<EnglishNumbers> getNumbers() {
        return numbersList;
    }
    
    public static Magazines getMagazine(int index) {
        return magazinesList.get(index);
    }
    
    public static Exercices getExercice(int index) {
        return exercicesList.get(index);
    }
}
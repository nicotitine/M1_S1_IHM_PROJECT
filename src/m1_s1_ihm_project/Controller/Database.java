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
        
    public static void connect(String host, int port, String databaseName, String user, String password) {
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:derby://" + host + ":" + port + "/" + databaseName, user, password);
            System.out.println("Connexion à la base de données réussie !");              
        } catch(java.sql.SQLException e) {
            System.err.println(e.getMessage());
            Runtime.getRuntime().exit(1);
        }
    }
    
    public static ObservableList<Magazines> getMagazines() {
        ObservableList<Magazines> magazinesList = FXCollections.observableArrayList();
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
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return magazinesList;
    }
    
    public static Magazines getMagazine(String id) {
        Magazines mag = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet magazineResultSet = stmt.executeQuery("SELECT * FROM MAGAZINES WHERE ID=" + id);
            if(magazineResultSet.next()) {
                String title = magazineResultSet.getString("TITLE");
                String description = magazineResultSet.getString("DESCRIPTION");
                String imageUrl = magazineResultSet.getString("IMAGEURL");
                Date publishDate = magazineResultSet.getDate("PUBLISHDATE");
                String type = magazineResultSet.getString("TYPE");
                String mediaUrl = magazineResultSet.getString("MEDIAURL");
                String browsingUrl = magazineResultSet.getString("BROWSINGURL");
                switch(type) {
                    case "book":
                        mag = new Book(title, description, imageUrl, publishDate, type, browsingUrl);
                    break;
                    case "audio":
                        mag = new Audio(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl);
                    break;
                    case "document" :
                        mag = new Document(title, description, imageUrl, publishDate, type, browsingUrl);
                    break;
                    case "video" :
                        mag = new Video(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl);
                    break;
                    default: 
                        mag = new Magazines(title, description, imageUrl, publishDate, type, browsingUrl);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mag;
    }
    
    public static Exercices getExercice(String id) {
        Exercices exe = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet exerciceResultSet = stmt.executeQuery("SELECT * FROM EXERCICES WHERE ID=" + id);
            if(exerciceResultSet.next()) {
                String title = exerciceResultSet.getString("TITLE");
                String description = exerciceResultSet.getString("DESCRIPTION");
                String[] questions = exerciceResultSet.getString("QUESTIONS").split(", ");
                String[] answers = exerciceResultSet.getString("ANSWERS").split(", ");
                String duration = exerciceResultSet.getString("DURATION");
                String imageUrl = exerciceResultSet.getString("IMAGEURL");
                String type = exerciceResultSet.getString("TYPE");
                switch(type) {
                    case "qcm":
                        exe = new Exercices(title, description, questions, answers, duration, imageUrl, type);
                    break;
                    /*case "audio":
                        mag = new Audio(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl);
                    break;
                    case "document" :
                        mag = new Document(title, description, imageUrl, publishDate, type, browsingUrl);
                    break;
                    case "video" :
                        mag = new Video(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl);
                    break;
                    default: 
                        mag = new Magazines(title, description, imageUrl, publishDate, type, browsingUrl);*/
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exe;
    }
    
    
    public static ObservableList<Exercices> getExercices() {
        ObservableList<Exercices> exercicesList = FXCollections.observableArrayList();
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
                switch(type) {
                    case "qcm":
                        exercicesList.add(new Exercices(title, description, questions, answers, duration, imageUrl, type));
                    break;
                    case "audio":
                        //exercicesList.add(new Audio(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl));
                    break;
                    case "document" :
                        //exercicesList.add(new Document(title, description, imageUrl, publishDate, type, browsingUrl));
                    break;
                    case "video" :
                        //exercicesList.add(new Video(title, description, imageUrl, publishDate, type, browsingUrl, mediaUrl));
                    break;
                    default: 
                        //exercicesList.add(new Magazines(title, description, imageUrl, publishDate, type, browsingUrl));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exercicesList;
    }
    
    
    public static ObservableList<EnglishTime> getTimes() {
        ObservableList<EnglishTime> englishTimes = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet timesResultSet = stmt.executeQuery("SELECT * FROM TIMES");
            while(timesResultSet.next()){
                String time = timesResultSet.getString("TIME");
                String example = timesResultSet.getString("EXAMPLE");
                String explenation = timesResultSet.getString("EXPLENATION");
                englishTimes.add(new EnglishTime(time, example, explenation));
            }
        } catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return englishTimes;
    }
    
    public static ObservableList<EnglishNumbers> getNumbers() {
        ObservableList<EnglishNumbers> englishNumbers = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet numbersResultSet = stmt.executeQuery("SELECT * FROM NUMBERS");
            while(numbersResultSet.next()) {
                String number = numbersResultSet.getString("NUMBER");
                String numberEn = numbersResultSet.getString("NUMBEREN");
                String ordinal = numbersResultSet.getString("ORDINAL");
                String ordinalEn = numbersResultSet.getString("ORDINALEN");
                englishNumbers.add(new EnglishNumbers(number, numberEn, ordinal, ordinalEn));
            }
        } catch(SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return englishNumbers;
    }
}
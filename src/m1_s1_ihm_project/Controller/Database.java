/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import m1_s1_ihm_project.Model.Magazines.Audio;
import m1_s1_ihm_project.Model.Magazines.Book;
import m1_s1_ihm_project.Model.Magazines.Document;
import m1_s1_ihm_project.Model.Magazines.Magazines;
import m1_s1_ihm_project.Model.Magazines.Video;

/**
 *
 * @author Nico
 */
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
                String description = magazinesResultSet.getString("DESCRIPTION");
                String imageUrl = magazinesResultSet.getString("IMAGEURL");
                Date publishDate = magazinesResultSet.getDate("PUBLISHDATE");
                String type = magazinesResultSet.getString("TYPE");
                //magazinesList.add(new Magazines());
                switch(type) {
                    case "book":
                        magazinesList.add(new Book(title, description, imageUrl, publishDate));
                    break;
                    case "audio":
                        magazinesList.add(new Audio(title, description, imageUrl, publishDate));
                    break;
                    case "document" :
                        magazinesList.add(new Document(title, description, imageUrl, publishDate));
                    break;
                    case "video" :
                        magazinesList.add(new Video(title, description, imageUrl, publishDate));
                    break;
                    default: 
                        magazinesList.add(new Magazines(title, description, imageUrl, publishDate));
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
                //magazinesList.add(new Magazines());
                switch(type) {
                    case "book":
                        mag = new Book(title, description, imageUrl, publishDate);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mag;
    }
}
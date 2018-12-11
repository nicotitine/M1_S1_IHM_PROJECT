/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Model.Exercices;

/**
 *
 * @author Nico
 */
public class Exercices {
    protected String title;
    protected String description;
    protected String[] questions;
    protected String[] answers;
    protected String duration;
    protected String imageUrl;
    protected String type;
    
    public Exercices(String title, String description, String[] questions, String[] answers, String duration, String imageUrl, String type) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.answers = answers;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.type = type;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String[] getQuestions() {
        return this.questions;
    }
    
    public String[] getAnswers() {
        return this.answers;
    }

    public String getDuration() {
        return this.duration;
    }
    
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public String getType() {
        return this.type;
    }
}

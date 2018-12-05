/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Model.Magazines;

import java.util.Date;

/**
 *
 * @author Nico
 */
public class Magazines {
    protected String title;
    protected String description;
    protected String imageUrl;
    protected Date publishDate;
    protected String type;
    
    public Magazines(String title, String description, String imageUrl, Date publishDate, String type) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.type = type;
    }
    
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public Date getPublishDate() {
        return this.publishDate;
    }
    public String getType() {
        return this.type;
    }
}

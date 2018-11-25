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
    
    protected Magazines(String title, String description, String imageUrl, Date publishDate) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
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
}

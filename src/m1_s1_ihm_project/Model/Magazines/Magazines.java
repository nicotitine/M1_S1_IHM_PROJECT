package m1_s1_ihm_project.Model.Magazines;

import java.util.Date;

public class Magazines {
    protected int id;
    protected String title;
    protected String description;
    protected String imageUrl;
    protected Date publishDate;
    protected String type;
    protected String browsingUrl;
    
    public Magazines(String title, String description, String imageUrl, Date publishDate, String type, String browsingUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.type = type;
        this.browsingUrl = browsingUrl;
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
    public String getBrowsingUrl() {
        return this.browsingUrl;
    }
}
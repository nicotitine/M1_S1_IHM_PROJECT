package m1_s1_ihm_project.Model.Magazines;

import java.util.Date;

public class Document extends Magazines{
    
    public Document(String title, String description, String url, Date publishDate, String type, String browsingUrl) {
        super(title, description, url, publishDate, type, browsingUrl);
    }
    
}
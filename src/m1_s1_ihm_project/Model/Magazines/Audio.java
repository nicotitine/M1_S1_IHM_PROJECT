package m1_s1_ihm_project.Model.Magazines;

import java.util.Date;

public class Audio extends Magazines{
    
    private final String mediaUrl;
    
    public Audio(String title, String description, String url, Date publishDate, String type, String browsingUrl, String mediaUrl) {
        super(title, description, url, publishDate, type, browsingUrl);
        this.mediaUrl = mediaUrl;
    }
    
    public String getMediaUrl() {
        return this.mediaUrl;
    }
}
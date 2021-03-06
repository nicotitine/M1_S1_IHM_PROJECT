package m1_s1_ihm_project.Model.Magazine;

import java.util.Date;

public class Video extends Magazine{
    
    private final String mediaUrl;
    
    public Video(String title, String description, String url, Date publishDate, String type, String browsingUrl, String mediaUrl) {
        super(title, description, url, publishDate, type, browsingUrl);
        this.mediaUrl = mediaUrl;
    }
    
    public String getMediaUrl() {
        return mediaUrl;
    }
}
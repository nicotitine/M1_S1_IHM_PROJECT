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
public class Document extends Magazines{
    
    public Document(String title, String description, String url, Date publishDate, String type) {
        super(title, description, url, publishDate, type);
    }
    
}

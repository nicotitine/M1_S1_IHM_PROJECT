/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Model;

import java.util.Date;
import m1_s1_ihm_project.Model.Magazines.Magazines;

/**
 *
 * @author Nico
 */
public class Audio extends Magazines{
    
    public Audio(String title, String description, String url, Date publishDate) {
        super(title, description, url, publishDate);
    }
}

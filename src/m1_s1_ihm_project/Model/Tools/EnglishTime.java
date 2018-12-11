/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Model.Tools;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 *
 * @author Nico
 */
public class EnglishTime extends RecursiveTreeObject<EnglishTime>{
    private final String title;
    private final String example;
    private final String explenation;
    
    public EnglishTime(String title, String example, String explenation) {
        this.title = title;
        this.example = example;
        this.explenation = explenation;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getExample() {
        return this.example;
    }
    
    public String getExplenation() {
        return this.explenation;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1_s1_ihm_project.Model.Tools;

/**
 *
 * @author Nico
 */
public class EnglishNumbers {
    
    private final String number;
    private final String numberEn;
    private final String ordinal;
    private final String ordinalEn;
    
    public EnglishNumbers(String number, String numberEn, String ordinal, String ordinalEn) {
        this.number = number;
        this.numberEn = numberEn;
        this.ordinal = ordinal;
        this.ordinalEn = ordinalEn;
    }
    
    public String getNumber() {
        return this.number;
    }
    
    public String getNumberEn() {
        return this.numberEn;
    }
    
    public String getOrdinal() {
        return this.ordinal;
    }
    
    public String getOrdinalEn() {
        return this.ordinalEn;
    }
 }

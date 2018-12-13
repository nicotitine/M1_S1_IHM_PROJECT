package m1_s1_ihm_project.Model.Tools;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class EnglishNumbers extends RecursiveTreeObject<EnglishNumbers>{
    
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
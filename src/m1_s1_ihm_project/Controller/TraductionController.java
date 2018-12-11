package m1_s1_ihm_project.Controller;

import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TraductionController {
    
    private final Map<String, String> mapLang;
 
    TraductionController() {
        mapLang = new TreeMap<>();
        init();
    }
 
    private void init() {
        mapLang.put("en", "ENGLISH");
        mapLang.put("fr", "FRENCH");
    }
     
    public ObservableList getNameList(){
        return FXCollections.observableArrayList(mapLang.values().toArray());
    }
     
    public ObservableList getPrefixList(){
        return FXCollections.observableArrayList(mapLang.keySet().toArray());
    }
}

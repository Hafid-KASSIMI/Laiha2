

package lp.controllers;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp.Settings;

public class PreviewController implements Initializable {
    
    @FXML private ImageView img;
    @FXML private Label categoryLbl, infosLbl, titleLbl;

    private final String url = "/lp/resources/images/";
    private ResourceBundle rb;
    
    public PreviewController() {
    }
    
    public PreviewController(int template) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
    }    
    
    public void preview(int template) {
        String[] infosTab = Settings.PREF_BUNDLE.get(Settings.AVAILABLE_TEMPLATES.get(template).getLabel() + "_INFOS").split("\\|");
        String tmp = rb.getString(Settings.PREF_BUNDLE.get(Settings.AVAILABLE_TEMPLATES.get(template).getLabel() + "_CATEGORY"));
        try {
            img.setImage(new Image(url + Settings.AVAILABLE_TEMPLATES.get(template).getLabel() + ".jpeg"));
        } catch ( NullPointerException | IllegalArgumentException e ) {}
        categoryLbl.setText( tmp + "." );
        tmp = "";
        for ( int i = 0, n = infosTab.length; i < n; i++ ) {
            try {
                tmp += rb.getString(infosTab[i]);
            }
            catch( MissingResourceException mre ) {
                tmp += infosTab[i];
            }
        }
        infosLbl.setText(tmp);
        tmp = Settings.PREF_BUNDLE.get(Settings.AVAILABLE_TEMPLATES.get(template).getLabel() + "_TITLE");
        titleLbl.setText( tmp + "." );
    }
    
}

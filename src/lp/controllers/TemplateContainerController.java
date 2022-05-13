
package lp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import lp.Settings;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class TemplateContainerController implements Initializable {

    @FXML private ToggleButton tplTgBtn;
    @FXML private Button setBtn, viewBtn;
    @FXML private Label nameLbl;
    @FXML private StackPane backSP;
    
    private int template;

    public TemplateContainerController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tplTgBtn.setTooltip(new Tooltip(rb.getString("CHOOSE_TEMPLATE")));
        setBtn.setTooltip(new Tooltip(rb.getString("CHANGE_PARAMETERS")));
        viewBtn.setTooltip(new Tooltip(rb.getString("PREVIEW_TEMPLATE")));
        
        tplTgBtn.selectedProperty().addListener((obs, old, cur) -> {
            if ( cur ) {
                if ( !Settings.SELECTED_TEMPLATES.contains(template) )
                    Settings.SELECTED_TEMPLATES.add(template);
                backSP.getStyleClass().add("selected");
            }
            else {
                Settings.SELECTED_TEMPLATES.removeAll(template);
                backSP.getStyleClass().removeAll("selected");
            }
        });
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
        nameLbl.setText(Settings.AVAILABLE_TEMPLATES.get(template).getStrNumero());
        tplTgBtn.setStyle("-fx-background-image: url('/lp/resources/images/" + 
            Settings.AVAILABLE_TEMPLATES.get(template).getLabel() + ".png')");
        setBtn.setUserData(template);
        viewBtn.setUserData(template);
    }
    
    public void attachListener(EventHandler previewEvt, EventHandler settingsEvt) {
        setBtn.setOnAction(settingsEvt);
        viewBtn.setOnAction(previewEvt);
    }

    public void select(Boolean selected) {
        tplTgBtn.setSelected(selected);
    }

    public boolean isSelected() {
        return tplTgBtn.isSelected();
    }

    public BooleanProperty selecetedProperty() {
        return tplTgBtn.selectedProperty();
    }
    
}

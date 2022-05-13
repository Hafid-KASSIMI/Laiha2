

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class List1Controller extends SettingsController implements Initializable {

    @FXML private ListsCommonController commonController;
    @FXML private Button saveBtn;
    
    public List1Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("LIST_1");
        commonController.completeInitialize();
        commonController.showSeparator();
        commonController.showCustomStr();
        commonController.showGenderIconsCB();
        saveBtn.setOnAction(evt -> {
            commonController.saveSeparator();
            commonController.saveCustomStr();
            commonController.saveGenderIconsCB();
            saveSettings();
        });
    }
}

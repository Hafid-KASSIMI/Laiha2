

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class List4Controller extends SettingsController implements Initializable {
    
    @FXML private ListsCommonController commonController;
    @FXML private Button saveBtn;
    
    public List4Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("LIST_4");
        commonController.completeInitialize();
        commonController.showCustomStr();
        saveBtn.setOnAction(evt -> {
            commonController.saveCustomStr();
            saveSettings();
        });
    }
}

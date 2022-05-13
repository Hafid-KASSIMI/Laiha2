

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class List5Controller extends SettingsController implements Initializable {

    @FXML private ListsCommonController commonController;
    @FXML private Button saveBtn;
    
    public List5Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("LIST_5");
        commonController.completeInitialize();
        commonController.showCustomStr1();
        commonController.showCustomStr2();
        commonController.showCustomStr3();
        saveBtn.setOnAction(evt -> {
            commonController.saveCustomStr1();
            commonController.saveCustomStr2();
            commonController.saveCustomStr3();
            saveSettings();
        });
    }
    
}

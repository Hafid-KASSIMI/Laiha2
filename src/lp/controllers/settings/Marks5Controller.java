

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Marks5Controller extends SettingsController implements Initializable {

    @FXML private MarksCommonController commonController;
    @FXML private Button saveBtn;
    
    public Marks5Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("MARKS_5");
        commonController.completeInitialize();
        commonController.showCustomStr();
        saveBtn.setOnAction(evt -> {
            commonController.saveCustomStr();
            saveSettings();
        });
    }

}

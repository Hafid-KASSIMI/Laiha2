

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Marks2Controller extends SettingsController implements Initializable {

    @FXML private MarksCommonController commonController;
    @FXML private Button saveBtn;
    
    public Marks2Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("MARKS_2");
        commonController.completeInitialize();
        saveBtn.setOnAction(evt -> {
            saveSettings();
        });
    }

}

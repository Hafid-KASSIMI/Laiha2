

package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Marks4Controller extends SettingsController implements Initializable {

    @FXML private MarksCommonController commonController;
    @FXML private Button saveBtn;
    
    public Marks4Controller() {
        super();
    }

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("MARKS_4");
        commonController.completeInitialize();
        commonController.showCustomStr();
        saveBtn.setOnAction(evt -> {
            commonController.saveCustomStr();
            saveSettings();
        });
    }

}

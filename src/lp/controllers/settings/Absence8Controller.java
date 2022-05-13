/*
 * Copyright (C) 2022 H. KASSIMI
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lp.controllers.settings;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author H. KASSIMI
 */
public class Absence8Controller extends SettingsController implements Initializable {

    @FXML private AbsencesCommonController commonController;
    @FXML private Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commonController.setTpl("ABSENCE_8");
        commonController.completeInitialize();
        commonController.showBureau();
        commonController.showSignature();
        commonController.showLstSettingsTab();
        commonController.showFormatTab();
        saveBtn.setOnAction(evt -> {
            commonController.saveBureau();
            commonController.saveSignature();
            commonController.saveLstSettingsTab();
            commonController.saveFormatTab();
            saveSettings();
        });
    }    

    @Override
    protected void saveSettings() {
        super.saveSettings();
        commonController.saveSettings();
    }
    
}

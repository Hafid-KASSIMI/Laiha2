/*
 * Copyright (C) 2022 Hafid KASSIMI (@mdrassty.net)
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import lp.Settings;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public class ListsCommonController extends CommonInnerController {

    @FXML private TextField titleTF, separatorTF, customStrTF, customStr1TF, customStr2TF, customStr3TF;
    @FXML private Label separatorLb, customStrLb, customStr1Lb, customStr2Lb, customStr3Lb, customStrsLb;
    @FXML private CheckBox bolderCB, genderIconsCB;
    @FXML private ToggleButton leftBtn, centerBtn, rightBtn;
    @FXML private ToggleGroup namesAlign;
    @FXML private ListGeneralController lstGenSetController;

    public ListsCommonController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        leftBtn.setTooltip(new Tooltip(rb.getString("LEFT")));
        rightBtn.setTooltip(new Tooltip(rb.getString("RIGHT")));
        centerBtn.setTooltip(new Tooltip(rb.getString("CENTER")));
        separatorLb.managedProperty().bind(separatorLb.visibleProperty());
        separatorTF.managedProperty().bind(separatorTF.visibleProperty());
        customStrLb.managedProperty().bind(customStrLb.visibleProperty());
        customStrTF.managedProperty().bind(customStrTF.visibleProperty());
        customStrsLb.managedProperty().bind(customStrsLb.visibleProperty());
        customStr1Lb.managedProperty().bind(customStr1Lb.visibleProperty());
        customStr1TF.managedProperty().bind(customStr1TF.visibleProperty());
        customStr2Lb.managedProperty().bind(customStr2Lb.visibleProperty());
        customStr2TF.managedProperty().bind(customStr2TF.visibleProperty());
        customStr3Lb.managedProperty().bind(customStr3Lb.visibleProperty());
        customStr3TF.managedProperty().bind(customStr3TF.visibleProperty());
        genderIconsCB.managedProperty().bind(genderIconsCB.visibleProperty());
        separatorTF.visibleProperty().bind(separatorLb.visibleProperty());
        customStrTF.visibleProperty().bind(customStrLb.visibleProperty());
        separatorLb.setVisible(false);
        customStrLb.setVisible(false);
        customStr1Lb.setVisible(false);
        customStr2Lb.setVisible(false);
        customStr3Lb.setVisible(false);
        customStrsLb.setVisible(false);
        genderIconsCB.setVisible(false);
    }
    
    @Override
    public void completeInitialize() {
        titleTF.setText(Settings.PREF_BUNDLE.get(tpl + "_TITLE"));
        bolderCB.setSelected(Settings.PREF_BUNDLE.get(tpl + "_BOLDER_NAMES").equals("Y"));
        switch(Settings.PREF_BUNDLE.get(tpl + "_NAMES_ALIGN")) {
            case "L":
                leftBtn.setSelected(true);
                break;
            case "R":
                rightBtn.setSelected(true);
                break;
            default:
                centerBtn.setSelected(true);
                break;
        }
    }
    
    public void showSeparator() {
        separatorLb.setVisible(true);
        separatorTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SCHOOL_INFOS_SEPARATOR"));
    }
    
    public void showCustomStr() {
        customStrLb.setVisible(true);
        customStrTF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING"));
    }
    
    public void showCustomStr1() {
        customStr1Lb.setVisible(true);
        customStrsLb.setVisible(true);
        customStr1TF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING"));
    }
    
    public void showCustomStr2() {
        customStr2Lb.setVisible(true);
        customStrsLb.setVisible(true);
        customStr2TF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING2"));
    }
    
    public void showCustomStr3() {
        customStr3Lb.setVisible(true);
        customStrsLb.setVisible(true);
        customStr3TF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING3"));
    }
    
    public void showGenderIconsCB() {
        genderIconsCB.setVisible(true);
        genderIconsCB.setSelected(Settings.PREF_BUNDLE.get(tpl + "_USE_GENDER_ICONS").equals("Y"));
    }

    public void saveSeparator() {
        Settings.PREF_BUNDLE.update(tpl + "_SCHOOL_INFOS_SEPARATOR", separatorTF.getText());
    }

    public void saveCustomStr() {
        Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING", customStrTF.getText());
    }

    public void saveCustomStr1() {
        Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING", customStr1TF.getText());
    }

    public void saveCustomStr2() {
        Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING2", customStr2TF.getText());
    }

    public void saveCustomStr3() {
        Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING3", customStr3TF.getText());
    }

    public void saveGenderIconsCB() {
        Settings.PREF_BUNDLE.update(tpl + "_USE_GENDER_ICONS", genderIconsCB.isSelected() ? "Y" : "N");
    }

    @Override
    public void saveSettings() {
        Settings.PREF_BUNDLE.update(tpl + "_TITLE", titleTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "_BOLDER_NAMES", bolderCB.isSelected() ? "Y" : "N");
        if ( leftBtn.isSelected() ) {
            Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "L");
        }
        else if ( rightBtn.isSelected() ) {
            Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "R");
        }
        else {
            Settings.PREF_BUNDLE.update(tpl + "_NAMES_ALIGN", "C");
        }
        lstGenSetController.saveSettings();
    }
}

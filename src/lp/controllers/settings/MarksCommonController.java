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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import lp.Settings;
import net.mdrassty.object.Matter;
import net.mdrassty.object.Semester;
import net.mdrassty.util.SEMESTER;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public class MarksCommonController extends CommonInnerController {

    @FXML private TextField titleTF, separatorTF, customStrTF;
    @FXML private Label separatorLb, customStrLb;
    @FXML private CheckBox bolderCB;
    @FXML private ToggleButton leftBtn, centerBtn, rightBtn;
    @FXML private ToggleGroup namesAlign;
    @FXML private CheckBox marksCB;
    @FXML private ComboBox<Matter> mattersCMB;
    @FXML private ComboBox<Semester> semestersCMB;
    @FXML private TabPane tabsTP;
    @FXML private Tab ccMarksTab;
    private ListGeneralController genLstSetController;

    public MarksCommonController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        mattersCMB.getItems().add(new Matter(rb.getString("ALL")));
//        mattersCMB.getItems().addAll(
//            Settings.DATABASES.values().stream()
//            .flatMap(s -> s.getLevels().stream())
//            .flatMap(g -> g.getGroups().stream())
//            .flatMap(g -> g.getAssociatedMatters().stream())
//            .distinct().map(mat -> new Matter(mat, Settings.PREF_BUNDLE.get("MATTER_" + mat + "_LABEL")))
//            .collect(Collectors.toCollection(ArrayList::new))
//        );
//        semestersCMB.getItems().add(new Semester(SEMESTER.ALL, rb.getString("ALL")));
//        semestersCMB.getItems().addAll(
//            Settings.DATABASES.values().stream()
//            .flatMap(s -> s.getLevels().stream())
//            .flatMap(g -> g.getGroups().stream())
//            .flatMap(g -> g.getAssociatedSemesters().stream())
//            .distinct().map(sem -> new Semester(sem, rb.getString(sem.toString())))
//            .collect(Collectors.toCollection(ArrayList::new))
//        );
//        mattersCMB.disableProperty().bind(marksCB.selectedProperty().not());
//        semestersCMB.disableProperty().bind(marksCB.selectedProperty().not());
        
        leftBtn.setTooltip(new Tooltip(rb.getString("LEFT")));
        rightBtn.setTooltip(new Tooltip(rb.getString("RIGHT")));
        centerBtn.setTooltip(new Tooltip(rb.getString("CENTER")));
        separatorLb.managedProperty().bind(separatorLb.visibleProperty());
        separatorTF.managedProperty().bind(separatorTF.visibleProperty());
        customStrLb.managedProperty().bind(customStrLb.visibleProperty());
        customStrTF.managedProperty().bind(customStrTF.visibleProperty());
        separatorTF.visibleProperty().bind(separatorLb.visibleProperty());
        customStrTF.visibleProperty().bind(customStrLb.visibleProperty());
        separatorLb.setVisible(false);
        customStrLb.setVisible(false);
        
        tabsTP.getTabs().remove(ccMarksTab);
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
//        try {
//            int code = Integer.parseInt(Settings.PREF_BUNDLE.get(tpl + "_SELECTED_MATTER"));
//            mattersCMB.getSelectionModel().select(mattersCMB.getItems().filtered(m -> m.getCode() == code).get(0));
//        } catch ( NumberFormatException | IndexOutOfBoundsException nfe ) {
//            mattersCMB.getSelectionModel().select(0);
//        }
//        try {
//            SEMESTER selectedSem = SEMESTER.valueOf(Settings.PREF_BUNDLE.get(tpl + "_SELECTED_SEMESTER"));
//            semestersCMB.getSelectionModel().select(semestersCMB.getItems().filtered(s -> s.get().equals(selectedSem)).get(0));
//        } catch ( IllegalArgumentException | IndexOutOfBoundsException ex ) {
//            semestersCMB.getSelectionModel().select(0);
//        }
//        marksCB.setSelected(Settings.PREF_BUNDLE.get(tpl + "_INCLUDE_MARKS").equals("Y"));
    }
    
    public void showSeparator() {
        separatorLb.setVisible(true);
        separatorTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SCHOOL_INFOS_SEPARATOR"));
    }
    
    public void showCustomStr() {
        customStrLb.setVisible(true);
        customStrTF.setText(Settings.PREF_BUNDLE.get(tpl + "_CUSTOM_STRING"));
    }

    public void saveSeparator() {
        Settings.PREF_BUNDLE.update(tpl + "_SCHOOL_INFOS_SEPARATOR", separatorTF.getText());
    }

    public void saveCustomStr() {
        Settings.PREF_BUNDLE.update(tpl + "_CUSTOM_STRING", customStrTF.getText());
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
//        Settings.PREF_BUNDLE.update(tpl + "_INCLUDE_MARKS", marksCB.isSelected() ? "Y" : "N");
//        Settings.PREF_BUNDLE.update(tpl + "_SELECTED_MATTER", mattersCMB.getSelectionModel().getSelectedItem().getCode() + "");
//        Settings.PREF_BUNDLE.update(tpl + "_SELECTED_SEMESTER", semestersCMB.getSelectionModel().getSelectedItem().get().toString());
        genLstSetController.saveSettings();
    }
}

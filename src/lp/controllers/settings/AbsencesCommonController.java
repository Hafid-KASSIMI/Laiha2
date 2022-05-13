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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import lp.Settings;
import lp.util.Calendar.DateRange;
import lp.util.Calendar.WeekInfos;

/**
 *
 * @author H. KASSIMI
 */
public class AbsencesCommonController extends CommonInnerController {

    @FXML private ComboBox firstDayCB, selWeekCB;
    @FXML private RadioButton curWeekRB, nextWeekRB, othWeekRB;
    @FXML private ToggleGroup selectedWeek, namesAlign;
    @FXML private Label curWeekL, otherWeekL, day1L, day2L, day3L, day4L, day5L, day6L, nextWeekL, signatureL, surDeskL, survNumL;
    @FXML private CheckBox d1mCB, d1aCB, d2mCB, d2aCB, d3mCB, d3aCB, d4mCB, d4aCB, d5mCB, d5aCB, d6mCB, d6aCB, bolderCB;
    @FXML private TextField shtTitleTF, toAdminTF, signatureTF, survDeskTF, survNumTF, mornTF, aftTF;
    @FXML private TextField m1TF, m2TF, m3TF, m4TF, a1TF, a2TF, a3TF, a4TF;
    @FXML private TextArea customMsgTA;
    @FXML private ToggleButton leftBtn, centerBtn, rightBtn;
    @FXML private HBox hoursHeaderVB;
    @FXML private Tab listSettingsTab, formatTab, msgTab;
    @FXML private TabPane tabs;
    @FXML private ListGeneralController genLstSetController;
    
    private String dtRng;
    private final WeekInfos wi;
    private final ArrayList<CheckBox> holidays;
    private final ArrayList<Label> days;
    private final ArrayList<TextField> hours;

    public AbsencesCommonController() {
        holidays = new ArrayList();
        days = new ArrayList();
        wi = new WeekInfos();
        hours = new ArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        mornTF.setText(Settings.PREF_BUNDLE.get("ABSTPL_MORNING"));
        aftTF.setText(Settings.PREF_BUNDLE.get("ABSTPL_AFTERNOON"));
        holidays.addAll(Arrays.asList(d1mCB, d1aCB, d2mCB, d2aCB, d3mCB, d3aCB, d4mCB, d4aCB, d5mCB, d5aCB, d6mCB, d6aCB));
        days.addAll(Arrays.asList(day1L, day2L, day3L, day4L, day5L, day6L));
        hours.addAll(Arrays.asList(m1TF, a1TF, m2TF, a2TF, m3TF, a3TF, m4TF, a4TF));
        leftBtn.setTooltip(new Tooltip(Settings.I18N_BUNDLE.getString("LEFT")));
        rightBtn.setTooltip(new Tooltip(Settings.I18N_BUNDLE.getString("RIGHT")));
        centerBtn.setTooltip(new Tooltip(Settings.I18N_BUNDLE.getString("CENTER")));
        for ( int i = 0, n = holidays.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            holidays.get(i).setSelected(Settings.PREF_BUNDLE.get("ABSTPL_" + "DAY_" + j + ( i % 2 == 0 ? "_MORNING_GRAYED" : "_AFTERNOON_GRAYED")).equals("Y"));
        }
        for ( int i = 0; i < 7; i++ ) {
            firstDayCB.getItems().add(rb.getString("WEEK_DAY_" + i));
        }
        
       
        tabs.getTabs().remove(listSettingsTab);
        tabs.getTabs().remove(formatTab);
        tabs.getTabs().remove(msgTab);
        signatureL.managedProperty().bind(signatureL.visibleProperty());
        signatureL.setVisible(false);
        surDeskL.managedProperty().bind(surDeskL.visibleProperty());
        surDeskL.setVisible(false);
        survNumL.managedProperty().bind(survNumL.visibleProperty());
        survNumL.setVisible(false);
        hoursHeaderVB.managedProperty().bind(hoursHeaderVB.visibleProperty());
        hoursHeaderVB.setVisible(false);
    }
    
    public void showSignature() {
        signatureL.setVisible(true);
        signatureTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SIGNATURE"));
    }
    
    public void saveSignature() {
        Settings.PREF_BUNDLE.update(tpl + "_SIGNATURE", signatureTF.getText());
    }
    
    public void showBureau() {
        surDeskL.setVisible(true);
        survNumL.setVisible(true);
        survDeskTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SURVEILANCE_DESKTOP"));
        survNumTF.setText(Settings.PREF_BUNDLE.get(tpl + "_SURVEILANCE_NUMERO"));
    }
    
    public void saveBureau() {
        Settings.PREF_BUNDLE.update(tpl + "_SURVEILANCE_DESKTOP", survDeskTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "_SURVEILANCE_NUMERO", survNumTF.getText());
    }
    
    public void showSubHeader() {
        hoursHeaderVB.setVisible(true);
        for ( int i = 0, n = hours.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            hours.get(i).setText(Settings.PREF_BUNDLE.get("ABSTPL_" + ( i % 2 == 0 ? "MORNING_" : "AFTERNOON_" ) + j));
        }
    }
    
    public void saveSubHeader() {
        for( int i = 0, n = hours.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            Settings.PREF_BUNDLE.update("ABSTPL_" + ( i % 2 == 0 ? "MORNING_" : "AFTERNOON_" ) + j, hours.get(i).getText());
        }
    }
    
    public void showCustomMessage() {
        tabs.getTabs().add(msgTab);
        customMsgTA.setText(Settings.PREF_BUNDLE.get(tpl + "_MESSAGE"));
    }
    
    public void saveCustomMessage() {
        Settings.PREF_BUNDLE.update(tpl + "_MESSAGE", customMsgTA.getText().replaceAll("[\t]", ""));
    }
    
    public void showLstSettingsTab() {
        tabs.getTabs().add(listSettingsTab);
    }
    
    public void showFormatTab() {
        tabs.getTabs().add(formatTab);
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
    
    public void saveLstSettingsTab() {
        genLstSetController.saveSettings();
    }
    
    public void saveFormatTab() {
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
    }

    @Override
    public void completeInitialize() {
        int tmp;
        shtTitleTF.setText(Settings.PREF_BUNDLE.get(tpl + "_TITLE"));
        toAdminTF.setText(Settings.PREF_BUNDLE.get(tpl + "_TO_ADMINISTRATION"));
        for ( int i = 0; i < 9; i++ ) {
            selWeekCB.getItems().add(Settings.I18N_BUNDLE.getString("WEEKS_RANGE_" + i));
        }
        try {
            tmp = Integer.parseInt(Settings.PREF_BUNDLE.get(tpl + "_SELECTED_WEEK"));
        }
        catch( NumberFormatException nfe) {
            tmp = 0;
        }
        switch (tmp) {
            case 0:
                curWeekRB.setSelected(true);
                break;
            case 1:
                nextWeekRB.setSelected(true);
                break;
            default:
                othWeekRB.setSelected(true);
                break;
        }
        selWeekCB.getSelectionModel().select(tmp + 4);
        selWeekCB.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            DateRange dr;
            dr = wi.getWeek(selWeekCB.getSelectionModel().getSelectedIndex() - 4);
            otherWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        });
        try {
            tmp = Integer.parseInt(Settings.PREF_BUNDLE.get(tpl + "_FIRST_DAY_OF_WEEK"));
        }
        catch( NumberFormatException nfe) {
            tmp = 0;
        }
        dtRng = Settings.I18N_BUNDLE.getString("FROM") + " %FROM% " + Settings.I18N_BUNDLE.getString("TO") + " %TO%";
        firstDayCB.getSelectionModel().select(tmp);
        generateDates(tmp + 1);
        firstDayCB.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            generateDates(newValue.intValue() + 1);
        });
    }

    @Override
    public void saveSettings() {
        int w;
        if ( nextWeekRB.isSelected() )
            w = 1;
        else if ( curWeekRB.isSelected() )
            w = 0;
        else {
            w = selWeekCB.getSelectionModel().getSelectedIndex() - 4;
        }
        Settings.PREF_BUNDLE.update(tpl + "_SELECTED_WEEK", w + "");
        Settings.PREF_BUNDLE.update(tpl + "_TITLE", shtTitleTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "_TO_ADMINISTRATION", toAdminTF.getText());
        Settings.PREF_BUNDLE.update("ABSTPL_MORNING", mornTF.getText());
        Settings.PREF_BUNDLE.update("ABSTPL_AFTERNOON", aftTF.getText());
        Settings.PREF_BUNDLE.update(tpl + "_FIRST_DAY_OF_WEEK", firstDayCB.getSelectionModel().getSelectedIndex() + "");
        for( int i = 0, n = holidays.size(), j = 1; i < n; i++, j = 1 + i / 2 ) {
            Settings.PREF_BUNDLE.update("ABSTPL_DAY_" + j + ( i % 2 == 0 ? "_MORNING_GRAYED" : "_AFTERNOON_GRAYED"), holidays.get(i).isSelected() ? "Y" : "N");
        }
    }
    
    private void generateDates(int firstDay) {
        DateRange dr;
        wi.setFirstDay(firstDay);
        dr = wi.getCurrentWeek();
        curWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        dr = wi.getNextWeek();
        nextWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        dr = wi.getWeek(selWeekCB.getSelectionModel().getSelectedIndex() - 4);
        otherWeekL.setText(dtRng.replace("%FROM%", dr.getBeginning()).replace("%TO%", dr.getEnd()));
        for ( int i = 0; i < 6; i++ ) {
            days.get(i).setText(firstDayCB.getItems().get((firstDay - 1 + i) % 7).toString());
        }
    }
    
}

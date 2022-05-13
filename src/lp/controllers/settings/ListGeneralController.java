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
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import lp.Settings;
import lp.models.templates.pdffiles.metrics.ListMetrics;

/**
 *
 * @author Hafid KASSIMI (@mdrassty.net)
 */
public class ListGeneralController extends CommonInnerController {

    @FXML private CheckBox uniLevRowsCB, stripCB;
    @FXML private Label selColLbl;
    @FXML private Spinner<Integer> emptyRowsSp, doublePgSizeSp, stripIndexSp;
    @FXML private ColorPicker stripCP;
    private SpinnerValueFactory<Integer> svf1, svf2, svf3;

    public ListGeneralController() {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        svf1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        svf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(ListMetrics.SINGLE_PAGE_ROWS_MIN_COUNT, 100);
        svf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        TextFormatter tf1 = new TextFormatter(svf1.getConverter(), svf1.getValue());
        TextFormatter tf2 = new TextFormatter(svf2.getConverter(), svf2.getValue());
        TextFormatter tf3 = new TextFormatter(svf3.getConverter(), svf3.getValue());
        svf1.valueProperty().bindBidirectional(tf1.valueProperty());
        svf2.valueProperty().bindBidirectional(tf2.valueProperty());
        svf3.valueProperty().bindBidirectional(tf3.valueProperty());
        emptyRowsSp.setValueFactory(svf1);
        doublePgSizeSp.setValueFactory(svf2);
        stripIndexSp.setValueFactory(svf3);
        emptyRowsSp.getEditor().setTextFormatter(tf1);
        doublePgSizeSp.getEditor().setTextFormatter(tf2);
        stripIndexSp.getEditor().setTextFormatter(tf3);
        uniLevRowsCB.setSelected(Settings.PREF_BUNDLE.get("LSTTPL_UNIFY_ROWS_LEVEL").equals("Y"));
        stripCB.setSelected(Settings.PREF_BUNDLE.get("LSTTPL_STRIPED").equals("Y"));
        try {
            svf1.setValue(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_ADDITIONAL_EMPTY_ROWS")));
        } catch ( NumberFormatException nfe ) { }
        try {
            svf2.setValue(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_DOUBLE_PAGE_ROWS_COUNT")));
        } catch ( NumberFormatException nfe ) { }
        try {
            svf3.setValue(Integer.parseInt(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_INDEX")));
        } catch ( NumberFormatException nfe ) { }
        try {
            stripCP.setValue(Color.valueOf(Settings.PREF_BUNDLE.get("LSTTPL_STRIP_COLOR")));
        } catch ( IllegalArgumentException | NullPointerException nfe ) { }
        selColLbl.setText(stripCP.getValue().toString());
        stripCP.valueProperty().addListener((obs, old, cur) -> {
            selColLbl.setText(cur.toString());
        });
    }

    @Override
    public void saveSettings() {
        Settings.PREF_BUNDLE.update("LSTTPL_UNIFY_ROWS_LEVEL", uniLevRowsCB.isSelected() ? "Y" : "N");
        Settings.PREF_BUNDLE.update("LSTTPL_STRIPED", stripCB.isSelected() ? "Y" : "N");
        Settings.PREF_BUNDLE.update("LSTTPL_ADDITIONAL_EMPTY_ROWS", svf1.getValue().toString());
        Settings.PREF_BUNDLE.update("LSTTPL_DOUBLE_PAGE_ROWS_COUNT", svf2.getValue().toString());
        Settings.PREF_BUNDLE.update("LSTTPL_STRIP_INDEX", svf3.getValue().toString());
        Settings.PREF_BUNDLE.update("LSTTPL_STRIP_COLOR", stripCP.getValue().toString());
    }
}

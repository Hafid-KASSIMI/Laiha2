

package lp.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lp.Settings;
import lp.util.DB_LOAD_STATUS;
import net.mdrassty.massar.DBWorkbook;

public class DBSelectorController implements Initializable {

    @FXML Button prevDBBtn, dBBtn;
    @FXML private VBox loaderVB, selectorVB;
    @FXML private Label progressLbl;
    @FXML private ProgressIndicatorController piController;
    private DBWorkbook mw;
    private final SimpleObjectProperty status = new SimpleObjectProperty(DB_LOAD_STATUS.WAITING);
    private File ini_db;
    private final FileChooser fc = new FileChooser();
    
    public DBSelectorController() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel 2003", "*.xls"));
        fc.setTitle(Settings.I18N_BUNDLE.getString("CHOOSE_DB_TITLE"));
        initialize();
        dBBtn.setOnAction(evt -> {
            File selectedMW;
            ini_db = new File(Settings.PREF_BUNDLE.get("SELECTED_DB"));
            File ini_db_dir = new File(Settings.PREF_BUNDLE.get("SELECTED_DB_DIR"));
            if ( ini_db_dir.exists() && ini_db_dir.isDirectory() )
                fc.setInitialDirectory(ini_db_dir);
            selectedMW = fc.showOpenDialog(dBBtn.getScene().getWindow());
            if ( selectedMW == null )
                return;
            Settings.PREF_BUNDLE.update("SELECTED_DB_DIR", selectedMW.getParent());
            loaderVB.setVisible(true);
            status.set(DB_LOAD_STATUS.CHECKING);
            mw = new DBWorkbook();
            if ( mw.setWorkbook(selectedMW) ) {
                Settings.PREF_BUNDLE.update("SELECTED_DB", selectedMW.getPath());
                start();
            }
            else {
                loaderVB.setVisible(false);
                status.set(DB_LOAD_STATUS.DATA_ERROR);
            }
        });
        
        loaderVB.managedProperty().bind(loaderVB.visibleProperty());
        selectorVB.managedProperty().bind(selectorVB.visibleProperty());
        loaderVB.visibleProperty().addListener((observable, old, cur) -> {
            selectorVB.setVisible(!cur);
        });
        loaderVB.setVisible(false);
        reset();
    }
    
    public void start() {
        reset();
        new Thread(() -> {
            status.set(DB_LOAD_STATUS.LOADING);
            mw.loadDB();
            mw.getStatus().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if ( newValue ) {
                    Platform.runLater(() -> {
                        loaderVB.setVisible(false);
                        Settings.DATABASES.put(mw.getSchool().getUId(), mw.getSchool());
                        status.set(DB_LOAD_STATUS.LOAD_DONE);
                    });
                }
            });
            mw.getLoadPercentage().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                Platform.runLater(() -> {
                    piController.setProgress((double) newValue);
                });
            });
        }).start();
    }

    private void reset() {
        piController.reset();
        progressLbl.setText(Settings.I18N_BUNDLE.getString("LOAD_PERCENTAGE"));
    }
    
    public void initialize() {
        ini_db = new File(Settings.PREF_BUNDLE.get("SELECTED_DB"));
        if ( !ini_db.exists() )
            prevDBBtn.setVisible(false);
        else {
            prevDBBtn.setTooltip(new Tooltip(ini_db.getPath()));
            prevDBBtn.setOnAction(evt -> {
                loaderVB.setVisible(true);
                status.set(DB_LOAD_STATUS.CHECKING);
                mw = new DBWorkbook();
                if ( mw.setWorkbook(ini_db) ) {
                    start();
                }
                else {
                    loaderVB.setVisible(false);
                    status.set(DB_LOAD_STATUS.DATA_ERROR);
                }
            });
        }
    }

    public SimpleObjectProperty getStatus() {
        return status;
    }
    
}

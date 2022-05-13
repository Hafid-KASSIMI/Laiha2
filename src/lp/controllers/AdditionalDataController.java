

package lp.controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lp.Settings;
import lp.util.DB_LOAD_STATUS;
import net.mdrassty.massar.MarksWorkbook;
import net.mdrassty.massar.MASSARExcelWorkbook;

public class AdditionalDataController implements Initializable {

    @FXML private VBox loaderVB, selectorVB;
    @FXML private Label progressLbl, loadedMarksLbl;
    @FXML private Button getMarksBtn;
//    @FXML private Button getPVsBtn;
    @FXML private ProgressIndicatorController piController;
    
    private final SimpleObjectProperty status = new SimpleObjectProperty(DB_LOAD_STATUS.WAITING);
    private final FileChooser fc = new FileChooser();
    
    public AdditionalDataController() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BiConsumer<MASSARExcelWorkbook, String> loader = (mw, str) -> {
            List<File> selectedMWs;
            File ini_db_dir = new File(Settings.PREF_BUNDLE.get("SELECTED_" + str + "_WORKBOOKS_DIR"));
            if ( ini_db_dir.exists() && ini_db_dir.isDirectory() )
                fc.setInitialDirectory(ini_db_dir);
            fc.setTitle(Settings.I18N_BUNDLE.getString("CHOOSE_" + str + "_WORKBOOKS_TITLE"));
            selectedMWs = fc.showOpenMultipleDialog(getMarksBtn.getScene().getWindow());
            if ( selectedMWs == null )
                return;
            Settings.PREF_BUNDLE.update("SELECTED_" + str + "_WORKBOOKS_DIR", selectedMWs.get(0).getParent());
            loaderVB.setVisible(true);
            status.set(DB_LOAD_STATUS.LOADING);
            mw.getProcessedStudents().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                Platform.runLater(() -> {
                    loadedMarksLbl.setText(newValue.toString());
                });
            });
            mw.getProcessedGroups().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                Platform.runLater(() -> {
                    piController.setProgress(newValue.doubleValue() / selectedMWs.size());
                    if ( newValue.intValue() == selectedMWs.size() ) {
                        loaderVB.setVisible(false);
                        status.set(DB_LOAD_STATUS.LOAD_DONE);
                    }
                });
            });
            reset();
            new Thread(() -> {
                selectedMWs.forEach(selectedMW -> {
                    if ( mw.setWorkbook(selectedMW) ) {
                        Optional<Integer> op = Settings.DATABASES.keySet().stream().filter(id -> Objects.equals(id, mw.getUid())).findFirst();
                        if ( op.isPresent() ) {
                            mw.loadGroupInfos(Settings.DATABASES.get(op.get()).getGroup(mw.getGroup()));
                            if ( mw instanceof MarksWorkbook ) {
                                Settings.PREF_BUNDLE.update("MATTER_" + ((MarksWorkbook) mw).getMatter() + 
                                        "_LABEL", ((MarksWorkbook) mw).getMatterLabel());
                            }
                        }
                        else
                            mw.passGroup();
                    }
                    else {
                        mw.passGroup();
                    }
                });
            }).start();
        };
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel XML", "*.xlsx"));
        getMarksBtn.setOnAction(evt -> {
            loader.accept(new MarksWorkbook(), "MARKS");
        });
//        getPVsBtn.setOnAction(evt -> {
//            loader.accept(new PV(), "PVS");
//        });

        loaderVB.managedProperty().bind(loaderVB.visibleProperty());
        selectorVB.managedProperty().bind(selectorVB.visibleProperty());
        selectorVB.visibleProperty().bind(loaderVB.visibleProperty().not());
        loaderVB.setVisible(false);
        reset();
    }

    private void reset() {
        piController.reset();
        progressLbl.setText(Settings.I18N_BUNDLE.getString("LOAD_PERCENTAGE"));
        loadedMarksLbl.setText("0");
    }

    public SimpleObjectProperty getStatus() {
        return status;
    }
    
}

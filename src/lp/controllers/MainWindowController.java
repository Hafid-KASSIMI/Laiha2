

package lp.controllers;

import lp.controllers.settings.SettingsLoader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import lp.Engine;
import lp.Settings;
import lp.util.DB_LOAD_STATUS;
import net.mdrassty.object.DataObject;
import net.mdrassty.object.School;
import net.mdrassty.util.DATA_TYPE;
import net.mdrassty.util.Misc;

public class MainWindowController implements Initializable {
    @FXML private Button upBtn, downBtn, deleteBtn, clearBtn, addBtn, addAllBtn;
    @FXML private Button generateBtn, outDirBtn, clearGroupsBtn, addGroupsBtn;
    @FXML private CheckBox oneShtCB, oneFileCB, exportPdfCB, exportXlCB;
    @FXML private TextField outDirTF;
    @FXML private ProgressBar pdfPB, xlPB;
    @FXML private HBox pdfHB, xlHB, overlayHB;
    @FXML private ListView<DataObject> selGrpsLV;
    @FXML private TreeView<DataObject> avaGrpsTV;
    @FXML private MenuItem exitMI, aboutMI, reloadMI, forgetMI, addMarksMI;
    @FXML private FlowPane langNotification;
    @FXML private RadioMenuItem arRMI, frRMI, enRMI;
    @FXML private CheckMenuItem saveCMI;
    @FXML private ProgressIndicatorController piController;
    @FXML private VBox templatesVB, successMsgVB, rightBarVB, quitMsgVB, loadDbVB, loadMarksVB;
    @FXML private VBox aboutVB, previewVB, settingsVB, settingsBodyVB;
    @FXML private DBSelectorController loadDbIncHBController;
    @FXML private PreviewController previewBodyVBController;
    @FXML private Button openBtn, successMsgCloseBtn, quitMsgCloseBtn, quitMsgCancelBtn, quitMsgOkBtn, loadDbCloseBtn;
    @FXML private Button previewCloseBtn, settingsCloseBtn, loadMarksCloseBtn;
    @FXML private Label counterLbl, path, noTplSelLbl, selTplsLbl;
    @FXML private Label appName, buildInfos;
    @FXML private Menu avaTplsMenu;
    @FXML private ScrollPane tplsSP;

    private final SimpleIntegerProperty waitingJobs;
    private final Engine eng;
    private final String DBS_BACKUP_PATH = Settings.DB_FOLDER_PATH + Misc.generateLocalUId() + Settings.EXTENSION;
    private final String SELECTED_ITEMS_BACKUP_PATH = Settings.DB_FOLDER_PATH + Misc.generateLocalUId("x") + Settings.EXTENSION;
    
    private final DirectoryChooser dc = new DirectoryChooser();
    private final Duration SM_ANIMATION_DURATION = Duration.seconds(0.5);
    private final SettingsLoader bsw = new SettingsLoader();
    private EventHandler previewEvt, settingsEvt;
    private VBox currentNotifier;
    private TreeItem<DataObject> dbItem, levelItem;
    private int index, firstSelectedTpl;

    public MainWindowController() {
        eng = new Engine();
        waitingJobs = new SimpleIntegerProperty();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Boolean tmp;
        File output_dir = new File(Settings.PREF_BUNDLE.get("OUTPUT_DIR"));
        if ( output_dir.exists() ) {
            outDirTF.setText(Settings.PREF_BUNDLE.get("OUTPUT_DIR"));
            dc.setInitialDirectory(output_dir);
        }
        addAllBtn.setText(rb.getString("ADD_ALL_TO_LIST_ICON"));
        addAllBtn.setTooltip(new Tooltip(rb.getString("ADD_ALL_TO_LIST")));
        addBtn.setText(rb.getString("ADD_TO_LIST_ICON"));
        addBtn.setTooltip(new Tooltip(rb.getString("ADD_TO_LIST")));
        
        oneShtCB.setSelected(Settings.PREF_BUNDLE.get("TWO_SHEETS_PER_GROUP").equals("Y"));
        oneFileCB.setSelected(Settings.PREF_BUNDLE.get("ONE_FILE_PER_GROUP").equals("Y"));
        
        tmp = Settings.PREF_BUNDLE.get("PDF_EXPORT").equals("Y");
        exportPdfCB.setSelected(tmp);
        waitingJobs.set( tmp ? 1 : 0 );
        
        tmp = Settings.PREF_BUNDLE.get("XL_EXPORT").equals("Y");
        exportXlCB.setSelected(tmp);
        waitingJobs.set( waitingJobs.get() + ( tmp ? 1 : 0 ) );
        
        generateBtn.setDisable(true);
            
        addBtn.setOnAction(evt -> {
            selGrpsLV.getItems().addAll(avaGrpsTV.getSelectionModel().getSelectedItems().stream().map(o -> o.getValue()).collect(Collectors.toList()));
        });
        
        addAllBtn.setOnAction(evt -> {
            selGrpsLV.getItems().addAll(listEverything(avaGrpsTV.getRoot(), 
                    avaGrpsTV.getSelectionModel().isEmpty() ? DATA_TYPE.SCHOOL : avaGrpsTV.getSelectionModel().getSelectedItem().getValue().getDataType()));
        });
        
        generateBtn.setOnAction(evt -> {
            eng.setItems(new ArrayList(selGrpsLV.getItems()));
            piController.setProgress(0);
            pdfPB.setProgress(0);
            xlPB.setProgress(0);
            piController.setVisible(true);
            pdfHB.setVisible(exportPdfCB.isSelected());
            xlHB.setVisible(exportXlCB.isSelected());
            exportXlCB.setDisable(true);
            exportPdfCB.setDisable(true);
            eng.setPdfListener((obs, old, cur) -> {
                Platform.runLater(() -> {
                    pdfPB.setProgress(eng.getPdfPercentage());
                    piController.setProgress(eng.getPercentage());
                });
            });
            eng.setXlListener((obs, old, cur) -> {
                Platform.runLater(() -> {
                    xlPB.setProgress(eng.getXlPercentage());
                    piController.setProgress(eng.getPercentage());
                });
            });
            eng.vroom();
        });
        
        piController.doneProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if ( newValue ) {
                String str = Settings.PREF_TEMPORARY.get("OUTPUT_DIR");
                int counter = 1;
                if ( !(new File(str)).exists() )
                    str = Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR");
                path.setText(str);
                try {
                    counter = Integer.parseInt(Settings.PREF_TEMPORARY.get("GENERATIONS_COUNTER")) + 1;
                } catch ( NumberFormatException e ) {}
                str = counter > 999 ? String.format("%.1f", counter / 1000.0) + "k" : counter + "";
                Settings.PREF_TEMPORARY.update("GENERATIONS_COUNTER", str);
                counterLbl.setText(str);
                exportXlCB.setDisable(false);
                exportPdfCB.setDisable(false);
                showOverlayMessage(successMsgVB, true);
            }
        });
        
        oneShtCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("TWO_SHEETS_PER_GROUP", newValue ? "Y" : "N");
        });
        
        oneFileCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("ONE_FILE_PER_GROUP", newValue ? "Y" : "N");
        });
        
        exportPdfCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("PDF_EXPORT", newValue ? "Y" : "N");
            waitingJobs.set( waitingJobs.get() + ( newValue ? 1 : -1 ) );
        });
        
        exportXlCB.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Settings.PREF_BUNDLE.update("XL_EXPORT", newValue ? "Y" : "N");
            waitingJobs.set( waitingJobs.get() + ( newValue ? 1 : -1 ) );
        });
        
        waitingJobs.addListener((ChangeListener<Number>) (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            generateBtn.setDisable(newValue.intValue() == 0  || selGrpsLV.getItems().isEmpty() || Settings.SELECTED_TEMPLATES.isEmpty());
        });
        
        clearBtn.setOnAction(e -> {
            selGrpsLV.getItems().clear();
        });
        clearBtn.setTooltip(new Tooltip(rb.getString("DELETE_ALL")));
        
        deleteBtn.setOnAction(e -> {
            int selected = selGrpsLV.getSelectionModel().getSelectedIndex();
            selGrpsLV.getItems().remove( selGrpsLV.getSelectionModel().getSelectedIndex() );
            if ( selected == selGrpsLV.getItems().size() )
                selGrpsLV.getSelectionModel().select(selected - 1);
        });
        deleteBtn.setTooltip(new Tooltip(rb.getString("DELETE")));
        
        upBtn.setOnAction(e -> {
            int selected = selGrpsLV.getSelectionModel().getSelectedIndex();
            DataObject li = selGrpsLV.getItems().get(selected - 1);
            selGrpsLV.getItems().set(selected - 1, selGrpsLV.getItems().get(selected));
            selGrpsLV.getItems().set(selected, li);
            selGrpsLV.getSelectionModel().select(selected - 1);
        });
        upBtn.setTooltip(new Tooltip(rb.getString("MOVE_UP")));
        
        downBtn.setOnAction(e -> {
            int selected = (int) selGrpsLV.getSelectionModel().getSelectedIndices().get(0);
            DataObject li = selGrpsLV.getItems().get(selected + 1);
            selGrpsLV.getItems().set(selected + 1, selGrpsLV.getItems().get(selected));
            selGrpsLV.getItems().set(selected, li);
            selGrpsLV.getSelectionModel().select(selected + 1);
        });
        downBtn.setTooltip(new Tooltip(rb.getString("MOVE_DOWN")));
        
        avaGrpsTV.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) o -> {
            addBtn.setDisable( o.getList().isEmpty() );
        });
        
        selGrpsLV.getSelectionModel().getSelectedIndices().addListener((ListChangeListener) o -> {
            if ( o.getList().isEmpty() ) {
                upBtn.setDisable(true);
                downBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
            else {
                int selected = (int) o.getList().get(0);
                upBtn.setDisable( selected == 0 );
                downBtn.setDisable( selected == ( selGrpsLV.getItems().size() - 1 ) );
                deleteBtn.setDisable(false);
            }
        });
        
        selGrpsLV.getItems().addListener((ListChangeListener) o -> {
            Boolean empty = selGrpsLV.getItems().isEmpty();
            clearBtn.setDisable(empty);
            generateBtn.setDisable(empty || waitingJobs.get() == 0 || Settings.SELECTED_TEMPLATES.isEmpty());
        });
        
        exitMI.setOnAction(evt -> {
            getReadyToClose();
        });
        
        arRMI.setUserData("AR");
        frRMI.setUserData("FR");
        enRMI.setUserData("EN");
        switch( Settings.SELECTED_LANG ) {
            case "AR":
                arRMI.setSelected(true);
                break;
            case "FR":
                frRMI.setSelected(true);
                break;
            case "EN":
                enRMI.setSelected(true);
                break;
        }
        
        arRMI.getToggleGroup().selectedToggleProperty().addListener((ChangeListener<Toggle>) (ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if ( newValue != null ) {
                Settings.PREF_BUNDLE.update("LANGUAGE", newValue.getUserData().toString());
                langNotification.setVisible(!newValue.getUserData().toString().equals(Settings.SELECTED_LANG));
            }
        });
        
        langNotification.setVisible(false);
        
        aboutMI.setOnAction(evt -> {
            showOverlayMessage(aboutVB, true);
        });
        
        saveCMI.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("SERIALIZE_DBS_ON_CLOSE", cur ? "Y" : "N");
        });
        saveCMI.setSelected("Y".equals(Settings.PREF_BUNDLE.get("SERIALIZE_DBS_ON_CLOSE")));
        
        reloadMI.setOnAction(evt -> {
            loadSavedDbs();
        });
        reloadMI.disableProperty().bind(forgetMI.disableProperty());

        forgetMI.setOnAction(evt -> {
            forgetSerializedObjects();
        });
        forgetMI.setDisable(true);
        
        addMarksMI.setOnAction(e -> {
            showOverlayMessage(loadMarksVB, true);
        });
        
        addGroupsBtn.setOnAction(e -> {
            showOverlayMessage(loadDbVB, true);
        });
        
        clearGroupsBtn.setOnAction(e -> {
            avaGrpsTV.getRoot().getChildren().clear();
            selGrpsLV.getItems().clear();
            Settings.DATABASES.clear();
            forgetSerializedObjects();
        });
        
//        selector.getLoadStatus().addListener((obs, old, cur) -> {
//            if ( cur.shortValue() == DB_LOAD_STATUS.LOAD_DONE ) {
//                refillList();
//            }
//        });
        
        avaGrpsTV.setRoot(new TreeItem(new DataObject(DATA_TYPE.NOTHING)));
        avaGrpsTV.getRoot().getChildren().addListener((ListChangeListener) o -> {
            clearGroupsBtn.setDisable( o.getList().isEmpty() );
        });
        
        avaGrpsTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        outDirBtn.setOnAction(e -> {
            File f = dc.showDialog(outDirBtn.getScene().getWindow());
            if ( f != null ) {
                outDirTF.setText(f.getPath());
                Settings.PREF_BUNDLE.update("OUTPUT_DIR", f.getPath());
                dc.setInitialDirectory(f);
            }
        });
        outDirBtn.setTooltip(new Tooltip(rb.getString("BROWSE")));
        
        successMsgCloseBtn.setOnAction(evt -> {
            showOverlayMessage(successMsgVB, false);
        });
        openBtn.setOnAction(evt -> {
            File selectedDest = new File(Settings.PREF_TEMPORARY.get("OUTPUT_DIR"));
            if ( !selectedDest.exists() )
                selectedDest = new File(Settings.PREF_TEMPORARY.get("SELECTED_DB_DIR"));
            try {
                Desktop.getDesktop().open(selectedDest);
            } catch(IOException ioe) { }
            showOverlayMessage(successMsgVB, false);
        });
        
        overlayHB.setOnMouseClicked(evt -> {
            showOverlayMessage(currentNotifier, false);
        });
        overlayHB.setOnTouchPressed(evt -> {
            showOverlayMessage(currentNotifier, false);
        });
        
        quitMsgCloseBtn.setOnAction(evt -> {
            showOverlayMessage(quitMsgVB, false);
        });
        quitMsgCancelBtn.setOnAction(evt -> {
            showOverlayMessage(quitMsgVB, false);
        });
        quitMsgOkBtn.setOnAction(evt -> {
            Settings.quitAuthorized = true;
            generateBtn.getScene().getWindow().hide();
        });
        previewCloseBtn.setOnAction(evt -> {
            showOverlayMessage(previewVB, false);
        });
        settingsCloseBtn.setOnAction(evt -> {
            showOverlayMessage(settingsVB, false);
        });
        loadDbCloseBtn.setOnAction(evt -> {
            showOverlayMessage(loadDbVB, false);
        });
        loadMarksCloseBtn.setOnAction(evt -> {
            showOverlayMessage(loadMarksVB, false);
        });
        
        loadDbIncHBController.getStatus().addListener((obs, old, cur) -> {
            if ( cur == DB_LOAD_STATUS.LOAD_DONE ) {
                refillList();
                showOverlayMessage(loadDbVB, false);
                loadDbIncHBController.initialize();
            }
        });

        appName.setText(Settings.APP_TITLE + " (" + Settings.APP_YEAR + ")");
        buildInfos.setText(buildInfos.getText().replace("%version%", Settings.APP_VERSION).replace("%date%", Settings.APP_DATE));
        
        noTplSelLbl.managedProperty().bind(noTplSelLbl.visibleProperty());
        noTplSelLbl.setVisible(Settings.SELECTED_TEMPLATES.isEmpty());
        selTplsLbl.setText(String.format("%02d", Settings.SELECTED_TEMPLATES.size()));
        
        Settings.SELECTED_TEMPLATES.addListener((ListChangeListener.Change<? extends Integer> c) -> {
            generateBtn.setDisable(Settings.SELECTED_TEMPLATES.isEmpty() ? true : waitingJobs.intValue() == 0  || selGrpsLV.getItems().isEmpty());
            noTplSelLbl.setVisible(Settings.SELECTED_TEMPLATES.isEmpty());
            selTplsLbl.setText(String.format("%02d", Settings.SELECTED_TEMPLATES.size()));
        });
        
        previewEvt = (EventHandler) (Event event) -> {
            previewTemplate((int) ((Button) event.getSource()).getUserData());
        };
        
        settingsEvt = (EventHandler) (Event event) -> {
            showTemplateSettings((int) ((Button) event.getSource()).getUserData());
        };
        
        index = 1;
        firstSelectedTpl = -1;
        Settings.AVAILABLE_TEMPLATES.keySet().stream().sorted().forEach(key -> {
            TemplateContainer tc = new TemplateContainer();
            CheckMenuItem cmi = new CheckMenuItem(rb.getString("TEMPLATE") + " " + index);
            cmi.setSelected(Settings.SELECTED_TEMPLATES.contains(key));
            avaTplsMenu.getItems().add(cmi);
            Settings.AVAILABLE_TEMPLATES.get(key).setNumero(index);
            tc.setTemplate(key);
            tc.attachListener(previewEvt, settingsEvt);
            templatesVB.getChildren().add(tc.getRoot());
            if ( cmi.isSelected() && firstSelectedTpl < 0 )
                firstSelectedTpl = index - 1;
            tc.select(cmi.isSelected());
            cmi.selectedProperty().bindBidirectional(tc.selecetedProperty());
            index++;
        });
        
        loadSavedDbs();
    }
    
    public void refillList() {
        new Thread(() -> {
            Platform.runLater(() -> {
                avaGrpsTV.getRoot().getChildren().clear();
                Settings.DATABASES.values().forEach(database -> {
                    dbItem = new TreeItem(database);
                    dbItem.setExpanded(true);
                    avaGrpsTV.getRoot().getChildren().add(dbItem);
                    database.getLevels().forEach(level -> {
                        levelItem = new TreeItem(level);
                        dbItem.getChildren().add(levelItem);
                        level.getGroups().forEach(group -> {
                            levelItem.getChildren().add(new TreeItem(group));
                        });
                    });
                });
            });
        }).start();
        piController.setVisible(false);
        pdfHB.setVisible(false);
        xlHB.setVisible(false);
    }
    
    private ArrayList<DataObject> listEverything(TreeItem<DataObject> ti, DATA_TYPE itemType) {
        ArrayList<DataObject> c = new ArrayList();
        if ( ti.getValue().getDataType() == itemType )
            c.add(ti.getValue());
        ti.getChildren().forEach(item -> {
            c.addAll(listEverything(item, itemType));
        });
        return c;
    }
    
    private void loadSavedDbs() {
        try {
            Settings.DATABASES.putAll((ConcurrentHashMap<Integer, School>)Misc.eval(DBS_BACKUP_PATH));
            refillList();
            selGrpsLV.getItems().setAll((ArrayList<DataObject>)Misc.eval(SELECTED_ITEMS_BACKUP_PATH));
            forgetMI.setDisable(false);
        } catch( NullPointerException ex ) {  }
    }

    public void getReadyToClose() {
        showOverlayMessage(quitMsgVB, true);
        if ( Settings.PREF_BUNDLE.get("SERIALIZE_DBS_ON_CLOSE").equals("Y") ) {
            Misc.serialize(new ArrayList(selGrpsLV.getItems()), SELECTED_ITEMS_BACKUP_PATH);
            Misc.serialize(Settings.DATABASES, DBS_BACKUP_PATH);
        }
    }

    private void showOverlayMessage(VBox vb, boolean show) {
        if ( show && currentNotifier == vb )
            return;
        Timeline t = new Timeline();
        if ( show ) {
            overlayHB.setVisible(true);
            vb.setVisible(true);
            if ( currentNotifier != null ) {
                currentNotifier.setVisible(false);
                currentNotifier.setOpacity(0);
                currentNotifier.setTranslateY(-500);
            }
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(vb.translateYProperty(), 6, Interpolator.EASE_IN)));
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(vb.opacityProperty(), 1, Interpolator.EASE_IN)));
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(overlayHB.opacityProperty(), 1, Interpolator.EASE_IN)));
            currentNotifier = vb;
        }
        else {
            currentNotifier = null;
            t.setOnFinished((ActionEvent event) -> {
                overlayHB.setVisible(false);
                vb.setVisible(false);
            });
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(vb.translateYProperty(), -500, Interpolator.EASE_IN)));
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(vb.opacityProperty(), 0, Interpolator.EASE_IN)));
            t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(overlayHB.opacityProperty(), 0, Interpolator.EASE_IN)));
        }
        t.play();
    }

    public void hideRightBar(boolean hide) {
        Timeline t = new Timeline();
        t.getKeyFrames().add(new KeyFrame(SM_ANIMATION_DURATION, new KeyValue(rightBarVB.prefWidthProperty(), hide ? 0 : 300, Interpolator.EASE_OUT)));
        t.play();
    }

    private void previewTemplate(int template) {
        previewBodyVBController.preview(template);
        showOverlayMessage(previewVB, true);
    }

    private void showTemplateSettings(int template) {
        bsw.setView(Settings.AVAILABLE_TEMPLATES.get(template).getLabel());
        settingsBodyVB.getChildren().clear();
        settingsBodyVB.getChildren().add(bsw.load((a) -> {
            showOverlayMessage(settingsVB, false);
        }));
        showOverlayMessage(settingsVB, true);
    }
    
    private void forgetSerializedObjects() {
        try {
            Files.delete(Paths.get(DBS_BACKUP_PATH));
        } catch (IOException ex) { }
        try {
            Files.delete(Paths.get(SELECTED_ITEMS_BACKUP_PATH));
        } catch (IOException ex) { }
    }
    
    public void onShown() {
        if ( firstSelectedTpl > 0 ) {
            tplsSP.setVvalue(templatesVB.getChildren().get(firstSelectedTpl).getBoundsInParent().getMaxY() / tplsSP.getContent().getBoundsInLocal().getHeight());
        }
    }
}

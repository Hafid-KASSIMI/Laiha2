

package lp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lp.controllers.MainWindow;
import lp.models.templates.TemplateInfos;
import net.mdrassty.database.Preferences;

public class Launcher extends Application {
    
    private MainWindow pWindow;
    private Stage stage;
    private Boolean mwShown;
    private final String delimiter = ":";
    private final double MIN_WIDTH = 800;
    private boolean rightBarVisible;

    public Launcher() {
        
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Settings.PREF_BUNDLE = new Preferences(Settings.PREF_DB_PATH, Settings.DB_FOLDER_PATH, ResourceBundle.getBundle("lp.resources.preferences.default"));
        Settings.SELECTED_LANG = Settings.PREF_BUNDLE.get("LANGUAGE");
        Settings.SELECTED_TEMPLATES.addAll(Arrays.asList(Settings.PREF_BUNDLE.get("SELECTED_TEMPLATES").split(delimiter)).stream().filter(template -> !"".equals(template)).map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
        Settings.I18N_BUNDLE = ResourceBundle.getBundle("lp.resources.i18n.strings", new Locale(Settings.SELECTED_LANG.toLowerCase(), Settings.SELECTED_LANG.toUpperCase()));
        mapAvailableTemplates();

        pWindow = new MainWindow();
        stage = primaryStage;
        stage.setTitle(Settings.APP_TITLE);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/16.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/48.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/64.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/128.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/lp/resources/logos/256.png")));
        stage.setOnCloseRequest(evt -> {
            if ( !Settings.quitAuthorized ) {
                pWindow.getReadyToClose();
                evt.consume();
            }
        });
        stage.setOnHiding(evt -> {
            if ( mwShown ) {
                saveMWDimensions();
            }
            Settings.PREF_BUNDLE.update("SELECTED_TEMPLATES", 
                Settings.SELECTED_TEMPLATES.stream().map(i -> i.toString()).collect(Collectors.joining(delimiter)));
            Settings.PREF_BUNDLE.commit();
        });
        stage.setOnShown(evt -> {
            pWindow.onShown();
        });
        showMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void showMainWindow() {
        stage.hide();
        stage.setScene(pWindow.getScene());
        stage.setResizable(true);
        rightBarVisible = true;
        stage.widthProperty().addListener((obs, old, cur) -> {
            if ( cur.doubleValue() < MIN_WIDTH ) {
                pWindow.hideRightBar(true);
                rightBarVisible = false;
            }
            else if ( !rightBarVisible ) {
                pWindow.hideRightBar(false);
                rightBarVisible = true;
            }
        });
        try {
            stage.setX(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_X")));
            stage.setY(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_Y")));
            stage.setWidth(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_W")));
            stage.setHeight(Double.parseDouble(Settings.PREF_BUNDLE.get("MW_LAST_STATE_H")));
        } catch ( NumberFormatException ex ) {
            stage.setMaximized(true);
        }
        mwShown = true;
        stage.show();
    }
    
    private void saveMWDimensions() {
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_X", stage.getX() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_Y", stage.getY() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_W", stage.getWidth() + "");
        Settings.PREF_BUNDLE.update("MW_LAST_STATE_H", stage.getHeight() + "");
    }
    
    private void mapAvailableTemplates() {
        Settings.AVAILABLE_TEMPLATES.put(101, new TemplateInfos("ABSENCE_0", "Absence0"));
        Settings.AVAILABLE_TEMPLATES.put(102, new TemplateInfos("ABSENCE_1", "Absence1"));
        Settings.AVAILABLE_TEMPLATES.put(103, new TemplateInfos("ABSENCE_2", "Absence2"));
        Settings.AVAILABLE_TEMPLATES.put(104, new TemplateInfos("ABSENCE_3", "Absence3"));
        Settings.AVAILABLE_TEMPLATES.put(105, new TemplateInfos("ABSENCE_4", "Absence4"));
        Settings.AVAILABLE_TEMPLATES.put(106, new TemplateInfos("ABSENCE_5", "Absence5"));
        Settings.AVAILABLE_TEMPLATES.put(107, new TemplateInfos("ABSENCE_6", "Absence6"));
        Settings.AVAILABLE_TEMPLATES.put(108, new TemplateInfos("ABSENCE_7", "Absence7"));
        Settings.AVAILABLE_TEMPLATES.put(109, new TemplateInfos("ABSENCE_8", "Absence8"));

        Settings.AVAILABLE_TEMPLATES.put(201, new TemplateInfos("MARKS_1", "Marks1"));
        Settings.AVAILABLE_TEMPLATES.put(202, new TemplateInfos("MARKS_2", "Marks2"));
        Settings.AVAILABLE_TEMPLATES.put(203, new TemplateInfos("MARKS_3", "Marks3"));
        Settings.AVAILABLE_TEMPLATES.put(204, new TemplateInfos("MARKS_4", "Marks4"));
        Settings.AVAILABLE_TEMPLATES.put(205, new TemplateInfos("MARKS_5", "Marks5"));

        Settings.AVAILABLE_TEMPLATES.put(301, new TemplateInfos("LIST_0", "List0"));
        Settings.AVAILABLE_TEMPLATES.put(302, new TemplateInfos("LIST_1", "List1"));
        Settings.AVAILABLE_TEMPLATES.put(303, new TemplateInfos("LIST_2", "List2"));
        Settings.AVAILABLE_TEMPLATES.put(304, new TemplateInfos("LIST_3", "List3"));
        Settings.AVAILABLE_TEMPLATES.put(305, new TemplateInfos("LIST_4", "List4"));
        Settings.AVAILABLE_TEMPLATES.put(306, new TemplateInfos("LIST_5", "List5"));
    }
    
}

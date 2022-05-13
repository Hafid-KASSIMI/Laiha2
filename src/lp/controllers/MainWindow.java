

package lp.controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lp.Settings;

public class MainWindow {
    private Scene scene;
    private MainWindowController mwc;
    
    public MainWindow() {
        try {
            Parent root;
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/lp/views/MainWindow.fxml"), Settings.I18N_BUNDLE);
            root = fl.load();
            root.getStylesheets().add(0, "/net/mdrassty/resources/styles/theme.css");
            root.getStylesheets().add(1, "/lp/styles/theme-" + Settings.SELECTED_LANG + ".css");
            mwc = fl.getController();
            scene = new Scene(root);
            scene.setNodeOrientation( ( Settings.SELECTED_LANG.equals("AR") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT ) );
        }
        catch(IOException ex) { }
    }
    
    public void reinitialize() {
        mwc.refillList();
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public Boolean isResizable() {
        return true;
    }
    
    public Boolean isMaximized() {
        return false;
    }
    
    public void getReadyToClose() {
        mwc.getReadyToClose();
    }
    
    public void onShown() {
        mwc.onShown();
    }
    
    public void hideRightBar(boolean hide) {
        mwc.hideRightBar(hide);
    }
}

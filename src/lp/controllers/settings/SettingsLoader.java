

package lp.controllers.settings;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lp.Settings;
import java.util.function.Consumer;

public class SettingsLoader {
    private final String resource = "/lp/views/settings/%view%.fxml";
    private String view;

    public SettingsLoader() {
    }
    
    public SettingsLoader(String view) {
        this();
        setView(view);
    }

    public String getView() {
        return view;
    }

    public final void setView(String view) {
        this.view = resource.replace("%view%", view);
    }

    public Parent load(Consumer callback) {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource(view), Settings.I18N_BUNDLE);
            Parent p = fl.load();
            ((SettingsController) fl.getController()).setCallback(callback);
            return p;
        }
        catch(IOException ex) {
            return null;
        }
    }
}

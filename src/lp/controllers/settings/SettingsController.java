
package lp.controllers.settings;

import java.util.function.Consumer;


/**
 *
 * @author Sicut
 */
public abstract class SettingsController {
    
    protected Consumer callback;

    public Consumer getCallback() {
        return callback;
    }

    public void setCallback(Consumer callback) {
        this.callback = callback;
    }
    
    protected void saveSettings() {
        callback.accept(null);
    }
    
}

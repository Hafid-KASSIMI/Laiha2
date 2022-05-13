

package lp.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.shape.Circle;

public class ProgressIndicatorController implements Initializable {

    @FXML private ProgressIndicator pi;
    @FXML private Label value;
    @FXML private Label tick;
    @FXML private Circle innerCircle;
    private final SimpleBooleanProperty done;

    public ProgressIndicatorController() {
        done = new SimpleBooleanProperty(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        innerCircle.visibleProperty().bind(tick.visibleProperty().not());
        pi.progressProperty().addListener((ChangeListener<Number>) (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            value.setText(String.format("%02.0f", newValue.doubleValue() * 100) + "%");
            if (newValue.doubleValue() == 1) {
                tick.setVisible(true);
                value.setVisible(false);
            }
            else {
                tick.setVisible(false);
                value.setVisible(newValue.doubleValue() < 1 && newValue.doubleValue() >= 0);
            }
        });
    }
    
    public void setProgress(double progress) {
        pi.setProgress(progress);
        done.set(progress == 1);
    }
    
    public void setVisible(Boolean visible) {
        pi.getParent().setVisible(visible);
    }
    
    public void reset() {
        tick.setVisible(false);
        value.setVisible(false);
        pi.setProgress(0);
    }

    public SimpleBooleanProperty doneProperty() {
        return done;
    }
    
}

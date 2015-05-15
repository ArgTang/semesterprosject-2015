package GUI.AgentGUI;

import GUI.StartMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Logout button for Agent window
 * //todo: merge with AgentMenu?
 * Created by steinar on 15.05.2015.
 */
public class LogoutController {
    @FXML
    private Button logout;

    @FXML
    private void initialize() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(0.0);
        shadow.setOffsetX(0.0);
        shadow.setColor(Color.GRAY);
        shadow.setRadius(1);

        logout.setEffect(shadow);
        logout.pressedProperty().addListener( listener -> StartMain.changeWindowListener.setString("welcome"));
    }
}
package GUI;

import GUI.GuiHelper.RegEX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.io.IOException;


/**
 * Created by steinar on 13.04.2015.
 */
public final class WelcomeController
{
    @FXML
    private TextField userName;
    @FXML
    private TextField password;

    @FXML
    Label welcome;

    @FXML
    private Button loginButton;

    @FXML
    private void login(ActionEvent event) throws IOException {
        //todo: how to proceed from here
        StartMain.changeWindowListener.setPropertyString("Agent");
        //StartMain.changeWindowWindowListener.setPropertyObject(this.getClass()); todo: crash!
    }
    @FXML
    private void initialize()
    {
        RegEX.addCSSTextValidation(userName, RegEX.isLetters());
        RegEX.addCSSTextValidation(password, RegEX.isAllChars());
        welcome.setPrefWidth(Region.USE_COMPUTED_SIZE);
        welcome.setStyle("-fx-font-size: 3.5em;" +
                "-fx-padding: 10em, 0, 0, 0;");
    }

    public Parent initWelcome() throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("\\Welcome.fxml"));
        return parent;
    }
}

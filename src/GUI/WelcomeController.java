package GUI;

import GUI.GuiHelper.RegEX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    }

    public Parent initWelcome() throws IOException
    {
        Parent parent = FXMLLoader.load(getClass().getResource("\\Welcome.fxml"));
        return parent;
    }
}

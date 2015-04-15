package GUI.StartGUI;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.RegEX;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by steinar on 29.03.2015.
 *
 *
 */
public class StartController implements Initializable {
    @FXML
    private TextField inputName;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        addListener();
    }

    //todo: abstract this. needs to be aplyaple to ALL textfields. Predicates, functionable interface?
    public void addListener() {
        final PseudoClass invalidText = PseudoClass.getPseudoClass("invalidText");
        inputName.setOnKeyReleased(e -> inputName.pseudoClassStateChanged(invalidText, !RegEX.checkIfString(inputName.getText())));
    }

    @FXML public void login() {
        //login here
        //Test:
        AlertWindow.messageDialog("Button Clicked", "Button Clicked");

    }

    @FXML public void register() {
        //register here
    }
}

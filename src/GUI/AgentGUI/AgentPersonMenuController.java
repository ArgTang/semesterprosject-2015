package GUI.AgentGUI;

import GUI.GuiHelper.RegEX;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * Created by steinar on 16.04.2015.
 */
public class AgentPersonMenuController implements CommonGUIMethods
{
    @FXML
    private TextField socialSecurityNumber;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField adress;
    @FXML
    private TextField cityNumber;
    @FXML
    private TextField city;
    @FXML
    private TextField email;
    @FXML
    private ListView phonelist;
    private ObservableList<Integer> PhoneSet; //todo: ObservableSet here?

    @FXML
    private void initialize()
    {
       // RegEX.addCSSTextValidation(email, RegEX.isAllChars());
        phonelist.setItems( PhoneSet );
    }

    @Override
    public void clearFields() {
        socialSecurityNumber.setText("");
        firstname.setText("");
        lastname.setText("");
        adress.setText("");
        cityNumber.setText("");
        city.setText("");
        email.setText("");
    }
}

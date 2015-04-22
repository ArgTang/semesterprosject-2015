package GUI.AgentGUI;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private TextField citynumber;
    @FXML
    private TextField city;
    @FXML
    private TextField email;
    @FXML
    private ListView phonelist;
    private ObservableList<Integer> phoneList; //todo: ObservableSet here?

    @FXML
    private void initialize()
    {
        phonelist.setItems(phoneList);
        addCSSValidation();
    }

    @Override
    public void clearFields() {
        resetTextField(socialSecurityNumber);
        resetTextField(firstname);
        resetTextField(lastname);
        resetTextField(adress);
        resetTextField(citynumber);
        resetTextField(city);
        resetTextField(email);
        phoneList.clear();
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(socialSecurityNumber, RegEX.isNumber(11));
        RegEX.addCSSTextValidation(firstname, RegEX.isLetters());
        RegEX.addCSSTextValidation(lastname, RegEX.isLetters());
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(email, RegEX.isEmail());
    }
}

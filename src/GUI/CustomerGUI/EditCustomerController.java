package GUI.CustomerGUI;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Person.ContactInfo;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;

import java.util.List;
import java.util.stream.Collectors;

import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.customerRegister;

/**
 * Created by steinar on 19.04.2015.
 */
public class EditCustomerController extends CommonGUIMethods
{
    @FXML
    TextField socialSecurityNumber;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField adress;
    @FXML
    TextField citynumber;
    @FXML
    TextField city;
    @FXML
    TextField email;

    @FXML
    ListView phonelist;
    @FXML
    Button changeCustomerButton;

    private ObservableList<String> phones = FXCollections.observableArrayList();

    @FXML
    @Override
    protected void initialize() {
        phonelist.setItems(phones);
        phonelist.setCellFactory(TextFieldListCell.forListView());
        setListeners();
        addCSSValidation();
        setCustomer();
    }

    @Override
    public void clearFields() {
        socialSecurityNumber.setEditable(true);
        resetTextFields(socialSecurityNumber, firstname, lastname, adress, citynumber, city, email);
        phones.clear();
    }

    @Override
    protected void addCSSValidation() {
        RegEX.addCSSTextValidation(socialSecurityNumber, isNumberWithLength(11));
        RegEX.addCSSTextValidation(adress, isAdress());
        RegEX.addCSSTextValidation(citynumber, isNumberWithLength(4));
        RegEX.addCSSTextValidation(email, isEmail());
        addCSSTextValidation(isLetters(), firstname, lastname, city);
    }


    @Override
    protected void setListeners() {
        currentCustomer.addListener(observable -> setCustomer());

        phonelist.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() { //todo: lambda here??
            @Override
            public void handle(ListView.EditEvent event) {
                if (isNumberWithLength(8).test(event.getNewValue().toString())) {
                    AlertWindow.messageDialog("Telefonnummer må ha 8 siffer", "feil i telefonnummer");
                } else
                    phonelist.getItems().set(event.getIndex(), event.getNewValue());
            }
        });
    }

    protected void setCustomer() {
        clearFields();
        Customer customer = currentCustomer.get();
        if (customer == null)
            return;
        socialSecurityNumber.setText(customer.getSocialSecurityNumber());
        socialSecurityNumber.setEditable(false);
        firstname.setText(customer.getFirstName());
        lastname.setText(customer.getLastName());
        adress.setText(customer.getAdress());
        citynumber.setText(String.valueOf(customer.getCitynumber()));
        city.setText(customer.getCity());
        email.setText(customer.getEmail());

        phones.clear();
        customer.getPhoneNumbers()
                .stream()
                .map(Object::toString)
                .forEach(phones::add);
        while (phones.size() < 3)
            phones.add("");
    }

    @FXML
    private void updatePerson() {
        if( !checkValidation() ) {
            AlertWindow.messageDialog("Sjekk at du har fylt ut alle felt riktig", "Feil i innfylling");
            return;
        }

        String personNumber = socialSecurityNumber.getText();
        List<Integer> phonelist = phones.stream()
                                        .filter(string -> !isNumberWithLength(8).test(string))
                                        .distinct()
                                        .mapToInt(string -> Integer.parseInt(string, 10))
                                        .boxed()
                                        .collect(Collectors.toList());

        ContactInfo contactInfo = new ContactInfo(adress.getText(), Integer.parseInt( citynumber.getText() ), city.getText(), email.getText(), phonelist);
        Customer newcustomer = new Customer(firstname.getText(), lastname.getText(), personNumber, contactInfo);
        Customer oldcustomer = currentCustomer.get();

        oldcustomer.getInsuranceNumbers().stream().forEach(newcustomer::addInsuranceNumber);
        oldcustomer.getIncidentNumbers().stream().forEach(newcustomer::addIncidentNumber);

        customerRegister.update(newcustomer);
        currentCustomer.set(customerRegister.get(personNumber));
        AlertWindow.messageDialog("Endring av personlig info vellykket", "Endring godkjent");
    }

    @Override
    protected boolean checkValidation() {
        // we already do regex, so we only need to check pseudoclass state
        //todo: user feedbakc -> what textfields are wrong

        if ( socialSecurityNumber.getText().length() != 11 && socialSecurityNumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( !validationIsOk(3).test(adress) )
            return false;
        if ( !validationIsOk(4).test(email) )
            return false;
        if ( !validationIsOk(2).test(city))
            return false;
        if ( pseudoOK.test(citynumber) )
            return false;
        if ( !validationIsOk(1).test(firstname))
            return false;
        if ( !validationIsOk(2).test(lastname))
            return false;

        if(   phones.stream()
                    .filter( string -> !isNumberWithLength(8).test(string) )
                    .count()  == 0)
            return false;

        return true;
    }
}
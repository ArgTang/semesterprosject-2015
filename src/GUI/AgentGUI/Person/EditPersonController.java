package GUI.AgentGUI.Person;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Person.ContactInfo;
import Person.Customer;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by steinar on 19.04.2015.
 */
public class EditPersonController implements CommonGUIMethods
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
    Button changeCustomer;

    private ObservableList<String> phones = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        phonelist.setItems(phones);
        setCurrentPersonListener();
        addCSSValidation();

        if( StartMain.currentCustomer.getPersonProperty().isNotNull().get())
        {
            setCustomer( StartMain.currentCustomer.getPerson() );
            changeCustomer.setTextFill(Color.RED);
        }
        else
        {
            //todo: do we need option to add more than 3 phonenumbers?
            changeCustomer.setText("Registrer ny kunde");
            phones.addAll("", "", "");
        }
    }

    @Override
    public void clearFields()
    {
        socialSecurityNumber.setEditable(true);
        resetTextFields(socialSecurityNumber, firstname, lastname, adress, citynumber, city, email);
        phones.clear();
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(socialSecurityNumber, RegEX.isNumber(11));
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(email, RegEX.isEmail());
        addCSSTextValidation(RegEX.isLetters(), firstname, lastname, city);
    }

    //todo: put these into an interface (DRY)?? also need to redraw or update label after new name is set
    private void setCurrentPersonListener()
    {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Customer> property = (SimpleObjectProperty) observable;
                    if (property.isNotNull().get())
                        setCustomer(property.getValue());
                });

        phonelist.setCellFactory(TextFieldListCell.forListView());

        //todo: find a way to add cssValidation when editing Listview
        phonelist.setOnEditStart(new EventHandler<ListView.EditEvent>() {
            @Override
            public void handle(ListView.EditEvent event) {
                System.out.println(event.getNewValue());
                //RegEX.addCSSTextValidation(textfield, RegEX.isNumber(8));
            }
        });

        //todo: lambda here??
        phonelist.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent event) {
                if (RegEX.isNumber(8).test(event.getNewValue().toString())) {
                    AlertWindow.messageDialog("Telefonnummer må ha 8 siffer", "feil i telefonnummer");
                } else
                    phonelist.getItems().set(event.getIndex(), event.getNewValue());
            }
        });
        //todo why u no compile???:
        //phonelist.setOnEditCommit( event -> phonelist.getItems().set(event.getIndex(), event.getNewValue()));
    }

    private void setCustomer(Customer customer) {
        //todo: if there is an update problem, we need to change this to tesxproperty
        socialSecurityNumber.setText( customer.getSocialSecurityNumber() );
        socialSecurityNumber.setEditable(false);
        firstname.setText(customer.getFirstName());
        lastname.setText(customer.getLastName());
        adress.setText(customer.getAdress());
        citynumber.setText(String.valueOf(customer.getCitynumbr()));
        city.setText(customer.getCity());
        email.setText(customer.getEmail());

        phones.clear();
        customer.getPhoneNumbers()
                .stream()
                .map(i -> i.toString())
                .forEach(phones::add);
        while (phones.size() < 3)
            phones.add("");
    }

    @FXML
    private void updatePerson()
    {
        if( !checkValidation() )
            AlertWindow.messageDialog("Sjekk at du har fylt ut alle felt riktig", "Feil i innfylling");

        List<Integer> phonelist = phones.stream()
                .mapToInt(string -> Integer.parseInt(string, 10)) //todo: check if nullpointerException here?
                .boxed()
                .collect(Collectors.toList());

        if ( StartMain.currentCustomer.getPersonProperty().isNotNull().get())
        {
            Person oldPerson = StartMain.currentCustomer.getPerson();
            //todo: update person
        }
        else
        {
            newPerson(phonelist);
        }
    }

    private void newPerson(List pheonenumbers)
    {
        //todo: check in register if exists? maybe not needed, since we check if person is loaded from searchresult in updatePerson method

        if( !checkValidation() )
        {
            AlertWindow.errorDialog("Sjekk at alle felter er fylt ut korrekt", "Feil i skjema");
            return;
        }

        String socialsecuritynumber = socialSecurityNumber.getText();
        String addressString = adress.getText();
        String emailString = email.getText();
        String cityString = city.getText();
        int citynumberString = Integer.parseInt(citynumber.getText(), 10);
        String firstnameString = firstname.getText();
        String lastnameString = lastname.getText();

        //get valid phonenumbers
        List phonenumbers = phones.stream()
                                    .filter(RegEX.isNumber(8))
                                    .collect(Collectors.toList());

        ContactInfo contactInfo = new ContactInfo(addressString, citynumberString, cityString, emailString, phonenumbers );
        Customer customer = new Customer(firstnameString, lastnameString, socialsecuritynumber, contactInfo);
        StartMain.customerRegister.addCustomer(customer);
    }

    private boolean checkValidation()
    {
        // we already do regex, so we only need to check pseudoclass state

        if ( socialSecurityNumber.getText().length() == 11 && !socialSecurityNumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( adress.getText().length() > 3 && !adress.getPseudoClassStates().isEmpty() )
            return false;
        if ( email.getText().length() > 4 && !email.getPseudoClassStates().isEmpty() )
            return false;
        if ( city.getText().length() > 2 && !city.getPseudoClassStates().isEmpty() )
            return false;
        if ( citynumber.getText().length() == 4 && !citynumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( firstname.getText().length() < 1 && !firstname.getPseudoClassStates().isEmpty() )
            return false;
        if ( lastname.getText().length() < 2  && !lastname.getPseudoClassStates().isEmpty() )
            return false;
        //todo: check if this works as intended
        if ( phones.stream()
                .filter(RegEX.isNumber(8))
                .count() == 0 )
            return false;

        return true;
    }
}
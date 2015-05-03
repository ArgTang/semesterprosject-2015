package GUI.AgentGUI.Person;

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
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;

import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.customerRegister;

/**
 * Created by steinar on 19.04.2015.
 */
public class EditPersonController extends CommonGUIMethods
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
        currentCustomer.reset();
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


    //todo: put these into an interface (DRY)?? also need to redraw or update label after new name is set
    @Override
    protected void setListeners() {
        currentCustomer.getPersonProperty().addListener(observable -> setCustomer());

        //todo: find a way to add cssValidation when editing Listview
        phonelist.setOnEditStart(new EventHandler<ListView.EditEvent>() {
            @Override
            public void handle(ListView.EditEvent event) {
                System.out.println(event.getNewValue());
                //RegEX.addCSSTextValidation(textfield, RegEX.isNumberWithLength(8));
            }
        });

        //todo: lambda here??
        phonelist.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent event) {
                if (isNumberWithLength(8).test(event.getNewValue().toString())) {
                    AlertWindow.messageDialog("Telefonnummer må ha 8 siffer", "feil i telefonnummer");
                } else
                    phonelist.getItems().set(event.getIndex(), event.getNewValue());
            }
        });
        //todo why u no compile???:
        //phonelist.setOnEditCommit( event -> phonelist.getItems().set(event.getIndex(), event.getNewValue()));
    }

    @Override
    protected void setCustomer() {
        Customer customer = currentCustomer.getPerson();
        setRegisterButton();
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
                .map(i -> i.toString())
                .forEach(phones::add);
        while (phones.size() < 3)
            phones.add("");
    }

    // this will change the button text to new or change customer dependant on wether a currentcustomer is selected
    //todo: change na,e to something better
    private void setRegisterButton() {
        if( currentCustomer.getPersonProperty().isNotNull().get()) {
            changeCustomerButton.setTextFill(Color.RED);
            changeCustomerButton.setText("Endre Kunde");
        } else {
            //todo: do we need option to add more than 3 phonenumbers?
            changeCustomerButton.setText("Registrer ny kunde");
            changeCustomerButton.setTextFill(Color.BLACK);
            phones.addAll("", "", "");
        }
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
        Customer customer = new Customer(firstname.getText(), lastname.getText(), personNumber, contactInfo);

        if ( currentCustomer.getPersonProperty().isNotNull().get()) {
            customerRegister.update(customer);
            currentCustomer.setProperty( customerRegister.get( personNumber ));
        } else if ( customerRegister.get(socialSecurityNumber.getText()) != null ) {
            AlertWindow.messageDialog("denne personen finnes allerede i registeret, vennligst søk opp personen før du forsøker å endre", "kunde er allerede registrert");
        } else {
            customerRegister.add(customer);
        }
    }

    @Override
    protected boolean checkValidation() {
        // we already do regex, so we only need to check pseudoclass state
        //todo: user feedbakc -> what textfields are wrong

        if ( socialSecurityNumber.getText().length() != 11 && socialSecurityNumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( validationIsOk(3).negate().test(adress) )
            return false;
        if ( validationIsOk(4).negate().test(email) )
            return false;
        if ( validationIsOk(2).negate().test(city))
            return false;
        if ( citynumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( validationIsOk(1).negate().test(firstname))
            return false;
        if ( validationIsOk(2).negate().test(lastname))
            return false;

        if(   phones.stream()
                    .filter( string -> !isNumberWithLength(8).test(string) )
                    .count()  == 0)
            return false;

        return true;
    }

    @Override
    protected void makeInsurance() {
        throw  new NoSuchMethodError("EditPerson dont handle Insurances");
    }
}
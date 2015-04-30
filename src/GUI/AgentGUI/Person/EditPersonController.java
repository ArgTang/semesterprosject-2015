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
import java.util.Set;
import java.util.stream.Collectors;

import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.customerRegister;

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

        if( currentCustomer.getPersonProperty().isNotNull().get())
        {
            setCustomer( currentCustomer.getPerson() );
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
        RegEX.addCSSTextValidation(socialSecurityNumber, isNumber(11));
        RegEX.addCSSTextValidation(adress, isAdress());
        RegEX.addCSSTextValidation(citynumber, isNumber(4));
        RegEX.addCSSTextValidation(email, isEmail());
        addCSSTextValidation(isLetters(), firstname, lastname, city);
    }

    //todo: put these into an interface (DRY)?? also need to redraw or update label after new name is set
    private void setCurrentPersonListener()
    {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        currentCustomer.getPersonProperty().addListener(
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
                if (isNumber(8).test(event.getNewValue().toString())) {
                    AlertWindow.messageDialog("Telefonnummer må ha 8 siffer", "feil i telefonnummer");
                } else
                    phonelist.getItems().set(event.getIndex(), event.getNewValue());
            }
        });
        //todo why u no compile???:
        //phonelist.setOnEditCommit( event -> phonelist.getItems().set(event.getIndex(), event.getNewValue()));
    }

    private void setCustomer(Customer customer) {
        socialSecurityNumber.setText(customer.getSocialSecurityNumber());
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
        if( !checkValidation() ) {
            AlertWindow.messageDialog("Sjekk at du har fylt ut alle felt riktig", "Feil i innfylling");
            return;
        }

        String personNumber = socialSecurityNumber.getText();
        List<Integer> phonelist = phones.stream()
                                        .filter(string -> !isNumber(8).test(string))
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

    private boolean checkValidation()
    {
        // we already do regex, so we only need to check pseudoclass state
        //todo: user feedbakc -> what textfields are wrong

        if ( socialSecurityNumber.getText().length() != 11 && socialSecurityNumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( adress.getText().length() < 3 && adress.getPseudoClassStates().isEmpty() )
            return false;
        if ( email.getText().length() < 4 && email.getPseudoClassStates().isEmpty() )
            return false;
        if ( city.getText().length() < 2 && city.getPseudoClassStates().isEmpty() )
            return false;
        if ( citynumber.getText().length() != 4 && citynumber.getPseudoClassStates().isEmpty() )
            return false;
        if ( firstname.getText().length() < 1 && firstname.getPseudoClassStates().isEmpty() )
            return false;
        if ( lastname.getText().length() < 2  && lastname.getPseudoClassStates().isEmpty() )
            return false;

        if(  phones.stream()
                .filter( string -> !isNumber(8).test(string) )
                .count()  == 0)
            return false;

        return true;
    }
}
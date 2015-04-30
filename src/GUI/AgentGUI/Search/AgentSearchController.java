package GUI.AgentGUI.Search;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;

import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.*;
import static Register.RegisterCustomer.*;

/**
 * This Class controlls the Agent Search Window
 * Created by steinar on 13.04.2015.
 */
public final class AgentSearchController implements CommonGUIMethods
{
    // Register all search InputFields
    @FXML
    private TextField searchSocialsecuritynumber;
    @FXML
    private TextField searchSurename;
    @FXML
    private TextField searchLastname;
    @FXML
    private TextField searchCustomeriD;
    @FXML
    private TextField searchPhone;
    @FXML
    private Button emptyFields;
    @FXML
    private Button searchButton;

    //Register PersonOutputTable
    @FXML
    private TableView<Customer> personResults;
    @FXML
    private TableColumn<Customer, String> tablesocialnumber;
    @FXML
    private TableColumn<Customer, String> firstname;
    @FXML
    private TableColumn<Customer, String> lastname;
    @FXML
    private TableColumn<Customer, String> tableadress;
    @FXML
    private TableColumn<Customer, String> tablecity;

    public static final ObservableList<Customer> searchresults = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        //these lines sets up the binding from searchresult to he tablecolumns
        tablesocialnumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("SocialSecurityNumber"));
        firstname.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        tableadress.setCellValueFactory(new PropertyValueFactory<Customer, String>("Adress"));
        tablecity.setCellValueFactory(new PropertyValueFactory<Customer, String>("City"));

        //Gets the observable arraylist from witch the search function gets collected into
        searchresults.setAll( customerRegister.getRegister() );

        personResults.setPlaceholder(new Label("Her kommer resultatet fra ditt søk")); //todo: add icon here?
        personResults.setItems( searchresults );

        setListeners();
        addCSSValidation();
    }

    private void setListeners() {
        personResults.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldPerson, newPerson) -> {
                    if (newPerson != null)
                        currentCustomer.setProperty(newPerson);
                }
        );

        personResults.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                changeWindowListener.setPropertyString("Customer");
            }
        });
    }

    @FXML
    private void searchFunction() {
        // we already do regex, so we only need to check pseudoclass state
        String socialsecurity = searchSocialsecuritynumber.getText();
        if ( socialsecurity.length() > 7 && searchSocialsecuritynumber.getPseudoClassStates().isEmpty() )
            searchresults.setAll( customerRegister.get( socialsecurity));

        String surename = searchSurename.getText();
        if ( surename.length() > 1  &&  searchSurename.getPseudoClassStates().isEmpty() )
            searchresults.setAll( customerRegister.search( matchesInFirstnam(surename)));

        String lastname = searchLastname.getText();
        if ( lastname.length() > 1 && searchLastname.getPseudoClassStates().isEmpty() )
            searchresults.setAll( customerRegister.search( matchesInLastname(lastname)));

        String customerID = searchCustomeriD.getText();
        if ( customerID.length() > 7 && searchCustomeriD.getPseudoClassStates().isEmpty() )
            searchresults.setAll( customerRegister.search( matchesInCustomerID(customerID)));

        String phone = searchPhone.getText();
        if( phone.length() > 7 && searchPhone.getPseudoClassStates().isEmpty() ) {
            searchresults.setAll( customerRegister.search( matchesPhonenumer(Integer.parseInt(phone))));
        }
    }

    @FXML
    @Override
    public void clearFields() {
        resetTextFields(searchSocialsecuritynumber);
        resetTextFields(searchSurename);
        resetTextFields(searchLastname);
        resetTextFields(searchCustomeriD);
        resetTextFields(searchPhone);
        searchresults.clear();
        currentCustomer.reset();
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(searchSocialsecuritynumber, isNumber(11));
        RegEX.addCSSTextValidation(searchSurename, isLetters());
        RegEX.addCSSTextValidation(searchLastname, isLetters());
        RegEX.addCSSTextValidation(searchCustomeriD, isAllChars()); //todo:chage this when customer id is ready
        RegEX.addCSSTextValidation(searchPhone, isNumber(8));
    }

    public Parent initAgentSearch() {
        Parent searchPane = null;
        try {
            searchPane = FXMLLoader.load(getClass().getResource("\\AgentPersonSearch.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchPane;
    }
}
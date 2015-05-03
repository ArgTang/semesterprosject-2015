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
public final class AgentSearchController extends CommonGUIMethods
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

    //register search output table
    @FXML
    private TableView<Customer> personResults;
    @FXML
    private TableColumn<Customer, String> socialnumberColumn;
    @FXML
    private TableColumn<Customer, String> firstnameColumn;
    @FXML
    private TableColumn<Customer, String> lastnameColumn;
    @FXML
    private TableColumn<Customer, String> adressColumn;
    @FXML
    private TableColumn<Customer, String> cityColumn;

    public static final ObservableList<Customer> searchresults = FXCollections.observableArrayList();

    @FXML
    @Override
    protected void initialize() {
        //these lines sets up the binding from searchresult to he tablecolumns
        socialnumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("SocialSecurityNumber"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        adressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Adress"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("City"));

        //Gets the observable arraylist from witch the search function gets collected into
        searchresults.setAll( customerRegister.getRegister() );

        personResults.setPlaceholder(new Label("Her kommer resultatet fra ditt søk")); //todo: add icon here?
        personResults.setItems( searchresults );

        setListeners();
        addCSSValidation();
    }

    @Override
    protected void setListeners() {
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
        if ( validationIsOk(7).test(searchSocialsecuritynumber) )
            searchresults.setAll( customerRegister.get(socialsecurity));

        String surename = searchSurename.getText();
        if ( validationIsOk(1, searchSurename) )
            searchresults.setAll( customerRegister.search( matchesInFirstnam(surename)));

        String lastname = searchLastname.getText();
        if ( validationIsOk(1, searchLastname) )
            searchresults.setAll( customerRegister.search( matchesInLastname(lastname)));

        String customerID = searchCustomeriD.getText();
        if ( validationIsOk(7).test(searchCustomeriD) )
            searchresults.setAll( customerRegister.search( matchesInCustomerID(customerID)));

        String phone = searchPhone.getText();
        if( validationIsOk(7).test(searchPhone) ) {
            searchresults.setAll( customerRegister.search( matchesPhonenumer(Integer.parseInt(phone))));
        }
    }

    @FXML
    @Override
    public void clearFields() {
        resetTextFields(searchSocialsecuritynumber, searchSurename, searchLastname, searchCustomeriD, searchPhone);
        searchresults.clear();
        currentCustomer.reset();
    }

    @Override
    protected void addCSSValidation() {
        RegEX.addCSSTextValidation(searchSocialsecuritynumber, isNumberWithLength(11));
        addCSSTextValidation(isLetters(), searchSurename, searchLastname);
        RegEX.addCSSTextValidation(searchCustomeriD, isNumber()); //todo:change this when customer id is ready
        RegEX.addCSSTextValidation(searchPhone, isNumberWithLength(8));
    }

    public Parent initAgentSearch() {
        Parent searchPane = null;
        try {
            searchPane = FXMLLoader.load(getClass().getResource("\\AgentPersonSearch.fxml"));
        } catch (IOException e) {
            System.out.println("could not find file AgentPersonSearch.fxml");
            e.printStackTrace();
        }
        return searchPane;
    }

    @Override
    protected void makeInsurance() {
        throw new NoSuchMethodError("AgentSearchController have no use for this function");
    }
    @Override
    protected void setCustomer() {
        throw new NoSuchMethodError("AgentSearchController have no use for this function");
    }
    @Override
    protected boolean checkValidation() {
        throw new NoSuchMethodError("AgentSearchController have no use for this function");
    }
}
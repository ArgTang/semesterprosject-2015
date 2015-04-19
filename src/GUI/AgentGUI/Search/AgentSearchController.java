package GUI.AgentGUI.Search;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Person.Person;
import Insurance.Insurance;
import Test.GUItest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

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
    //todo: might need to register each column
    @FXML
    private TableView<Person> personResults;
    @FXML
    private TableColumn<Person, String> firstname;
    @FXML
    private TableColumn<Person, String> lastname;

    //Register PersonDetailOutput
    @FXML
    private Label socialsecurity;
    @FXML
    private Label customerId;
    @FXML
    private Label fullname;
    @FXML
    private Label adress;
    @FXML
    private Label phonenumber;
    @FXML
    private Label city;
    @FXML
    private Button editPerson;

    //Register CaseOutputTable
    @FXML
    private TableView<Insurance> caseResults;
    @FXML
    private TableColumn<Insurance, String> type;
    @FXML
    private TableColumn<Insurance, Integer> year;

    //class containing observale searchList
    GUItest test;
    //searchresults go here
    public static ObservableList<Person> searchresults;


    @FXML
    private void initialize()
    {
        //this function sets up the binding from searchresult to tables in the view
        firstname.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory("lastName"));
        //Gets the observable arraylist from witch the search function gets collected into
        test = new GUItest();
        personResults.setItems(test.getPersonData());
        personResults.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldPerson, newPerson) -> {
                    setSelectedPersonDetails(newPerson);
                    StartMain.currentCustomer.setProperty(newPerson);
                }
        );
        addCSSValidation();
    }

    @FXML
    private void showEditPersonDialog(ActionEvent actionEvent) throws IOException
    {
        //todo: goto personeditmenu instead? or is a popup sufficient
        Parent EditPerson = FXMLLoader.load(getClass().getResource("\\AgentEditPerson.fxml"));
        EditPerson.setStyle("-fx-font-size: 1.5em;");

        //todo: get mainstage somehow instead of creating a new one?
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.APPLICATION_MODAL); // freeze program todo: feedback when clicking outside of mpdal?

        Scene scene = new Scene(EditPerson);
        dialogStage.setScene(scene);

        //new stage -> add cssRules for cssValidation
        String css = StartMain.class.getResource("\\css\\login.css").toExternalForm();
        scene.getStylesheets().add(css);


        dialogStage.setAlwaysOnTop(true);
        dialogStage.isAlwaysOnTop();
        dialogStage.centerOnScreen();
        dialogStage.showAndWait();

    }

    @FXML
    private void searchFunction()
    {
        // we already do regex, so we only need to check pseudoclass state
        String socialsecurity = searchSocialsecuritynumber.getText();
        if ( socialsecurity.length() > 7 && searchSocialsecuritynumber.getPseudoClassStates().isEmpty() )
            AlertWindow.messageDialog("vi søker på personnummer her: " + socialsecurity, "søk");

        String surename = searchSurename.getText();
        if ( surename.length() > 1  &&  searchSurename.getPseudoClassStates().isEmpty() )
            AlertWindow.messageDialog("søker på fornavn her: " + surename, "søk");

        String lastname = searchLastname.getText();
        if ( lastname.length() > 1 && searchLastname.getPseudoClassStates().isEmpty() )
            AlertWindow.messageDialog("søker på etternavn her: " + lastname, "søk");

        String customerID = searchCustomeriD.getText();
        if ( customerID.length() > 7 && searchCustomeriD.getPseudoClassStates().isEmpty() ) //todo: change this when customerid is done
            AlertWindow.messageDialog("søker på kundeid her: " + customerID, "søk");

        String phone = searchPhone.getText();
        if( phone.length() > 7 && searchPhone.getPseudoClassStates().isEmpty() )
            AlertWindow.messageDialog("søker etter telefon: " + phone, "søk");

        AlertWindow.messageDialog("Søkeknapp", "søkeknapp");
    }

    @FXML
    @Override
    public void clearFields()
    {
        resetTextField(searchSocialsecuritynumber);
        resetTextField(searchSurename);
        resetTextField(searchLastname);
        resetTextField(searchCustomeriD);
        resetTextField(searchPhone);
        test.resetList();
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(searchSocialsecuritynumber, RegEX.isNumber(11));
        RegEX.addCSSTextValidation(searchSurename, RegEX.isLetters());
        RegEX.addCSSTextValidation(searchLastname, RegEX.isLetters());
        RegEX.addCSSTextValidation(searchCustomeriD, RegEX.isAllChars()); //todo:cnage this when customer id is ready
        RegEX.addCSSTextValidation(searchPhone, RegEX.isNumber(8));
    }


    public Parent initAgentSearch() throws IOException
    {
        Parent search = FXMLLoader.load( getClass().getResource("\\AgentPersonSearch.fxml"));
        return search;
    }

    private void setSelectedPersonDetails(Person person)
    {
        socialsecurity.setText( String.valueOf( person.getSocialSecurityNumber() ) );
        customerId.setText( String.valueOf(1234567) ); //todo: set this field when customers have customernumbers
        fullname.setText(person.getFirstName() + " " + person.getLastName() );
        phonenumber.setText( String.valueOf( person.getAPhonenumber() ) );
        adress.setText( person.getAdress() );
        city.setText( person.getCity() );
    }

}

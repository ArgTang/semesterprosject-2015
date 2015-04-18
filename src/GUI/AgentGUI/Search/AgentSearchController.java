package GUI.AgentGUI.Search;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.RegEX;
import Person.Person;
import Insurance.Insurance;
import Test.GUItest;
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

    @FXML
    private void initialize()
    {
        //this function sets up the binding from searchresult to tables in the view
        firstname.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory("lastName"));
        //Gets the observable arraylist from witch the search function gets collected into
        GUItest test = new GUItest();
        personResults.setItems(test.getPersonData());
        personResults.getSelectionModel().selectedItemProperty().addListener((observable, oldPerson, newPerson) -> setSelectedPersonDetails(newPerson));

        addCSSValidation();
    }

    @FXML
    private void showEditPersonDialog(ActionEvent actionEvent) throws IOException
    {
        //todo: goto personeditmenu instead?
        Parent EditPerson = FXMLLoader.load(getClass().getResource("\\AgentEditPerson.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        Scene scene = new Scene(EditPerson);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
    }

    @FXML
    private void searchFunction()
    {
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
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(searchSocialsecuritynumber, RegEX.isNumber(11));
        RegEX.addCSSTextValidation(searchSurename, RegEX.isLetters());
        RegEX.addCSSTextValidation(searchLastname, RegEX.isLetters());
        RegEX.addCSSTextValidation(searchCustomeriD, RegEX.isAllChars());
        RegEX.addCSSTextValidation(searchPhone,RegEX.isNumber(8));
    }

    private Stage owner;

    public Parent initAgentSearch(Stage stage) throws IOException
    {
        this.owner = stage;
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

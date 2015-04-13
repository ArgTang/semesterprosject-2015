package GUI.AgentGUI;

import GUI.StartMain;
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
import Person.Person;


/**
 * Created by steinar on 13.04.2015.
 */
public class AgentSearchController
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
    private TextField searchAdress;

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
    private void showEditPersonDialog(ActionEvent actionEvent) throws IOException
    {
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
    private void initialize()
    {
        firstname.setCellValueFactory( new PropertyValueFactory("firstName") );
        lastname.setCellValueFactory( new PropertyValueFactory("lastName") );
        test = new GUItest();
        personResults.setItems( test.getPersonData() );
    }

    private Stage owner;
    private StartMain main;
    private GUItest test;

    public Parent initAgentSearch(Stage owner) throws IOException
    {
        this.owner = owner;
        Parent search = FXMLLoader.load( getClass().getResource("\\AgentPersonSearchFull.fxml"));
        return search;
    }

}

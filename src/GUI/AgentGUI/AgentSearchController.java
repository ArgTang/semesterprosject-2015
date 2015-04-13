package GUI.AgentGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by steinar on 13.04.2015.
 */
public class AgentSearchController implements Initializable {


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
    private javafx.scene.control.TableView personResults;

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
    private javafx.scene.control.TableView caseResults;

    @FXML
    private void showEditPersonDialog(ActionEvent actionEvent) throws IOException
    {
        // Load the fxml file and create a new stage for the popup dialog.
        Parent EditPerson = FXMLLoader.load(getClass().getResource("\\AgentEditPerson.fxml"));

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);
        Scene scene = new Scene(EditPerson);
        dialogStage.setScene(scene);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Stage owner;

    public Parent initAgentSearch(Stage owner) throws IOException
    {
        this.owner = owner;
        Parent search = FXMLLoader.load( getClass().getResource("\\AgentPersonSearchFull.fxml"));
        return search;
    }


}

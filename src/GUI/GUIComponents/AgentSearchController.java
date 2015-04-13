package GUI.GUIComponents;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by steinar on 13.04.2015.
 */
public class AgentSearchController implements Initializable {

    // Register all search InputFields
    @FXML
    private TextField SearchSocialsecuritynumber;
    @FXML
    private TextField SearchSurename;
    @FXML
    private TextField SearchLastname;
    @FXML
    private TextField SearchCustomeriD;
    @FXML
    private TextField SearchPhone;
    @FXML
    private TextField SearchAdress;

    //Register PersonOutputTable
    @FXML
    private TableView personSearchResults;

    //Register PersonDetailOutput
    @FXML
    private TextField socialsecurity;
    @FXML
    private TextField customerId;
    @FXML
    private TextField adress;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField city;
    @FXML
    private Button editPerson;

    //Register CaseOutputTable
    private TableView caseResults;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public Parent initAgentSearch(Stage owner) throws IOException
    {
        Parent search = FXMLLoader.load( getClass().getResource("\\AgentPersonSearchFull.fxml"));

        //initActionListeners(owner);

        return search;
    }

    private void initActionListeners(Stage owner)
    {
        editPerson.setOnAction(event -> {
            try
            {
                showEditPersonDialog(owner);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    }

    private void showEditPersonDialog(Stage owner) throws IOException
    {
        // Load the fxml file and create a new stage for the popup dialog.
        Parent EditPerson = FXMLLoader.load(getClass().getResource("\\GUIComponents\\AgentPersonEdit.fxml"));

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
}

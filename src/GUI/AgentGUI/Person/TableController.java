package GUI.AgentGUI.Person;

import Insurance.Insurance;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by steinar on 22.04.2015.
 *
 * TODO: This Should be the abstracted version of incident\InsuranceTable
 */
public class TableController
{

    @FXML
    private TableView<Object> table;

    @FXML
    private TableColumn<Object, String> typeCollumn;
    @FXML
    private TableColumn<Object, Integer> yearCollumn;

    private ObservableList<Object> listen;

    @FXML
    private void initialize()
    {
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldObject, newObject) -> {

                    //todo: if insurance set insurance
                    if( newObject instanceof Insurance)
                    {
                        //goto and set insuranceView if doubleclicked
                    }
                    //Todo: if Incident do somethingelse
                    else
                    {
                        //goto and set Incident if doubleclicked
                    }
                }
        );
    }
}

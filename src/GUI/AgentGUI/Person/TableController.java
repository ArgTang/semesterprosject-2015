package GUI.AgentGUI.Person;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.StartMain;
import Insurance.Insurance;
import Person.Person;
import Test.GUItest;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by steinar on 22.04.2015.
 */
public class TableController
{

    @FXML
    private Label tableLabel;

    @FXML
    private TableView<Object> table;

    private TableColumn<Object, String> typeCollumn;
    private TableColumn<Object, Integer> yearCollumn;

    public TableController(String label)
    {
        tableLabel.setText( label );
    }

    @FXML
    private void initialize()
    {
        if ( ("Forsikring").equals( tableLabel.getText() ) )
        {
            //set table to insurance
        }
        else
        {
            //set tale to
        }

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

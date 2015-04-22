package GUI.AgentGUI.Person;

import GUI.StartMain;
import Incident.Incident;
import Person.Customer;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by steinar on 23.04.2015.
 */
public class IncidentController
{
    @FXML
    private TableView<Incident> table;

    @FXML
    private TableColumn<Incident, String> typeCollumn;
    @FXML
    private TableColumn<Incident, Integer> yearCollumn;


    private ObservableList<Incident> theList = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        typeCollumn.setCellValueFactory(new PropertyValueFactory("type")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("year")); //todo: Get LocalDate.year?

        table.setItems(theList);
    }


    private void setCurrentPersonListener() {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                    //setCustomer(property.getValue());
                });
    }


    private void setsetCustomer(Person person)
    {
        person = (Customer) person;

    }
}

package GUI.AgentGUI.Person;

import Incident.Incident;
import Person.Customer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static GUI.StartMain.*;

/**
 * Created by steinar on 23.04.2015.
 *
 *
 */

public class IncidentTableController
{
    @FXML
    private TableView<Incident> table;

    @FXML
    private TableColumn<Incident, String> typeCollumn;
    @FXML
    private TableColumn<Incident, Integer> yearCollumn;

    private ObservableList<Incident> theList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        typeCollumn.setCellValueFactory(new PropertyValueFactory("type")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("year")); //todo: Get LocalDate.year?

        table.setPlaceholder(new Label("Ingen registrerte hendelsere")); //todo: add icon here?
        table.setItems(theList);

        setListeners();
    }

    //todo: thi method is used some places ->
    private void setListeners() {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Customer> property = (SimpleObjectProperty) observable;
                    setCustomer(property.getValue());
                });

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldIncident, newIncident) -> {
                    if (newIncident != null)
                        currentIncident.setProperty(newIncident);
                }
        );

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                changeWindowListener.setPropertyString("Incident");
            }
        });
    }

    private void setCustomer(Customer customer) {
       customer.getIncidentNumbers().stream()
                                    .map(incidentRegister::get)
                                    .forEach(theList::add);
    }
}
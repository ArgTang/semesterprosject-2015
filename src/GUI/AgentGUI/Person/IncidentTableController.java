package GUI.AgentGUI.Person;

import Incident.Incident;
import Person.Customer;
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

    private ObservableList<Incident> tableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        typeCollumn.setCellValueFactory(new PropertyValueFactory("type")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("year")); //todo: Get LocalDate.year?

        table.setPlaceholder(new Label("Ingen registrerte hendelsere")); //todo: add icon here?
        table.setItems(tableList);

        setListeners();
    }

    private void setListeners() {
        currentCustomer.getPersonProperty().addListener((observable, oldValue, newCustomer) -> {
            if (newCustomer != null)
                setCustomer(newCustomer);
        });

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldIncident, newIncident) -> {
                    if (newIncident != null)
                        currentIncident.setProperty(newIncident);
                }
        );

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                Incident  incident= table.getSelectionModel().selectedItemProperty().get();
                if (incident != null)
                    currentIncident.setProperty(incident);
            }
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                changeWindowListener.setPropertyString("Incident");
            }
        });
    }

    private void setCustomer(Customer customer) {
       customer.getIncidentNumbers().stream()
                                    .map(incidentRegister::get)
                                    .forEach(tableList::add);
    }
}
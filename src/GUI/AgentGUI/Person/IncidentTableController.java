package GUI.AgentGUI.Person;

import GUI.StartMain;
import Incident.Incident;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static GUI.CurrentObjectListeners.CurrentIncident.incidentListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.StartMain.incidentRegister;

/**
 * Created by steinar on 23.04.2015.
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
        typeCollumn.setCellValueFactory(new PropertyValueFactory("type"));
        yearCollumn.setCellValueFactory(new PropertyValueFactory("year"));

        table.setPlaceholder(new Label("Ingen registrerte hendelsere")); //todo: add icon here?
        table.setItems(tableList);

        setListeners();
    }

    private void setListeners() {
        currentCustomer.addListener((observable, oldValue, newCustomer) -> {
            if (newCustomer != null)
                setCustomer(newCustomer);
            else
                tableList.clear();
        });

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldIncident, newIncident) -> {
                    if (newIncident != null)
                        incidentListener.set(newIncident);
                }
        );

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                Incident  incident = table.getSelectionModel().selectedItemProperty().get();
                if (incident != null)
                    incidentListener.set(incident);
            }
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                StartMain.changeWindowListener.setString("Incident");
            }
        });
    }

    private void setCustomer(Customer customer) {
       customer.getIncidentNumbers().stream()
                                    .map(incidentRegister::get)
                                    .forEach(tableList::add);
    }
}
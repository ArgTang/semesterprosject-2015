package GUI.CustomerGUI;

import Incident.Incident;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static GUI.StartMain.incidentRegister;

/**
 * Created by steinar on 23.04.2015.
 */

public class CustomerIncidentTableController
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
        typeCollumn.setCellValueFactory( new PropertyValueFactory("type"));
        yearCollumn.setCellValueFactory( new PropertyValueFactory("year"));

        table.setPlaceholder(new Label("Ingen registrerte hendelsere")); //todo: add icon here?
        table.setItems(tableList);
    }

    public void setCustomer(Customer customer) {
       customer.getIncidentNumbers().stream()
                                    .map(incidentRegister::get)
                                    .forEach(tableList::add);
    }
}
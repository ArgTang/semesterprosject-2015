package GUI.CustomerGUI;

import Insurance.Insurance;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.StartMain.insuranceRegister;

/**
 *
 * Created by steinar on 23.04.2015.
 *
 */

public class CustomerInsuranseTableController
{
    @FXML
    private TableView<Insurance> table;

    @FXML
    private TableColumn<Insurance, String> typeCollumn;
    @FXML
    private TableColumn<Insurance, Integer> yearCollumn;

    private ObservableList<Insurance> tableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        typeCollumn.setCellValueFactory(new PropertyValueFactory("InsuranceName")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("FromYear")); //todo: Get LocalDate.year?

        table.setPlaceholder(new Label("Ingen registrerte forsikringer")); //todo: add icon here?
        table.setItems(tableList);

        if ( currentCustomer.isNotNull().get() )
            setCustomer(currentCustomer.get());
    }

    public void setCustomer(Customer customer) {
        tableList.clear();
        customer.getInsuranceNumbers().stream()
                                      .map(insuranceRegister::get)
                                      .forEach(tableList::add);
    }
}
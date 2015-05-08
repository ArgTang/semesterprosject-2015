package GUI.AgentGUI.Person;

import Insurance.Insurance;
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

public class InsuranseTableController
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

        if ( currentCustomer.getPersonProperty().isNotNull().get() )
            setCustomer(currentCustomer.getPerson());
        setListeners();
    }

    private void setListeners() {
        currentCustomer.getPersonProperty().addListener((observable, oldValue, newCustomer) -> {
            if (newCustomer != null)
                setCustomer(newCustomer);
        });

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldInsirance, newInsurance) -> {
                    if (newInsurance != null) {
                        currentInsurance.setProperty(newInsurance);
                    }
                }
        );

        table.setOnMousePressed(event -> {
            //todo test if this is needed
/*            if (event.isPrimaryButtonDown()) {
                Insurance  insurance= table.getSelectionModel().selectedItemProperty().get();
                if (insurance != null)
                    currentInsurance.setProperty(insurance);
            }*/
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                changeWindowListener.setPropertyString("Insurance");
            }
        });
    }

    private void setCustomer(Customer customer) {
        tableList.clear();
        customer.getInsuranceNumbers().stream()
                                      .map(insuranceRegister::get)
                                      .forEach(tableList::add);
    }
}
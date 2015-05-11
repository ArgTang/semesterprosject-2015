package GUI.AgentGUI.Person;

import GUI.CurrentObjectListeners.CurrentInsurance;
import Insurance.Insurance;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.StartMain.changeWindowListener;
import static GUI.StartMain.insuranceRegister;

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

        if ( currentCustomer.isNotNull().get() )
            setCustomer(currentCustomer.get());
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
                (observable, oldInsirance, newInsurance) -> {
                    if (newInsurance != null) {
                        insuranceListener.set(newInsurance);
                    }
                }
        );

        table.setOnMousePressed(event -> {
            //todo test if this is needed
/*            if (event.isPrimaryButtonDown()) {
                Insurance  insurance= table.getSelectionModel().selectedItemProperty().get();
                if (insurance != null)
                    currentInsurance.setInsurance(insurance);
            }*/
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                insuranceListener.set(table.getSelectionModel().selectedItemProperty().get());
                changeWindowListener.setString("Insurance");
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
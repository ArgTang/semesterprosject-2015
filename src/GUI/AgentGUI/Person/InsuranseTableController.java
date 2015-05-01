package GUI.AgentGUI.Person;

import Insurance.Insurance;
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
public class InsuranseTableController
{
    @FXML
    private TableView<Insurance> table;

    @FXML
    private TableColumn<Insurance, String> typeCollumn;
    @FXML
    private TableColumn<Insurance, Integer> yearCollumn;

    private ObservableList<Insurance> theList = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        typeCollumn.setCellValueFactory(new PropertyValueFactory("InsuranceClass")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("FromYear")); //todo: Get LocalDate.year?

        table.setPlaceholder(new Label("Ingen registrerte forsikringer")); //todo: add icon here?
        table.setItems(theList);

        if ( currentCustomer.getPersonProperty().isNotNull().get() )
            setCustomer(currentCustomer.getPerson());
        setListeners();
    }

    //todo: thi method is used some places ->
    private void setListeners() {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Customer> property = (SimpleObjectProperty) observable;
                    if (property.isNotNull().get())
                        setCustomer(property.getValue());
                }
        );

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldInsirance, newInsurance) -> {
                    if (newInsurance != null)
                        currentInsurance.setProperty(newInsurance);
                }
        );

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                changeWindowListener.setPropertyString("Insurance");
            }
        });
    }

    private void setCustomer(Customer customer) {
        customer.getInsuranceNumbers().stream()
                                      .map(insuranceRegister::get)
                                      .forEach(theList::add);
    }
}
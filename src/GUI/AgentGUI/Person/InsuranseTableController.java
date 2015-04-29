package GUI.AgentGUI.Person;

import GUI.StartMain;
import Insurance.Insurance;
import Person.Customer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

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

        if ( StartMain.currentCustomer.getPersonProperty().isNotNull().get() )
            setCustomer(StartMain.currentCustomer.getPerson());
        setListeners();
    }

    //todo: thi method is used some places ->
    private void setListeners() {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Customer> property = (SimpleObjectProperty) observable;
                    setCustomer(property.getValue());
                });

        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                StartMain.changeWindowListener.setPropertyString("Insurance");
            }
        });
    }

    private void setCustomer(Customer customer)
    {

        theList.addAll(customer.getInsuranceNumbers().stream()
                .map(StartMain.insuranceRegister::get)
                                                     .collect(Collectors.toList()));
    }
}

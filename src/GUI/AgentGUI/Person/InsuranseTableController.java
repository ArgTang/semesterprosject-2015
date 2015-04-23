package GUI.AgentGUI.Person;

import GUI.StartMain;
import Insurance.Insurance;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        typeCollumn.setCellValueFactory(new PropertyValueFactory("type")); //todo getValue from Incident
        yearCollumn.setCellValueFactory(new PropertyValueFactory("year")); //todo: Get LocalDate.year?

        table.setPlaceholder( new Label("Ingen registrerte forsikringer") ); //todo: add icon here?
        table.setItems(theList);

        setCurrentPersonListener();
    }

    //todo: thi method is used some places ->
    private void setCurrentPersonListener() {
        //todo: might not need this? as users "should" open a new editPersonwindow each time
        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                    setCustomer(property.getValue());
                });
    }

    private void setCustomer(Person person)
    {
        //person = (Customer) person;

        //theList.add( ((Customer) person).getInsuranceNumbers().stream()
        //                                                      .forEach(IncidentRegister:get);) //TODO: finish this when register is done
    }
}

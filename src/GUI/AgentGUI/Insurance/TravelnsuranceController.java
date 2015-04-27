package GUI.AgentGUI.Insurance;

/**
 * Created by steinar on 27.04.2015.
 */
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class TravelnsuranceController {

    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> paymentOption;

    private static final ObservableList<String> types = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);

        types.addAll("Reise basic", "Reise pluss (Familie)");
        type.setItems(types);

        clearFields();
        setListeners();
    }

    private void clearFields()
    {
        paymentOption.setValue(AgentInsuranceController.paymentOptionNummber.get(0));
        fromDate.setValue(LocalDate.now());
        type.setValue(types.get(0));
    }

    private void setListeners() {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}

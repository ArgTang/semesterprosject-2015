package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.stream.IntStream;

public final class householdContentsInsuranceController implements CommonGUIMethods
{
    @FXML
    private TextField adress;
    @FXML
    private TextField citynumber;
    @FXML
    private TextField city;
    @FXML
    private ComboBox<Integer> roomnumbers;
    @FXML
    private ComboBox<Integer> personnumber;

    @FXML
    private DatePicker fromDate;
    @FXML
    private TextField amount;

    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private ComboBox<String> paymentOption;

    public static final ObservableList<Integer> numbers = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        IntStream.range(1, 11).forEach(numbers::add);
        roomnumbers.setItems(numbers);
        personnumber.setItems(numbers);

        deductible.setItems(AgentInsuranceController.deductablenumbers);
        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);

        addCSSValidation();
        clearFields();
        setListeners();
    }

    @Override
    public void clearFields()
    {
        resetTextFields(adress, citynumber, city, amount);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () ->
        {
            fromDate.setValue(LocalDate.now());
            deductible.setValue(deductible.getItems().get(1));
            paymentOption.setValue(paymentOption.getItems().get(0));
            roomnumbers.setValue(roomnumbers.getItems().get(1));
            personnumber.setValue(personnumber.getItems().get(0));
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(amount, RegEX.isNumber());
    }

    private void setListeners()
    {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}

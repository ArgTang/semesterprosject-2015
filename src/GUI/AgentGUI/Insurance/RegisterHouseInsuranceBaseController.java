package GUI.AgentGUI.Insurance;

import GUI.AgentGUI.CommonGUIMethods;
import Insurance.Helper.PaymentOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;


/**
 * Created by steinar on 16.04.2015.
 */
public final class RegisterHouseInsuranceBaseController implements CommonGUIMethods
{
    @FXML
    Label customername;
    @FXML
    TextField adress;
    @FXML
    TextField citynumber;
    @FXML
    TextField city;
    @FXML
    TextField constructedIn;
    @FXML
    TextField constructionYear;
    @FXML
    TextField buildingType;
    @FXML
    TextField grossArea;
    @FXML
    TextField primaryArea;
    @FXML
    TextField taxedvalue;

    @FXML
    DatePicker fromDate;
    @FXML
    ComboBox deductible;
    @FXML
    ComboBox paymentOption;

    private ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList();
    private ObservableList<String> paymentOptionNummber = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        deductablenumbers.addAll(2000,4000,8000,12000);
        deductible.setItems(deductablenumbers);
        deductible.setValue(4000);
        paymentOptionNummber.addAll(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
        paymentOption.setValue(PaymentOption.MONTHLY.getName());
        paymentOption.setItems(paymentOptionNummber);
        fromDate.setValue(LocalDate.now());
    }

    @Override
    public void clearFields()
    {
        adress.setText("");
        citynumber.setText("");
        city.setText("");
        constructedIn.setText("");
        constructionYear.setText("");
        buildingType.setText("");
        grossArea.setText("");
        primaryArea.setText("");
        taxedvalue.setText("");
        fromDate.setValue(LocalDate.now());
        deductible.setValue(4000);
        paymentOption.setValue(PaymentOption.MONTHLY.getName());

    }
}

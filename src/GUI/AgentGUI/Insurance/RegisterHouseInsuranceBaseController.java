package GUI.AgentGUI.Insurance;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    ComboBox<String> constructedIn;
    @FXML
    TextField constructionYear;
    @FXML
    ComboBox<String> buildingType;
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
    private ObservableList<String> buildingMaterials = FXCollections.observableArrayList();
    private ObservableList<String> buildingTypes = FXCollections.observableArrayList();
    private StringProperty personName = new SimpleStringProperty();

    @FXML
    private void initialize()
    {
        deductablenumbers.addAll(2000, 4000, 8000, 12000);
        deductible.setItems(deductablenumbers);
        deductible.setValue(deductablenumbers.get(1));

        paymentOptionNummber.addAll(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
        paymentOption.setValue(paymentOptionNummber.get(0));
        paymentOption.setItems(paymentOptionNummber);

        //todo: ENUM?
        buildingMaterials.addAll("Mur", "Tre", "strå");
        constructedIn.setItems(buildingMaterials);
        constructedIn.setValue(buildingMaterials.get(0));

        //todo: ENUM?
        buildingTypes.addAll("Rekkehus", "Enebolig", "Leilighet", "Tomannsbolig");
        buildingTypes.sorted();
        buildingType.setItems(buildingTypes);
        buildingType.setValue(buildingTypes.get(1));

        fromDate.setValue(LocalDate.now());

        customername.textProperty().bind(personName);
        customername.setStyle("-fx-font-weight: bold;");
        Person person = StartMain.currentCustomer.getProperty();
        if (person != null)
            setCustomername(person);
        setCurrentPersonListener();
        setInsuranceChoiceListener();
        addCSSValidation();
    }

    @Override
    public void clearFields()
    {
        resetTextField(adress);
        resetTextField(citynumber);
        resetTextField(city);
        constructedIn.setValue(buildingMaterials.get(0));
        resetTextField(constructionYear);
        buildingType.setValue(buildingTypes.get(1));
        resetTextField(grossArea);
        resetTextField(primaryArea);
        resetTextField(taxedvalue);
        personName.setValue("");
        fromDate.setValue(LocalDate.now());
        deductible.setValue(4000);
        paymentOption.setValue(PaymentOption.MONTHLY.getName());
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(constructionYear, RegEX.isNumber()); //todo: make another regex for this
        RegEX.addCSSTextValidation(grossArea, RegEX.isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, RegEX.isNumber()); //todo:make regex for this
    }
    private void setInsuranceChoiceListener()
    {
        AgentInsuranceController.insuranceChoiceListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    if (string.getValue().equals("tøm skjerm"))
                        clearFields();
                });

    }

    //todo: put these into an interface (DRY)?? also need to redraw or update label after new name is set
    private void setCurrentPersonListener()
    {
        StartMain.currentCustomer.getPersonProperty().addListener(
            observable -> {
                SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                setCustomername(property.getValue());
            });
    }

    private void setCustomername(Person person)
    {
        //customername.setText(person.getFirstName() + " " + person.getLastName());
        personName.setValue( person.getFirstName() + " " + person.getLastName() );
    }
}

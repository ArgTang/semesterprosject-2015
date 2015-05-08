package GUI.AgentGUI.Incident;

import GUI.GuiHelper.CommonGUIMethods;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

import static GUI.AgentGUI.Incident.AgentIncidentController.emptyscreenButton;
import static GUI.GuiHelper.RegEX.addCSSTextValidation;
import static GUI.GuiHelper.RegEX.isNumber;
import static GUI.GuiHelper.RegEX.validationIsOk;

/**
 * Created by steinar on 07.05.2015.
 */
public class IncidentReportController extends CommonGUIMethods
{

    @FXML
    private DatePicker dateOfIncident;

    @FXML
    private CheckBox fire;

    @FXML
    private CheckBox burglary;

    @FXML
    private CheckBox waterdamage;

    @FXML
    private CheckBox personDamage;

    @FXML
    private CheckBox accident;

    @FXML
    private CheckBox nature;

    @FXML
    private RadioButton oneVehicleInvolved;
    @FXML
    private RadioButton moreVehiclesInvolved;


    @FXML
    private TextField claimedValue;

    @FXML
    private Label payedDate;

    @FXML
    private Label payedTot;


    @FXML
    @Override
    protected void initialize() {
        ToggleGroup group = new ToggleGroup();
        oneVehicleInvolved.setToggleGroup(group);
        moreVehiclesInvolved.setToggleGroup(group);

        clearFields();
        setListeners();
        addCSSValidation();
    }

    @Override
    public void clearFields() {
        dateOfIncident.setValue(LocalDate.now());
        resetCheckBox(fire, nature, waterdamage, burglary, personDamage, accident);
        claimedValue.setText("");
    }

    @Override
    protected void setListeners() {
        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }

    @Override
    protected void addCSSValidation() {
        addCSSTextValidation(claimedValue, isNumber());
    }

    @Override
    protected boolean checkValidation() {
        return validationIsOk(2).test(claimedValue);
    }

    private void resetCheckBox(CheckBox... checkBoxes) {
        for (CheckBox checkBox: checkBoxes)
            checkBox.setSelected(false);
    }

}

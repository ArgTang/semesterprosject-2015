package GUI.AgentGUI.Incident;

import GUI.CurrentObjectListeners.CurrentIncident;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonInsuranceMethods;
import Incident.Incident;
import Insurance.Insurance;
import Person.Customer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

import static GUI.AgentGUI.Incident.AgentIncidentController.emptyscreenButton;
import static GUI.AgentGUI.Incident.IncidentConfirmModuleController.confirmIncidentButton;
import static GUI.AgentGUI.Incident.IncidentConfirmModuleController.description;
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.incidentRegister;

/**
 * Created by steinar on 07.05.2015.
 */
public class IncidentReportController extends CommonInsuranceMethods
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

    private Incident incident;

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

        confirmIncidentButton.addListener( listener -> { if(confirmIncidentButton.get()) makeInsurance();});
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

    @Override
    protected void loadCurrentInsurance() {
    }

    @Override
    protected void showInsurance() { //insurance == incident
        incident = CurrentIncident.incidentListener.get();

        description = incident.getIncidentDescription();
        dateOfIncident.setValue(incident.getDayOfIncident());
    }

    @Override
    protected void makeInsurance() { //insurance == incident
        Insurance insurance = insuranceListener.get();
        Customer customer = currentCustomer.get();
        if (insurance == null  || customer == null) {
            AlertWindow.messageDialog("Du må finne forsikringen før du kan rapportere en ulykke", "Mangler en forsikring");
            return;
        }

        incident = new Incident(dateOfIncident.getValue(), insurance.getCasenumber(), description);
        if ( incidentRegister.add(incident) ) {
            currentCustomer.get().addIncidentNumber(incident.getIncidentID());
            IncidentConfirmModuleController.saveFilesToIncident.set(incident.getIncidentID());
            CurrentIncident.incidentListener.set(incident);
        } else
            AlertWindow.errorDialog("Fikk ikke til å lagre i registeret, kontakt support", "Feil i lagring til register");
    }
}

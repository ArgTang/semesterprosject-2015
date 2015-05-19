package GUI.AgentGUI.Incident;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonInsuranceMethods;
import GUI.StartMain;
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
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.*;

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
    private CheckBox theft;
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
        resetCheckBox(fire, nature, waterdamage, theft, personDamage, accident);
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
        addCSSTextValidation(claimedValue, isNumber().and(isLongerThan(2)));
    }

    @Override
    protected boolean checkValidation() {
        return !validationIsOk(2).test(claimedValue);
    }

    private void resetCheckBox(CheckBox... checkBoxes) {
        for (CheckBox checkBox: checkBoxes)
            checkBox.setSelected(false);
    }

    @Override
    protected void showInsurance() { //insurance == incident
        incident = currentIncident.get();

        description = incident.getIncidentDescription();
        dateOfIncident.setValue( incident.getDayOfIncident());
        fire.setSelected( incident.isFire());
        nature.setSelected( incident.isNature());
        waterdamage.setSelected( incident.isWaterdamage());
        theft.setSelected( incident.isTheft());
        personDamage.setSelected( incident.isPersonDamage());
        accident.setSelected( incident.isAccident());
    }

    @Override
    protected void makeInsurance() { //insurance == incident
        Insurance insurance = currentInsurance.get();
        Customer customer = currentCustomer.get();
        if (currentInsurance == null  || customer == null) {
            AlertWindow.messageDialog("Du må finne forsikringen før du kan rapportere en ulykke", "Mangler en forsikring");
            return;
        }

        incident = new Incident(dateOfIncident.getValue(), insurance.getCasenumber(), description, fire.isSelected(),
                theft.isSelected(), waterdamage.isSelected(), accident.isSelected(), nature.isSelected(), personDamage.isSelected());

        if ( incidentRegister.add(incident) ) {
            customer.addIncidentNumber(incident.getIncidentID());
            IncidentConfirmModuleController.saveFilesToIncidentID.set(incident.getIncidentID());
            StartMain.currentIncident.set(incident);
            //todo: find a better way for this
            currentCustomer.reset();
            currentCustomer.set(customer);
        } else
            AlertWindow.errorDialog("Fikk ikke til å lagre i registeret, kontakt support", "Feil i lagring til register");
    }

    @Override
    protected void loadCurrentInsurance() {
        throw new NoSuchMethodError("IncidentReportController do not use this method");
    }
}

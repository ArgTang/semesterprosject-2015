package GUI.AgentGUI.Incident;

import GUI.AgentGUI.Insurance.InsuranceConfirmModuleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;

/**
 * Created by steinar on 17.04.2015.
 */
public final class IncidentChooserModuleController
{
    @FXML
    private ListView incidentChooser;

    private ObservableList<String> incidentTypes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        incidentTypes.addAll("Hus", "Bil", "Båt", "Reise", "Innbo");
        incidentChooser.setItems(incidentTypes);
        incidentChooser.getSelectionModel().selectFirst();
        incidentChooser.requestFocus(); //todo: find a way to set listwiev in focus

        incidentChooser.getSelectionModel().selectedItemProperty().addListener(
                event -> {
                    InsuranceConfirmModuleController.clearLabel();
                    String choice = incidentChooser.getSelectionModel().getSelectedItems().toString();
                    insuranceChoiceListener.setPropertyString( choice );
                });

        insuranceChoiceListener.setPropertyString(incidentTypes.get(0));
    }
}
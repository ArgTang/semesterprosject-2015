package GUI.AgentGUI.Insurance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


/**
 * Created by steinar on 17.04.2015.
 */
public final class InsuranceChooserModuleController
{
    @FXML
    private ListView insuranceChooser;

    private ObservableList<String> insuranceTypes = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        insuranceTypes.addAll("Hus", "Bil", "Båt", "Reise", "Dyr", "Innbo");
        insuranceChooser.setItems(insuranceTypes);
        insuranceChooser.getSelectionModel().selectFirst();
        insuranceChooser.requestFocus(); //todo: find a way to set listwiev in focus

        insuranceChooser.getSelectionModel().selectedItemProperty().addListener(
                event ->
                {
                    String choice = insuranceChooser.getSelectionModel().getSelectedItems().toString();
                    AgentInsuranceController.insuranceChoiceListener.setPropertyString( choice );
                });
    }
}

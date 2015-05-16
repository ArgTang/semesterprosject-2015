package GUI.AgentGUI.Insurance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;
import static GUI.StartMain.currentInsurance;

/**
 * Created by steinar on 17.04.2015.
 */
public final class InsuranceChooserModuleController
{
    @FXML
    private ListView<String> insuranceChooser;

    private ObservableList<String> insuranceTypes = FXCollections.observableArrayList("Hus", "Bil", "Båt", "Reise", "Innbo");

    @FXML
    private void initialize() {
        insuranceChooser.setItems(insuranceTypes);
        insuranceChooser.getSelectionModel().selectFirst();
        insuranceChooser.requestFocus(); //todo: find a way to set listwiev in focus

        insuranceChooser.getSelectionModel().selectedItemProperty().addListener(
                event -> {
                    InsuranceConfirmModuleController.clearLabel();
                    String choice = insuranceChooser.getSelectionModel().getSelectedItem();
                    insuranceChoiceListener.setString(choice);
                });

        currentInsurance.getProperty().addListener(listener -> {
            String currentchoice = insuranceChooser.getSelectionModel().getSelectedItem();
            String newValue = getNameOfInsurance(currentInsurance.get());
            if (newValue.equals(currentchoice))
                return;

            int index = insuranceTypes.indexOf(newValue);
            insuranceChooser.getSelectionModel().clearAndSelect(index);
        });
    }
}
package GUI.AgentGUI.Insurance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{

    public Parent initAgentHouseInsurance(Stage owner) throws IOException
    {
        HBox container = new HBox();
        Parent chooser = FXMLLoader.load(getClass().getResource("\\InsuranceChooserModule.fxml"));
        Parent HouseInsurance = FXMLLoader.load(getClass().getResource("\\RegisterHouseInsuranceBase.fxml"));
        Parent confirm = FXMLLoader.load(getClass().getResource("\\InsuranceConfirmModule.fxml"));

        container.setSpacing(5);
        container.setMaxWidth(Region.USE_COMPUTED_SIZE);
        container.getChildren().addAll(chooser, HouseInsurance, confirm);
        return container;
    }
}

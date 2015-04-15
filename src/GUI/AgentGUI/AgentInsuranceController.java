package GUI.AgentGUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by steinar on 15.04.2015.
 */
public class AgentInsuranceController
{

    public Parent initAgentInsurance(Stage owner) throws IOException
    {
        HBox container = new HBox();
        Parent chooser = FXMLLoader.load(getClass().getResource("\\InsuranceChooserModule.fxml"));
        Parent Insurance = FXMLLoader.load(getClass().getResource("\\RegisterInsuranceBase.fxml"));
        Parent confirm = FXMLLoader.load(getClass().getResource("\\InsuranceConfirmModule.fxml"));

        container.setSpacing(5.0);
        container.getChildren().addAll(chooser, Insurance, confirm);
        return container;
    }
}

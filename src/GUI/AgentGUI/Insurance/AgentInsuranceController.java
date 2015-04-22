package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import GUI.StartMain;
import GUI.WindowChangeListener;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();

    public static final WindowChangeListener insuranceChoiceListener = new WindowChangeListener();

    public Parent initAgentInsuranceView()
    {
        Parent chooser = null;
        Parent confirmModule = null;
        try {
            chooser =  FXMLLoader.load( getClass().getResource("\\InsuranceChooserModule.fxml"));
            confirmModule = FXMLLoader.load(getClass().getResource("\\InsuranceConfirmModule.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setInsuranceChoiceListener();

        container.setLeft(chooser);
        if( container.getCenter() == null)
            showtHouseInsurance();
        container.setRight(confirmModule);
        return container;
    }

    public void showtHouseInsurance()
    {
        loadParent("\\RegisterHouseInsuranceBase.fxml");
    }

    public void showCarinsurance()
    {
        loadParent("\\RegisterCarModule.fxml");
    }

    private void loadParent(String FXMLpath)
    {
        Parent scene = null;
        try {
            scene = FXMLLoader.load(getClass().getResource(FXMLpath));
            fade.setFading(scene);
            container.setCenter(scene);
            fade.setupFadeout(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInsuranceChoiceListener()
    {
        insuranceChoiceListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "tøm skjerm":
                            //do nothing
                            System.out.println("AgentInsuranceController:" + string.getValue());
                            break;
                        case "[Hus]":
                            showtHouseInsurance();
                            break;
                        case "[Bil]":
                            //todo: check if scheme is empty -> confirmdialog
                            showCarinsurance();
                            break;
                        case "[Reise]":
                            AlertWindow.messageDialog("Reiseforsikring", "Reiseforsikring");
                            break;
                        case "[Båt]":
                            AlertWindow.messageDialog("Båtforsikring", "Båtforsikring");

                    }
                }
        );
    }

    private void setCurrentPersonListener()
    {
        StartMain.currentCustomer.getPersonProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {

            }
        });
    }
}

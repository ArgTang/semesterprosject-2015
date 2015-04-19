package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.StartMain;
import GUI.WindowChangeListener;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();

    public static final WindowChangeListener insuranceChoiceListener = new WindowChangeListener();

    private static FadeTransition fader;
    private static final double FADETIME = 0.35;

    public Parent initAgentDefaultInsurance() throws IOException
    {

        Parent chooser =  FXMLLoader.load( getClass().getResource("\\InsuranceChooserModule.fxml"));
        Parent confirmModule =  FXMLLoader.load(getClass().getResource("\\InsuranceConfirmModule.fxml"));
        setInsuranceChoiceListener();

        container.setLeft(chooser);
        if( container.getCenter() == null)
            showtHouseInsurance();
        container.setRight(confirmModule);
        return container;
    }

    public void showtHouseInsurance()
    {
        setFading("\\RegisterHouseInsuranceBase.fxml");
    }

    public void showCarinsurance()
    {
        setFading("\\RegisterCarModule.fxml");
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

    private void setFading(String FXMLUrl)
    {
        Parent scene = null;
        try
        {
            scene = FXMLLoader.load(getClass().getResource(FXMLUrl));
            if (fader != null)
                fader.play();
            AgentInsuranceController.fader = new FadeTransition(Duration.seconds(FADETIME), scene);
            fader.setInterpolator(Interpolator.EASE_IN);
            fader.setFromValue(0.1);
            fader.setToValue(1.0);
            fader.play();
            container.setCenter(scene);
            setupFadeout(scene);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setupFadeout(Parent scene)
    {
        AgentInsuranceController.fader = new FadeTransition(Duration.millis(FADETIME), scene);
        fader.setInterpolator(Interpolator.EASE_OUT);
        fader.setFromValue(1.0);
        fader.setToValue(0.1);
    }
}

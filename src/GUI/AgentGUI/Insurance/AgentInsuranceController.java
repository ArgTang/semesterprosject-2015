package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import GUI.StartMain;
import GUI.WindowChangeListener;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();
    private Label kundenavn = new Label();

    private StringProperty navn = new SimpleStringProperty();;

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

        chooser = setlabel(chooser);
        kundenavn.textProperty().bind(navn);
        kundenavn.setStyle("-fx-font-weight: bold;");
        setListeners();

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

    private void setListeners()
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
                        break;
                    case "[Dyr]":
                        AlertWindow.messageDialog("dyr", "dyr");
                        break;
                }
            }
        );

        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                    Person person = property.getValue();
                    if (person != null)
                        setCustomername(person);
                }
        );
    }

    private Parent setlabel(Parent chooser)
    {
        GridPane grid= new GridPane();
        Label info = new Label("Du behandler nå:");
        grid.add(info, 1, 0);
        grid.add(kundenavn, 1, 1);

        if ( StartMain.currentCustomer.getPersonProperty().isNotNull().get() )
            setCustomername( StartMain.currentCustomer.getPerson() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid, chooser);
        return vBox;
    }

    private void setCustomername(Person person)
    {
        String navnet = person.getFirstName() + " " + person.getLastName();
        navn.setValue(navnet);
    }
}

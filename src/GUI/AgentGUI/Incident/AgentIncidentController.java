package GUI.AgentGUI.Incident;

import GUI.GuiHelper.CommonInsuranceMethods;
import Person.Person;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static GUI.CurrentObjectListeners.CurrentIncident.incidentListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.GuiHelper.CommonInsuranceMethods.setBoldFont;
import static java.lang.String.valueOf;

/**
 * Created by steinar on 15.04.2015.
 */


//todo: copied from InsuranceModule -> needs adjusting
public final class AgentIncidentController
{
    private static BorderPane container = new BorderPane();
    private Label customerName = new Label();
    Label incidentID = new Label();
    Label incidentInfo = new Label("valgt Hendelse: ");

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreenButton = new SimpleBooleanProperty(false);

    private static Parent chooserModule, confirmModule, incidentReport;

    public Parent initAgentIncidentView() {
        customerName.textProperty().bind(selectedCustomerName);
        setBoldFont(customerName, incidentID);

        chooserModule = setlabel( showChooserModule() );
        confirmModule = showConfirmModule();
        setListeners();

        container.setLeft(chooserModule);
        container.setCenter( showIncidentReport() );
        container.setRight(confirmModule);
        return container;
    }

    private Parent showChooserModule() {
        if (chooserModule == null)
            chooserModule = loadParent("\\IncidentTable.fxml");

        return chooserModule;
    }

    private Parent showConfirmModule() {
        if (confirmModule == null)
            confirmModule = loadParent("\\IncidentConfirmModule.fxml");

        return confirmModule;
    }

    private Parent showIncidentReport() {
        if (incidentReport == null)
            incidentReport = loadParent("\\IncidentReport.fxml");

        return incidentReport;
    }

    private Parent loadParent(String FXMLpath) {
        Parent scene = null;
        try {
            scene = FXMLLoader.load(getClass().getResource(FXMLpath));
        } catch (Exception e) {
            System.out.println("cant find file: " + FXMLpath);
            e.printStackTrace();
        }
        return scene;
    }

    private void setListeners() {
        /*stringListener.addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "[Hus]":
                            break;
                        default:
                            AlertWindow.messageDialog("her kommer en rapportinnlevering", "ikke ferdig");
                    }
                }
        );*/

        incidentListener.addListener( listener -> {
            setIncidentLabel();
        });

        currentCustomer.addListener(
            observable -> {
                SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                Person person = property.getValue();
                if (person != null)
                    setCustomername(person);
            }
        );
    }

    private Parent setlabel(Parent chooser) {
        GridPane grid= new GridPane();
        Label info = new Label("Du behandler nå:");
        grid.add(info, 0, 0);
        grid.add(customerName, 0, 1);
        grid.add(incidentInfo, 0, 2);
        grid.add(incidentID, 1, 2);

        if ( currentCustomer.isNotNull().get() )
            setCustomername( currentCustomer.get() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid, chooser);
        return vBox;
    }

    private void setCustomername(Person person) {
        String navnet = person.getFirstName() + " " + person.getLastName();
        selectedCustomerName.setValue(navnet);
    }

    private void setIncidentLabel() {
        if (incidentListener.isNull().get())
            return;

        incidentID.setText( valueOf(incidentListener.get().getIncidentID()));
    }
}
package GUI.AgentGUI.Incident;


import GUI.CurrentObjectListeners.WindowChangeListener;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import Person.Person;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static GUI.StartMain.currentCustomer;

/**
 * Created by steinar on 15.04.2015.
 */


//todo: copied from InsuranceModule -> needs adjusting
public final class AgentIncidentController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();
    private Label kundenavn = new Label();

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreenButton = new SimpleBooleanProperty(false);

    public static final WindowChangeListener incidentChoiceListener = new WindowChangeListener();

    private static Parent chooserModule, confirmModule, incidentReport;

    public Parent initAgentIncidentView() {

        kundenavn.textProperty().bind(selectedCustomerName);
        kundenavn.setStyle("-fx-font-weight: bold;");

        chooserModule = setlabel( showChooserModule() );
        confirmModule = showConfirmModule();
        setListeners();

        container.setLeft(chooserModule);
        if( container.getCenter() == null)
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

    private void setFade(Parent scene) {
        fade.setFading(scene);
        container.setCenter(scene);
        fade.setupFadeout(scene);
    }

    private void setListeners() {
        incidentChoiceListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "[Hus]":
                            break;
                        default:
                            AlertWindow.messageDialog("her kommer en rapportinnlevering", "ikke ferdig");
                    }
                }
        );

        currentCustomer.getPersonProperty().addListener(
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
        grid.add(info, 1, 0);
        grid.add(kundenavn, 1, 1);

        if ( currentCustomer.getPersonProperty().isNotNull().get() )
            setCustomername( currentCustomer.getPerson() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid, chooser);
        return vBox;
    }

    private void setCustomername(Person person) {
        String navnet = person.getFirstName() + " " + person.getLastName();
        selectedCustomerName.setValue(navnet);
    }
}
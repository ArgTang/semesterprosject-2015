package GUI.AgentGUI.Incident;

import Person.Customer;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;
import static GUI.GuiHelper.CommonInsuranceMethods.setBoldFont;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentIncident;
import static GUI.StartMain.currentInsurance;
import static java.lang.String.valueOf;

/**
 * Created by steinar on 15.04.2015.
 */

public final class AgentIncidentController
{
    private static BorderPane container = new BorderPane();
    private Label customerName = new Label();
    private Label insuranceLabel = new Label();
    Label incidentID = new Label();
    Label incidentInfo = new Label("Valgt hendelse: ");

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreenButton = new SimpleBooleanProperty(false);

    private Parent chooserModule, confirmModule, incidentReport;

    public Parent initAgentIncidentView() {
        customerName.textProperty().bind(selectedCustomerName);
        setBoldFont(customerName, incidentID);
        insuranceLabel.visibleProperty().bind(currentInsurance.getProperty().isNotNull());
        incidentInfo.visibleProperty().bind(currentIncident.getProperty().isNotNull());
        incidentID.visibleProperty().bind(currentIncident.getProperty().isNotNull());

        chooserModule = initlabel(showChooserModule());
        container.setLeft( chooserModule );
        container.setCenter( showIncidentReport() );
        container.setRight( showConfirmModule() );

        setListeners();
        setInsuranceLabel();
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
        currentIncident.getProperty().addListener(listener -> setIncidentLabel());
        currentInsurance.getProperty().addListener(listener ->  setInsuranceLabel() );

        currentCustomer.getProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Customer> property = (SimpleObjectProperty<Customer>) observable;
                    Customer customer = property.getValue();
                    if (customer != null)
                        setCustomername(customer);
                }
        );
    }

    private Parent initlabel(Parent chooser) {
        GridPane grid= new GridPane();
        grid.setHgap(5);
        Label info = new Label("Du behandler nå:");
        grid.add(info, 0, 0);
        grid.add(customerName, 0, 1);
        grid.add(insuranceLabel, 0, 2, 2, 1);
        grid.add(incidentInfo, 0, 3);
        grid.add(incidentID, 1, 3);

        if ( currentCustomer.getProperty().isNotNull().get() )
            setCustomername( currentCustomer.get() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid, chooser);
        return grid;
    }

    private void setCustomername(Customer customer) {
        String navnet = customer.getFirstName() + " " + customer.getLastName();
        selectedCustomerName.setValue(navnet);
    }

    private void setIncidentLabel() {
        if (currentIncident.getProperty().isNull().get())
            return;

        incidentID.setText( valueOf( currentIncident.get().getIncidentID()));
    }

    private void setInsuranceLabel() {
        if (currentInsurance.getProperty().isNotNull().get()) {
            String type = getNameOfInsurance( currentInsurance.get());
            insuranceLabel.setText( type + " forsikring");
        }
    }
}
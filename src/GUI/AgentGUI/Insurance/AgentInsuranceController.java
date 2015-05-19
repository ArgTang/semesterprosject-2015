package GUI.AgentGUI.Insurance;

import GUI.CurrentObjectListeners.WindowChangeListener;
import GUI.GuiHelper.Fader;
import Person.Customer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();
    private Label customerName = new Label();

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreenButton = new SimpleBooleanProperty(false);

    public static final WindowChangeListener insuranceChoiceListener = new WindowChangeListener();

    private Parent chooserModule, confirmModule, house, car, animal, travel, boat, household;

    public Parent initAgentInsuranceView() {

        customerName.textProperty().bind(selectedCustomerName);
        customerName.setStyle("-fx-font-weight: bold;");

        confirmModule = showConfirmModule();
        chooserModule = setlabel(showChooserModule());
        setListeners();

        String openwithPane = "Hus";
        if ( currentInsurance.getProperty().isNotNull().get() )
            openwithPane = getNameOfInsurance(currentInsurance.get());
        insuranceChoiceListener.setString(openwithPane);

        container.setLeft(chooserModule);
        container.setRight(confirmModule);
        return container;
    }

    private Parent showChooserModule() {
        if (chooserModule == null)
            chooserModule = loadParent("/GUI/AgentGUI/Insurance/InsuranceChooserModule.fxml");

        return chooserModule;
    }

    private Parent showConfirmModule() {
        if (confirmModule == null)
            confirmModule = loadParent("/GUI/AgentGUI/Insurance/InsuranceConfirmModule.fxml");

        return confirmModule;
    }

    public void showtHouseInsurance() {
        if (house == null)
            house = loadParent("/GUI/AgentGUI/Insurance/Modules/HouseModule.fxml");
        setFade(house);
    }

    public void showCarinsurance() {
        if (car == null)
            car = loadParent("/GUI/AgentGUI/Insurance/Modules/CarModule.fxml");
        setFade(car);
    }

    private void showAnimalInsurance() {
        if (animal == null)
            animal = loadParent("/GUI/AgentGUI/Insurance/Modules/AnimalModule.fxml");
        setFade(animal);
    }

    private void showTravelInsurance() {
        if (travel == null)
            travel = loadParent("/GUI/AgentGUI/Insurance/Modules/TravelModule.fxml");
        setFade(travel);
    }

    private void showBoatInsurance() {
        if (boat == null)
            boat = loadParent("/GUI/AgentGUI/Insurance/Modules/BoatModule.fxml");
        setFade(boat);
    }

    private void showHouseholdInsurance() {
        if (household == null)
            household = loadParent("/GUI/AgentGUI/Insurance/Modules/HouseholdContentsModule.fxml");
        setFade(household);
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
        insuranceChoiceListener.getProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "Bil":
                            showCarinsurance();
                            break;
                        case "Reise":
                            showTravelInsurance();
                            break;
                        case "Båt":
                            showBoatInsurance();
                            break;
                        case "Dyr":
                            showAnimalInsurance();
                            break;
                        case "Innbo":
                            showHouseholdInsurance();
                            break;
                        case "Hus":
                        default:
                            showtHouseInsurance();
                            break;
                    }
                }
        );

        currentInsurance.getProperty().addListener(observable -> {
            if (currentInsurance.getProperty().isNull().get())
                return;

            String insuranceName = getNameOfInsurance(currentInsurance.get());
            insuranceChoiceListener.setString(insuranceName);
        });

        currentCustomer.getProperty().addListener(
                observable -> {
                    Customer customer = currentCustomer.get();
                    if (customer != null)
                        setCustomername(customer);
                    else
                        selectedCustomerName.setValue("Vennligst velg kunde");
                }
        );
    }

    private Parent setlabel(Parent chooser) {
        GridPane grid= new GridPane();
        Label info = new Label("Du behandler nå:");
        grid.add(info, 0, 0);
        grid.add(customerName, 0, 1);

        if ( currentCustomer.getProperty().isNotNull().get() )
            setCustomername( currentCustomer.get() );

        VBox vBox = new VBox();
        vBox.getChildren().addAll(grid, chooser);
        return vBox;
    }

    private void setCustomername(Customer customer) {
        String navnet = customer.getFirstName() + " " + customer.getLastName();
        selectedCustomerName.setValue(navnet);
    }
}
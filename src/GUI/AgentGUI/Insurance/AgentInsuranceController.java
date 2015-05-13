package GUI.AgentGUI.Insurance;

import GUI.CurrentObjectListeners.WindowChangeListener;
import GUI.GuiHelper.Fader;
import Person.Person;
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
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;

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

        container.setLeft(chooserModule);
        if( container.getCenter() == null)
            showtHouseInsurance();
        container.setRight(confirmModule);
        return container;
    }

    private Parent showChooserModule() {
        if (chooserModule == null)
            chooserModule = loadParent("\\InsuranceChooserModule.fxml");

        return chooserModule;
    }

    private Parent showConfirmModule() {
        if (confirmModule == null)
            confirmModule = loadParent("\\InsuranceConfirmModule.fxml");

        return confirmModule;
    }

    public void showtHouseInsurance() {
        if (house == null)
            house = loadParent("\\Modules\\HouseModule.fxml");
        setFade(house);
    }

    public void showCarinsurance() {
        if (car == null)
            car = loadParent("\\Modules\\CarModule.fxml");
        setFade(car);
    }

    private void showAnimalInsurance() {
        if (animal == null)
            animal = loadParent("\\Modules\\AnimalModule.fxml");
        setFade(animal);
    }

    private void showTravelInsurance() {
        if (travel == null)
            travel = loadParent("\\Modules\\TravelModule.fxml");
        setFade(travel);
    }

    private void showBoatInsurance() {
        if (boat == null)
            boat = loadParent("\\Modules\\BoatModule.fxml");
        setFade(boat);
    }

    private void showHouseholdInsurance() {
        if (household == null)
            household = loadParent("\\Modules\\HouseholdContentsModule.fxml");
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
                        case "Hus":
                            showtHouseInsurance();
                            break;
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
                    }
                }
        );

        insuranceListener.addListener(observable -> {
            if (insuranceListener.isNull().get())
                return;

            String insuranceName = getNameOfInsurance(insuranceListener.get());
            insuranceChoiceListener.setString(insuranceName);
        });

        currentCustomer.addListener(
                observable -> {
                    Person person = currentCustomer.get();
                    if (person != null)
                        setCustomername(person);
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
}
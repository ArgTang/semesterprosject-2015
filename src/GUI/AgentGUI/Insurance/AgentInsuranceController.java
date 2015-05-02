package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.Fader;
import GUI.WindowChangeListener;
import Insurance.Animal.AnimalInsurance;
import Insurance.Helper.PaymentOption;
import Insurance.*;
import Insurance.Property.HomeInsurance;
import Insurance.Property.HouseholdContentsInsurance;
import Insurance.Vehicle.BoatInsurance;
import Insurance.Vehicle.CarInsurance;
import Person.Person;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();
    private Label kundenavn = new Label();

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreenButton = new SimpleBooleanProperty(false);

    @Deprecated
    public static final ObservableList<String> paymentOptionNames = FXCollections.observableArrayList(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
    @Deprecated
    public static final ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList(2000, 4000, 8000, 12000);

    public static final WindowChangeListener insuranceChoiceListener = new WindowChangeListener();
    private static Parent chooserModule, confirmModule, house, car, animal, travel, boat, household;

    public Parent initAgentInsuranceView() {

        chooserModule = showChooserModule();
        confirmModule = showConfirmModule();
        chooserModule = setlabel(chooserModule);
        kundenavn.textProperty().bind(selectedCustomerName);
        kundenavn.setStyle("-fx-font-weight: bold;");
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
        setFade(travel);
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
        insuranceChoiceListener.getStringProperty().addListener(
            observable -> {
                StringProperty string = (StringProperty) observable;
                switch (string.getValue()) {
                    case "[Hus]":
                        showtHouseInsurance();
                        break;
                    case "[Bil]":
                        showCarinsurance();
                        break;
                    case "[Reise]":
                        showTravelInsurance();
                        break;
                    case "[Båt]":
                        showBoatInsurance();
                        break;
                    case "[Dyr]":
                        showAnimalInsurance();
                        break;
                    case "[Innbo]":
                        showHouseholdInsurance();
                        break;
                }
            }
        );


        currentInsurance.getInsuranceProperty().addListener( observable1 -> {
            if (currentInsurance.getInsuranceProperty().isNull().get())
                return;

            String insurance = currentInsurance.getNameOfInsurance(currentInsurance.getInsurance());
            insuranceChoiceListener.setPropertyString("[" + insurance + "]");
        });

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
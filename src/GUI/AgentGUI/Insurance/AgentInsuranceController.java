package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.Fader;
import GUI.WindowChangeListener;
import Insurance.Helper.PaymentOption;
import Person.Person;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

import static GUI.StartMain.currentCustomer;

/**
 * Created by steinar on 15.04.2015.
 */
public final class AgentInsuranceController
{
    private static BorderPane container = new BorderPane();
    private Fader fade = new Fader();
    private Label kundenavn = new Label();

    private final StringProperty selectedCustomerName = new SimpleStringProperty();
    public static final BooleanProperty emptyscreen = new SimpleBooleanProperty(false);
    public static final ObservableList<String> paymentOptionNummber = FXCollections.observableArrayList(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
    public static final ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList(2000, 4000, 8000, 12000);

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
        kundenavn.textProperty().bind(selectedCustomerName);
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
        loadParent("\\Modules\\HouseModule.fxml");
    }

    public void showCarinsurance()
    {
        loadParent("\\Modules\\CarModule.fxml");
    }

    private void showAnimalInsurance()
    {
        loadParent("\\Modules\\AnimalModule.fxml");
    }

    private void showTravelInsurance()
    {
        loadParent("\\Modules\\TravelModule.fxml");
    }

    private void showBoatInsurance()
    {
        loadParent("\\Modules\\BoatModule.fxml");
    }

    private void showHouseholdInsurance() { loadParent("\\Modules\\HouseholdContentsModule.fxml"); }

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

        currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                    Person person = property.getValue();
                    if (person != null)
                        AgentInsuranceController.this.setCustomername(person);
                }
        );
    }

    private Parent setlabel(Parent chooser)
    {
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

    private void setCustomername(Person person)
    {
        String navnet = person.getFirstName() + " " + person.getLastName();
        selectedCustomerName.setValue(navnet);
    }
}
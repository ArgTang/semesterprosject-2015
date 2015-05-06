package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Incident.AgentIncidentController;
import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Person.PersonController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.CurrentObjectListeners.*;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import Register.RegisterCustomer;
import Register.RegisterIncident;
import Register.RegisterInsurance;
import Test.MakePersons;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class StartMain extends Application
{
    private static Dimension SCREEN;
    private Stage PrimaryStage;
    private BorderPane rootLayout = new BorderPane();
    private Fader fade = new Fader();

    public static final CurrentCustomer currentCustomer = new CurrentCustomer();
    public static final CurrentInsurance currentInsurance = new CurrentInsurance();
    public static final CurrentIncident currentIncident = new CurrentIncident();                            //todo: change this? more generic

    public static RegisterCustomer customerRegister = new RegisterCustomer();
    public static RegisterInsurance insuranceRegister = new RegisterInsurance();
    public static RegisterIncident incidentRegister = new RegisterIncident();

    public static final WindowChangeListener changeWindowListener = new WindowChangeListener();
    @Deprecated
    public static final WindowWindowListener changeWindowWindowListener = new WindowWindowListener();     //todo: change to this? more generic

    //"storage" for the different Panes
    private static Parent welcome, agentSearch, agentPerson, agentMenu, agentInsurance, agentIncident, agentStatistics;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //generate Customers in new thread -> might be faster when we generate insurance\incidentCases
        Runnable newthread = () -> MakePersons.makeCustomers(1000);
        Thread thread = new Thread(newthread);
        thread.start();
        MakePersons.makeDefaultPerson();

        setListeners();
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.setPrefSize(SCREEN.getWidth() / 1.35, SCREEN.getHeight() / 1.5); //todo: change this maybe?

        startup();

        //adding rules for CSS Validation
        String css = StartMain.class.getResource("\\css\\login.css").toExternalForm();
        //scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        rootLayout.setStyle("-fx-font-size: 1.5em;");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        launch(args);
    }

    private void startup() throws IOException {
        if (welcome == null) {
            WelcomeController welcomeController = new WelcomeController();
            welcome = welcomeController.initWelcome();
        }
        PrimaryStage.setTitle("Velkommen");
        loadParent(welcome);
    }

    private Parent getAgentMenu() {
        PrimaryStage.setTitle("HUBC Forsikring"); //todo: change name when name is ready
        if (agentMenu != null)
            return agentMenu;

        try {
            Parent menu = FXMLLoader.load(getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
            Separator separator = new Separator();
            separator.setPadding(new Insets(0, 0, 5, 0));
            VBox agentMenuContainer = new VBox();
            agentMenuContainer.getChildren().addAll(menu, separator);
            agentMenu = agentMenuContainer;
        } catch (IOException e) {
            System.out.println("failed loading agentmenu.fxml");
            e.printStackTrace();
        }
        return agentMenu;
    }

    private Parent getIncidentPane() {
        if (agentIncident != null)
            return agentIncident;

        AgentIncidentController incidentController = new AgentIncidentController();
        agentIncident  = incidentController.initAgentIncidentView();
        return agentIncident;
    }

    private Parent getInsurancePane() {
        if (agentInsurance != null)
            return agentInsurance;

        AgentInsuranceController insuranceController = new AgentInsuranceController();
        agentInsurance  = insuranceController.initAgentInsuranceView();
        return agentInsurance;
    }

    private Parent getAgenSearchPane() {
        if (agentSearch != null)
            return agentSearch;

        AgentSearchController searchController = new AgentSearchController();
        agentSearch = searchController.initAgentSearch();
        return agentSearch;
    }

    private Parent getAgentPersonPane() {
        if (agentPerson != null)
            return agentPerson;

        PersonController personController = new PersonController();
        agentPerson = personController.initEditPerson();
        return agentPerson;
    }

    private void setListeners() throws IOException {
        changeWindowListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "Customer":
                            loadParent( getAgentPersonPane() );
                            break;
                        case "Insurance":
                            loadParent( getInsurancePane() );
                            break;
                        case "Incident":
                            loadParent( getIncidentPane() );
                            break;
                        case "statistics":
                            AlertWindow.messageDialog("her kommer  snart statistikkskjerm", "statistikkskjerm");
                            break;
                        case "Agent":
                        default:
                            loadParent( getAgenSearchPane() );
                            rootLayout.setTop(getAgentMenu());
                            break;
                    }
                }
        );
    }

    //todo: make us switch screens by not using a hardcoded String
    private void initWindowInvalidationListener() throws IOException {
        changeWindowWindowListener.getObjectProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("windowwindow");
                System.out.println(observable instanceof Stage);
            }
        });
    }

    private void loadParent(Parent scene) {
        fade.setFading(scene);
        rootLayout.setCenter(scene);
        fade.setupFadeout(scene);
    }
}
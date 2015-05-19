package GUI;

/**
 * This is the Main Class for this class. All windows are created here.
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Incident.AgentIncidentController;
import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Person.PersonController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.AgentGUI.Statistics.AgentStatisticController;
import GUI.CurrentObjectListeners.CurrentCustomer;
import GUI.CurrentObjectListeners.CurrentIncident;
import GUI.CurrentObjectListeners.CurrentInsurance;
import GUI.CurrentObjectListeners.WindowChangeListener;
import GUI.CustomerGUI.CustomerLoggedInController;
import GUI.GuiHelper.Fader;
import Register.RegisterCustomer;
import Register.RegisterIncident;
import Register.RegisterInsurance;
import Test.MakePersons;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
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
    public static final CurrentIncident currentIncident = new CurrentIncident();

    public static final RegisterCustomer customerRegister = new RegisterCustomer();
    public static final RegisterInsurance insuranceRegister = new RegisterInsurance();
    public static final RegisterIncident incidentRegister = new RegisterIncident();

    public static final WindowChangeListener changeWindowListener = new WindowChangeListener();
    //"storage" for the different Panes
    private static Parent welcome, agentSearch, agentPerson, agentMenu, agentInsurance, agentIncident, agentStatistics;
    private static Parent CustomerLoggedInPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //generate Customers in new thread -> might be faster when we generate insurance\incidentCases
        Runnable makeMockData = () -> {
                MakePersons.makeCustomers(1000);
                MakePersons.makeDefaultPerson();

                customerRegister.saveRegister();
                insuranceRegister.saveRegister();
                incidentRegister.saveRegister();

            };
        Thread thread = new Thread(makeMockData);
        //thread.start(); //enable this and disable loadRegister for new set of data

        customerRegister.loadRegister();
        insuranceRegister.loadRegister();
        incidentRegister.loadRegister();

        setListeners();
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.setPrefSize(SCREEN.getWidth() / 1.35, SCREEN.getHeight() / 1.5); //todo: change this maybe?
        rootLayout.setStyle("-fx-font-size: 1.5em;");
        loadParent(getLoginPane());

        try {
            String css = StartMain.class.getResource("/GUI/css/CSSValidation.css").toExternalForm();
            //scene.getStylesheets().clear();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            System.out.println("could not load css file: /GUI/css/CSSValidation.css" );
            e.printStackTrace();
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        launch(args);
    }

    private Parent getLoginPane() {
        if (welcome == null) {
            WelcomeController welcomeController = new WelcomeController();
            welcome = welcomeController.initWelcome();
        }
        PrimaryStage.setTitle("Velkommen");
        return welcome;
    }

    private Parent getAgentMenu() {
        PrimaryStage.setTitle("HUBC Forsikring"); //todo: change name when name is ready
        if (agentMenu != null)
            return agentMenu;

        try {
            Parent menu = FXMLLoader.load(getClass().getResource("/GUI/AgentGUI/AgentMenu.fxml"));
            StackPane logout = FXMLLoader.load(getClass().getResource("/GUI/AgentGUI/Logout.fxml"));
            Separator separator = new Separator();
            separator.setPadding(new Insets(0, 0, 5, 0));
            HBox agentMenuContainer = new HBox();
            agentMenuContainer.getChildren().addAll(menu, logout);
            HBox.setHgrow(logout, Priority.ALWAYS);
            VBox separatorcontainer = new VBox();
            separatorcontainer.getChildren().addAll(agentMenuContainer, separator);
            agentMenu = separatorcontainer;

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

    private Parent getAgentStatisticsPane() {
        if (agentStatistics != null)
            return agentStatistics;

        AgentStatisticController statisticController = new AgentStatisticController();
        agentStatistics = statisticController.initStatistics();
        return agentStatistics;
    }

    private Parent getCustomerLoggedInPane() {
        if (CustomerLoggedInPane != null)
            return CustomerLoggedInPane;

        CustomerLoggedInController loggedInController= new CustomerLoggedInController();
        CustomerLoggedInPane = loggedInController.initEditPerson();
        return CustomerLoggedInPane;
    }

    private void setListeners() throws IOException {
        changeWindowListener.getProperty().addListener(
                action -> {
                    switch (changeWindowListener.getString()) {
                        case "welcome":
                            rootLayout.setTop(null);
                            loadParent(getLoginPane());
                            break;
                        case "CustomerLoggedIn":
                            loadParent(getCustomerLoggedInPane());
                            break;
                        case "Customer":
                            loadParent(getAgentPersonPane());
                            break;
                        case "Insurance":
                            loadParent(getInsurancePane());
                            break;
                        case "Incident":
                            loadParent(getIncidentPane());
                            break;
                        case "statistics":
                            loadParent(getAgentStatisticsPane());
                            break;
                        case "Agent":
                        default:
                            loadParent(getAgenSearchPane());
                            rootLayout.setTop(getAgentMenu());
                            break;
                    }
                }
        );
    }

    private void loadParent(Parent scene) {
        fade.setFading(scene);
        rootLayout.setCenter(scene);
        fade.setupFadeout(scene);
    }
}
package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Incident.AgentIncidentController;
import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Person.PersonController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.CurrentObjectListeners.CurrentIncident;
import GUI.CurrentObjectListeners.CurrentInsurance;
import GUI.CurrentObjectListeners.CustomerListener;
import GUI.CurrentObjectListeners.WindowChangeListener;
import GUI.GuiHelper.AlertWindow;
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

    public static final CustomerListener currentCustomer = new CustomerListener();
    public static final CurrentInsurance currentInsurance = new CurrentInsurance();
    public static final CurrentIncident currentIncident = new CurrentIncident();                            //todo: change this? more generic

    public static final RegisterCustomer customerRegister = new RegisterCustomer();
    public static final RegisterInsurance insuranceRegister = new RegisterInsurance();
    public static final RegisterIncident incidentRegister = new RegisterIncident();

    public static final WindowChangeListener changeWindowListener = new WindowChangeListener();
    //"storage" for the different Panes
    private static Parent welcome, agentSearch, agentPerson, agentMenu, agentInsurance, agentIncident, agentStatistics;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //generate Customers in new thread -> might be faster when we generate insurance\incidentCases
        Runnable newthread =
                () -> { MakePersons.makeCustomers(1000);
/*                    try {
                        customerRegister.saveRegister();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                };

        Thread thread = new Thread(newthread);
        thread.start();
        MakePersons.makeDefaultPerson();
        //customerRegister.loadRegister();

        setListeners();
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.setPrefSize(SCREEN.getWidth() / 1.35, SCREEN.getHeight() / 1.5); //todo: change this maybe?

        getLoginPane();
        makePanes();

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

    private void getLoginPane() throws IOException {
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

    @Deprecated //todo: find another way
    private void makePanes(){
        AgentIncidentController incidentController = new AgentIncidentController();
        agentIncident  = incidentController.initAgentIncidentView();
        AgentInsuranceController insuranceController = new AgentInsuranceController();
        agentInsurance  = insuranceController.initAgentInsuranceView();
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
        changeWindowListener.getProperty().addListener(
                action -> {
                    switch (changeWindowListener.getString()) {
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
                            AlertWindow.messageDialog("her kommer  snart statistikkskjerm", "statistikkskjerm");
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
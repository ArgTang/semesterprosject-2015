package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Person.PersonController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import Test.MakePersons;
import Register.CustomerRegister;
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

    public static CustomerRegister customerRegister = new CustomerRegister();

    public static final WindowChangeListener changeWindowListener = new WindowChangeListener();
    public static final WindowWindowListener changeWindowWindowListener = new WindowWindowListener();     //todo: change to this? more generic

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //generate Customers
        MakePersons.makeCustomers(1000);

        initListeners();
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

    public static void main(String[] args)
    {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        launch(args);
    }
    private void startup() throws IOException
    {
        WelcomeController welcomeController = new WelcomeController();
        Parent welcome = welcomeController.initWelcome();
        PrimaryStage.setTitle("Velkommen");
        loadParent(welcome);
    }

    private Parent initAgentMenu()
    {
        Parent agentMenu = null;
        try {
            agentMenu = FXMLLoader.load(getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Separator separator = new Separator();
        separator.setPadding(new Insets(0, 0, 5, 0));
        VBox menu = new VBox();
        menu.getChildren().addAll(agentMenu, separator);

        PrimaryStage.setTitle("HUBC Forsikring"); //todo: change name when name is ready
        return menu;
    }

    private Parent getHomeInsurancePane()
    {
        //todo: maybe move controllers to classVariable
        AgentInsuranceController HomeController = new AgentInsuranceController();
        return HomeController.initAgentInsuranceView();
    }

    private Parent getAgenSearchPane()
    {
        //todo: maybe move controllers to classVariable
        AgentSearchController searchController = new AgentSearchController();
        return searchController.initAgentSearch();
    }

    private Parent getAgentPersonPane()
    {
        //todo: maybe move controllers to classVariable
        PersonController personController = new PersonController();
        return personController.initEditPerson();
    }

    private void initListeners() throws IOException
    {
        changeWindowListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {
                        case "Customer":
                            loadParent( getAgentPersonPane() );
                            break;
                        case "Insurance":
                            loadParent( getHomeInsurancePane() );
                            break;
                        case "Incident":
                            AlertWindow.messageDialog("Her kommer hendelsesvindu", "Hendelsesvindu");
                            break;
                        case "statistics":
                            AlertWindow.messageDialog("her kommer  snart statistikkskjerm", "statistikkskjerm");
                            break;
                        case "Agent":
                        default:
                            loadParent( getAgenSearchPane() );
                            rootLayout.setTop(initAgentMenu());
                            break;
                    }
                }
        );
    }

    //todo: make us switch screens by not using a hardcoded String
    private void initWindowInvalidationListener() throws IOException
    {
        changeWindowWindowListener.getObjectProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("windowwindow");
                System.out.println(observable instanceof Stage);
            }
        });
    }

    private void loadParent(Parent scene)
    {
        fade.setFading(scene);
        rootLayout.setCenter(scene);
        fade.setupFadeout(scene);
    }
}
package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.GuiHelper.AlertWindow;
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

    public static WindowChangeListener changeWindowListener = new WindowChangeListener();
    public static WindowWindowListener changeWindowWindowListener = new WindowWindowListener();     //todo: change to this? more generic

    private BorderPane rootLayout = new BorderPane();
    WelcomeController welcomeController = new WelcomeController();
    private Parent agentMenu;
    private final AgentSearchController agentSearchController = new AgentSearchController();
    private Parent agentSearchPane;
    private AgentInsuranceController agentInsuranceController = new AgentInsuranceController();
    private Parent agentHomeInsurancePane;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initInvalidationListener();
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.setPrefSize(SCREEN.getWidth() / 1.5, SCREEN.getHeight() / 1.5);

        startup();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        launch(args);
    }

    private Parent initAgentMenu() throws IOException {
        Parent AgentMenu = FXMLLoader.load( getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
        Separator separator = new Separator();
        separator.setPadding(new Insets(0, 0, 5, 0));
        VBox menu = new VBox();
        menu.getChildren().addAll(AgentMenu, separator);

        PrimaryStage.setTitle("HUBC Forsikring");
        return menu;
    }

    public void startup() throws IOException
    {
        PrimaryStage.setTitle("Velkommen");
        rootLayout.setCenter(welcomeController.initWelcome());
        agentMenu = initAgentMenu();
        agentSearchPane = agentSearchController.initAgentSearch(PrimaryStage);
        agentHomeInsurancePane = agentInsuranceController.initAgentHouseInsurance(PrimaryStage);
    }

    private void initInvalidationListener() throws IOException
    {
        changeWindowListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch (string.getValue()) {

                        case "Customer":
                                    AlertWindow.messageDialog("her kommer snart kundebehandlingsskjerm", "kunde");
                                    break;
                        case "Insurance":
                                    rootLayout.setCenter(agentHomeInsurancePane);
                                    break;
                        case "Incident":
                                    AlertWindow.messageDialog("her kommer snart ulykkesskjerm","hendelses skjerm");
                                    break;
                        case "statistics":
                                    AlertWindow.messageDialog("her kommer  snart statistikkskjerm", "statistikkskjerm");
                                    break;
                        case "Agent":
                        default:
                                    rootLayout.setTop(agentMenu);
                                    rootLayout.setCenter(agentSearchPane);
                                    break;
                    }
                });
    }

    private void initWindowInvalidationListener() throws IOException
    {
        changeWindowWindowListener.getObjectProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("windowwindow");
                System.out.println( observable instanceof Stage );
            }
        });
    }
}
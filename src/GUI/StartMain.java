package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Search.AgentSearchController;
import GUI.GuiHelper.AlertWindow;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
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
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;


public class StartMain extends Application
{
    private static Dimension SCREEN;
    private Stage PrimaryStage;
    private static final double FADETIME = 0.3; //seconds
    private static FadeTransition fader;

    public static final WindowChangeListener changeWindowListener = new WindowChangeListener();
    public static final WindowWindowListener changeWindowWindowListener = new WindowWindowListener();     //todo: change to this? more generic

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
        rootLayout.setPrefSize(SCREEN.getWidth() / 1.35, SCREEN.getHeight() / 1.5);

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

    private Parent initAgentMenu() throws IOException {
        Parent AgentMenu = FXMLLoader.load(getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
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
        Parent welcome = welcomeController.initWelcome();
        rootLayout.setCenter(welcome);
        setupFadeout(welcome);
        agentMenu = initAgentMenu();
        agentSearchPane = agentSearchController.initAgentSearch(PrimaryStage);
        agentHomeInsurancePane = agentInsuranceController.initAgentDefaultInsurance(PrimaryStage);

    }

    private void initInvalidationListener() throws IOException
    {
        changeWindowListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    switch ( string.getValue() )
                    {

                        case "Customer":
                                    AlertWindow.messageDialog("her kommer snart kundebehandlingsskjerm", "kunde");
                                    break;
                        case "Insurance":
                                    setFading(agentHomeInsurancePane);
                                    break;
                        case "Incident":
                                    AlertWindow.messageDialog("her kommer snart ulykkesskjerm","hendelses skjerm");
                                    break;
                        case "statistics":
                                    AlertWindow.messageDialog("her kommer  snart statistikkskjerm", "statistikkskjerm");
                                    break;
                        case "Agent":
                        default:
                                    setFading(agentSearchPane);
                                    rootLayout.setTop(agentMenu);
                                    break;
                    }
                }
        );
    }

    //todo: make us switch screens by not using a String
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

    private void setFading(Parent scene)
    {
        fader.play();
        StartMain.fader = new FadeTransition(Duration.seconds(FADETIME), scene);
        fader.setInterpolator(Interpolator.EASE_IN);
        fader.setFromValue(0.1);
        fader.setToValue(1.0);
        fader.play();
        rootLayout.setCenter(scene);
        setupFadeout(scene);
    }

    private void setupFadeout(Parent scene)
    {
        StartMain.fader = new FadeTransition(Duration.millis(FADETIME), scene);
        fader.setInterpolator(Interpolator.EASE_OUT);
        fader.setFromValue(1.0);
        fader.setToValue(0.1);
    }
}
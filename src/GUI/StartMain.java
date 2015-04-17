package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Search.AgentSearchController;
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
    AgentSearchController agentSearch = new AgentSearchController(PrimaryStage);
    WelcomeController welcomeController = new WelcomeController();
    AgentInsuranceController agentInsuranceController = new AgentInsuranceController();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.setPrefSize( SCREEN.getWidth()/1.5, SCREEN.getHeight()/1.5);
        //initMenu();
        //startup();
        initAgentScreen();
        //initInsuranceScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        launch(args);
    }

    private void initMenu() throws IOException
    {
        Parent AgentMenu = FXMLLoader.load( getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
        Separator separator = new Separator();
        separator.setPadding( new Insets(0, 0, 5, 0));
        VBox menu = new VBox();
        menu.getChildren().addAll(AgentMenu, separator);

        PrimaryStage.setTitle("HUBC Forsikring");
        rootLayout.setTop(menu);
    }

    public void initAgentScreen() throws IOException
    {
        rootLayout.setCenter( agentSearch.initAgentSearch() );
    }

    public void startup() throws IOException
    {
        PrimaryStage.setTitle("Velkommen");
        rootLayout.setCenter( welcomeController.initWelcome(this) );
    }

    private void initInsuranceScreen() throws IOException
    {
       rootLayout.setCenter( agentInsuranceController.initAgentHouseInsurance(PrimaryStage) );
    }
}
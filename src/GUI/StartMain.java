package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.AgentInsuranceController;
import GUI.AgentGUI.AgentSearchController;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class StartMain extends Application
{

    private Stage PrimaryStage;
    private BorderPane rootLayout = new BorderPane();
    AgentSearchController agentSearch = new AgentSearchController();
    WelcomeController welcomeController = new WelcomeController();
    AgentInsuranceController agentInsuranceController = new AgentInsuranceController();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);
        
        initMenu();
        //startup();
        //initAgentScreen();
        initInsuranceScreen();


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
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
        rootLayout.setCenter(agentSearch.initAgentSearch(PrimaryStage));
    }

    public void startup() throws IOException
    {
        PrimaryStage.setTitle("Velkommen");
        rootLayout.setCenter( welcomeController.initWelcome(this) );
    }

    private void initInsuranceScreen() throws IOException
    {
       rootLayout.setCenter( agentInsuranceController.initAgentInsurance(PrimaryStage) );
    }

}
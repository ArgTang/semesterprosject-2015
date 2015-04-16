package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.AgentSearchController;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
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

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.PrimaryStage = primaryStage;
        Scene scene = new Scene(rootLayout);

        startup();
        //initAgentScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void initAgentScreen() throws IOException {
        Parent AgentMenu = FXMLLoader.load( getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
        Separator separator = new Separator();
        VBox menu = new VBox();
        menu.getChildren().addAll(AgentMenu, separator);

        PrimaryStage.setTitle("HUBC Forsikring");
        rootLayout.setTop(menu);
        rootLayout.setCenter(agentSearch.initAgentSearch(PrimaryStage));
    }

    public void startup() throws IOException
    {
        PrimaryStage.setTitle("Velkommen");
        rootLayout.setCenter( welcomeController.initWelcome(this) );
    }

}
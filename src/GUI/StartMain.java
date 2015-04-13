package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import GUI.AgentGUI.AgentSearchController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//testing git integrations once

public class StartMain extends Application {

    private Stage PrimaryStage;
    private BorderPane rootLayout = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        AgentSearchController agentSearch = new AgentSearchController();

        this.PrimaryStage = primaryStage;

        Scene scene = new Scene(rootLayout);
        rootLayout.setTop(initAgentMenu());
        rootLayout.setCenter( agentSearch.initAgentSearch(PrimaryStage) );

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Parent initAgentMenu() throws IOException {
        Parent AgentMenu = FXMLLoader.load( getClass().getResource("\\AgentGUI\\AgentMenu.fxml"));
        Separator separator = new Separator();
        VBox body = new VBox();
        body.getChildren().addAll(AgentMenu, separator);
        return AgentMenu;
    }
}
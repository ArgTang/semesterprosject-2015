package GUI;

/**
 * Created by steinar on 29.03.2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

//testing git integrations once

public class StartMain extends Application {

    private Stage PrimaryStage;
    private BorderPane rootLayout = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.PrimaryStage = primaryStage;

        Scene scene = new Scene(rootLayout);
        rootLayout.setTop(initMenu());
        rootLayout.setCenter(initAgentSearch());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private Parent initMenu() throws IOException {
        Parent AgentMenu = FXMLLoader.load(getClass().getResource("\\GUIComponents\\Meny.fxml"));
        return AgentMenu;
    }

    private HBox initAgentSearch() throws IOException {

            Parent search = FXMLLoader.load(getClass().getResource("\\GUIComponents\\Søk.fxml"));
            Parent result = FXMLLoader.load(getClass().getResource("\\GUIComponents\\Result.fxml"));
            HBox body = new HBox();
            body.getChildren().addAll(search, result);

            return body;
    }
}
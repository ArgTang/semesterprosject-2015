package GUI.StartGUI;

/**
 * Created by steinar on 29.03.2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//testing git integrations once

public class startMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 200, 175));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

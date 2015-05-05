package GUI;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import GUI.GuiHelper.RegEX;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;


/**
 * Created by steinar on 13.04.2015.
 */
public final class WelcomeController
{
    private Fader fader = new Fader();
    BorderPane root = new BorderPane();

    private void login()
    {
        //todo: how to proceed from here
        StartMain.changeWindowListener.setPropertyString("Agent");
        //StartMain.changeWindowWindowListener.setPropertyObject(this.getClass()); todo: crash!
    }

    public Parent initWelcome() throws IOException
    {
        root.setCenter(makeWelcome());
        loadParent(makelogin());
        return root;
    }

    private Parent makeWelcome()
    {

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);

        Text welcome = new Text("Velkommen til oss!");
        welcome.setEffect(ds);
        welcome.setStyle("-fx-font-size: 5em;");
        StackPane pane = new StackPane();

        pane.setAlignment(Pos.CENTER_LEFT);
        StackPane.setMargin(welcome, new Insets(0, 0, 100, 200));

        pane.getChildren().addAll(welcome);

        return pane;
    }

    private Parent makelogin()
    {
        GridPane gridPane = new GridPane();
        setGridOptions(gridPane);

        Label userName = new Label("Brukernavn");
        Label password = new Label("Passord");

        TextField userNameInput = new TextField();
        PasswordField passwordInput = new PasswordField();

        Hyperlink forgotPassword = new Hyperlink("Glemt passord?");
        Button login = new Button("Login");

        login.setOnAction(event -> login()); //this needs to check for passwords
        forgotPassword.setOnAction(event -> loadParent(makeDialog()));

        RegEX.addCSSTextValidation(userNameInput, RegEX.isAllChars()); //usernameRegex?
        RegEX.addCSSTextValidation(passwordInput, RegEX.isPassword());

        gridPane.add(userName, 0, 0);
        gridPane.add(userNameInput, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(passwordInput, 1, 1);
        gridPane.add(forgotPassword, 0, 2);
        gridPane.add(login, 1, 2);

        return gridPane;
    }

    private GridPane makeDialog() //http://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    {
        StringProperty message = new SimpleStringProperty();
        Label userName = new Label("Fyll inn brukernavn");
        Label password = new Label("Velg passord");
        Label confirmPassword = new Label("Gjenta Passord");
        Label faultmessage = new Label();
        faultmessage.textProperty().bind(message);
        faultmessage.setTextFill(Color.RED);

        TextField userNameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        PasswordField confirmPasswordInput = new PasswordField();

        RegEX.addCSSTextValidation(userNameInput, RegEX.isAllChars());
        RegEX.addCSSTextValidation(passwordInput, RegEX.isPassword());
        RegEX.addCSSTextValidation(confirmPasswordInput, RegEX.isPassword());

        GridPane gridPane = new GridPane();
        setGridOptions(gridPane);

        gridPane.add(faultmessage, 0, 0);
        gridPane.add(userName, 0, 1);
        gridPane.add(userNameInput, 1, 1);
        gridPane.add(password, 0, 2);
        gridPane.add(passwordInput, 1, 2);
        gridPane.add(confirmPassword, 0, 3);
        gridPane.add(confirmPasswordInput, 1, 3);

        HBox hbox = new HBox();
        hbox.setSpacing(5);
        Button changePassword = new Button("Bytt passord");
        changePassword.setOnAction(
                event ->
                {
                    if (RegEX.isLetters().test( userNameInput.getText() )) //TODO: get user from register
                    {
                        message.setValue("Finner ikke bruker");
                    }
                    else if (!passwordInput.getText().equals( confirmPasswordInput.getText() ))
                        message.setValue("Passordene må være like!");

                    AlertWindow.messageDialog("change password", "change password");
                });
        Button cancel = new Button("Gå tilbake");
        cancel.setOnAction(event -> root.setRight(fader.setFading(makelogin())));
        hbox.getChildren().addAll(changePassword, cancel);
        gridPane.add(hbox, 1, 4);

        return gridPane;
    }

    private void setGridOptions(GridPane gridPane)
    {
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER_LEFT);

        gridPane.setPadding(new Insets(20, 80, 20, 20));

        ColumnConstraints left = new ColumnConstraints();
        left.setHalignment(HPos.LEFT);
        ColumnConstraints right = new ColumnConstraints();
        right.setHalignment(HPos.RIGHT);
        gridPane.getColumnConstraints().addAll(left, right);
    }

    private void loadParent(Parent scene)
    {
        fader.setFading(scene);
        root.setRight(scene); //set scene
        fader.setupFadeout(scene);
    }
}

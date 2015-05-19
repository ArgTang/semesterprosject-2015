package GUI;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.Fader;
import Person.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.*;

/**
 * This class constructs and controls login and forgot passwordScreen
 * Created by steinar on 13.04.2015.
 */
public final class WelcomeController
{
    private Fader fader = new Fader();
    private BorderPane root = new BorderPane();
    private Parent welcomePane, loginPane, forgotpasswordPane;

    public Parent initWelcome() {
        makeWelcome();
        makelogin();
        root.setCenter(welcomePane);
        loadParent(loginPane);
        return root;
    }

    private void makeWelcome() {
        if (welcomePane != null)
            return;

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);

        Text welcome = new Text("Velkommen til oss!");
        welcome.setEffect(ds);
        welcome.setStyle("-fx-font-size: 5em;");
        Image logo = new Image( getClass().getResourceAsStream("/GUI/png/logo.png"));
        ImageView image = new ImageView(logo);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(image, welcome);
        vBox.alignmentProperty().set(Pos.CENTER);
        vBox.setPadding(new Insets(0, 0, 80, 0));

        welcomePane = vBox;
    }

    private void makelogin() {
        if (loginPane != null)
            return;

        GridPane gridPane = new GridPane();
        setGridOptions(gridPane);

        Label userName = new Label("Brukernavn");
        Label password = new Label("Passord");

        TextField userNameInput = new TextField();
        PasswordField passwordInput = new PasswordField();

        Hyperlink forgotPassword = new Hyperlink("Glemt passord?");
        Button login = new Button("Login");

        addCSSTextValidation(userNameInput, isAllChars());
        addCSSTextValidation(passwordInput, isPassword());
        login.setOnAction(event -> {
            if (isNumberWithLength(10).test(userNameInput.getText())) {
                Customer customer = customerRegister.get( userNameInput.getText());
                if ( customer != null) {
                    if (customer.getPassword().equals(passwordInput.getText())) {
                        currentCustomer.set(customer);
                        changeWindowListener.setString("CustomerLoggedIn");
                        return;
                    }
                }
            }
            if ( userNameInput.getText().equalsIgnoreCase(passwordInput.getText())) {
                changeWindowListener.setString("Agent"); //goto Agentscreen
                return;
            }

            AlertWindow.messageDialog("Kunne ikke finne brukernavn og passord i databasen, prøv igjen", "Fant ikke brukernavn/passord");
        });

        forgotPassword.setOnAction( event -> { makeChangePasswordDialog();
                                                loadParent(forgotpasswordPane);});

        gridPane.add(userName, 0, 0);
        gridPane.add(userNameInput, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(passwordInput, 1, 1);
        gridPane.add(forgotPassword, 0, 2);
        gridPane.add(login, 1, 2);

        loginPane = gridPane;
    }

    //http://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    private void makeChangePasswordDialog() {
        if (forgotpasswordPane != null)
            return;

        StringProperty message = new SimpleStringProperty();
        Label userName = new Label("Brukernavn");
        Label password = new Label("Velg passord");
        Label confirmPassword = new Label("Gjenta Passord");
        Label faultmessage = new Label();
        faultmessage.textProperty().bind(message);
        faultmessage.setTextFill(Color.RED);

        TextField userNameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        PasswordField confirmPasswordInput = new PasswordField();

        addCSSTextValidation(userNameInput, isNumberWithLength(11));
        addCSSTextValidation(passwordInput, isPassword());
        addCSSTextValidation(confirmPasswordInput, isPassword());

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
                    Customer result = null;
                    if (isLetters().test( userNameInput.getText())) {
                        String customerID = userNameInput.getText();
                        result = customerRegister.get(customerID);

                        if (result == null) {
                            message.setValue("Finner ikke bruker med dette brukernavnet");
                            return;
                        }

                    } else if (!passwordInput.getText().equals( confirmPasswordInput.getText() ))
                        message.setValue("Passordene må være like!");

                    if (result != null) {
                        result.setPassword(passwordInput.getText());
                        AlertWindow.messageDialog("Du kan nå logge inn med ditt nye passord", "Passord forandret");
                        root.setRight(fader.setFading(loginPane));
                    }
                });
        Button cancel = new Button("Gå tilbake");
        cancel.setOnAction(event -> root.setRight(fader.setFading(loginPane)));
        hbox.getChildren().addAll(changePassword, cancel);
        gridPane.add(hbox, 1, 4);

        forgotpasswordPane = gridPane;
    }

    private void setGridOptions(GridPane gridPane) {
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

    private void loadParent(Parent scene) {
        fader.setFading(scene);
        root.setRight(scene); //set scene
        fader.setupFadeout(scene);
    }
}
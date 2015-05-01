package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonPublicGUIMethods;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.Optional;

import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * Created by steinar on 15.04.2015.
 */
public final class InsuranceConfirmModuleController implements CommonPublicGUIMethods
{
    @FXML
    private Label yearlyPremium;
    @FXML
    private Label totalFee;
    @FXML
    private Label paymentEachTermin;
    @FXML
    private Pane helperPane;
    @FXML
    private Label bonusLabel;
    @FXML
    private Label bonusValue;

    @FXML
    private TextArea description;

    @FXML
    private Button endThis;
    @FXML
    private Button clearScheme;
    @FXML
    private Button insuranceOffer;
    @FXML
    private Button confirmInsurance;

    public static BooleanProperty confirmOrderButton = new SimpleBooleanProperty(false);
    public static BooleanProperty insuranceOfferButton = new SimpleBooleanProperty(false);
    public static IntegerProperty yearlyPremiumLabel = new SimpleIntegerProperty();
    public static IntegerProperty totalFeeLabel = new SimpleIntegerProperty();
    public static IntegerProperty paymentEachTerminLabel = new SimpleIntegerProperty();
    public static StringProperty bonusValueLabel = new SimpleStringProperty();

    @FXML
    private void initialize() {
        yearlyPremium.textProperty().bind(yearlyPremiumLabel.asString());
        totalFee.textProperty().bind(totalFeeLabel.asString());
        paymentEachTermin.textProperty().bind(paymentEachTerminLabel.asString());
        bonusLabel.setVisible(false);

        helperPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); //todo: easier way to do this?
        setListeners();
    }

    private void setListeners()
    {
        BooleanBinding insuranceIsNotChosen = currentInsurance.getInsuranceProperty().isNull();
        endThis.disableProperty().bind(insuranceIsNotChosen);

        confirmOrderButton.bind( confirmInsurance.pressedProperty() );
        confirmInsurance.disableProperty().bind( currentCustomer.getPersonProperty().isNull() );
        insuranceOffer.disableProperty().bind( currentCustomer.getPersonProperty().isNull() );

    }

    @FXML
    private void endInsurance() {
        Optional<LocalDate> result = makeDialog();
        if ( result.isPresent() )
            AlertWindow.confirmDialog(result.toString(), "svar");
        else
            AlertWindow.confirmDialog("tomt resultat", "svar");
    }

    @FXML
    @Override
    public void clearFields()
    {
        if( AlertWindow.confirmDialog("Vil du tømme Skjema?", "tøm skjema") )
        {
            currentInsurance.reset();
            description.setText("");

            Runnable newthread = () -> {
                AgentInsuranceController.emptyscreen.setValue(true);
                AgentInsuranceController.emptyscreen.setValue(false);};
            Thread thread = new Thread(newthread);
            thread.start();
        }
    }

    @Override
    public void addCSSValidation() {
        //no textfields here
    }

    @FXML
    private void saveInsuranceOffer()
    {
        AlertWindow.messageDialog("lagret Tilbud", "lagret tilbud");
    }

    @FXML
    private void confirmInsurance() {

        AlertWindow.messageDialog("Opprettet Forsikring","Opprettet Forsikring");
    }

    public static void clearLabel() {
        yearlyPremiumLabel.setValue(0);
        paymentEachTerminLabel.setValue(0);
        totalFeeLabel.setValue(0);
    }

    private Optional<LocalDate> makeDialog() //todo: move this to GUIHelper.AlertWindow? http://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    {
        //todo: disable buttn when no insurance is chosen
        Dialog dialog = new Dialog();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        //dialog.setGraphic(alert.getGraphic().getClip()); //InvocationTargetException //todo: find a way to get warning icon and add to this dialog
        //Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
//        Image icon = alertStage.getIcons().stream().findFirst().get(); //this do crash

        //Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        //stage.getIcons().add( icon );

        dialog.setTitle("Avslutt avtale");
        dialog.setHeaderText("Er du sikker på at denne avtalen skal avsluttes?");

        Label label = new Label("Dato for avslutting av avtale: ");
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setPrefWidth(Region.USE_COMPUTED_SIZE);
        HBox dateContainer= new HBox();
        dateContainer.setSpacing(5);
        dateContainer.setPadding(new Insets(20, 20, 20, 20));
        dateContainer.getChildren().addAll(label, datePicker);
        dialog.getDialogPane().setContent(dateContainer);

        ButtonType ok = new ButtonType("Ja jeg er sikker", OK_DONE);
        ButtonType cancel = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ok, cancel );

        dialog.setResultConverter(button -> {  //todo: compact this?
                                            if (button == ok)
                                                return Optional.of( datePicker.getValue() );
                                            else
                                                return Optional.empty(); });

        return dialog.showAndWait();
    }
}
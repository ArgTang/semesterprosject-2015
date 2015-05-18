package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import Insurance.Insurance;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.Optional;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * Created by steinar on 15.04.2015.
 */
public final class InsuranceConfirmModuleController extends CommonGUIMethods
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

    public static final BooleanProperty confirmOrderButton = new SimpleBooleanProperty(false);
    public static final BooleanProperty insuranceOfferButton = new SimpleBooleanProperty(false);
    public static final IntegerProperty yearlyPremiumLabel = new SimpleIntegerProperty();
    public static final IntegerProperty totalFeeLabel = new SimpleIntegerProperty();
    public static final IntegerProperty paymentEachTerminLabel = new SimpleIntegerProperty();
    public static final StringProperty bonusValueLabel = new SimpleStringProperty();

    @FXML
    @Override
    protected void initialize() {
        description.setPromptText("beskrivelse av forsikrings objektet");
        description.setVisible(false);

        yearlyPremium.textProperty().bind(yearlyPremiumLabel.asString());
        totalFee.textProperty().bind(totalFeeLabel.asString());
        paymentEachTermin.textProperty().bind(paymentEachTerminLabel.asString());
        helperPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); //todo: easier way to do this

        insuranceOffer.setVisible(false); //todo: delete after offer is implemented

        setListeners();
        clearLabel();
    }

    @Override
    protected void setListeners() {
        BooleanProperty isNotNull = new SimpleBooleanProperty( currentInsurance.getProperty().isNotNull().get() && currentInsurance.get().getEndDate()== null);
        endThis.disableProperty().bind(isNotNull.not());

        confirmInsurance.disableProperty().bind(currentCustomer.getProperty().isNull().and(currentInsurance.getProperty().isNull()));
        confirmOrderButton.bind(confirmInsurance.pressedProperty());
        //insuranceOffer.disableProperty().bind(currentCustomer.isNull()); //todo: when offer is implemented

        bonusValue.textProperty().bind(bonusValueLabel);
        bonusValueLabel.addListener(observable -> {
            if ( bonusValueLabel.getValue().equalsIgnoreCase("0") ) {
                bonusLabel.setVisible(false);
                bonusValue.setVisible(false);
            } else {
                bonusLabel.setVisible(true);
                bonusValue.setVisible(true);
            }
        });
    }

    @FXML
    private void endInsurance() {
        Optional<LocalDate> result = makeDialog();
        if ( result.isPresent() ) {
            Insurance insurance = currentInsurance.get();
            insurance.endInsuranse(result.get());
            currentInsurance.set(insurance);
        } else
            return; //cancel: do nothing
    }

    @FXML
    @Override
    public void clearFields() {
        if( AlertWindow.confirmDialog("Vil du tømme Skjema?", "tøm skjema") ) {
            currentInsurance.reset();
            description.setText("");
            clearLabel();

            Runnable newthread = () -> {
                emptyscreenButton.setValue(true);
                emptyscreenButton.setValue(false);};
            Thread thread = new Thread(newthread);
            thread.start();
        }
    }

    @FXML
    private void saveInsuranceOffer()
    {
        AlertWindow.messageDialog("lagret Tilbud", "lagret tilbud");
    }

    @FXML
    private void confirmInsurance() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have a need for this function");
    }

    public static void clearLabel() {
        yearlyPremiumLabel.setValue(0);
        paymentEachTerminLabel.setValue(0);
        totalFeeLabel.setValue(0);
        bonusValueLabel.setValue("0");
    }

    private Optional<LocalDate> makeDialog() {
        Dialog dialog = new Dialog();
        //Alert alert = new Alert(Alert.AlertType.WARNING);
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

        ButtonType ok = new ButtonType("Ja, jeg er sikker", OK_DONE);
        ButtonType cancel = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(ok, cancel );

        dialog.setResultConverter(button -> {  //todo: compact this?
                                            if (button == ok)
                                                return Optional.of( datePicker.getValue() );
                                            else
                                                return Optional.empty(); });

        return dialog.showAndWait();
    }

    @Override
    public void addCSSValidation() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have any textfields");
    }
    @Override
    protected boolean checkValidation() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have a need for this function");
    }
}
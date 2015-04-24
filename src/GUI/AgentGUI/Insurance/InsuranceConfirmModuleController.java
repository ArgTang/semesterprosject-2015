package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.StartMain;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * Created by steinar on 15.04.2015.
 */
public final class InsuranceConfirmModuleController implements CommonGUIMethods
{
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


    @FXML
    private void initialize()
    {
        BooleanBinding insuranceIsNotChosen = StartMain.currentInsurance.getPersonProperty().isNull();
        endThis.disableProperty().bind(insuranceIsNotChosen);
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
            StartMain.currentInsurance.reset();
            description.setText("");
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
    private void confirmInsurance()
    {
        AlertWindow.messageDialog("Opprettet Forsikring","Opprettet Forsikring");
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

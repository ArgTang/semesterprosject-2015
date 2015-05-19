package GUI.AgentGUI.Insurance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;
import static GUI.StartMain.currentInsurance;

/**
 * Created by steinar on 17.04.2015.
 */
public final class InsuranceChooserModuleController
{
    @FXML
    private ListView<HBox> insuranceChooser;

    private ObservableList<HBox> insuranceLogo = FXCollections.observableArrayList();
    private List<String> insuranceTypes = new ArrayList();

    @FXML
    private void initialize() {
        insuranceChooser.setItems(insuranceLogo);
        insuranceChooser.getSelectionModel().selectFirst();
        insuranceChooser.requestFocus(); //todo: find a way to set listwiev in focus

        insuranceTypes.add("Hus");
        insuranceTypes.add("Bil");
        insuranceTypes.add("Båt");
        insuranceTypes.add("Reise");
        insuranceTypes.add("Innbo");
        //insuranceTypes.addAll("Hus", "Bil", "Båt", "Reise", "Innbo");

        HBox hus = new HBox();
        ImageView image = new ImageView(new Image( getClass().getResourceAsStream("/GUI/png/house112.png")));
        Text hustext = new Text();
        hustext.setText("Hus");
        hus.getChildren().addAll(image, hustext);

        HBox bil = new HBox();
        image = new ImageView(new Image( getClass().getResourceAsStream("/GUI/png/car189.png")));
        Text biltext = new Text();
        biltext.setText("Bil");
        bil.getChildren().addAll(image, biltext);

        HBox båt = new HBox();
        image = new ImageView(new Image( getClass().getResourceAsStream("/GUI/png/drive7.png")));
        Text båttext = new Text();
        båttext.setText("Båt");
        båt.getChildren().addAll(image, båttext);

        HBox reise = new HBox();
        image = new ImageView(new Image( getClass().getResourceAsStream("/GUI/png/travel25.png")));
        Text reisetext = new Text();
        reisetext.setText("Reise");
        reise.getChildren().addAll(image, reisetext);

        HBox innbo = new HBox();
        image = new ImageView(new Image( getClass().getResourceAsStream("/GUI/png/bedroom10.png")));
        Text innbotext = new Text();
        innbotext.setText("Innbo");
        innbo.getChildren().addAll(image, innbotext);

        setAlignment(hus, bil, båt, reise, innbo);
        insuranceLogo.addAll(hus, bil, båt, reise, innbo);


        insuranceChooser.getSelectionModel().selectedItemProperty().addListener(
                event -> {
                    InsuranceConfirmModuleController.clearLabel();
                    String choice = insuranceTypes.get(insuranceChooser.getSelectionModel().getSelectedIndex());
                    insuranceChoiceListener.setString(choice);
                });

        currentInsurance.getProperty().addListener(listener -> {
            String currentchoice = "";
            String newValue = getNameOfInsurance(currentInsurance.get());
            if (newValue.equals(currentchoice))
                return;

            int index = insuranceTypes.indexOf(newValue);
            insuranceChooser.getSelectionModel().clearAndSelect(index);
        });
    }

    private void setAlignment(HBox... hBoxes) {
        for (HBox hBox: hBoxes) {
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setSpacing(5);
        }

    }
}
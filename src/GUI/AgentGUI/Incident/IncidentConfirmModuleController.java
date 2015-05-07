package GUI.AgentGUI.Incident;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static GUI.AgentGUI.Incident.AgentIncidentController.emptyscreenButton;
import static GUI.StartMain.currentIncident;

/**
 * Created by steinar on 15.04.2015.
 */
public final class IncidentConfirmModuleController extends CommonGUIMethods
{
    @FXML
    private TextArea description;

    @FXML
    private Button clearScheme;
    @FXML
    private Button confirmIncident;

    @FXML
    private TextField filenames;
    @FXML
    private Button addFiles;
    @FXML
    private Button openFolder;


    public static BooleanProperty confirmIncidentButton = new SimpleBooleanProperty(false);

    @FXML
    @Override
    protected void initialize() {
        description.setPromptText("Beskrivelse av Hendelsen");

        filenames.setText("Husk å legg til bilder, videoer og andre dokumenter!");
        filenames.setDisable(true);
        filenames.setEditable(false);
        filenames.setFocusTraversable(false);
        filenames.setStyle("-fx-background-insets:0, 0, 0, 0;");
        setListeners();
    }

    @Override
    protected void setListeners() {
        openFolder.pressedProperty().addListener(listener -> { if (openFolder.pressedProperty().get()) openFolder("dfsa");});
        addFiles.pressedProperty().addListener( listener -> { if ( addFiles.pressedProperty().get()) showFileAdderDialog();});

        clearScheme.pressedProperty().addListener(listener -> {
            if (clearScheme.pressedProperty().get()) clearScheme();});
        confirmIncident.disableProperty().bind(currentIncident.getIncidentProperty().isNull());
        confirmIncident.onActionProperty().addListener(listener -> confirmIncident());
        confirmIncidentButton.bind(confirmIncident.pressedProperty());

        /*confirmIncident.pressedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    confirmIncidentButton.set(true);
                    confirmIncidentButton.set(false);
                }
            }
        });*/
    }

    private void showFileAdderDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Legg til filer");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("All Files", "*.*"));

        List<File> returnedFiles = fileChooser.showOpenMultipleDialog(null);

        filenames.setDisable(false);
        filenames.setText("");
        //convert java io -> nio library
        if (!returnedFiles.isEmpty()) {
            List<Path> selectedFiles = new ArrayList<>();
            returnedFiles.stream()
                         .map(File::toPath)
                         .forEach(selectedFiles::add);

            copyToNewDir(selectedFiles);
        }
    }

    @FXML
    public void clearScheme() {
        if (AlertWindow.confirmDialog("Vil du tømme Skjema?", "tøm skjema")) {
            currentIncident.reset();
            clearFields();

            Runnable newthread = () -> {
                emptyscreenButton.setValue(true);
                emptyscreenButton.setValue(false);
            };
            Thread thread = new Thread(newthread);
            thread.start();
        }
    }

    @Override
    public void clearFields() {
        description.setText("");
        filenames.setText("");
    }

    @FXML
    private void confirmIncident() {
        AlertWindow.messageDialog("Opprettet Hendelse", "Opprettet Hendelse");
    }

    private void openFolder(String path) {
        //todo:clean up method
        createDir();
        //http://stackoverflow.com/questions/3153337/how-do-i-get-my-current-working-directory-in-java
        File currentDirectory = new File(new File(".").getAbsolutePath());
        //path = currentDirectory.getAbsolutePath();

        //stackoverflow.com/questions/9134096/java-open-folder-on-button-click
        //File folder = new File(path); // path to the directory to be opened
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            try {
                desktop.open(currentDirectory);
            } catch (IOException exception) {
                System.out.println("Error opening folder at: " + path);
                exception.printStackTrace();
            }
        }
    }

    private Path createDir() {
        //todo: get incidentID
        Path directory = Paths.get("." + "\\IncidetReports");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory;
    }

    private void copyToNewDir(List<Path> files) {
        //get dir of current Incident
        Path destination = createDir();

        try {
            for (Path source : files) {
                Files.copy(source, destination.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                filenames.appendText(source.getFileName() + " ");
            }
        } catch (IOException e) {
            System.out.println("failed copy file");
            e.printStackTrace();
        }
    }

    @Override
    public void addCSSValidation() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have any textfields");
    }
    @Override
    protected void makeInsurance() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have a need for this function");
    }
    @Override
    protected boolean checkValidation() {
        throw new NoSuchMethodError("InsuranceConfirmModule dont have a need for this function");
    }
}
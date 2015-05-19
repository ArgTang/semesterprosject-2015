package GUI.AgentGUI.Incident;

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import Incident.Incident;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import static GUI.StartMain.*;

/**
 * Created by steinar on 15.04.2015.
 */
public final class IncidentConfirmModuleController extends CommonGUIMethods
{
    @FXML
    private TextArea descriptionInput;

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

    public static final BooleanProperty confirmIncidentButton = new SimpleBooleanProperty(false);
    public static final IntegerProperty saveFilesToIncidentID = new SimpleIntegerProperty(0);
    private List<Path> uploadedFiles = new ArrayList<>();
    private Path currentDir;
    private final Path tempDir = createDir("temp");

    public static String description = "";

    @FXML
    @Override
    protected void initialize() {
        descriptionInput.setPromptText("Beskrivelse av Hendelsen");
        filenames.setEditable(false);
        filenames.setFocusTraversable(false);
        //http://stackoverflow.com/questions/6092500/how-do-i-remove-the-default-border-glow-of-a-javafx-button-when-selected?lq=1
        filenames.setStyle("-fx-background-insets:0, 0, 0, 0;");

        clearFields();
        setListeners();
    }

    @Override
    protected void setListeners() {
        openFolder.disableProperty().bind( filenames.disableProperty() );
        openFolder.pressedProperty().addListener(listener -> { if (openFolder.pressedProperty().get()) openCurrentDir();});
        addFiles.disableProperty().bind(currentInsurance.getProperty().isNull());
        addFiles.pressedProperty().addListener( listener -> { if (addFiles.pressedProperty().get()) showFileAdderDialog();});

        clearScheme.pressedProperty().addListener(listener -> {
            if (clearScheme.pressedProperty().get()) {
                clearScheme();
                emptyscreenButton.set(true);
                emptyscreenButton.set(false);
            }
        });

        confirmIncident.disableProperty().bind( currentCustomer.getProperty().isNull().and(currentIncident.getProperty().isNull()));
        confirmIncident.pressedProperty().addListener( listener -> {
            if (confirmIncident.pressedProperty().get()) {
                description = descriptionInput.getText();
                confirmIncidentButton.set(true);
                confirmIncidentButton.set(false);
            }
        });

        saveFilesToIncidentID.addListener(listener -> {
            Incident incident = incidentRegister.get(saveFilesToIncidentID.get());
            saveFiles(incident);
        });

        currentIncident.getProperty().addListener(listener -> {
            if (currentIncident.getProperty().isNotNull().get()) {
                descriptionInput.setText( currentIncident.get().getIncidentDescription());
            }
        });
    }

    private void showFileAdderDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Legg til filer");
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Alle Filtyper", "*.*"));

        List<File> returnedFiles = fileChooser.showOpenMultipleDialog(null);

        filenames.setDisable(false);
        filenames.setText("");
        //convert java io -> nio library
        if (!returnedFiles.isEmpty()) {
            returnedFiles.stream()
                         .map(File::toPath)
                         .forEach(this.uploadedFiles::add);

            uploadedFiles.stream()
                         .forEach(file -> filenames.appendText(file.getFileName() + " "));
        }

        currentDir = tempDir;
        copyToNewDir(tempDir);
    }

    public void saveFiles(Incident incident) {
        createDir(String.valueOf(incident.getIncidentID())); //currentdir =
        copyToNewDir(currentDir);

        incident.setFiles(uploadedFiles);
        incidentRegister.update(incident);
    }

    private void openCurrentDir() {
        if (uploadedFiles.isEmpty() || currentDir == null)
            return;

        //todo:clean up method
        //http://stackoverflow.com/questions/3153337/how-do-i-get-my-current-working-directory-in-java
        File dir = new File(currentDir.toString());

        //stackoverflow.com/questions/9134096/java-open-folder-on-button-click
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            try {
                desktop.open(dir);
            } catch (IOException exception) {
                System.out.println("Error opening folder at: " + dir.toString());
                exception.printStackTrace();
            }
        }
    }

    private Path createDir(String path) {
        Path directory = Paths.get(".\\IncidentReports\\IncidentID_" + path);
        if (!Files.exists(Paths.get(".\\IncidentReports")))
            try {
                Files.createDirectory(Paths.get(".\\IncidentReports"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
                currentDir = directory;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory;
    }

    private void copyToNewDir(Path destination) {
        try {
            for (Path source : uploadedFiles)
                Files.copy(source, destination.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Failed copy file");
            e.printStackTrace();
        }
        uploadedFiles.clear();
        try {
            Files.list(destination).forEach(uploadedFiles::add);
        } catch (IOException e) {
            System.out.println("Failed refreshing path for current files after moving");
            e.printStackTrace();
        }
    }

    @FXML
    private void clearScheme() {
        if (AlertWindow.confirmDialog("Vil du tømme Skjema?", "tøm skjema")) {
            currentIncident.reset();
            clearFields();

            Runnable clear = () -> {
                emptyscreenButton.setValue(true);
                emptyscreenButton.setValue(false);
            };
            Thread thread = new Thread(clear);
            thread.start();
        }
    }

    @Override
    public void clearFields() {
        filenames.setText("Husk å legg til bilder, videoer og andre dokumenter!");
        filenames.setDisable(true);
        try {
            Files.list(tempDir)
                 .forEach(path -> {
                     try { Files.delete(path);
                     } catch (IOException e) {
                         System.out.println("failed deleting tempfile: " + path);
                         e.printStackTrace(); }
                 });
        } catch (IOException e) {
            System.out.println("failed opening tempPath: " + tempDir);
            e.printStackTrace();
        }
        uploadedFiles.clear();
        descriptionInput.setText("");
        description = "";
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
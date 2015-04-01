package GUI.GuiHelper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by steinar on 19.03.2015.
 *
 * Class for alertwindows
 * Add more methods as you see fit
 * Refer to http://code.makery.ch/blog/javafx-dialogs-official/ for how these work
 *  - All functions return a boolean
 *  - Except multichoice
 */
public final class ModalWindow {

    public static boolean messageDialog( String message, String title ) {
        Alert.AlertType aType = Alert.AlertType.INFORMATION;
        return alert(message, title, aType);
    }

    public static boolean errorDialog( String message, String title ) {
        Alert.AlertType aType = Alert.AlertType.ERROR;
        return alert(message, title, aType);
    }

    public static boolean waringDialog( String message, String title ) {
        Alert.AlertType aType = Alert.AlertType.WARNING;
        return alert(message, title, aType);
    }

    public static boolean confirmDialog( String message, String title ) {
        Alert.AlertType aType = Alert.AlertType.CONFIRMATION;
        return alert(message, title, aType);
    }

    private static boolean alert(String message, String title, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait().get() == ButtonType.OK;
    }
}



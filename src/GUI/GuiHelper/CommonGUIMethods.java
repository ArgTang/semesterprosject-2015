package GUI.GuiHelper;

import javafx.scene.control.TextField;

/**
 * Created by steinar on 16.04.2015.
 */
public interface CommonGUIMethods {

    void clearFields();

    void addCSSValidation();


    default void resetTextField(TextField textField)
    {
        textField.setText("");
        RegEX.resetCSSValidationRule(textField);
    }
}

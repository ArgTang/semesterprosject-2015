package GUI.GuiHelper;

import javafx.scene.control.TextField;

import java.util.function.Predicate;

/**
 * Created by steinar on 16.04.2015.
 */
public interface CommonGUIMethods {

    void clearFields();

    void addCSSValidation();

    //void setListeners(); //todo: can we make private methods obligatory?

    default void resetTextFields(TextField... textFields) //Varangs!!
    {
        for(TextField textField: textFields)
        {
            textField.setText("");
            RegEX.resetCSSValidationRule(textField);
        }
    }

    default void addCSSTextValidation(Predicate condition, TextField... textFields) //Varangs!!
    {
        for (TextField textField: textFields)
        {
            RegEX.addCSSTextValidation(textField, condition);
        }
    }
}

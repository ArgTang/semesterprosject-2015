package GUI.GuiHelper;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;

import java.util.function.Predicate;

/**
 * Created by steinar on 16.03.2015.
 * This is a class for doing input validation.
 * Add more functions as needed.
 *  - All functions should be static
 *  - All functions should return Boolean
 */

//todo: Maybe interface instead?
public final class RegEX {

    private static final String number = "[\\d]+";
    private static final String allChars = "[\\wøØæÆåÅ]+";

    public static boolean checkIfString( String string )
    {
        return string.matches(allChars);
    }

    public static Predicate<TextField> isAllChars()
    {
        return t -> t.getText().matches(allChars);
    }

    public static Predicate<TextField> isNumber()
    {
        return t -> t.getText().matches(number);
    }

    public static void addCSSTextValidation(TextField textField, Predicate condition)
    {
        final PseudoClass invalidText = PseudoClass.getPseudoClass("invalidText");
        textField.setOnKeyReleased(e -> textField.pseudoClassStateChanged(invalidText, condition.test(textField)));
    }
}

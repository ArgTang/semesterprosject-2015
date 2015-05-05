package GUI.GuiHelper;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;

import java.util.function.Predicate;

/**
 * Created by steinar on 16.03.2015.
 * This is a class for doing input validation on textfields.
 * Add more Predicates and functions as needed.
 *
 *  To use any of the predicates you can do like this: RegEX.isAdress.test(somestringhere)
 *
 *  Add this to a css file:
 *  .text-field:invalidText {
 *      -red: rgb(237.0, 68.0, 37.0);
 *      -fx-background-color: -red, rgb(230, 230, 230) !important;
 *      -fx-background-insets: 0.0, 0.2em;
 *      -fx-background-radius: 2.0;
 *  }
 */

public final class RegEX {

    public static final PseudoClass invalidText = PseudoClass.getPseudoClass("invalidText");

    //todo: make sure all of the regular expressions works as intended
    private static final String NUMBER = "[1-9]{1}[\\d]";
    private static final String ALLCHARS = "[\\wøØæÆåÅ]+";
    private static final String LETTERS = "([^\\d\\WøØæÆåÅ ]+( )?)+";
    private static final String EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String ADRESS = "^((.){1,}(\\d){1,}(.){0,})$";

    public static final Predicate<String> isAdress() { return string -> !string.matches(ADRESS); }
    public static final Predicate<String> isLetters() { return string -> !string.matches(LETTERS); }
    public static final Predicate<String> isEmail() { return string -> !string.matches(EMAIL); }
    public static final Predicate<String> isAllChars() { return string -> !string.matches(ALLCHARS); }
    public static final Predicate<String> isNumber() { return string -> !string.matches(NUMBER + "+"); }
    public static final Predicate<String> isNumberWithLength(int lenght) { return string -> !string.matches(NUMBER + "{" + (lenght-1) + "}"); }
    public static final Predicate<String> isLongerThan(int length) { return string -> !(string.length() > length); }
    public static final Predicate<String> isPassword() { return (string -> isAllChars().and(isLongerThan(5)).test(string)); }

    public static final Predicate<TextField> validationIsOk(int minLength) { return textfield -> textfield.getLength() > minLength && pseudoOK.negate().test(textfield); }
    //if pseudoclass is not empty -> check if it contains invalidText pseudoclass
    public static final Predicate<TextField> pseudoOK = textField -> !(textField.getPseudoClassStates().isEmpty() || !textField.getPseudoClassStates().toString().contains("invalidText"));


    public static void addCSSTextValidation(TextField textField, Predicate condition) {
        //todo: how to add css only when done typing?
        textField.setOnKeyReleased(event -> textField.pseudoClassStateChanged(invalidText, condition.test(textField.getText())));
    }

    @SafeVarargs
    public static void addCSSTextValidation(Predicate condition, TextField... textFields) {
        for (TextField textField: textFields)
            RegEX.addCSSTextValidation(textField, condition);
    }

    @SafeVarargs
    public static void resetTextFields(TextField... textFields) {
        for(TextField textField: textFields) {
            textField.setText("");
            textField.pseudoClassStateChanged(invalidText, false);
        }
    }

    @SafeVarargs
    public static boolean validationIsOk(int minLength, TextField... textFields) {
        for(TextField textField: textFields)
            if (!validationIsOk(minLength).test(textField))
                return false;
        return true;
    }
}
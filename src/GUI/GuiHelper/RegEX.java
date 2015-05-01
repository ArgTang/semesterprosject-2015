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
 *
 *  To use any of the predicates you can do like this: RegEX.isAdress.test(somestringhere)
 */

public final class RegEX {

    public static final PseudoClass invalidText = PseudoClass.getPseudoClass("invalidText");
    //todo: this string does not seem to work. css file works fine
    public static  final String CSSrules = ".text-field:invalidText { " +
                                            "-red: rgb(237.0, 68.0, 37.0); " +
                                            "-fx-background-color: -red, rgb(230, 230, 230) !important; " +
                                            "-fx-background-insets: 0.0, 0.2em; " +
                                            "-fx-background-radius: 2.0;" +
                                            "} ";

    //todo: make sure all of the regular expressions works as intended
    private static final String NUMBER = "[1-9]{1}[\\d]";
    private static final String ALLCHARS = "[\\wøØæÆåÅ]+";
    private static final String LETTERS = "[^\\d\\W]+|[øØæÆåÅ]+";
    private static final String EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String ADRESS = "^((.){1,}(\\d){1,}(.){0,})$";

    public static final Predicate<String> isAdress() { return string -> !string.matches(ADRESS); }
    public static final Predicate<String> isLetters() { return string -> !string.matches(LETTERS); }
    public static final Predicate<String> isEmail() { return string -> !string.matches(EMAIL); }
    public static final Predicate<String> isAllChars() { return string -> !string.matches(ALLCHARS); }
    public static final Predicate<String> isNumber() { return string -> !string.matches(NUMBER + "+"); }
    public static final Predicate<String> isNumber(int lenght) { return string -> !string.matches(NUMBER + "{" + (lenght-1) + "}"); }
    public static final Predicate<String> isLongerThan(int length) { return string -> !(string.length() > length); }
    public static final Predicate<String> isPassword() { return (string -> isAllChars().and(isLongerThan(5)).test(string)); }

    public static final Predicate<TextField> validationIsOk(int minLength) { return textfield -> textfield.getLength() > minLength && textfield.getPseudoClassStates().isEmpty() ;}

    public static void addCSSTextValidation(TextField textField, Predicate condition) {
        //todo: how to add css only when done typing?
        textField.setOnKeyReleased(event -> textField.pseudoClassStateChanged(invalidText, condition.test(textField.getText())));
    }

    public static void resetCSSValidationRule(TextField textField) {
            textField.pseudoClassStateChanged(invalidText, false);
    }

    public static void resetTextFields(TextField... textFields) { //Varangs!!
        for(TextField textField: textFields) {
            textField.setText("");
            RegEX.resetCSSValidationRule(textField);
        }
    }

    public static void addCSSTextValidation(Predicate condition, TextField... textFields) { //Varangs!!
        for (TextField textField: textFields)
            RegEX.addCSSTextValidation(textField, condition);
    }

    public static final boolean validationIsOk(int minLength, TextField... textFields) { //Varangs!!
        for(TextField textField: textFields)
            if (validationIsOk(minLength).negate().test(textField))
                return false;
        return true;
    }
}
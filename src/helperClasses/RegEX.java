package HelperClasses;
/**
 * Created by steinar on 16.03.2015.
 * This is a class for doing input validation.
 * Add more functions as needed.
 *  - All functions should be static
 *  - All functions should return Boolean
 */

public final class RegEX {

    static final String number = "[\\d]+";
    static final String allChars = "[\\wøØæÆåÅ]+";

    public static boolean checkIfnumber( String string ) {
        return string.matches(number);
    }

    public static boolean checkIfString( String string ) {
        return string.matches(allChars);
    }
}

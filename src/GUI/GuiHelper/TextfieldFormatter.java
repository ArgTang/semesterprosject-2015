/*
package GUI.GuiHelper;

import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

*/
/**
 * Created by steinar on 05.05.2015.
 *//*

public class TextfieldFormatter {

    buyPrice.editableProperty().setValue(true);
    buyPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2000, 2000000, 0, 10000));

    NumberFormat form = NumberFormat.getInstance();
    form.setParseIntegerOnly(false);
    form.setGroupingUsed(true);
    form.setMaximumIntegerDigits(8);
    TextFormatter<Integer> numbform = new TextFormatter(new NumberStringConverter(form));

    TextFormatter<Integer> something = new TextFormatter<Integer>(numbform.getFilter());

    UnaryOperator<TextFormatter.Change> filter = c -> {
        if (c.isContentChange()) {
            ParsePosition parsePosition = new ParsePosition(0);
            // NumberFormat evaluates the beginning of the text
            form.parse(c.getControlNewText(), parsePosition);
            if (parsePosition.getIndex() == 0 ||
                    parsePosition.getIndex() < c.getControlNewText().length()) {
                // reject parsing the complete text failed
                return null;
            }
        }
        return c;
    };

    TextFormatter<Integer> priceFormatter = new TextFormatter<Integer>(
            new IntegerStringConverter(), 0,
            filter);

    TextFormatter formatter = new TextFormatter<>( change -> {
        return numbform.getFilter().apply(change);
    });

    buyPrice.getEditor().setTextFormatter(priceFormatter);
}
*/

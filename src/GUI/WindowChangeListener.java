package GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by steinar on 17.04.2015.
 */
public class WindowChangeListener
{
    private final StringProperty stringListener = new SimpleStringProperty();

    public final String getPropertyString() { return stringListener.get(); }
    public final void setPropertyString( String string ) { stringListener.set( string ); }
    public StringProperty getStringProperty() { return stringListener; }
}

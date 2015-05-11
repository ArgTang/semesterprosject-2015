package GUI.CurrentObjectListeners;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by steinar on 17.04.2015.
 */
public final class WindowChangeListener
{
    private final StringProperty stringListener = new SimpleStringProperty();

    public String getString() { return stringListener.get(); }
    public void setString( String string ) { stringListener.set( string ); }
    public StringProperty getProperty() { return stringListener; }
}
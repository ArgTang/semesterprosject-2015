package GUI.CurrentObjectListeners;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class that store the type of window that is shown.
 * When this property is changed, windowControllers will show the window requested
 * Created by steinar on 17.04.2015.
 */
public final class WindowChangeListener
{
    private final StringProperty stringListener = new SimpleStringProperty();

    public String getString() { return stringListener.get(); }
    public void setString( String string ) { stringListener.set( string ); }
    public StringProperty getProperty() { return stringListener; }
}
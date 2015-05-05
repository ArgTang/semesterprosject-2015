package GUI.CurrentObjectListeners;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 17.04.2015.
 *
 *
 * todo: Why This does not work??
 */
public class WindowWindowListener
{
    private final ObjectProperty objectProperty = new SimpleObjectProperty<>();

    public final Object getPropertyObject() { return objectProperty.getValue(); }
    public final void setPropertyObject(Object object) { objectProperty.setValue( object ); }
    public ObjectProperty getObjectProperty() { return objectProperty; }
    public void reset() { objectProperty.setValue(null); }
}

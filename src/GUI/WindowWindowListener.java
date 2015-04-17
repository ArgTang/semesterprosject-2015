package GUI;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;

/**
 * Created by steinar on 17.04.2015.
 *
 *
 * todo: Why This does not work??
 */
public class WindowWindowListener
{
    private final Property objectProperty = null;

    public final Object getPropertyObject() { return objectProperty.getValue(); }
    public final void setPropertyObject(Class object) { objectProperty.setValue( object ); }
    public Property<Class> getObjectProperty() { return objectProperty; }
}

package GUI;

import Person.Person;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 19.04.2015.
 */
public class CurrentCustomer
{
    //todo: make generic?
    private final SimpleObjectProperty<Person> personListener = new SimpleObjectProperty<>();

    public final Person getPerson() { return personListener.get(); }
    public final void setProperty(Person person) { personListener.set( person ); }
    public ObjectProperty<Person> getPersonProperty() { return personListener; }
    public void reset() { personListener.setValue(null); }
}

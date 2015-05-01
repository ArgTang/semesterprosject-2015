package GUI;

import Person.Customer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 19.04.2015.
 */
public class CurrentCustomer
{
    //todo: make generic?
    private final SimpleObjectProperty<Customer> personListener = new SimpleObjectProperty<>(null);

    public final Customer getPerson() { return personListener.get(); }
    public final void setProperty(Customer customer) { personListener.set( customer ); }
    public ObjectProperty<Customer> getPersonProperty() { return personListener; }
    public void reset() { personListener.setValue(null); }
}

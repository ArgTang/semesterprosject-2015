package GUI.CurrentObjectListeners;

import GUI.StartMain;
import Person.Customer;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class for storing the customer that is currently being worked on
 * when this property gets a change,  info about the customer will be loaded.
 * Created by steinar on 19.04.2015.
 */

public final class CurrentCustomer
{
    private final SimpleObjectProperty<Customer> currentCustomer = new SimpleObjectProperty<>(null);

    public void set(Customer customer) {
        currentCustomer.set(customer);
    }
    public Customer get() {
        return currentCustomer.get();
    }
    public SimpleObjectProperty<Customer> getProperty() {
        return currentCustomer;
    }
    public void reset() {
        currentCustomer.setValue(null);
        StartMain.currentInsurance.reset();
        StartMain.currentIncident.reset();
    }
}
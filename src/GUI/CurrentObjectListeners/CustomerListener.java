package GUI.CurrentObjectListeners;

import GUI.StartMain;
import Person.Customer;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 19.04.2015.
 */
public final class CustomerListener
{
    //todo: make generic?
    public static final SimpleObjectProperty<Customer> currentCustomer = new SimpleObjectProperty<>(null);

    public static void reset() {
        currentCustomer.setValue(null);
        StartMain.currentInsurance.reset();
        StartMain.currentIncident.reset();
    }
}
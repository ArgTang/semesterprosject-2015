package GUI.GuiHelper;

import Person.Customer;
import Register.RegisterCustomer;
import javafx.scene.control.TextField;

import static GUI.StartMain.currentCustomer;

/**
 * Alot of classes have similar functions here we define
 * standard names for some functions.
 * Created by steinar on 01.05.2015.
 */
public abstract class CommonGUIMethods
{
    public abstract void clearFields();
    protected abstract void setListeners();
    protected abstract void initialize();
    protected abstract void addCSSValidation();
    protected abstract boolean checkValidation();

    protected int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
    protected void setInt(TextField textField, int number) { textField.setText(String.valueOf(number)); }

    protected Customer getCustomerOrDummyCustomer() {
        Customer customer = currentCustomer.get();
        if (customer == null)
            customer = RegisterCustomer.tempCustomer;
        return customer;
    }
}
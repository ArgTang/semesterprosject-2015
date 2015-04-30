package Register;

import Person.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by steinar on 28.04.2015.
 */
public final class RegisterCustomer extends Register {

    public RegisterCustomer()
    {
        super(new HashMap< String, Customer >());
    }

    public boolean add(Customer customer) {
       return super.add(customer.getSocialSecurityNumber(), customer);
    }

    public Customer get(String key) {
        return (Customer) super.getWithKey(key);
    }

    public boolean update(Customer customer) {
        return super.update(customer.getSocialSecurityNumber(), customer);
    }

    public List searchSurename(String name) {
        return search(matchesInFirstnam(name));
    }

    public List searchLastname(String name) {
        return search(matchesInLastname(name));
    }

    public List searchCustomerID(String id) {
        return search(matchesInCustomerID(id));
    }

    public List searchForPhone(int phone) {
        return search(matchesPhonenumer(phone));
    }

    public List<Customer> searchPhone(int phone) {
        Collection<Customer> col = super.getRegister();
        return col.stream()
                  .filter(customer -> customer.getPhoneNumbers().stream()
                          .anyMatch(i -> i == phone))
                  .collect(Collectors.toList());

    }

    public static final Predicate<Customer> matchesPhonenumer(int phonenumber ) { return customer -> customer.getPhoneNumbers().contains(phonenumber); }
    public static final Predicate<Customer> matchesInLastname(String match) { return customer -> customer.getLastName().matches("(?i:.*" + match + ".*)"); }
    public static final Predicate<Customer> matchesInFirstnam(String match) { return customer -> customer.getFirstName().matches("(?i:.*" + match + ".*)" ); }
    public static final Predicate<Customer> matchesInCustomerID(String match) { return customer -> customer.getCustomerId().matches("(?i:.*" + match + ".*)"); }

    public List<Customer> search(Predicate<Customer> condition) {
        Collection<Customer> col = super.getRegister();
        return   col.stream()
                    .filter(condition)
                    .collect(Collectors.toList());
    }
}
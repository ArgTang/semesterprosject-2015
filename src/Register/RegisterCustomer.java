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

    public boolean add(Customer customer)
    {
       return super.add(customer.getSocialSecurityNumber(), customer);
    }

    public Customer get(String key)
    {
        return (Customer) super.getWithKey(key);
    }

    public boolean update(Customer customer)
    {
        return super.update(customer.getSocialSecurityNumber(), customer);
    }

    //private Predicate<Customer> CustomerFirstnameContainsIgnoreCase() { customer -> customer.getFirstName().matches("(?i:.*" + name + ".*)") };

    //todo: predicates?
    public List searchSurename(String name)
    {
        Collection<Customer> col = super.getRegister();
        List list =  col.stream()
                        .filter(customer -> customer.getFirstName().matches("(?i:.*" + name + ".*)"))
                        .collect(Collectors.toList());
        return list;
    }

    public List searchLastname(String name)
    {
        Collection<Customer> col = super.getRegister();
        List list =  col.stream()
                .filter(customer -> customer.getLastName().matches("(?i:.*" + name + ".*)"))
                .collect(Collectors.toList());
        return list;
    }

    public List searchCustomerID(String id)
    {
        Collection<Customer> col = super.getRegister();
        List list =  col.stream()
                .filter(customer -> customer.getCustomerId().matches("(?i:.*" + id + ".*)"))
                .collect(Collectors.toList());
        return list;
    }

    //todo: how to do this?????
    public List searchPhone(String phone)
    {
        Collection<Customer> col = super.getRegister();
        /*List list =  col.stream()
                        .filter(customer -> customer.getPhoneNumbers().stream()
                                                                    .filter(i ->String.valueOf(i).matches(phone))
                        .collect(Collectors.toList());*/
        return null;
    }

    publi


}

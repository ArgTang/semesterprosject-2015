package Register;

import Person.ContactInfo;
import Person.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * this class is the register for storing Customer Objects
 * the tempcustomer is used for displaying Insurance pricing when no customer is selected
 * Predicates defines what you are searching for in the searchmethod
 * Created by steinar on 28.04.2015.
 */
public final class RegisterCustomer extends Register {

    private static final ContactInfo contactinfo = new ContactInfo("oslogata 3", "2341", "oslo", "lastname@email.com", 12345678);
    public static final Customer tempCustomer = new Customer("ola", "normann", "11111111111", contactinfo);

    public RegisterCustomer() {
        super(new HashMap< String, Customer >(), "customer");
    }

    public boolean add(Customer customer) {
       if ( super.add(customer.getSocialSecurityNumber(), customer) ) {
           saveRegister();
           return  true;
       }
        return false;
    }

    public Customer get(String key) {
        return (Customer) super.getWithKey(key);
    }

    public boolean update(Customer customer) {
        return super.update(customer.getSocialSecurityNumber(), customer);
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

    public void saveRegister() {
        if (!Files.exists(Paths.get("Registers")))
            try {
                Files.createDirectory(Paths.get("Registers"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        ObjectOutputStream output = null;
        try {
            String current = new File( "." ).getCanonicalPath();
            Path path = Paths.get("/" + "customer" + "Register.data");
            output = new ObjectOutputStream( new FileOutputStream( current + path.toFile()));
            output.writeObject(register);
            output.close();
        } catch (IOException e) {
            System.out.println("could write to file: " + "customer" + "Register.data");
            e.printStackTrace();
        } finally {
            if (output != null ) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "customer" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadRegister() {

        ObjectInputStream input = null;
        try {
            String current = new File( "." ).getCanonicalPath();
            Path path = Paths.get("/" +  "customer" + "Register.data");
            input = new ObjectInputStream( new FileInputStream( current + path.toFile()));
            register = (HashMap)input.readObject();
            input.close();
        } catch (IOException e) {
            System.out.println("could not read from file: " + "customer" + "Register.data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "customer" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }
}
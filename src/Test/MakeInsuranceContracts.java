package Test;


import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Insurance.TravelInsurance;
import Person.Customer;

import java.time.LocalDate;
import java.util.Random;

import static Insurance.Insurance.paymentOptions;

/**
 * Created by nugGet on 22.04.2015.
 */
public class MakeInsuranceContracts
{
    //todo: make some insuranseContracts of all the different insuranse types
    private final static Random randomGenerator = new Random();

    public void generate(Customer customer) {
        addTravel(customer);
    }

    private void addTravel(Customer customer) {

        TravelInsurance travelinsurance, travelinsurance2 = null;
        int birthYear = getBirthYear(customer);
        int currentYear = LocalDate.now().getYear();
        int age =  currentYear - birthYear;

        PaymentOption paymentOption = paymentOptions.get( randomGenerator.nextInt( paymentOptions.size()));
        int randomNumber = 2 + randomGenerator.nextInt(8);
        int startYear = birthYear + 20;
        LocalDate fromdate = generateDate(startYear);
        if (startYear > currentYear)
            return;
        else {
            travelinsurance = new TravelInsurance(fromdate, "policy", customer, paymentOption, false);
        }

        if (age > 20 + randomNumber) {
            fromdate = fromdate.plusYears(randomNumber);
            travelinsurance.endInsuranse(fromdate);
            travelinsurance2 = new TravelInsurance(fromdate, "policy", customer, paymentOption, true);
        }

        StartMain.insuranceRegister.add(travelinsurance);
        customer.addInsuranceNumber(travelinsurance.getCasenumber());

        if (travelinsurance2 != null) {
            StartMain.insuranceRegister.add(travelinsurance2);
            customer.addInsuranceNumber(travelinsurance2.getCasenumber());
        }

    }

    private int getBirthYear(Customer customer) {
        String ID = customer.getSocialSecurityNumber();
        ID = ID.substring(4,6);

        if (Byte.parseByte(ID) > 50)
            ID = "19" + ID;
        else
            ID = "20" + ID;

        return Integer.parseInt(ID);
    }

    private LocalDate generateDate(int year) {
        LocalDate date = LocalDate.of(year, generateMonth(), 1);

        int day = 9;
        do {
            day = randomGenerator.nextInt(31);
        } while (day > date.lengthOfMonth());

        date = date.plusDays(day);
        return date;
    }

    private int generateMonth() {
        int month = 1;
        do {
            month = 1 + randomGenerator.nextInt(11);
        } while (month > 12);
        return month;
    }
}
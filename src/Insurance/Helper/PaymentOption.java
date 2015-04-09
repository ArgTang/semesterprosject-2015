package Insurance.Helper;

import Insurance.Insurance;
import Person.Person;

/**
 * Created by steinar on 07.04.2015.
 */
public enum PaymentOption {
    YEARLY(12),
    QUARTERLY(3),
    MONTHLY(1);

    private final int MonthsInPayment;

    PaymentOption(int monthsInPayment) {
        MonthsInPayment = monthsInPayment;
    }
}

package Insurance.Helper;

/**
 * Created by steinar on 07.04.2015.
 */
public enum PaymentOption {
    YEARLY(12, "Årlig"),
    QUARTERLY(3 ,"Kvartalsvis"),
    MONTHLY(1, "Månedlig");

    private final int monthsInPayment;
    private final String name;

    PaymentOption(int monthsInPayment, String name) {
        this.monthsInPayment = monthsInPayment;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return monthsInPayment;
    }
}

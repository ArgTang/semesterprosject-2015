package Insurance.Helper;

/**
 * Created by steinar on 07.04.2015.
 */
public enum PaymentOption
{
    YEARLY(1, "Årlig"),
    QUARTERLY(4 ,"Kvartalsvis"),
    MONTHLY(12, "Månedlig");

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

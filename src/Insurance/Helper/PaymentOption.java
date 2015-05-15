package Insurance.Helper;

/**
 * This helper enum is for connecting a string to a value so that premium calculations is easier
 * Created by steinar on 07.04.2015.
 */
public enum PaymentOption
{
    YEARLY(1, "Årlig"),
    QUARTERLY(4 ,"Kvartalsvis"), //4 kvartaler i året
    MONTHLY(12, "Månedlig");    //12 måneder i året

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
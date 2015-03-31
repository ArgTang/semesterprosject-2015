package Insurance;


import java.time.LocalDateTime;

/**
 * Created by steinar on 27.03.2015.
 * Base information for the different insurance types
 */
public abstract class Insurance {
    final double insuranceFee;
    final LocalDateTime fromDate;
    final double insuranseValue;
    final String insurancePolicy; // informasjon om hva forsikringen dekker kanskje ikke her?
    //todo: Decide on what we should store here annet?


    public Insurance(double insuranceFee, double insuranseValue, String insurancePolicy) {
        this.insuranceFee = insuranceFee;
        this.fromDate = LocalDateTime.now();
        this.insuranseValue = insuranseValue;
        this.insurancePolicy = insurancePolicy;
    }
}

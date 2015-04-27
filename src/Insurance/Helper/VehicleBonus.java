package Insurance.Helper;

/**
 * Created by nugGet on 27.04.2015.
 */
public enum VehicleBonus
{
    START(20),
    ONEYEAR(30),
    TWOYEAR(40),
    THREEYEAR(50),
    FOURYEAR(60),
    FIVEYEAR(70),
    TENYEAR(75),
    FIFTEENYEAR(80);

    private final int bonus;

    VehicleBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public int getBonus() { return bonus; }
}

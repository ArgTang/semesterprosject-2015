package Insurance.Vehicle;

import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Boat extends Vehicle
{


    private int numberOfEngines, knots, feet;
    //TODO: Add youngest boat drivers age?

    public Boat(double value, String policy, String type, String model, Year productionYear, int numberOfEngines, int knots, int feet)
    {
        super(value, policy, type, model, productionYear);

        this.numberOfEngines = numberOfEngines;
        this.knots = knots;
        this.feet = feet;
    }

    public int getNumberOfEngines() { return numberOfEngines; }
    public int getKnots() { return knots; }
    public int getFeet() { return feet; }
}

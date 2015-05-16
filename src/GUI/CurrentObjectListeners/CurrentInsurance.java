package GUI.CurrentObjectListeners;

import Insurance.Animal.AnimalInsurance;
import Insurance.Insurance;
import Insurance.Property.HomeInsurance;
import Insurance.Property.HouseholdContentsInsurance;
import Insurance.TravelInsurance;
import Insurance.Vehicle.BoatInsurance;
import Insurance.Vehicle.CarInsurance;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class for storing the insurance that is currently being worked on
 * Created by steinar on 19.04.2015.
 */

public final class CurrentInsurance
{
    //todo: make generic?
    private final SimpleObjectProperty<Insurance> insuranceListener = new SimpleObjectProperty<>(null);

    public Insurance get() { return insuranceListener.get(); }
    public void set(Insurance incident) { insuranceListener.set(incident); }
    public SimpleObjectProperty<Insurance> getProperty() { return insuranceListener; }
    public void reset() { insuranceListener.setValue(null); }

    public static String getNameOfInsurance(Insurance insurance) {
        if (insurance instanceof HomeInsurance)
            return "Hus";
        else if (insurance instanceof CarInsurance)
            return "Bil";
        else if (insurance instanceof TravelInsurance)
            return "Reise";
        else if (insurance instanceof BoatInsurance)
            return "Båt";
        else if (insurance instanceof AnimalInsurance)
           return "Dyr";
        else if (insurance instanceof HouseholdContentsInsurance)
            return "Innbo";

        return "Hus";
    }
}
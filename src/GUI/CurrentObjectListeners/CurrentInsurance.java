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
 * Created by steinar on 19.04.2015.
 */
public final class CurrentInsurance
{
/*
    private final SimpleObjectProperty<? extends Insurance> insuranceListener = new SimpleObjectProperty<>();

    public <T extends Insurance> T getInsurance() { return insuranceListener.get(); }
    public <? extends Insurance> void setInsurance(T insurance) { insuranceListener.set( insurance ); }*/
    //todo: make generic?
    public static final SimpleObjectProperty<Insurance> insuranceListener = new SimpleObjectProperty<>(null);

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
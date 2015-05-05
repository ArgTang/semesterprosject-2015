package GUI.CurrentObjectListeners;

import Insurance.Animal.AnimalInsurance;
import Insurance.Insurance;
import Insurance.Property.HomeInsurance;
import Insurance.Property.HouseholdContentsInsurance;
import Insurance.TravelInsurance;
import Insurance.Vehicle.BoatInsurance;
import Insurance.Vehicle.CarInsurance;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 19.04.2015.
 */
public final class CurrentInsurance
{
    //todo: make generic?
    private  final SimpleObjectProperty<Insurance> insuranseListener = new SimpleObjectProperty<>();

    public Insurance getInsurance() { return insuranseListener.get(); }
    public void setProperty(Insurance insurance) { insuranseListener.set( insurance ); }
    public ObjectProperty<Insurance> getInsuranceProperty() { return insuranseListener; }
    public void reset() { insuranseListener.setValue(null); }

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
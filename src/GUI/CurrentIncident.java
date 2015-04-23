package GUI;

import Insurance.Insurance;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Optional;

/**
 * Created by steinar on 23.04.2015.
 */
public class CurrentIncident
{
    //todo: make generic?
    private final SimpleObjectProperty<Insurance> insuranceListener = new SimpleObjectProperty<>();

    public final Optional<Insurance> getProperty() { return Optional.ofNullable(insuranceListener.get()); }
    public final void setProperty(Insurance insurance) { insuranceListener.set( insurance ); }
    public ObjectProperty<Insurance> getPersonProperty() { return insuranceListener; }
    public void reset() { insuranceListener.setValue(null); }

}

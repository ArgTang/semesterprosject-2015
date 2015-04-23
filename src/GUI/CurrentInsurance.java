package GUI;

import Insurance.Insurance;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Optional;

/**
 * Created by steinar on 19.04.2015.
 */
public class CurrentInsurance
{
    //todo: make generic?
    private final SimpleObjectProperty<Insurance> insuranseListener = new SimpleObjectProperty<>();

    public final Optional<Insurance> getProperty() { return Optional.ofNullable(insuranseListener.get()); }
    public final void setProperty(Insurance insurance) { insuranseListener.set( insurance ); }
    public ObjectProperty<Insurance> getPersonProperty() { return insuranseListener; }
    public void reset() { insuranseListener.setValue(null); }
}

package GUI;

import Incident.Incident;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by steinar on 23.04.2015.
 */
public final class CurrentIncident
{
    //todo: make generic?
    private final SimpleObjectProperty<Incident> incidentListener = new SimpleObjectProperty<>();

    public Incident getIncident() { return incidentListener.get(); }
    public void setProperty(Incident incident) { incidentListener.set(incident); }
    public ObjectProperty<Incident> getPersonProperty() { return incidentListener; }
    public void reset() { incidentListener.setValue(null); }
}
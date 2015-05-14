package GUI.CurrentObjectListeners;

import Incident.Incident;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Class for storing the incident that is currently being worked on
 * Created by steinar on 23.04.2015.
 */

public final class CurrentIncident
{
    //todo: make generic?
    public static final SimpleObjectProperty<Incident> incidentListener = new SimpleObjectProperty<>(null);

    @Deprecated
    public Incident getIncident() { return incidentListener.get(); }
    @Deprecated
    public void setProperty(Incident incident) { incidentListener.set(incident); }
    @Deprecated
    public ObjectProperty<Incident> getIncidentProperty() { return incidentListener; }
    public void reset() { incidentListener.setValue(null); }
}
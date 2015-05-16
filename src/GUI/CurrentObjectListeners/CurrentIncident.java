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
    private final SimpleObjectProperty<Incident> incidentListener = new SimpleObjectProperty<>(null);

    public Incident get() { return incidentListener.get(); }
    public void set(Incident incident) { incidentListener.set(incident); }
    public ObjectProperty<Incident> getProperty() { return incidentListener; }
    public void reset() { incidentListener.setValue(null); }
}
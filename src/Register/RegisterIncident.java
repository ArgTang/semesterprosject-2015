package Register;

import Incident.Incident;

import java.util.HashMap;

/**
 * Class for storing Incidents
 * Created by steinar on 28.04.2015.
 */
public final class RegisterIncident extends Register
{
    public RegisterIncident() {
        super(new HashMap< Integer, Incident>());
    }

    public boolean add(Incident incident) {
        try {
            incident.setIncidentID(super.getNumberofObjectsStored() + 1);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        return super.add( incident.getIncidentID(), incident );
    }

    public Incident get(int incidetID) {
        return (Incident) super.getWithKey(incidetID);
    }

    public boolean update(Incident incident) {
        return super.update(incident.getIncidentID(), incident);
    }
}
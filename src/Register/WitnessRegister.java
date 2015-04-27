package Register;

import Person.Witness;
import Incident.Incident;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public final class WitnessRegister  implements CommonRegisterMethods
{
    Map< Long, Witness> register = new HashMap();

    /**
     * Adds a witness to an incident
     * @param witness
     * @param incident
     * @return true if witness and incident don't already exists
     */
    public boolean addWitness(Witness witness, Incident incident)
    {
        return add(incident.getIncidentID(), witness, register);
    }

    /**
     * Finds witnesses to an incident
     * @param incidentID
     * @return true if incident exists
     */
    public Object getWitness(long incidentID)
    {
        return getWithKey(incidentID, register);
    }

    /**
     * Updates witness to an incident
     * @param witness
     * @param incident
     * @return true if witness and incident exists
     */
    public boolean updateWitness(Witness witness, Incident incident)
    {
        return update(incident.getIncidentID(), witness, register);
    }
}

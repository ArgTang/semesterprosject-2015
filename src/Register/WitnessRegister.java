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

    public boolean addWitness(Witness witness, Incident incident)
    {
        return add(incident.getIncidentID(), witness, register);
    }

    public Object getWitness(long incidentID)
    {
        return getWithKey(incidentID, register);
    }
}

package Register;

import Incident.Incident;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class IncidentRegister  implements CommonRegisterMethods
{
    Map< Long, Incident > register = new HashMap();

    public boolean addIncident(Incident incident)
    {
        return addToMap(incident.getIncidentID(), incident, register);
    }

    public Object getIncident(long incidentID)
    {
        return getWithKey(incidentID, register);
    }
}

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

    /**
     * Adds an incident
     * @param incident
     * @return
     */
    public boolean addIncident(Incident incident)
    {
        return add(incident.getIncidentID(), incident, register);
    }

    /**
     * Finds an incident
     * @param incidentID
     * @return true if incident exists
     */
    public Object getIncident(long incidentID)
    {
        return getWithKey(incidentID, register);
    }

    /**
     * Updates an existing incident
     * @param incident
     * @return true if incident exists
     */
    public boolean updateIncident(Incident incident)
    {
        return update(incident.getIncidentID(), incident, register);
    }
}

package Register;

import Incident.Incident;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class IncidentRegister  implements CommonRegisterMethods
{

    Map< Integer, Incident > register = new HashMap();
}

package Incident;


import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public class PropertyIncident extends Incident
{
    //temp constructor (for compile)
    public PropertyIncident(String incidentDescription, LocalDate dayOfIncident, String timeOfIncident) {
        super(incidentDescription, dayOfIncident, timeOfIncident);
    }
}

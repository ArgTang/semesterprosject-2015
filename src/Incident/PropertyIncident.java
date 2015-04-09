package Incident;


import Person.Person;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayDeque;

/**
 * Created by steinar on 27.03.2015.
 */
public class PropertyIncident extends Incident {

    //temp constructor (for compile)
    public PropertyIncident(String incidentDescription, LocalDate dayOfIncident, String timeOfIncident) {
        super(incidentDescription, dayOfIncident, timeOfIncident);
    }
}

package Incident;


import Person.Person;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayDeque;

/**
 * Created by steinar on 27.03.2015.
 */
public class VehicleIncident extends Incident {

    //temp constructor (for compile)
    public VehicleIncident(String incidentDescription, LocalDate dayOfIncident, String timeOfIncident, ArrayDeque<Files> pictures, ArrayDeque<Person> witness) {
        super(incidentDescription, dayOfIncident, timeOfIncident, pictures, witness);
    }
}

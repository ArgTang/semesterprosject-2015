package Incident;

import Person.Person;

import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Incident implements Serializable
{
    private static final long serialVersionUID = 5526472295622776147L;
    final LocalDateTime timeOfReport;
    final LocalDate dayOfIncident; //todo: if string maybe just one string?

    private int incidentID = -1;
    private String policeReport;
    private int ourInsuranceReference;

    private String incidentDescription;
    private String detailedDamageDescription;
    private List<Files> pictures;
    private List<Person> witness;
    private List<Person> caseworker;

    private int damageEstimate;
    private LocalDateTime timeOfPayout; //todo: make these an object? to get them final.
    private int payout;

    public Incident(String incidentDescription, LocalDate dayOfIncident, String timeOfIncident) {
        this.timeOfReport = LocalDateTime.now();
        this.incidentDescription = incidentDescription;
        this.dayOfIncident = dayOfIncident;
    }

    public int getIncidentID() {
        return incidentID;
    }
    public void setIncidentID(int ID) { incidentID = ID; }
}
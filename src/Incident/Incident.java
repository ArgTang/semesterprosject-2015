package Incident;

import Person.Person;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by steinar on 27.03.2015.
 */
public class Incident implements Serializable
{
    private static final long serialVersionUID = 5526472295622776147L;
    final LocalDateTime timeOfReport;
    final LocalDate dayOfIncident; //todo: if string maybe just one string?

    private int incidentID = -1;
    private String policeReport;
    private int InsuranceIDReference;

    private String incidentDescription;
    private String detailedDamageDescription;
    private List<Path> files;

    private int damageEstimate;
    private LocalDateTime timeOfPayout; //todo: make these an object? to get them final.
    private int payout;

    public Incident(LocalDate dayOfIncident, int InsuranceIDReference, String incidentDescription) {
        this.timeOfReport = LocalDateTime.now();
        this.dayOfIncident = dayOfIncident;
        this.InsuranceIDReference = InsuranceIDReference;
        this.incidentDescription = incidentDescription;
    }

    public int getIncidentID() {
        return incidentID;
    }
    public void setIncidentID(int ID) { incidentID = ID; }
    public String getIncidentDescription() {
        return incidentDescription;
    }
    public LocalDate getDayOfIncident() {
        return dayOfIncident;
    }
    public List<Path> getFiles() {
        return files;
    }

    public void setFiles(List<Path> files) {
        this.files = files;
    }
}
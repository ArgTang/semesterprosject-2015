package Incident;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by steinar on 27.03.2015.
 */
public class Incident implements Serializable {
    private static final long serialVersionUID = 5526472295622776147L;
    final LocalDateTime timeOfReport;
    final LocalDate dayOfIncident; //todo: if string maybe just one string?

    private int incidentID = -1;
    private boolean fire;
    private boolean theft;
    private boolean waterdamage;
    private boolean personDamage;
    private boolean accident;
    private boolean nature;
    private String policeReport;
    private int InsuranceIDReference;

    private String incidentDescription;
    private String detailedDamageDescription;
    private List<Path> files;

    private int damageEstimate;
    private LocalDateTime timeOfPayout; //todo: make these an object? to get them final.
    private int payout;

    public Incident(LocalDate dayOfIncident, int InsuranceIDReference, String incidentDescription, boolean fire,
                    boolean theft, boolean waterdamage, boolean accident, boolean nature) {
        this.timeOfReport = LocalDateTime.now();
        this.dayOfIncident = dayOfIncident;
        this.InsuranceIDReference = InsuranceIDReference;
        this.fire = fire;
        this.theft = theft;
        this.waterdamage = waterdamage;
        this.accident = accident;
        this.nature = nature;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int ID) {
        incidentID = ID;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public LocalDate getDayOfIncident() {
        return dayOfIncident;
    }

    public List<Path> getFiles() {
        return files;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isTheft() {
        return theft;
    }

    public boolean isWaterdamage() {
        return waterdamage;
    }

    public boolean isPersonDamage() {
        return personDamage;
    }

    public boolean isAccident() {
        return accident;
    }

    public boolean isNature() {
        return nature;
    }

    public void setFiles(List<Path> files) {
        this.files = files;
    }
}
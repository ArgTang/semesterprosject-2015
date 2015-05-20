package Register;

import Incident.Incident;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class for storing Incidents
 * Created by steinar on 28.04.2015.
 */
public final class RegisterIncident extends Register
{
    public RegisterIncident() {
        super(new HashMap< Integer, Incident>(), "incident");
    }

    public boolean add(Incident incident) {
        try {
            incident.setIncidentID( super.getNumberofObjectsStored() + 1);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        if ( super.add( incident.getIncidentID(), incident )) {
            saveRegister();
            return true;
        }
        return false;
    }

    public Incident get(int incidetID) {
        return (Incident) super.getWithKey(incidetID);
    }

    public boolean update(Incident incident) {
        return super.update(incident.getIncidentID(), incident);
    }

    public void saveRegister() {
        if (!Files.exists(Paths.get("Registers")))
            try {
                Files.createDirectory(Paths.get("Registers"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        ObjectOutputStream output = null;
        try {
            String current = new File( "." ).getCanonicalPath();
            Path path = Paths.get("/" + "incident" + "Register.data");
            output = new ObjectOutputStream( new FileOutputStream( current + path.toFile()));
            output.writeObject(register);
            output.close();
        } catch (IOException e) {
            System.out.println("could write to file: " + "incident" + "Register.data");
            e.printStackTrace();
        } finally {
            if (output != null ) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "incident" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadRegister() {

        ObjectInputStream input = null;
        try {
            String current = new File( "." ).getCanonicalPath();
            Path path = Paths.get("/" +  "incident" + "Register.data");
            input = new ObjectInputStream( new FileInputStream( current + path.toFile()));
            register = (HashMap)input.readObject();
            input.close();
        } catch (IOException e) {
            System.out.println("could not read from file: " + "incident" + "Register.data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "incident" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }
}
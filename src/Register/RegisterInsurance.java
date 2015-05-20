package Register;

import Insurance.Insurance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class for storing Insurance
 * Created by steinar on 28.04.2015.
 */
public final class RegisterInsurance extends  Register
{
    public RegisterInsurance() {
        super(new HashMap<Integer, Insurance>(), "insurance");
    }

    public boolean add(Insurance insurance) {
        try {
            insurance.setCasenumber(super.getNumberofObjectsStored() + 1);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        if ( super.add(insurance.getCasenumber(), insurance)) {
            saveRegister();
            return true;
        }
        return false;
    }

    public Insurance get(int insuranceID) {
        return (Insurance) super.getWithKey(insuranceID);
    }

    public boolean update(Insurance insurance) {
        return super.update(insurance.getCasenumber(), insurance);
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
            Path path = Paths.get("/" + "insurance" + "Register.data");
            output = new ObjectOutputStream( new FileOutputStream( current + path.toFile()));
            output.writeObject(register);
            output.close();
        } catch (IOException e) {
            System.out.println("could write to file: " + "insurance" + "Register.data");
            e.printStackTrace();
        } finally {
            if (output != null ) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "insurance" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadRegister() {

        ObjectInputStream input = null;
        try {
            String current = new File( "." ).getCanonicalPath();
            Path path = Paths.get("/" +  "insurance" + "Register.data");
            input = new ObjectInputStream( new FileInputStream( current + path.toFile()));
            register = (HashMap)input.readObject();
            input.close();
        } catch (IOException e) {
            System.out.println("could not read from file: " + "insurance" + "Register.data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + "insurance" + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }
}
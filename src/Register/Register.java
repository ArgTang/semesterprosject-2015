package Register;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 28.04.2015.
 *
 * superclass for our registers
 * Methods to add and update entries. Theres not a way to delete entries, this is intended.
 * Theres also methods for writing to\from file
 * //linky; https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
 */
abstract class Register {

    Map register;
    String savetoFileName;

    public Register(HashMap hashMap, String savetoFileName) {
        register = hashMap;
        this.savetoFileName = savetoFileName;
    }

    <K, O> boolean add(K key, O object) {
        if ( !register.containsKey(key) ) {
            register.put(key, object);
            return true;
        }
        return false;
    }

    <K, O> Object getWithKey( K key )
    {
        return register.get(key);
    }

    <K, O> boolean update( K key, O object) {
        if( register.containsKey(key) ) {
            register.replace(key, object);
            return true;
        }
        return false;
    }

    public Collection getRegister() { return register.values(); }
    protected int getNumberofObjectsStored() {
        return register.size();
    }

    public void saveRegister() {
        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream( new FileOutputStream( savetoFileName + "Register.data"));
            output.writeObject(register);
            output.close();
        } catch (IOException e) {
            System.out.println("could write to file: " + savetoFileName + "Register.data");
            e.printStackTrace();
        } finally {
            if (output != null ) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + savetoFileName + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadRegister() {

        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream( new FileInputStream(savetoFileName + "Register.data"));
            register = (Map)input.readObject();
            input.close();
        } catch (IOException e) {
            System.out.println("could not read from file: " + savetoFileName + "Register.data");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("could not close file: " + savetoFileName + "Register.data");
                    e.printStackTrace();
                }
            }
        }
    }
}
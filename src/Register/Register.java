package Register;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 28.04.2015.
 *
 * superclass for our registers
 * //linky; https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
 */
abstract class Register {

    Map register;

    public Register(HashMap hashMap)
    {
        register = hashMap;
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
            register.replace(key, object); //todo check if this method throws an exception
            return true;
        }
        return false;
    }

    public Collection getRegister() { return register.values(); }
}
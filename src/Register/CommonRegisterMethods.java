package Register;

import java.util.Map;

/**
 * Created by steinar on 10.04.2015.
 */
interface CommonRegisterMethods {


    //todo: maybe this will work but wont save us much code? but its a good way to show we know generics and interface :)
    //todo: check for exceptions
    //todo: other common functions
    //linky; https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
    default <K, O> boolean add( K key, O object,  Map<K, O> collection )
    {
        if (! collection.containsKey(key) )
        {
            collection.put(key, object);
            return true;
        }
        return false;
    }

    default <K, O> Object getWithKey( K key, Map<K, O> collection )
    {
        return collection.get(key);
    }

    default <K, O> boolean update( K key, O object, Map<K, O> collection )
    {
        if( collection.containsKey(key) )
        {
            collection.replace(key, object); //todo check if this method throws an exception
            return true;
        }
        return false;
    }

}

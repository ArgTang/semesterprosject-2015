package Register;

import java.util.Map;

/**
 * Created by steinar on 10.04.2015.
 */
interface registerMethods {


    //todo: maybe this will work but wont save us much code?
    //linky; https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
    default public <K, O> boolean add(K key, O object,  Map<K, O> collection)
    {
        if (! collection.containsKey(key) )
        {
            collection.put(key, object);
            return true;
        }
        return false;
    }

    //Todo: create other common list methods: getWithKey(), findWithName()

}

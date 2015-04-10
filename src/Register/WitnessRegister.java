package Register;

import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public final class WitnessRegister {

    Map< String, Person > register = new HashMap();


    public boolean remove(String hasKey)
    {

        //todo: only register we need remove if customer register contains person then remove
        return true;
    }
}

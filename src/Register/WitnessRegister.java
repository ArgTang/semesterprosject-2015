package Register;

import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public final class WitnessRegister  implements CommonRegisterMethods
{

    Map< String, Person > register = new HashMap();


    public boolean remove(String hasKey)
    {
        //todo: if upgrading a witness we can delete all info, and insert sosialsecuritynumber instead
        return true;
    }
}

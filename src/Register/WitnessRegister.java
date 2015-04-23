package Register;

import java.util.ArrayList;
//import Person.ContactInfo;
import Person.Witness;
//import Person.Person;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public final class WitnessRegister  implements CommonRegisterMethods
{

    Map< String, Witness> register = new HashMap();


    public boolean add(Witness wit)
    {
        //todo: if upgrading a witness we can delete all info, and insert sosialsecuritynumber instead
        return false;

    }
}

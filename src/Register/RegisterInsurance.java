package Register;

import Insurance.Insurance;
import java.util.HashMap;


/**
 * Created by steinar on 28.04.2015.
 */
public final class RegisterInsurance extends  Register{

    private static int idCounter = 1;

    public RegisterInsurance()
    {
        super(new HashMap<Integer, Insurance>());
    }

    public boolean add(Insurance insurance)
    {
        if (insurance.setCasenumber(idCounter) == idCounter && super.add(insurance.getCasenumber(), insurance)) {
            ++idCounter;
            return true;
        }
        return false;
    }

    public Insurance get(int insuranceID)
    {
        return (Insurance) super.getWithKey(insuranceID);
    }

    public boolean update(Insurance insurance)
    {
        return super.update(insurance.getCasenumber(), insurance);
    }
}

package Register;

import Insurance.Insurance;

import java.util.HashMap;

/**
 * Created by steinar on 28.04.2015.
 */
public final class RegisterInsurance extends  Register
{
    public RegisterInsurance() {
        super(new HashMap<Integer, Insurance>());
    }

    public boolean add(Insurance insurance) {
        insurance.setCasenumber(super.getNumberofObjectsStored()+1);
        return  super.add(insurance.getCasenumber(), insurance);
    }

    public Insurance get(int insuranceID) {
        return (Insurance) super.getWithKey(insuranceID);
    }

    public boolean update(Insurance insurance) {
        return super.update(insurance.getCasenumber(), insurance);
    }
}
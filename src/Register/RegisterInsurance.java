package Register;

import Insurance.Insurance;

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
            super.saveRegister();
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
}
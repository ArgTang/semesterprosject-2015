package Objects;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by steinar on 09.04.2015.
 */
public final class VehicleBoat extends Vehicle { //todo: name ok?

    int feet;
    short numberOfMotors;
    String type; //TODO; enum snekke, cruiser osv is it needed?

    public VehicleBoat(int registeredKM, int horsePower, int value, int purchasePrice, LocalDate purchaceDate, short makeYear, String maker, String model, String color, String registrationNumber, List<File> pictures) {
        super(registeredKM, horsePower, value, purchasePrice, purchaceDate, makeYear, maker, model, color, registrationNumber, pictures);
    }
}

package Insurance.Vehicle;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by steinar on 09.04.2015.
 */

//todo: delete this when new Vehicle Class is complete

public class DepreciatedVehicle {

    int registeredKM; // todo do boats have this? -> new car class?
    int horsePower;
    int value;
    int purchasePrice;
    LocalDate purchaceDate;
    short makeYear; //todo Change name of this årsmodell.

    String maker;
    String model;
    String color; //todo: maybe enum? do boats need this?
    String registrationNumber;
    List<File> pictures;

}

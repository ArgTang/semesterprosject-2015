package Objects;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by steinar on 09.04.2015.
 */
public class Vehicle {

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

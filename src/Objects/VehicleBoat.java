package Objects;

/**
 * Created by steinar on 09.04.2015.
 */
public final class VehicleBoat extends Vehicle { //todo: name ok?

    int feet;
    short numberOfMotors;
    String type; //TODO; enum snekke, cruiser osv is it needed?

    public VehicleBoat(int feet) {
        this.feet = feet;
    }
}

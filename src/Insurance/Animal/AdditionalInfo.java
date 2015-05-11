package Insurance.Animal;

import java.io.Serializable;

/**
 * Created by nugGet on 25.04.2015.
 */
public class AdditionalInfo implements Serializable
{
    private static final long serialVersionUID = 7524672295622776147L;
    String color;
    String name;
    String chipNumber;

    public AdditionalInfo(String color, String name, String chipNumber, String illnessDescription) {
        this.color = color;
        this.name = name;
        this.chipNumber = chipNumber;
    }
}
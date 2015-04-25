package Insurance.Animal;

/**
 * Created by nugGet on 25.04.2015.
 */
public class AdditionalInfo
{
    String color;
    String name;
    String chipNumber;
    String illnessDescription; //TODO: Neccessary in our case?

    public AdditionalInfo(String color, String name, String chipNumber, String illnessDescription)
    {
        this.color = color;
        this.name = name;
        this.chipNumber = chipNumber;
        this.illnessDescription = illnessDescription;
    }
}

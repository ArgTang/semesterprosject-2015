package Insurance.Helper;

/**
 * Created by nugGet on 27.04.2015.
 */
public enum BuildingMaterial
{
    CONCRETE(1, "Sement"),
    BRICK(50, "Mur"),
    WOOD(100, "Treverk");

    private final int percentPremiumIncrease;
    private final String material;

    BuildingMaterial(int percentPremiumIncrease, String material)
    {
        this.percentPremiumIncrease = percentPremiumIncrease;
        this.material = material;
    }

    public int getPercentPremiumIncrease() { return percentPremiumIncrease; }
    public String getMaterial() { return material; }
}

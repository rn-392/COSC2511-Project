/**
 * This class represents a location in the game world.
 */
public class Location {
    /**
     * The name of the location.
     */
    private final String name;

    /**
     * A description of the location.
     */
    private final String description;

    /**
     * Indicates whether the location contains an item.
     */
    private boolean hasItem;

    /**
     * Indicates whether the location is hostile.
     */
    private boolean isHostile;

    /**
     * Constructor to initialize the location with a name and description.
     *
     * @param name        The name of the location.
     * @param description A description of the location.
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.hasItem = false; // Default value: no item.
        this.isHostile = false; // Default value: not hostile.
    }

    /**
     * Returns the description of the location.
     *
     * @return The description of the location.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets whether the location contains an item.
     *
     * @param value True if the location contains an item, false otherwise.
     */
    public void setHasItem(boolean value) {
        this.hasItem = value;
    }

    /**
     * Checks if the location contains an item.
     *
     * @return True if the location contains an item, false otherwise.
     */
    public boolean hasItem() {
        return hasItem;
    }

    /**
     * Sets whether the location is hostile.
     *
     * @param value True if the location is hostile, false otherwise.
     */
    public void setHostile(boolean value) {
        this.isHostile = value;
    }

    /**
     * Checks if the location is hostile.
     *
     * @return True if the location is hostile, false otherwise.
     */
    public boolean isHostile() {
        return isHostile;
    }

    /**
     * Returns the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }
}

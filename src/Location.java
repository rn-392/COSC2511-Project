/**
 * This class represents a location in the game world.
 * * @author Ricky Nguyen
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

    private String longDescription;

    /**
     * Indicates whether the location contains an item.
     */
    private boolean hasItem;

    /**
     * Indicates whether the location is hostile.
     */
    private boolean isHostile;

    private Items item;

    private boolean eventTriggered;

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
        this.eventTriggered = false;
        this.item = null;
        this.longDescription = "";
    }

    /**
     * Returns the description of the location.
     *
     * @return The description of the location.
     */
    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets whether the location contains an item.
     *
     * @param value True if the location contains an item, false otherwise.
     */
    public void setHasItem(boolean value) {
        this.hasItem = value;
    }

    public void setItem(Items item) {
        this.item = item;
        this.hasItem = item != null;
    }

    /**
     * Checks if the location contains an item.
     *
     * @return True if the location contains an item, false otherwise.
     */
    public boolean hasItem() {
        return hasItem;
    }

    public Items getItem() {
        return item;
    }

    /**
     * Sets whether the location is hostile.
     *
     * @param value True if the location is hostile, false otherwise.
     */
    public void setHostile(boolean value) {
        this.isHostile = value;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Checks if the location is hostile.
     *
     * @return True if the location is hostile, false otherwise.
     */
    public boolean isHostile() {
        return isHostile;
    }

    public boolean isEventTriggered() {
        return eventTriggered;
    }

    public void triggerEvent() {
        this.eventTriggered = true;
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

/**
 * This class represents a location in the game world.
 * It contains information about the location's name, description, items,
 * hostility, events, and characters.
 * 
 * @author Ricky Nguyen
 */
public class Location {
    /** The name of the location. */
    private final String name;

    /** A short description of the location. */
    private final String description;

    /** A long description of the location. */
    private String longDescription;

    /** Indicates whether the location contains an item. */
    private boolean hasItem;

    /** Indicates whether the location is hostile. */
    private boolean isHostile;

    /** The item present in the location, if any. */
    private Items item;

    /** Indicates whether a special event in the location has been triggered. */
    private boolean eventTriggered;

    /** The character present in the location, if any. */
    private combatCharacters character;

    /** Indicates whether all enemies in the location have been defeated. */
    private boolean enemiesDefeated;

    /**
     * Constructs a Location with the specified name and description.
     *
     * @param name        The name of the location.
     * @param description A short description of the location.
     */
    public Location(String name, String description, boolean isHostile) {
        this.name = name;
        this.description = description;
        this.hasItem = false;
        this.isHostile = isHostile;
        this.eventTriggered = false;
        this.item = null;
        this.longDescription = "";
        this.character = null;
        this.enemiesDefeated = false;
    }

    /**
     * Returns the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the short description of the location.
     *
     * @return The description of the location.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the long description of the location.
     *
     * @return The long description of the location.
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets the long description of the location.
     *
     * @param longDescription The long description to set.
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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
     * Sets whether the location contains an item.
     *
     * @param value True if the location contains an item, false otherwise.
     */
    public void setHasItem(boolean value) {
        this.hasItem = value;
    }

    /**
     * Returns the item present in the location.
     *
     * @return The item in the location, or null if none.
     */
    public Items getItemAtLoc() {
        return item;
    }

    /**
     * Sets the item present in the location.
     * Also updates the hasItem flag accordingly.
     *
     * @param item The item to set, or null if no item.
     */
    public void setItem(Items item) {
        this.item = item;
        this.hasItem = item != null;
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
     * Sets whether the location is hostile.
     *
     * @param value True if the location is hostile, false otherwise.
     */
    public void setHostile(boolean value) {
        this.isHostile = value;
    }

    /**
     * Returns the character present in the location.
     *
     * @return The character in the location.
     */
    public combatCharacters getCharacter() {
        return character;
    }

    /**
     * Sets the character of the location.
     *
     * @param character The character to set, or null if no character.
     */
    public void setCharacter(combatCharacters character) {
        this.character = character;
    }

    /**
     * Checks if a special event in the location has been triggered.
     *
     * @return True if the event has been triggered, false otherwise.
     */
    public boolean isEventTriggered() {
        return eventTriggered;
    }

    /**
     * Triggers the special event in the location.
     */
    public void triggerEvent() {
        this.eventTriggered = true;
    }

    /**
     * Checks if all enemies in the location have been defeated.
     *
     * @return True if all enemies are defeated, false otherwise.
     */
    public boolean areEnemiesDefeated() {
        return enemiesDefeated;
    }

    /**
     * Sets whether all enemies in the location have been defeated.
     *
     * @param defeated True if all enemies are defeated, false otherwise.
     */
    public void setEnemiesDefeated(boolean defeated) {
        this.enemiesDefeated = defeated;
    }
}

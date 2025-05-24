import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 * The player has a name, health, position on the map, and an inventory of
 * items.
 * The player can move in cardinal directions within the game’s 5x5 grid.
 * 
 * @author Ricky Nguyen
 */
public class Player {

    /**
     * The x-coordinate of the player's position (horizontal).
     */
    private int x;

    /**
     * The y-coordinate of the player's position (vertical).
     */
    private int y;

    /**
     * The player's current health value.
     */
    private double health;

    /**
     * The name of the player.
     */
    private String playerName;

    /**
     * The player's inventory of items.
     */
    private final ArrayList<Items> inventory = new ArrayList<>();

    /**
     * Creates a new player starting at the center of the map with default health
     * and name.
     */
    public Player() {
        this.x = 2;
        this.y = 2;
        this.health = 100;
        this.playerName = "Unknown Traveler";
    }

    /**
     * Moves the player north (up the map), if within bounds.
     */
    public void moveNorth() {
        if (y < 4) {
            y++;
        } else {
            System.out.println("Can't go further North.");
        }
    }

    /**
     * Moves the player south (down the map), if within bounds.
     */
    public void moveSouth() {
        if (y > 0) {
            y--;
        } else {
            System.out.println("Can't go further south.");
        }
    }

    /**
     * Moves the player east (right on the map), if within bounds.
     */
    public void moveEast() {
        if (x < 4) {
            x++;
        } else {
            System.out.println("Can't go further east.");
        }
    }

    /**
     * Moves the player west (left on the map), if within bounds.
     */
    public void moveWest() {
        if (x > 0) {
            x--;
        } else {
            System.out.println("Can't go further west.");
        }
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added.
     */
    public void addItem(Items item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        for (Items item : inventory) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(itemName)) {
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a list of all items in the player's inventory.
     *
     * @return A list of the player's inventory items.
     */
    public List<Items> getInventory() {
        return inventory;
    }

    /**
     * Prints a list of items currently in the player's inventory.
     */
    public void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your Inventory:");
            for (Items item : inventory) {
                System.out.println(" - " + item.getName());
            }
        }
    }

    /**
     * Gets the player's x-coordinate position.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the player's y-coordinate position.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the player's current health.
     *
     * @return The health value.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return playerName;
    }

    /**
     * Sets the player's name.
     *
     * @param name The new player name.
     */
    public void setName(String name) {
        this.playerName = name;
    }

    /**
     * Sets the player's health.
     *
     * @param health The new health value.
     */
    public void setHealth(double health) {
        this.health = health;
    }
}

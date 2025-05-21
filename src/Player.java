
/**
 * This class represents a player in the game.
 * The player can move around the game map within defined boundaries.
 * 
 * @author Ricky Nguyen
 */
public class Player {
    /**
     * The x-coordinate of the player's position.
     */
    private int x;

    /**
     * The y-coordinate of the player's position.
     */
    private int y;

    private double health;

    private String playerName;

    /**
     * Constructor to initialize the player's starting position at the center of the
     * map.
     */
    public Player() {
        // start in center of map
        this.x = 2;
        this.y = 2;
    }

    /**
     * Moves the player north (increasing the y-coordinate) if within bounds.
     * Prints a message if the player cannot move further north.
     */
    public void moveNorth() {
        if (y < 4)
            y++;
        else
            System.out.println("Can't go further North.");
    }

    /**
     * Moves the player south (decreasing the y-coordinate) if within bounds.
     * Prints a message if the player cannot move further south.
     */
    public void moveSouth() {
        if (y > 0)
            y--;
        else
            System.out.println("Can't go further south.");
    }

    /**
     * Moves the player east (increasing the x-coordinate) if within bounds.
     * Prints a message if the player cannot move further east.
     */
    public void moveEast() {
        if (x < 4)
            x++;
        else
            System.out.println("Can't go further east.");
    }

    /**
     * Moves the player west (decreasing the x-coordinate) if within bounds.
     * Prints a message if the player cannot move further west.
     */
    public void moveWest() {
        if (x > 0)
            x--;
        else
            System.out.println("Can't go further west.");
    }

    public void setName(String name) {
        this.playerName = name;
    }

    /**
     * Gets the x-coordinate of the player's position.
     *
     * @return The x-coordinate of the player's position.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the player's position.
     *
     * @return The y-coordinate of the player's position.
     */
    public int getY() {
        return y;
    }
}

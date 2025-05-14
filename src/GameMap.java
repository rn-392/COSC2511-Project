/**
 * This class represents the game map, which consists of a grid of locations.
 * * @author Ricky Nguyen
 */
public class GameMap {
    /**
     * A 2D array representing the map of locations.
     */
    private final Location[][] map;

    /**
     * Constructor to initialize the game map with default locations.
     */
    public GameMap() {
        map = new Location[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new Location("Empty Space", "You are drifting through empty space.");
            }
        }
    }

    /**
     * Retrieves the location at the specified coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @return The location at the specified coordinates, or null if out of bounds.
     */
    public Location getLocation(int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 5) {
            return map[x][y];
        } else {
            return null;
        }
    }

    /**
     * Prints the game map, showing the player's position.
     *
     * @param player The player whose position is displayed on the map.
     */
    public void printMap(Player player) {
        for (int y = 4; y >= 0; y--) {
            System.out.println();
            for (int x = 0; x < 5; x++) {
                if (x == player.getX() && y == player.getY()) {
                    System.out.print("[ P ] ");
                } else {
                    System.out.print("[" + x + "," + y + "] ");
                }
            }
            System.out.println();
        }
    }
}

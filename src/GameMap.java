/**
 * This class represents the game map, which consists of a grid of locations.
 * * @author Ricky Nguyen
 */
public class GameMap {
    /**
     * A 2D array representing the map of locations.
     * The coordinate system uses [x][y] where:
     * - x increases going east (0-4)
     * - y increases going north (0-4)
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
        // Abandoned Space Station
        Location station = new Location(
                "Abandoned Space Station",
                "You see the remains of an abandoned space station drifting silently.");
        station.setLongDescription(
                "A dormant AI terminal blinks faintly.\nPower couplings near the entrance suggest this station needs an energy source.\nFaded text on the hull reads 'ARMORY - AUTHORIZED PERSONNEL ONLY'.\n");
        map[2][3] = station;
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

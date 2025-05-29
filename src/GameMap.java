
import java.util.Random;

/**
 * This class represents the game map, which consists of a grid of locations.
 * 
 * @author Ricky Nguyen
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
                Random random = new java.util.Random();
                for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                                map[i][j] = new Location("Empty Space", "You are drifting through empty space.", false);
                                int variation = random.nextInt(3);
                                switch (variation) { // randomize descriptions
                                        case 0 -> map[i][j].setLongDescription(
                                                        "There is nothing of interest here. Just the cold, silent void of space.");
                                        case 1 -> map[i][j].setLongDescription(
                                                        "Just a vast, empty void. You see nothing but darkness and distant stars.");
                                        case 2 -> map[i][j].setLongDescription(
                                                        "You drift through space with nothing but your own breath and the ship's hum for company.");
                                }
                        }
                }

                // Planets
                Location eridani = new Location(
                                "Eridani",
                                "A harsh desert wasteland under a blood-red sky.", true);
                eridani.setLongDescription(
                                "Grand General Zig's forces patrol the dunes.\nYou can trade an Ore Chunk with him for the warp drive fragment.\nAlternatively, there are less amicable ways to get it...");
                map[2][4] = eridani;

                Location ixyll = new Location(
                                "Ixyll",
                                "Dense jungle vines obscure your path.", true);
                ixyll.setLongDescription(
                                "The air hums with energy.\nZep Zop, a tribal warrior, defends the jungle.\nDefeating him grants a mysterious fruit and a warp drive fragment.");
                map[0][3] = ixyll;

                Location strix = new Location(
                                "Strix",
                                "Freezing winds howl across a barren tundra.", true);
                strix.setLongDescription(
                                "Mastermind lurks within a ruined facility.\nEntertain his musings for a special reward.\nVictory yields a warp drive fragment.");
                map[1][1] = strix;

                Location ternion = new Location(
                                "Ternion",
                                "Ruins of a once-thriving city now echo with silence.", true);
                ternion.setLongDescription(
                                "A Rogue Droid stalks the alleys.\nIts defeat is the only way to claim the warp drive fragment hidden here.");
                map[4][0] = ternion;

                // Points of Interest
                Location station = new Location(
                                "Abandoned Space Station",
                                "You see the remains of an abandoned space station drifting silently.", false);
                station.setLongDescription(
                                "A dormant AI terminal blinks faintly.\nPower couplings near the entrance suggest this station needs an energy source.\nFaded text on the hull reads 'ARMORY - AUTHORIZED PERSONNEL ONLY'.");
                map[2][3] = station;

                Location asteroid = new Location(
                                "Asteroid",
                                "Jagged rocks surround a mining site carved into the asteroid's crust.", false);
                asteroid.setLongDescription(
                                "Laser drills hum quietly.\nRich ore veins glint beneath the surface, promising valuable resources.");
                asteroid.setHasItem(true);
                asteroid.setItem(new Items("Ore Chunk"));
                map[4][2] = asteroid;

                Location jungleMoon = new Location(
                                "Jungle Moon",
                                "Thick underbrush and alien chirps define the moon's surface.", false);
                jungleMoon.setLongDescription(
                                "The Feral Hermit watches from the shadows.\nHe seems open to trading a Fruit for a Shield Module.");
                map[4][3] = jungleMoon;

                Location monolith = new Location(
                                "Mysterious Monolith",
                                "A tall, obsidian structure pulses with otherworldly light.", false);
                monolith.setLongDescription(
                                "Etched glyphs hint at a riddle.\nAnswering it may unlock the key to a hidden gate.");
                map[3][1] = monolith;

                Location riftGate = new Location(
                                "Rift Gate",
                                "A swirling vortex pulses with dark energy.", true);
                riftGate.setLongDescription(
                                "The final challenge awaits.\nOnly those with the Gate Key may face Emperor Poutine and end his corruption.");
                map[0][0] = riftGate;

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

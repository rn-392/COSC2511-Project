import java.util.Scanner;

/**
 * The main application class for the game.
 * This class initializes the game map, player, and handles user input for
 * gameplay.
 */
public class App {
    /**
     * The main method to start the game.
     *
     * 
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GameMap map = new GameMap();
        Player player = new Player();
        boolean gameStart = false;

        displayWelcomeBanner();

        // menu loop
        while (!gameStart) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                gameStart = true;
            } else if (input.equalsIgnoreCase("q")) {
                System.out.println("Quitting...");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid input. Press 1 to start or q to quit.");
            }
        }
        // get player name
        System.out.println("\nPlease enter a name: ");
        String playerName = scanner.nextLine();
        player.setName(playerName);

        displayGameInstructions();

        while (gameStart) {
            Location currentLocation = map.getLocation(player.getX(), player.getY());
            System.out.println(
                    "\nLocation: " + currentLocation.getName() + " (" + player.getX() + ", " + player.getY() + ")");
            System.out.println(currentLocation.getDescription());

            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            System.out.println();

            processCommand(input, player, map);

        }
    }

    private static void displayWelcomeBanner() {
        // Title of Game using Ascii - christ
        System.out.println(
                "  _______      ___       __          ___       ______ .___________. __    ______     _______       ___   ____    __    ____ .__   __. \n"
                        + //
                        " /  _____|    /   \\     |  |        /   \\     /      ||           ||  |  /      |   |       \\     /   \\  \\   \\  /  \\  /   / |  \\ |  | \n"
                        + //
                        "|  |  __     /  ^  \\    |  |       /  ^  \\   |  ,----'`---|  |----`|  | |  ,----'   |  .--.  |   /  ^  \\  \\   \\/    \\/   /  |   \\|  | \n"
                        + //
                        "|  | |_ |   /  /_\\  \\   |  |      /  /_\\  \\  |  |         |  |     |  | |  |        |  |  |  |  /  /_\\  \\  \\            /   |  . `  | \n"
                        + //
                        "|  |__| |  /  _____  \\  |  `----./  _____  \\ |  `----.    |  |     |  | |  `----.   |  '--'  | /  _____  \\  \\    /\\    /    |  |\\   | \n"
                        + //
                        " \\______| /__/     \\__\\ |_______/__/     \\__\\ \\______|    |__|     |__|  \\______|   |_______/ /__/     \\__\\  \\__/  \\__/     |__| \\__|   ");
        System.out.println("\n\n\t\t\t\t\t\t\tPress 1 to start");
        System.out.println("\n\t\t\t\t\t\t\tPress q to quit");
    }

    private static void displayGameInstructions() {
        System.out.println("\n=== GAME INSTRUCTIONS ===");
        System.out.println("Type n/s/e/w to move in that direction.");
        System.out.println("Type 'look' to examine your surroundings.");
        System.out.println("Type 'take [item]' to pick up an item.");
        System.out.println("Type 'inv' to check your inventory.");
        System.out.println("Type 'map' to see the map.");
        System.out.println("Type 'help' or '?' for commands.");
        System.out.println("Type 'q' to quit the game.");
        System.out.println("=========================\n");
    }

    private static void processCommand(String input, Player player, GameMap map) {
        switch (input.toLowerCase()) {
            case "n" -> player.moveNorth();
            case "s" -> player.moveSouth();
            case "e" -> player.moveEast();
            case "w" -> player.moveWest();
            case "map" -> map.printMap(player);
            case "?", "help" -> {
                System.out.println("Available commands:");
                System.out.println("n / s / e / w - Move north, south, east, west");
                System.out.println("map - Display the game map");
                System.out.println("inv - Show your inventory");
                System.out.println("help / ? - Show this help menu");
                System.out.println("q - Quit the game");
            }
            case "q" -> {
                System.out.println("Quitting...");
                System.exit(0);
            }
            default -> System.out.println("Invalid input.");
        }
    }
}

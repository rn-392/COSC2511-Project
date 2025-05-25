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
     * @param args Command-line arguments (not used).
     * @throws Exception If an unexpected error occurs during execution.
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
            switch (input.toLowerCase()) {
                case "1" -> gameStart = true;
                case "q" -> {
                    System.out.println("Quitting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid input. Press 1 to start or q to quit.");
            }
        }
        // get player name
        String playerName = "";
        while (playerName.isEmpty()) {
            System.out.println("\nPlease enter a name: ");
            playerName = scanner.nextLine();
            if (playerName.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
            }
        }
        player.setName(playerName);

        displayGameInstructions(player);

        while (gameStart) {

            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            System.out.println();
            processCommand(input, player, map, scanner);
            System.out.println();

        }
    }

    /**
     * Displays the welcome banner for the game.
     */
    private static void displayWelcomeBanner() {
        // Title of Game using Ascii - christ
        System.out.println(
                """
                          _______      ___       __          ___       ______ .___________. __    ______     _______       ___   ____    __    ____ .__   __.
                         /  _____|    /   \\     |  |        /   \\     /      ||           ||  |  /      |   |       \\     /   \\  \\   \\  /  \\  /   / |  \\ |  |
                        |  |  __     /  ^  \\    |  |       /  ^  \\   |  ,----'`---|  |----`|  | |  ,----'   |  .--.  |   /  ^  \\  \\   \\/    \\/   /  |   \\|  |
                        |  | |_ |   /  /_\\  \\   |  |      /  /_\\  \\  |  |         |  |     |  | |  |        |  |  |  |  /  /_\\  \\  \\            /   |  . `  |
                        |  |__| |  /  _____  \\  |  `----./  _____  \\ |  `----.    |  |     |  | |  `----.   |  '--'  | /  _____  \\  \\    /\\    /    |  |\\   |
                         \\______| /__/     \\__\\ |_______/__/     \\__\\ \\______|    |__|     |__|  \\______|   |_______/ /__/     \\__\\  \\__/  \\__/     |__| \\__|   """ //
        //
        //
        //
        //
        );
        System.out.println("\n\n\t\t\t\t\t\t\tPress 1 to start");
        System.out.println("\n\t\t\t\t\t\t\tPress q to quit");
    }

    /**
     * Displays the game instructions to the player.
     *
     * @param player The player object to personalize the instructions.
     */
    private static void displayGameInstructions(Player player) {
        System.out.printf("Welcome to GALACTIC DAWN, %s!\n", player.getName());
        System.out.println();
        System.out.println("==================== BRIEF ===================");
        System.out.println("You awaken in empty space aboard a crippled spacecraft.");
        System.out.println("Its warp drive is obliterated and the stars are unreachable.");
        System.out.println("To escape, you must explore nearby planets, retrieve");
        System.out.println("four warp drive fragments, and defeat Emperor Poutine,");
        System.out.println("the tyrant whose corruption locks down the system.");
        System.out.printf("The Rift Gate awaits, %s.\n", player.getName());
        System.out.println("===============================================");
        System.out.println();
        System.out.println("============= GAME INSTRUCTIONS =============");
        System.out.println("Type n/s/e/w to move in that direction.");
        System.out.println("Type 'look' to examine your surroundings.");
        System.out.println("Type 'take' to pick up an item.");
        System.out.println("Type 'inv' to check your inventory.");
        System.out.println("Type 'map' to see the map.");
        System.out.println("Type 'solve' to attempt a puzzle at your location.");
        System.out.println("Type 'help' or '?' for commands.");
        System.out.println("Type 'q' to quit the game.");
        System.out.println("==============================================");
        System.out.println();

    }

    /**
     * Prints the current location of the player on the map.
     *
     * @param player The player object representing the current player.
     * @param map    The game map object.
     */
    private static void printCurrentLocation(Player player, GameMap map) {
        Location loc = map.getLocation(player.getX(), player.getY());
        System.out.println();
        System.out.println("\nLocation: " + loc.getName() + " (" + player.getX() + ", " + player.getY() + ")");
        System.out.println(loc.getDescription());
        System.out.println();
        if (loc.isHostile()) {
            System.out.println("Warning: This location is hostile!");
            System.out.println();
        }
    }

    /**
     * Processes a user command and updates the game state accordingly.
     * Handles movement, map display, help, quitting, and invalid input.
     *
     * @param input   The command entered by the user.
     * @param player  The player object representing the current player.
     * @param map     The game map object.
     * @param scanner The scanner object for reading user input.
     */
    private static void processCommand(String input, Player player, GameMap map, Scanner scanner) {
        Location currentLocation = map.getLocation(player.getX(), player.getY());
        switch (input.toLowerCase()) {
            case "n" -> {
                player.moveNorth();
                printCurrentLocation(player, map);
            }
            case "s" -> {
                player.moveSouth();
                printCurrentLocation(player, map);
            }
            case "e" -> {
                player.moveEast();
                printCurrentLocation(player, map);
            }
            case "w" -> {
                player.moveWest();
                printCurrentLocation(player, map);
            }
            case "map" -> map.printMap(player);
            case "inv" -> player.listInventory();
            case "look" -> {
                if (currentLocation.hasItem()) {
                    System.out.println("You examine your surroundings more carefully...");
                    System.out.println();
                    System.out.println("You notice: " + currentLocation.getItemAtLoc().getName());
                } else {
                    System.out.println("There are no items at this location.");
                }
                System.out.println();

                System.out.println(currentLocation.getLongDescription());
            }
            case "take" -> {
                if (currentLocation.hasItem()) {
                    Items item = currentLocation.getItemAtLoc();
                    player.addItem(item);
                    currentLocation.setItem(null); // Remove from location
                    System.out.println("You picked up: " + item.getName());
                } else {
                    System.out.println("Nothing to take here.");
                }
            }
            case "?", "help" -> {
                System.out.println("Available commands:");
                System.out.println("n / s / e / w - Move north, south, east, west");
                System.out.println("look - Examine your surroundings");
                System.out.println("take - Pick up an item");
                System.out.println("map - Display the game map");
                System.out.println("inv - Show your inventory");
                System.out.println("solve - Attempt a puzzle at your location");
                System.out.println("help / ? - Show this help menu");
                System.out.println("q - Quit the game");
            }
            case "q" -> {
                System.out.println("Quitting...");
                System.exit(0);
            }
            case "solve" -> {
                int x = player.getX();
                int y = player.getY();

                // monolith puzzle
                if (x == 3 && y == 1) {
                    System.out.println("The Monolith whispers a riddle:");
                    System.out.println(
                            "\"I speak without a mouth and hear without ears. I have nobody, but I come alive with wind. What am I?\"");
                    System.out.print("Your answer: ");
                    String answer = scanner.nextLine().trim().toLowerCase();

                    if (answer.equals("echo")) {
                        System.out.println();
                        System.out.println("The Monolith hums approvingly. You have solved the riddle!");
                        // You can add logic here to reward the player
                    } else {
                        System.out.println("That is not correct. The Monolith remains silent.");
                    }
                } else {
                    System.out.println("There is no puzzle to solve here.");
                }
            }
            default -> System.out.println("Invalid input.");
        }
    }
}

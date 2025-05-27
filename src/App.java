import java.util.Random;
import java.util.Scanner;

/**
 * The main application class for the game.
 * This class initializes the game map, player, and handles user input for
 * gameplay.
 */
public class App {
    private static final Random random = new Random();
    private static boolean gameStart = false;

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

        displayWelcomeBanner(scanner);

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

        System.out.println();

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
     * Displays the game’s ASCII welcome banner and prompts the user to start or
     * quit.
     * <p>
     * This method loops until a valid menu choice is entered: “1” to start the game
     * (sets the static gameStart flag to true) or “2” to quit (prints a message and
     * calls System.exit(0) to terminate the program).
     *
     * @param scanner the Scanner instance used for reading the user’s input
     */
    private static void displayWelcomeBanner(Scanner scanner) {
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

        int choice = -1;
        while (!gameStart) {
            System.out.print("\n\n\nChoose action (1=Start, 2=Quit): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2)
                    break;
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid input.");
        }

        if (choice == 1) {
            gameStart = true;
        } else {
            System.out.println("Quitting...");
            System.exit(0);
        }

    }

    /**
     * Displays the game instructions to the player.
     *
     * @param player The player object to personalize the instructions.
     */
    private static void displayGameInstructions(Player player) {
        System.out.printf("Welcome to GALACTIC DAWN, %s!\n", player.getName());
        System.out.println();
        System.out.println("====================== BRIEF =====================");
        System.out.println("You awaken in empty space aboard a crippled spacecraft.");
        System.out.println("Its warp drive is obliterated and the stars are unreachable.");
        System.out.println("To escape, you must explore nearby planets, retrieve");
        System.out.println("four warp drive fragments, and defeat Emperor Poutine,");
        System.out.println("the tyrant whose corruption locks down the system.");
        System.out.printf("The Rift Gate awaits, %s.\n", player.getName());
        System.out.println("===================================================");
        System.out.println();
        System.out.println("================= GAME INSTRUCTIONS ===============");
        System.out.println("Type n/s/e/w to move in that direction.");
        System.out.println("Type 'look' to examine your surroundings.");
        System.out.println("Type 'take' to pick up an item.");
        System.out.println("Type 'inv' to check your inventory.");
        System.out.println("Type 'map' to see the map.");
        System.out.println("Type 'solve' to attempt a puzzle at your location.");
        System.out.println("Type 'use' to use an item at your location.");
        System.out.println("Type 'help' or '?' for commands.");
        System.out.println("Type 'q' to quit the game.");
        System.out.println("====================================================");
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
        System.out.print("\nLocation: ");
        if (loc.isHostile()) {
            System.out.print("**[HOSTILE]** ");
        }
        System.out.println(loc.getName() + " (" + player.getX() + ", " + player.getY() + ")");
        System.out.println(loc.getDescription());
        System.out.println();
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
        switch (input.trim().toLowerCase()) {
            case "n" -> {
                player.moveNorth();
                if (random.nextBoolean()) {
                    System.out.println("Your ship moves forward to the north.");
                } else {
                    System.out.println("You guide your ship northward through space.");
                }
                printCurrentLocation(player, map);
            }
            case "s" -> {
                player.moveSouth();
                if (random.nextBoolean()) {
                    System.out.println("Your ship moves forward to the south.");
                } else {
                    System.out.println("You guide your ship southward through space.");
                }
                printCurrentLocation(player, map);
            }
            case "e" -> {
                player.moveEast();
                if (random.nextBoolean()) {
                    System.out.println("Your ship moves forward to the east.");
                } else {
                    System.out.println("You guide your ship eastward through space.");
                }
                printCurrentLocation(player, map);
            }
            case "w" -> {
                player.moveWest();
                if (random.nextBoolean()) {
                    System.out.println("Your ship moves forward to the west.");
                } else {
                    System.out.println("You guide your ship westward through space.");
                }
                printCurrentLocation(player, map);
            }
            case "map" -> map.printMap(player);
            case "inv" -> player.listInventory();
            case "look" -> {
                if (currentLocation.hasItem()) {
                    System.out.println("You examine your surroundings more carefully...");
                    System.out.println();
                    System.out.println("You notice: " + currentLocation.getItemAtLoc().getName());
                }
                System.out.println();
                System.out.println(currentLocation.getLongDescription());
            }
            case "take" -> {
                if (currentLocation.hasItem()) {
                    Items item = currentLocation.getItemAtLoc();
                    player.addItem(item); // add to inventory
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
                System.out.println("use - Use an item at your location");
                System.out.println("help / ? - Show this help menu");
                System.out.println("q - Quit the game");
            }
            case "use" -> {
                handleUse(player, map, scanner);
            }
            case "solve" -> {
                handleSolve(player, scanner, map);
            }

            case "fight" -> {
                int x = player.getX(), y = player.getY();
                Location loc = map.getLocation(x, y);

                // eridani general zig
                if (x == 2 && y == 4) {
                    // handle combat
                }
            }

            case "q" -> {
                if (confirm("Are you sure you want to quit? (y/n): ", scanner)) {
                    System.out.println("Thanks for playing. Goodbye!");
                    System.exit(0);
                } else {
                    System.out.println("Continuing game...");
                }
            }
            default -> System.out.println("Invalid input.");

        }
    }

    /**
     * Attempts to use an item or trigger an event at the player's current location.
     * <p>
     * - At (2,3): powers up the AI terminal if the player has a Cryo Core.
     * - At (0,0): activates the Rift Gate if the player has a Gate Key.
     * - Otherwise: informs the player there is nothing to use here.
     *
     * @param player  The player object, whose inventory will be checked and
     *                modified.
     * @param map     The game map, used to retrieve and update the current
     *                location.
     * @param scanner The scanner for reading additional player input (e.g.,
     *                confirmation).
     */
    private static void handleUse(Player player, GameMap map, Scanner scanner) {
        int x = player.getX(), y = player.getY();
        Location loc = map.getLocation(x, y);

        if (x == 2 && y == 3) { // space station laser rifle
            if (loc.isEventTriggered()) {
                System.out.println("You have already done this.");
            } else if (player.hasItem("Cryo Core")) {
                if (confirm("Do you wish to use the Cryo Core? (y/n): ", scanner)) {
                    loc.triggerEvent();
                    loc.setLongDescription("""
                            The AI terminal is fully powered, displaying active system readouts.
                            The armory access lights are green, and the secured doors are wide open.
                            """);
                    player.removeItem("Cryo Core");
                    System.out.println("You use the Cryo Core to power up the AI terminal.");
                    System.out.println("The room hums to life as the armory doors slide open.");
                    System.out.println("You have found: Laser Rifle");
                    player.addItem(new Items("Laser Rifle"));
                } else {
                    System.out.println("You decide not to use the Cryo Core right now.");
                }
            } else {
                System.out.println("You need something to power this terminal...");
            }

        } else if (x == 2 && y == 4) {
            if (loc.isEventTriggered()) {
                System.out.println("You have already traded with Grand General Zig.");
            } else if (player.hasItem("Ore Chunk")) {
                if (confirm("Trade the Ore Chunk with Grand General Zig? (y/n): ", scanner)) {
                    loc.triggerEvent();
                    player.removeItem("Ore Chunk");
                    System.out.println("You trade the Ore Chunk with Grand General Zig.");
                    System.out.println("He is pleased with the trade and hands you a Warp Drive Fragment.");
                    player.addItem(new Items("Warp Drive Fragment"));
                    System.out.println("\nYou have received: Warp Drive Fragment 1");
                } else {
                    System.out.println("You hold onto the Ore Chunk for now.");
                }
            } else {
                System.out.println("You don't have anything that pleases Grand General Zig.");
            }

        } else if (x == 0 && y == 0) { // rift gate activation
            if (player.hasItem("Gate Key")) {
                System.out.println("You use the Gate Key to activate the Rift Gate.");
                System.out.println("\nThe fabric of space-time ripples around the Gate...\n");
                if (confirm("You are about to face the boss. Continue? (y/n): ", scanner)) {
                    player.removeItem("Gate Key");
                    System.out.println("Your ship is engulfed by a blinding light...");
                    System.exit(0);
                } else {
                    System.out.println();
                    System.out.println("You step back from the Rift Gate.");
                }
            } else {
                System.out.println("The Rift Gate is locked. You need something to activate it.");
            }

        } else if (x == 4 && y == 3) { // jungle moon hermit trade
            if (loc.isEventTriggered()) {
                System.out.println("You have already traded with the Jungle Hermit.");
            } else if (player.hasItem("Ixyll Fruit")) {
                if (confirm("Trade the Ixyll Fruit with the Jungle Hermit? (y/n): ", scanner)) {
                    loc.triggerEvent();
                    loc.setLongDescription("The Jungle Hermit has disappeared into the dense foliage of the jungle.");
                    player.removeItem("Ixyll Fruit");
                    System.out.println("You trade the Ixyll Fruit with the Jungle Hermit.");
                    System.out.println("\nYou have received: Shield Module");
                    player.addItem(new Items("Shield Module"));
                } else {
                    System.out.println("You tuck the Ixyll Fruit away for later.");
                }
            } else {
                System.out.println("You need an Ixyll Fruit to trade with the Jungle Hermit.");
            }

        } else {
            System.out.println("There's nothing here you can use.");
        }
    }

    /**
     * Prompts the user with a yes/no question and reads input until a valid
     * response is given.
     *
     * @param prompt  the message to display asking the user to confirm (e.g. "Are
     *                you sure? (y/n): ")
     * @param scanner the Scanner instance to read the user's input from
     * @return {@code true} if the user entered 'y'; {@code false} if the user
     *         entered 'n'
     */
    private static boolean confirm(String prompt, Scanner scanner) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
            if (!input.equals("y") && !input.equals("n")) {
                System.out.println("Please enter 'y' or 'n'.");
            }
        } while (!input.equals("y") && !input.equals("n"));
        return input.equals("y");
    }

    /**
     * Handles the “solve” command by checking for and attempting the puzzle
     * at the player’s current coordinates.
     *
     * <ul>
     * <li>At (3,1): Presents the Monolith riddle. On a correct answer (echo),
     * grants a Gate Key and marks the puzzle as solved.</li>
     * <li>At (1,1): Presents the Strix Mastermind puzzle. On a correct answer
     * (seven), grants a Cryo Core and marks the puzzle as solved.</li>
     * <li>Otherwise: Informs the player that there is no puzzle at this
     * location.</li>
     * </ul>
     *
     * If a puzzle has already been solved at the location, the player is notified
     * and no further action is taken.
     *
     * @param player  the Player whose inventory may be updated with rewards
     * @param scanner the Scanner used to read the player’s answer
     * @param map     the GameMap used to determine the player’s current Location
     */
    private static void handleSolve(Player player, Scanner scanner, GameMap map) {
        int x = player.getX(), y = player.getY();
        Location loc = map.getLocation(x, y);

        if (x == 3 && y == 1) {
            if (loc.isEventTriggered()) {
                System.out.println("You have already solved the Monolith's riddle.");
                return;
            }
            System.out.println("The Monolith whispers a riddle:");
            System.out.println("""
                    "I speak without a mouth and hear without ears."
                    "I have nobody, but I come alive with wind. What am I?"
                    """);
            System.out.print("Your answer: ");
            if (scanner.nextLine().trim().equalsIgnoreCase("echo")) {
                System.out.println("The Monolith glows brightly. Correct!");
                player.addItem(new Items("Gate Key"));
                System.out.println("You have gained: Gate Key");
                loc.triggerEvent();
            } else {
                System.out.println("That is not correct. The Monolith remains silent.");
            }
        } else if (x == 1 && y == 1) {
            if (loc.isEventTriggered()) {
                System.out.println("You have already solved the Strix Mastermind's puzzle.");
                return;
            }
            System.out.println("The Strix Mastermind challenges you:");
            System.out.println("""
                    "I am an odd number. Take away one letter and I become even. What number am I?"
                    """);
            System.out.print("Your answer: ");
            if (scanner.nextLine().trim().equalsIgnoreCase("seven")) {
                System.out.println("Correct! You have solved the puzzle.");
                player.addItem(new Items("Cryo Core"));
                System.out.println("You have gained: Cryo Core");
                loc.triggerEvent();
            } else {
                System.out.println("Incorrect. The Strix Mastermind laughs.");
            }
        } else {
            System.out.println("There is no puzzle to solve here.");
        }
    }
}

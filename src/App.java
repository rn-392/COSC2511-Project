import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main application class for the game.
 * This class initializes the game map, player, and handles user input for
 * gameplay.
 * This class handles initialization of core game components (player, map),
 * processes player commands, manages combat and item usage, and
 * coordinates game flow through various menu and interaction methods.
 */
public class App {
    /**
     * Variable that handles game state
     */
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
        player.addItem(Items.STIMPACK);

        displayWelcomeMenu(scanner);

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

        // main game loop
        while (gameStart) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            System.out.println();
            handleCommand(input, player, map, scanner);
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
    private static void displayWelcomeMenu(Scanner scanner) {
        // Title of Game using Ascii - christ
        System.out.println(
                """
                          _______      ___       __          ___       ______ .___________. __    ______     _______       ___   ____    __    ____ .__   __.
                         /  _____|    /   \\     |  |        /   \\     /      ||           ||  |  /      |   |       \\     /   \\  \\   \\  /  \\  /   / |  \\ |  |
                        |  |  __     /  ^  \\    |  |       /  ^  \\   |  ,----'`---|  |----`|  | |  ,----'   |  .--.  |   /  ^  \\  \\   \\/    \\/   /  |   \\|  |
                        |  | |_ |   /  /_\\  \\   |  |      /  /_\\  \\  |  |         |  |     |  | |  |        |  |  |  |  /  /_\\  \\  \\            /   |  . `  |
                        |  |__| |  /  _____  \\  |  `----./  _____  \\ |  `----.    |  |     |  | |  `----.   |  '--'  | /  _____  \\  \\    /\\    /    |  |\\   |
                         \\______| /__/     \\__\\ |_______/__/     \\__\\ \\______|    |__|     |__|  \\______|   |_______/ /__/     \\__\\  \\__/  \\__/     |__| \\__|   """);

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
        System.out.println("You awaken in empty space aboard a crippled spaceship.");
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
        System.out.println("Type 'heal' to use a Stimpack to restore health");
        System.out.println("Type 'use' to use or trade an item at your location.");
        System.out.println("Type 'help' or '?' for commands.");
        System.out.println("Type 'fight' initiates the fight with hostile npc.");
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
     *
     * @param input   The command entered by the user.
     * @param player  The player object representing the current player.
     * @param map     The game map object.
     * @param scanner The scanner object for reading user input.
     */
    private static void handleCommand(String input, Player player, GameMap map, Scanner scanner) {
        Location currentLocation = map.getLocation(player.getX(), player.getY());
        switch (input.trim().toLowerCase()) {
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
                System.out.println("use - Use or trade an item at your location");
                System.out.println("heal - Use a Stimpack to restore health");
                System.out.println("help / ? - Show this help menu");
                System.out.println("fight - initiates the fight with hostile npc.");
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

                // Eridani (2,4): Grand General Zig
                if (x == 2 && y == 4) {
                    if (!CombatCharacters.zig.isDead() && !currentLocation.isEventTriggered()) {
                        CombatSystem.combat(player, CombatCharacters.zig, scanner, map);
                    } else {
                        System.out.println("Zig and his forces have retreated.");
                    }

                    // Ixyll (0,3): Zep Zop
                } else if (x == 0 && y == 3) {
                    if (!CombatCharacters.zepZop.isDead()) {
                        CombatSystem.combat(player, CombatCharacters.zepZop, scanner, map);
                    } else {
                        System.out.println("The jungle rustles gently, but Zep Zop has been defeated.");
                    }

                    // Strix (1,1): Mastermind
                } else if (x == 1 && y == 1) {
                    if (!CombatCharacters.mastermind.isDead()) {
                        CombatSystem.combat(player, CombatCharacters.mastermind, scanner, map);
                    } else {
                        System.out.println("The air is still and cold. The Mastermind has already been dealt with.");
                    }

                    // Ternion (4,0): Rogue Droid
                } else if (x == 4 && y == 0) {
                    if (!CombatCharacters.droid.isDead()) {
                        CombatSystem.combat(player, CombatCharacters.droid, scanner, map);
                    } else {
                        System.out.println("The droid's remains lie motionless among the ruins.");
                    }

                } else {
                    System.out.println("There's nothing to fight here.");
                }
            }

            case "heal" -> {
                if (player.hasItem("Stimpack")) {
                    if (confirm("Do you want to use a Stimpack? (y/n) ", scanner)) {
                        player.removeItem("Stimpack");
                        System.out.println();
                        System.out.println("You use a stimpack and replenish some health.");
                        int oldHealth = player.getHealth();
                        player.setHealth(oldHealth + 50);
                        int newHealth = player.getHealth();
                        System.out.println();
                        System.out.printf("Previous Health: %d\nCurrent Health: %d\n", oldHealth, newHealth);
                    } else {
                        System.out.println("You save your Stimpacks for another time.");
                    }
                } else {
                    System.out.println("You don't have any Stimpacks.");
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

            // case "debug" -> {
            // player.addItem(Items.WARP_DRIVE_FRAGMENT_1);
            // player.addItem(Items.WARP_DRIVE_FRAGMENT_2);
            // player.addItem(Items.WARP_DRIVE_FRAGMENT_3);
            // player.addItem(Items.WARP_DRIVE_FRAGMENT_4);
            // player.addItem(Items.GATE_KEY);
            // player.addItem(Items.LASER_RIFLE);
            // player.addItem(Items.SHIELD_MODULE);
            // player.setHealth(300);
            // System.out.println("DEBUG MODE ACTIVE");
            // }
            default -> System.out.println("Invalid input.");

        }
    }

    /**
     * Attempts to use an item or trigger an event at the player's current location.
     * <p>
     * - At (2,3): Powers up the AI terminal if the player has a Cryo Core,
     * unlocking the armory.
     * - At (2,4): Trades an Ore Chunk with Grand General Zig for a Warp Drive
     * Fragment.
     * - At (0,0): Activates the Rift Gate if the player has a Gate Key and all Warp
     * Drive Fragments.
     * - At (4,3): Trades an Ixyll Fruit with the Jungle Hermit for a Shield Module.
     * - Otherwise: Informs the player there is nothing to use here.
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
                    System.out.println();
                    player.addItem(Items.LASER_RIFLE);
                    System.out.println("You have found: Laser Rifle");
                } else {
                    System.out.println("You decide not to use the Cryo Core right now.");
                }
            } else {
                System.out.println("You need something to power this terminal...");
            }

        } else if (x == 2 && y == 4) {
            //
            if (CombatCharacters.zig.isDead()) { // check if dead
                System.out.println("There's nothing more to do here.");
            } else if (loc.isEventTriggered()) {
                System.out.println("You have already traded with Grand General Zig."); // check if already traded
            } else if (player.hasItem("Ore Chunk")) {
                if (confirm("Trade the Ore Chunk with Grand General Zig? (y/n): ", scanner)) {
                    loc.triggerEvent();
                    loc.setLongDescription("""
                            Grand General Zig's forces have retreated into the dunes.
                            The desert wasteland is now eerily quiet, with only the
                            sound of the wind sweeping across the barren sands.
                            """);
                    player.removeItem("Ore Chunk");
                    System.out.println("You trade the Ore Chunk with Grand General Zig.");
                    System.out.println("He is pleased with the trade and hands you a Warp Drive Fragment.");
                    player.addItem(Items.WARP_DRIVE_FRAGMENT_1);
                    System.out.println("\nYou have received: Warp Drive Fragment 1");
                } else {
                    System.out.println("You hold onto the Ore Chunk for now.");
                }
            } else {
                System.out.println("You don't have anything that pleases Grand General Zig.");
            }

        } else if (x == 0 && y == 0) {

            // rift gate activation
            if (player.hasItem("Gate Key") && player.hasAllWarpFragments()) {
                System.out.println("You use the Gate Key to activate the Rift Gate.");
                System.out.println("\nThe fabric of space-time ripples around the Gate...\n");
                if (confirm("You are about to face the boss. Continue? (y/n): ", scanner)) {
                    player.removeItem("Gate Key");
                    System.out.println("Your ship is engulfed by a blinding light...");
                    System.out.println();
                    CombatSystem.combat(player, CombatCharacters.boss, scanner, map);
                } else {
                    System.out.println();
                    System.out.println("You step back from the Rift Gate.");
                }
            } else {
                System.out.println("The Rift Gate is blocked. You need something to activate it.");
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
    public static boolean confirm(String prompt, Scanner scanner) {
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
     * <li>At (3,1): Presents the Monolith riddle. On a correct answer sequence,
     * grants a Gate Key and marks the puzzle as solved.</li>
     * <li>At (1,1): Presents the Strix Mastermind puzzle. On correct answer
     * (seven),
     * grants a Cryo Core and marks the puzzle as solved.</li>
     * <li>Otherwise: Informs the player that there is no puzzle at this
     * location.</li>
     * </ul>
     *
     * If a puzzle has already been solved at the location, the player is notified.
     *
     * @param player  the Player whose inventory may be updated with rewards
     * @param scanner the Scanner used to read the player’s answer
     * @param map     the GameMap used to determine the player’s current Location
     */
    private static void handleSolve(Player player, Scanner scanner, GameMap map) {
        int x = player.getX(), y = player.getY();
        Location loc = map.getLocation(x, y);

        // monolith riddle
        if (x == 3 && y == 1) {
            if (loc.isEventTriggered()) {
                System.out.println("You have already solved the Monolith's paradox.");
                return;
            }

            System.out.println("The Monolith whispers a paradox:");
            System.out.println("\"This statement is false. True or false?\"");
            System.out.println("Answer correctly three times in a row.\n");

            boolean expected = false; // first answer is false
            int streak = 0;
            final int NEEDED = 3;

            while (streak < NEEDED) {
                System.out.print("Your answer (true/false): ");
                boolean playerAnswer;
                try {
                    playerAnswer = scanner.nextBoolean();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please type true or false.");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();

                if (playerAnswer == expected) {
                    streak++;
                    System.out.printf("...hmm. (%d/%d)\n", streak, NEEDED);
                    // flip answer
                    expected = !expected;
                } else {
                    System.out.println("Wrong. The Monolith resets your progress.\n");
                    streak = 0;
                    expected = false; // restart expecting false first
                }
            }

            // correct solution
            System.out.println("\nThe Monolith glows brightly. You've overcome the challenge.");
            player.addItem(Items.GATE_KEY);
            System.out.println("You have gained: Gate Key");
            loc.setLongDescription("""
                    The etched glyphs of the Monolith have disappeared.
                    It stands still.
                    """);
            loc.triggerEvent();

        } else if (x == 1 && y == 1) {// strix mastermind riddle
            if (loc.isEventTriggered()) {
                System.out.println("You have already solved Mastermind's riddle.");
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

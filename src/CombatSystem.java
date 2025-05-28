import java.util.Random;
import java.util.Scanner;

public class CombatSystem {
    private static final Random random = new Random();

    public static void enemyEncounter(Player player, Scanner scanner, GameMap map,
            combatCharacters enemy) {
        System.out.println("An enemy has appeared!");
        boolean encounter = true;

        while (encounter) {
            System.out.println("Would you like to [f]ight or [r]un?");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "f", "fight" -> {
                    combat(player, enemy, scanner, map);
                    encounter = false; // End encounter after combat
                }
                case "r", "run" -> {
                    if (random.nextBoolean()) {
                        System.out.println("You have successfully ran away!");
                        encounter = false; // Successfully escaped
                    } else {
                        System.out.println("You failed to escape! The enemy launches an attack.");
                        combat(player, enemy, scanner, map);
                        encounter = false; // Combat started, end encounter loop
                    }
                }
                default -> System.out.println("Please enter 'f' to fight or 'r' to run.");
            }
        }
    }

    public static void combat(Player player, combatCharacters enemy, Scanner scanner, GameMap map) {
        Location loc = map.getLocation(player.getX(), player.getY());

        // Display enemy intro text
        switch (enemy.getName()) {
            case "Rogue Droid" ->
                System.out.println(
                        "Rogue Droid readies its weapons!\nRogue Droid: INTRUDER DETECTED. INITIATING TERMINATION PROTOCOL.\n");

            case "Zep Zop" ->
                System.out.println(
                        "Zep Zop screeches and bares its sharp claws at you!\nIt circles you, saliva dripping from its jagged teeth.");

            case "Mastermind" -> {
                if (loc.isEventTriggered()) { // if puzzle already solved
                    System.out.println(
                            "The Mastermind levitates above the ground, its voice echoing inside your head.\nMastermind: 'You still dare defy me? Insolent worm!'");
                } else { // puzzle not solved yet
                    System.out.println(
                            "The Mastermind descends in silence, its eyes glowing with cold intent.\nMastermind: 'You will not survive this intrusion.'");
                }
            }

            case "Grand General Zig" -> {
                System.out.println(
                        "Grand General Zig narrows his gaze, hand hovering over his holster.\nZig: 'You come empty-handed? Pathetic. Let's end this quickly.'");
            }

            case "Emperor Poutine" ->
                System.out.println(
                        "Emperor Poutine roars with fury and points his blade at your chest.\nPoutine: 'THIS IS THE END OF YOU, HUMAN!' He charges with his laser sword drawn!");
        }

        // Combat loop
        boolean fight = true;

        while (fight) {
            System.out.println();
            System.out.printf("%s's HP: %d\n", player.getName(), player.getHealth());
            System.out.printf("%s's HP: %d\n", enemy.getName(), enemy.getHealth());
            System.out.println();
            System.out.println("1. Attack\n2. Inventory\n3. Flee\nChoice: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1", "attack" -> {
                    int weaponDamage = 0;
                    int playerDamage = random.nextInt(16) + 10;
                    if (player.hasItem("Laser Rifle")) {
                        weaponDamage = 25;
                    }

                    int totalDamage = playerDamage + weaponDamage;
                    System.out.println();
                    System.out.printf("You attack and deal %d damage!\n", totalDamage);
                    enemy.setHealth(enemy.getHealth() - totalDamage);

                    // Check if enemy is defeated after player attack
                    if (enemy.getHealth() <= 0) {
                        handleEnemyDefeat(player, enemy);
                        return;
                    }
                }

                case "2", "inventory" -> {
                    player.listInventory();
                    continue; // Skip enemy turn when viewing inventory
                }

                case "3", "flee" -> {
                    if (random.nextBoolean()) {
                        System.out.println("You successfully escaped!");
                        return;
                    } else {
                        System.out.println("You failed to escape!");
                    }
                }

                default -> {
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                    continue; // Skip enemy turn for invalid input
                }
            }

            // Enemy attacks (only if player didn't view inventory or enter invalid input)
            if (choice.equals("1") || choice.equals("attack") || choice.equals("3") || choice.equals("flee")) {
                int enemyDamage = enemy.rollDamage();
                System.out.println();
                System.out.printf("%s attacks and deals %d damage!\n", enemy.getName(), enemyDamage);
                player.setHealth(player.getHealth() - enemyDamage);

                // Check if player is defeated
                if (player.getHealth() <= 0) {
                    System.out.println("\nYou have been defeated...");
                    System.exit(0);
                }
            }
        }
    }

    private static void handleEnemyDefeat(Player player, combatCharacters enemy) {
        enemy.setDead(true);
        System.out.println();
        System.out.printf("You defeated %s!\n", enemy.getName());

        switch (enemy.getName()) {
            case "Grand General Zig" -> {
                player.addItem(Items.WARP_DRIVE_FRAGMENT_1);
                player.addItem(Items.STIMPACK);
                System.out.println("You have gained: Warp Drive Fragment 1, Stimpack");
            }
            case "Rogue Droid" -> {
                player.addItem(Items.WARP_DRIVE_FRAGMENT_2);
                System.out.println("Rogue Droid drops a shiny component. You gained a warp drive fragment!");
            }
            case "Zep Zop" -> {
                player.addItem(Items.IXYLL_FRUIT);
                player.addItem(Items.WARP_DRIVE_FRAGMENT_3);
                System.out.println("Zep Zop drops a strange fruit.");
                System.out.println("You have gained: Ixyll Fruit, Warp Drive Fragment 3");

            }
            case "Mastermind" -> {
                player.addItem(Items.CRYO_CORE);
                player.addItem(Items.WARP_DRIVE_FRAGMENT_4);
                player.addItem(Items.STIMPACK);
                System.out.println("You have gained: Cryo Core, Warp Drive Fragment 4, Stimpack");
            }
            case "Emperor Poutine" -> {
                System.out.println(
                        "You have defeated Emperor Poutine! The Rift Gate activates...\nYou warp out and escape.");
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }
}
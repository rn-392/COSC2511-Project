
import java.util.Random;
import java.util.Scanner;

/**
 * The CombatSystem class handles combat encounters between the player and
 * enemies.
 * It includes methods for initiating combat, processing player and enemy
 * actions,
 * and handling the outcomes of battles.
 * 
 * @author Christ Khin
 */
public class CombatSystem {
    private static final Random random = new Random();

    // public static void enemyEncounter(Player player, Scanner scanner, GameMap
    // map,
    // combatCharacters enemy) {
    // System.out.println("An enemy has appeared!");
    // boolean encounter = true;

    // while (encounter) {
    // System.out.println("Would you like to [f]ight or [r]un?");
    // String choice = scanner.nextLine().trim().toLowerCase();

    // switch (choice) {
    // case "f", "fight" -> {
    // combat(player, enemy, scanner, map);
    // encounter = false; // End encounter after combat
    // }
    // case "r", "run" -> {
    // if (random.nextBoolean()) {
    // System.out.println("You have successfully ran away!");
    // encounter = false; // Successfully escaped
    // } else {
    // System.out.println("You failed to escape! The enemy launches an attack.");
    // combat(player, enemy, scanner, map);
    // encounter = false; // Combat started, end encounter loop
    // }
    // }
    // default -> System.out.println("Please enter 'f' to fight or 'r' to run.");
    // }
    // }
    // }

    /**
     * Runs a fight between the player and an enemy.
     * Shows the enemy’s intro line, then lets the player choose to attack, heal, or
     * try to run.
     * If the player attacks, damage is dealt to the enemy.
     * If the player uses a Stimpack, they heal and the enemy skips their turn.
     * If the player runs and succeeds, the fight ends.
     * If the enemy attacks, damage is reduced if the player has a Shield Module.
     * Ends when the enemy is defeated, the player escapes, or the player dies.
     *
     * @param player  The player participating in combat.
     * @param enemy   The enemy being fought.
     * @param scanner Scanner object used for player input.
     * @param map     The game map, used to update the location state upon enemy
     *                defeat.
     */
    public static void combat(Player player, CombatCharacters enemy, Scanner scanner, GameMap map) {
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
            System.out.println("1. Attack\n2. Heal\n3. Flee\n\nChoice: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1", "attack" -> {
                    int weaponDamage = 0;
                    int playerDamage = random.nextInt(16) + 10;
                    if (player.hasItem("Laser Rifle")) {
                        weaponDamage = 30;
                    }

                    int totalDamage = playerDamage + weaponDamage;
                    System.out.println();
                    if (player.hasItem("Laser Rifle")) {
                        System.out.printf("You shoot your Laser Rifle and deal %d damage!\n", totalDamage);
                    } else {
                        System.out.printf("You attack and deal %d damage!\n", totalDamage);
                    }

                    enemy.setHealth(enemy.getHealth() - totalDamage);

                    // Check if enemy is defeated after player attack
                    if (enemy.getHealth() <= 0) {
                        handleEnemyDefeat(player, enemy, map);
                        return;
                    }
                }

                case "2", "heal" -> {
                    if (player.hasItem("Stimpack")) {
                        if (App.confirm("Do you want to use a Stimpack? (y/n) ", scanner)) {
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
                    continue; // Skip enemy turn when healing
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

            // Enemy attacks (only if player didn't heal or enter invalid input)
            if (choice.equals("1") || choice.equals("attack") || choice.equals("3") || choice.equals("flee")) {
                int enemyDamage = enemy.rollDamage();

                if (player.hasItem("Shield Module")) {
                    int mitigated = (int) (enemyDamage * 0.5); // reduce damage by 50%
                    System.out.printf("\nYour Shield Module activates! Incoming damage reduced from %d to %d.\n",
                            enemyDamage, mitigated);
                    enemyDamage = mitigated;
                }
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

    /**
     * Triggers when the player beats an enemy.
     * Gives the player certain items depending on which enemy was defeated.
     * Changes the location so it’s no longer hostile and updates the description.
     * If the final boss "Emperor Poutine" is defeated, the game ends.
     *
     * @param player The player who defeated the enemy.
     * @param enemy  The defeated enemy.
     * @param map    The game map used to locate and update the current location.
     */
    private static void handleEnemyDefeat(Player player, CombatCharacters enemy, GameMap map) {
        enemy.setDead(true);
        System.out.println();
        System.out.printf("You defeated %s!\n", enemy.getName());
        Location loc = map.getLocation(player.getX(), player.getY());

        switch (enemy.getName()) {
            case "Grand General Zig" -> {
                player.addItem(Items.WARP_DRIVE_FRAGMENT_1);
                player.addItem(Items.STIMPACK);
                System.out.println("You have gained: Warp Drive Fragment 1, Stimpack");
                loc.setHostile(false);
                loc.setLongDescription("""
                        The crimson sun beats down over the scorched sands of Eridani.
                        The dunes stretch out in silence, a barren expanse reclaimed from tyranny.
                        """);
            }
            case "Rogue Droid" -> {
                player.addItem(Items.WARP_DRIVE_FRAGMENT_2);
                player.addItem(Items.STIMPACK);
                System.out.println("You have gained: Warp Drive Fragment 2, Stimpack");
                loc.setHostile(false);
                loc.setLongDescription("""
                        Ternion's skyline, a jagged silhouette of twisted spires, looms over the quiet wreckage below.
                        The city's broken streets echo with ghosts of data and faded signals.
                        """);
            }
            case "Zep Zop" -> {
                player.addItem(Items.IXYLL_FRUIT);
                player.addItem(Items.WARP_DRIVE_FRAGMENT_3);
                player.addItem(Items.STIMPACK);
                System.out.println("Zep Zop drops a strange fruit.");
                System.out.println("You have gained: Ixyll Fruit, Warp Drive Fragment 3, Stimpack");
                loc.setHostile(false);
                loc.setLongDescription("""
                            Vines hang heavy with dew, and shafts of golden light pierce the jungle canopy.
                            Zep Zop's shrine lies in solemn ruin, half-consumed by the encroaching wilderness.
                        """);

            }
            case "Mastermind" -> {
                player.addItem(Items.CRYO_CORE);
                player.addItem(Items.WARP_DRIVE_FRAGMENT_4);
                player.addItem(Items.STIMPACK);
                System.out.println("You have gained: Cryo Core, Warp Drive Fragment 4, Stimpack");
                loc.setHostile(false);
                loc.setLongDescription("""
                        The freezing gales no longer scream with malevolent whispers.
                        A blue glow pulses faintly from the Cryo Core chamber, now dormant.
                        """);
            }
            case "Emperor Poutine" -> {
                System.out.println(
                        "You have defeated Emperor Poutine! The Rift Gate activates...\nYou warp out and escape.");
                System.out.println("\nThanks for playing!");
                System.exit(0);
            }
        }
    }
}
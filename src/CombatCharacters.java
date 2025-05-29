
import java.util.Random;

/**
 * Represents an enemy character in the game with attributes such as name,
 * type, health, and damage range.
 * Provides functionality for rolling random damage and tracking death status.
 *
 * @author Christ Khin
 */
public class CombatCharacters {
    /**
     * The name of the character
     */
    private final String name;

    /**
     * The type of character
     */
    private final String charType;

    /**
     * The current health of the character.
     */
    private int health;

    /**
     * The minimum amount of damage this character can deal.
     */
    private final int minDamage;

    /**
     * The maximum amount of damage this character can deal.
     */
    private final int maxDamage;

    /**
     * Whether character is dead or not.
     */
    private boolean isDead;

    /** Enemies */
    public static CombatCharacters droid = new CombatCharacters("Rogue Droid", "Robot", 60, 5, 15);
    public static CombatCharacters zepZop = new CombatCharacters("Zep Zop", "Xeno", 80, 5, 15);
    public static CombatCharacters mastermind = new CombatCharacters("Mastermind", "AI", 100, 5, 20);
    public static CombatCharacters zig = new CombatCharacters("Grand General Zig", "Alpha Xeno", 150, 10, 30);
    public static CombatCharacters boss = new CombatCharacters("Emperor Poutine", "Grand Xeno", 200, 25, 50);

    /**
     * Constructs a new combat character with specified attributes.
     *
     * @param name     the name of the character
     * @param charType the type/classification of the character
     * @param health   the starting health value
     * @param min      the minimum damage this character can deal
     * @param max      the maximum damage this character can deal
     */
    public CombatCharacters(String name, String charType, int health, int min, int max) {
        this.name = name;
        this.charType = charType;
        this.health = health;
        this.minDamage = min;
        this.maxDamage = max;
        this.isDead = false;
    }

    /**
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type/classification of the character
     */
    public String getCharType() {
        return charType;
    }

    /**
     * @return the current health of the character
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the character's health to a new value.
     *
     * @param health the new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Rolls and returns a random damage value within the character's damage range.
     *
     * @return the damage dealt
     */
    public int rollDamage() {
        return new Random().nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    /**
     * @return true if the character is marked as dead, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets the death status of the character.
     *
     * @param isDead true to mark as dead, false otherwise
     */
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
}

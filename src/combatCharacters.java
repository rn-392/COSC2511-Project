import java.util.Random;

public class combatCharacters {

    private final String name;
    private final String charType;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private boolean isDead;

    public static combatCharacters droid = new combatCharacters("Rogue Droid", "Robot", 60, 5, 10);
    public static combatCharacters zepZop = new combatCharacters("Zep Zop", "Xeno", 80, 5, 15);
    public static combatCharacters mastermind = new combatCharacters("Mastermind", "AI", 100, 5, 20);
    public static combatCharacters zig = new combatCharacters("Grand General Zig", "Alpha Xeno", 150, 10, 25);
    public static combatCharacters boss = new combatCharacters("Emperor Poutine", "Grand Xeno", 200, 20, 30);

    public combatCharacters(String name, String charType, int health, int min, int max) {
        this.name = name;
        this.charType = charType;
        this.health = health;
        this.minDamage = min;
        this.maxDamage = max;
        this.isDead = false;
    }

    public String getName() {
        return name;
    }

    public String getCharType() {
        return charType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int rollDamage() {
        return new Random().nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
}
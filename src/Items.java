/**
 * Represents an item in the game.
 * Each item has a name and can be used for various gameplay mechanics like
 * healing,
 * unlocking areas, or enhancing combat.
 * 
 * @author George Thodis
 */
public class Items {

    /**
     * Name of the item
     */
    private String name;

    /**
     * Creates a new item with the given name.
     *
     * @param name The name of the item.
     */
    public Items(String name) {
        this.name = name;
    }

    /**
     * Gets the item's name.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item's name.
     *
     * @param name The new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    // Predefined items used throughout the game.
    public static final Items ORE_CHUNK = new Items("Ore Chunk");
    public static final Items LASER_RIFLE = new Items("Laser Rifle");
    public static final Items GATE_KEY = new Items("Gate Key");
    public static final Items CRYO_CORE = new Items("Cryo Core");
    public static final Items SHIELD_MODULE = new Items("Shield Module");
    public static final Items IXYLL_FRUIT = new Items("Ixyll Fruit");
    public static final Items STIMPACK = new Items("Stimpack");
    public static final Items WARP_DRIVE_FRAGMENT_1 = new Items("Warp Drive Fragment 1");
    public static final Items WARP_DRIVE_FRAGMENT_2 = new Items("Warp Drive Fragment 2");
    public static final Items WARP_DRIVE_FRAGMENT_3 = new Items("Warp Drive Fragment 3");
    public static final Items WARP_DRIVE_FRAGMENT_4 = new Items("Warp Drive Fragment 4");

    /**
     * Resources array
     */
    public static final Items[] RESOURCES = {
            ORE_CHUNK, CRYO_CORE, SHIELD_MODULE, IXYLL_FRUIT, STIMPACK
    };

    /**
     * Warp Drive Fragments array
     */
    public static final Items[] FRAGMENTS = {
            WARP_DRIVE_FRAGMENT_1,
            WARP_DRIVE_FRAGMENT_2,
            WARP_DRIVE_FRAGMENT_3,
            WARP_DRIVE_FRAGMENT_4
    };

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // Items[] items = new Items[5];
    // ArrayList<Items> inventory = new ArrayList<>(); // creates inventory array
    // boolean[] pickedUp = new boolean[5];

    // // Picking up items
    // for (int i = 0; i < items.length; i++) {
    // items[i] = new Items("Rocket Piece " + (i + 1));
    // }

    // // Ask user to pick up each item
    // for (int i = 0; i < items.length; i++) {
    // System.out.println("You found " + items[i].getName() + ". Would you like to
    // pick it up? (yes/y)");
    // String input = scanner.nextLine().trim().toLowerCase();

    // if (input.equals("yes") || input.equals("y")) {
    // pickedUp[i] = true;
    // System.out.println(items[i].getName() + " has been picked up.");
    // } else {
    // pickedUp[i] = false;
    // System.out.println("You didn't pick up " + items[i].getName() + ".");
    // }
    // }

    // System.out.println("\nYour Inventory:");
    // if (inventory.isEmpty()) {
    // System.out.println("Your inventory is empty. Completely.");
    // } else {
    // for (Items item : inventory) {
    // System.out.println(" - " + item.getName());
    // }
    // }

    // scanner.close();
    // }

}

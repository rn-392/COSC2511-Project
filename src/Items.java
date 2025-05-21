import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
//Contains both Items and Inventory classes

//@georgethodis
public class Items {
    private String name;

    public Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Items[] items = new Items[5];
        ArrayList<Items> inventory = new ArrayList<>(); //creates inventory array 
        boolean[] pickedUp = new boolean[5];

        // Picking up items
        for (int i = 0; i < items.length; i++) {
            items[i] = new Items("Rocket Piece " + (i + 1));
        }

        // Ask user to pick up each item
        for (int i = 0; i < items.length; i++) {
            System.out.println("You found " + items[i].getName() + ". Would you like to pick it up? (yes/y)");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                pickedUp[i] = true;
                System.out.println(items[i].getName() + " has been picked up.");
            } else {
                pickedUp[i] = false;
                System.out.println("You didn't pick up " + items[i].getName() + ".");
            }
        }

        System.out.println("\nYour Inventory:");
        if (inventory.isEmpty()){
            System.out.println("Your inventory is empty. Completely.");
        } else {
            for(Items item : inventory) {
                System.out.println(" - " + item.getName());
            }
        }

        scanner.close();
    }    

}

//@ChristKhin
public class combatCharacters {
    private String  name;
    private String charType;
    private int health;

    public combatCharacters(String name, String charType, int health){
        this.name = name;
        this.charType = charType;
        this.health = health;
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

}







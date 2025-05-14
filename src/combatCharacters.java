//@ChristKhin
public class combatCharacters {
    private String  name;
    private String charType;
    private boolean health;

    public combatCharacters(String name, String charType, boolean health){
        this.name = name;
        this.charType = charType;
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharType() {
        return this.charType;
    }

    public void setCharType(String charType) {
        this.charType = charType;
    }

    public boolean isHealth() {
        return this.health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return String.format("Character Info\nName: %s\nType: %f\nHealth:", this.name, this.charType, this.health);
    }
 
    

   


    

}







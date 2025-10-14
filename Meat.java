/*
 * Class for the meat ingredient in the game.
 */
public class Meat extends Ingredient {
    private boolean cooked;

    /**
     * Constructor for meat.
     */
    public Meat(String name, boolean chopped) {
        super(name);
        cooked = false;
    }   

    public boolean isCooked() {
        return cooked;
    }

    public void cook() {
        this.cooked = true;
    }
}

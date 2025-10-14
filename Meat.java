/*
 * Class for the meat ingredient in the game.
 */
public class Meat extends PrepIngredient {
    private boolean cooked;

    /**
     * Constructor for meat.
     */
    public Meat(String name) {
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

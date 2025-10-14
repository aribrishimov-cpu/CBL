/*
 * Class for the lettuce, tomato.
 */
public class Ingredient {
    private String name;
    private boolean chopped;

    /**
     * Construtor of an ingredient.
     */
    public Ingredient(String name) {
        this.name = name;
        chopped = false;
    }

    public String getName() {
        return name;
    }

    public boolean isChopped() {
        return chopped;
    }

    public void chop() {
        this.chopped = true;
    }
}

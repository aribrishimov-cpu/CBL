public class PrepIngredient extends Ingredient {
    private boolean chopped;

    public PrepIngredient(String name) {
        super(name);
        chopped = false;
    }

    public boolean isChopped() {
        return chopped;
    }

    public void chop() {
        this.chopped = true;
    }

}
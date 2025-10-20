public abstract class Ingredient {
    protected String name;
    protected boolean chopped = false;
    protected boolean cooked = false;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isChopped() {
        return chopped;
    }

    public boolean isCooked() {
        return cooked;
    }

    public void chop() {
        // Default: can’t be chopped unless overridden
    }

    public void cook() {
        // Default: can’t be cooked unless overridden
    }
}

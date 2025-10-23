/**
 * class for the ingredients that can be choppee but not cooked.
 */
public class PrepIngredient extends Ingredient {
    public PrepIngredient(String name) {
        super(name);
    }

    @Override
    public void chop() {
        if (!chopped) {
            chopped = true;
        }
    }

    @Override
    public void cook() {
        // do nothing — these can’t be cooked
    }
}

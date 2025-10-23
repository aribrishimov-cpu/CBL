/**
 * Class for meat since it can be both chopped and cooked.
 */
public class Meat extends Ingredient {
    public Meat(String name) {
        super(name);
    }

    @Override
    public void chop() {
        if (!chopped && !cooked) {  // can only chop raw meat
            chopped = true;
        }
    }

    @Override
    public void cook() {
        if (!cooked && chopped) {
            cooked = true;
        }
    }
}

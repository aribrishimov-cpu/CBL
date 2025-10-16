import java.util.Timer;
import java.util.TimerTask;

/*
 * Class for the lettuce, tomato.
 */
public class Ingredient {
    private String name;

    /**
     * Construtor of an ingredient.
     */
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

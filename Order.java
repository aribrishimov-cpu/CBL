import java.util.Stack;

/**
 * Class for the orders.
 */
public class Order {
    private Stack<String> order = new Stack<>();

    /**
     * building an an order.
     */
    public Order(String... ingredients) {
        for (String ingredient : ingredients) {
            order.push(ingredient);
        }
    }

    public Stack<String> getItems() {
        return order;
    }
}

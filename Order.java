import java.util.Collections;
import java.util.Stack;

public class Order {
    private Stack<String> order = new Stack<>();
    private boolean complete;

    public Order(String... ingredients) {
        for (String ingredient : ingredients) {
            order.push(ingredient);
        }
        complete = false;
    }

    public Stack<String> getItems() {
        return order;
    }

    public boolean isComplete() {
        return complete;
    }

    /**
     * Check if the submitted order matches the required ingredients.
     */
    public boolean checkSubmittedOrder(Stack<Ingredient> submittedOrder) {
        if (submittedOrder.size() != order.size()) {
            System.out.println("Order can’t be submitted (size mismatch)");
            return false;
        }

        Stack<String> copyOrder = (Stack<String>) order.clone();
        Stack<String> copySubmittedOrder = new Stack<>();

        for (Ingredient ing : submittedOrder) {
            // require meat to be cooked, veggies chopped
            if ((ing.getName().contains("meat") && !ing.isCooked())
                || ((ing.getName().contains("lettuce") 
                || ing.getName().contains("tomato")) && !ing.isChopped())) {
                System.out.println("Order can’t be submitted (ingredient not ready)");
                return false;
            }
            copySubmittedOrder.push(ing.getName().replaceAll("[0-9]", "")); 
        }

        copyOrder.replaceAll(s -> s.replaceAll("[0-9]", "")); // normalize names

        Collections.sort(copyOrder);
        Collections.sort(copySubmittedOrder);

        boolean match = copyOrder.equals(copySubmittedOrder);
        complete = match;

        System.out.println(match ? "Order submitted!" : "Order incorrect!");
        return match;
    }
}
import java.util.Stack;

/**
 * Handles checking if the first incomplete order can be submitted.
 */
public class SubmitStation {
    private IngredientMap map;

    public SubmitStation(IngredientMap map) {
        this.map = map;
    }



    /**
     * Only allow the first NOT completed order to be submitted.
     */
    public void checkSubmittingTile(OrdersPanel ordersPanel) {
        Stack<Ingredient> tile1 = map.getTileStack(11, 6);
        Stack<Ingredient> tile2 = map.getTileStack(11, 7);

        if (tile1.isEmpty() && tile2.isEmpty()) {
            System.out.println("Nothing to submit!");
            return;
        }

        // Find first incomplete order
        int firstIncomplete = -1;
        for (int i = 0; i < ordersPanel.getOrderCount(); i++) {
            if (!ordersPanel.getOrder(i).isComplete()) {
                firstIncomplete = i;
                break;
            }
        }

        if (firstIncomplete == -1) {
            System.out.println("All orders are already complete!");
            return;
        }

        Order order = ordersPanel.getOrder(firstIncomplete);

        if (order.checkSubmittedOrder(tile1) 
            || order.checkSubmittedOrder(tile2)) {
            map.emptyTileStack(11, 6);
            map.emptyTileStack(11, 7); // fix: clear the second tile
            System.out.println("✅ Correct order submitted!");
            
            // ✅ Important: repaint the orders panel so it updates visually
            ordersPanel.repaint();
        } else {
            System.out.println("❌ Wrong order!");
        }
    }

}

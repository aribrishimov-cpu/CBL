import java.awt.*;
import javax.swing.*;

/**
 * main file to run the game from.
 */
public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map map = new Map();
            IngredientMap ingredientMap = new IngredientMap();
            OrdersPanel ordersPanel = new OrdersPanel();
            SubmitStation submitStation = new SubmitStation(ingredientMap);
            MapPanel mapPanel = new MapPanel(map, ordersPanel, submitStation, ingredientMap);


            JFrame frame = new JFrame("Cooking Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(mapPanel, BorderLayout.CENTER);
            frame.getContentPane().add(ordersPanel, BorderLayout.EAST);

            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

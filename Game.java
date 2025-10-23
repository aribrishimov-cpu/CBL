import java.awt.*;
import javax.swing.*;

/**
 * main file to run the game from.
 */
public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map map = new Map();
            MapPanel mapPanel = new MapPanel(map);

            OrdersPanel ordersPanel = new OrdersPanel();

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

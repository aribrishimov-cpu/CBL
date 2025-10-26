package src.core;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import src.cbl.ui.MapPanel;
import src.cbl.ui.OrdersPanel;
import src.cbl.ui.Timer;


/**
 * main file to run the game from.
 */
public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map map = new Map();
            OrdersPanel ordersPanel = new OrdersPanel();    
            MapPanel mapPanel = new MapPanel(map, ordersPanel);
            Timer timerPanel = new Timer();

            JFrame frame = new JFrame("Cooking Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLayeredPane layeredPane = new JLayeredPane();
            int width = map.getWidth() * 64;   
            int height = map.getHeight() * 64;
            layeredPane.setPreferredSize(new Dimension(width, height));

            mapPanel.setBounds(0, 0, width, height);
            layeredPane.add(mapPanel, Integer.valueOf(0));

            timerPanel.setBounds(0, 0, width, height);
            layeredPane.add(timerPanel, Integer.valueOf(1));

            frame.getContentPane().setLayout(new java.awt.BorderLayout());
            frame.getContentPane().add(layeredPane, java.awt.BorderLayout.CENTER);
            frame.getContentPane().add(ordersPanel, java.awt.BorderLayout.EAST);

            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}

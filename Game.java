import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Map map = new Map();                 // create the map
            MapPanel panel = new MapPanel(map);  // create the game panel

            JFrame frame = new JFrame("Cooking Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);   // center the window
            frame.setVisible(true);
        });
    }
}

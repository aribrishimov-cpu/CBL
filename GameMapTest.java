import javax.swing.*;

public class GameMapTest {
    public static void main(String[] args) {
        Map map = new Map();
        MapPanel panel = new MapPanel(map);

        JFrame frame = new JFrame("Cooking Game Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
import javax.swing.*;

public class Window {
    void open() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cook or get cooked");

            Map map = new Map();
            MapPanel mapPanel = new MapPanel(map);

            frame.getContentPane().add(mapPanel);   
            frame.pack();                           
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new Window().open();
    }
}

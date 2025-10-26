import javax.swing.*;
import java.awt.*;

/*
 * Shows the victory screen if you complete the orders.
 */

public class Victory {

    public static void showVictory() {
        JFrame frame = new JFrame("Victory!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel message = new JLabel("You Won!", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 28));
        frame.add(message, BorderLayout.CENTER);

        JButton closeBtn = new JButton("Exit");
        closeBtn.addActionListener(e -> System.exit(0));
        frame.add(closeBtn, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

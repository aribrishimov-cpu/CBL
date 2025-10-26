package src.cbl.ui;

import java.awt.*;
import javax.swing.*;


/**
 * Shows the game over screen.
 */
public class GameOver {

    /** 
    * Shows a "Game Over" window with an exit button. 
    */
    public static void showGameOver() {
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JLabel message = new JLabel("Game Over.", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 28));
        frame.add(message, BorderLayout.CENTER);

        JButton closeBtn = new JButton("Exit");
        closeBtn.addActionListener(e -> System.exit(0));
        frame.add(closeBtn, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

package src.cbl.ui;

import java.awt.*;
import javax.swing.*;

/**
 * Displays a countdown timer for the game.
 * Stops and triggers game over when it reaches 0.
 */
public class Timer extends JPanel {
    private int timeLeft = 120; 
    private javax.swing.Timer swingTimer;

    /**
     * Constructor for timer.
     */
    public Timer() {
        setOpaque(false); // transparent background

        swingTimer = new javax.swing.Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
                repaint();
            } else {
                swingTimer.stop();
                // When the timer reaches 0, show a game over screen
                GameOver.showGameOver();
            }
        });

        swingTimer.start();
    }

    /**
     * Draws the timer on the screen.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.BLACK);

        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        String timeStr = String.format("%d:%02d", minutes, seconds);

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(timeStr);

        int x = getWidth() - textWidth - 20;
        int y = 40;
        g.drawString(timeStr, x, y);
    }

    /*
    * stops the timer after winning
    */
    public void stopTimer() {
        swingTimer.stop();
    }

    /*
     * Shows how much time remains
     */
    public int getTimeLeft() {
        return timeLeft;
    }
}

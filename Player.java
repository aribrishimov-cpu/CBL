import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JComponent {
    private int tileX = 5; 
    private int tileY = 5; 
    private final int TILE_SIZE = 64;
    private Map map; 

    public Player(Map map) {
        this.map = map;
        setSize(TILE_SIZE, TILE_SIZE);
        setOpaque(false);

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePlayer(e);
            }
        });
    }

    private void movePlayer(KeyEvent e) {
        int newX = tileX;
        int newY = tileY;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> newX--;
            case KeyEvent.VK_RIGHT -> newX++;
            case KeyEvent.VK_UP -> newY--;
            case KeyEvent.VK_DOWN -> newY++;
        }

        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight()) {
            if (map.getTiles()[newY][newX] != TileType.counterTop) {
                tileX = newX;
                tileY = newY;
            }
        }

        getParent().repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int playerSize = 40;
        int x = (TILE_SIZE - playerSize) / 2;
        int y = (TILE_SIZE - playerSize) / 2;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, playerSize, playerSize);
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }
}

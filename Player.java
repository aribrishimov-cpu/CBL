import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JComponent {
    private int tileX = 5; 
    private int tileY = 5; 
    private final int tileSize = 64;
    private Map map; 
    private Direction facing = Direction.DOWN; // default facing position

    public Player(Map map) {
        this.map = map;
        setSize(tileSize, tileSize);
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
            case KeyEvent.VK_LEFT -> {
                newX--;
                facing = Direction.LEFT;
            }
            case KeyEvent.VK_RIGHT -> {
                newX++;
                facing = Direction.RIGHT;
            }
            case KeyEvent.VK_UP -> {
                newY--;
                facing = Direction.UP;
            }
            case KeyEvent.VK_DOWN -> { 
                newY++;
                facing = Direction.DOWN;
            }
        }

        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight()) {
            if (map.getTiles()[newY][newX] != TileType.counterTop) {
                tileX = newX;
                tileY = newY;
            }
        }

        getParent().repaint(); 
    }

    public Direction getDirection() {
        return facing;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int playerSize = 40;
        int x = (tileSize - playerSize) / 2;
        int y = (tileSize - playerSize) / 2;
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

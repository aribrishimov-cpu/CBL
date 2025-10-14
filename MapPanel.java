import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapPanel extends JPanel {
    private final int TILE_SIZE = 64;
    private Map map;

    private int playerX = 5;
    private int playerY = 5;

    public MapPanel(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(map.getWidth() * TILE_SIZE, map.getHeight() * TILE_SIZE));

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
        int key = e.getKeyCode();

        int newX = playerX;
        int newY = playerY;

        switch (key) {
            case KeyEvent.VK_LEFT -> newX--;
            case KeyEvent.VK_RIGHT -> newX++;
            case KeyEvent.VK_UP -> newY--;
            case KeyEvent.VK_DOWN -> newY++;
        }

        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight()) {
            if (map.getTiles()[newY][newX] == TileType.empty) {
                playerX = newX;
                playerY = newY;
            }
        }

        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        TileType[][] tiles = map.getTiles();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                drawTile(g, tiles[y][x], x * TILE_SIZE, y * TILE_SIZE);
            }
        }

        drawPlayer(g);
    }

    private void drawTile(Graphics g, TileType type, int x, int y) {
        switch (type) {
            case empty -> g.setColor(Color.LIGHT_GRAY);
            case choppingBoard -> g.setColor(Color.ORANGE);
            case pan -> g.setColor(Color.RED);
            case trashBin -> g.setColor(Color.BLACK);
            case tomatoBox -> g.setColor(Color.PINK);
            case lettuceBox -> g.setColor(Color.GREEN);
            case meatBox -> g.setColor(Color.MAGENTA);
            case bunBox -> g.setColor(Color.YELLOW);
            case orderSubmit -> g.setColor(Color.WHITE);
            case counterTop -> g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
    }

    private void drawPlayer(Graphics g) {
        int playerSize = 40;
        int x = playerX * TILE_SIZE + (TILE_SIZE - playerSize) / 2;
        int y = playerY * TILE_SIZE + (TILE_SIZE - playerSize) / 2;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, playerSize, playerSize);
    }
}

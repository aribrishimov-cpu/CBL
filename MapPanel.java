import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private final int TILE_SIZE = 64;
    private Map map;

    public MapPanel(Map map) {
        this.map = map;
        setPreferredSize(new Dimension(map.getWidth() * TILE_SIZE, map.getHeight() * TILE_SIZE));
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
}
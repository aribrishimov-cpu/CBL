import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapPanel extends JPanel {
    private final int tileSize = 64;
    private Map map;
    private Player player;
    private int frontX = -1;
    private int frontY = -1;
    private IngredientMap ingredientMap = new IngredientMap();

    public MapPanel(Map map) {
        this.map = map;
        this.player = new Player(5, 5, ingredientMap, this);
        setPreferredSize(new Dimension(map.getWidth() * tileSize, map.getHeight() * tileSize));
        setFocusable(true);
        setRequestFocusEnabled(true);
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                requestFocusInWindow();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKey(e);
                updateFrontTile();
                repaint();
            }
        });

        updateFrontTile();
    }

    private void handleKey(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> player.move(Direction.LEFT, map);
            case KeyEvent.VK_RIGHT -> player.move(Direction.RIGHT, map);
            case KeyEvent.VK_UP -> player.move(Direction.UP, map);
            case KeyEvent.VK_DOWN -> player.move(Direction.DOWN, map);
            case KeyEvent.VK_Q -> {
                if (map.getTile(frontX, frontY) == TileType.counterTop 
                    || map.getTile(frontX, frontY) == TileType.pan
                    || map.getTile(frontX, frontY) == TileType.choppingBoard
                    || map.getTile(frontX, frontY) == TileType.orderSubmit) {
                    player.drop();
                    ingredientMap.printTiles();
                } else {
                    System.out.println("you cant drop this here");
                }
            }
            case KeyEvent.VK_E -> {
                if (map.getTile(frontX, frontY) == TileType.bunBox) {
                    ingredientMap.fillTile(frontX, frontY, new Bun("bun1"));
                } else if (map.getTile(frontX, frontY) == TileType.meatBox) {
                    ingredientMap.fillTile(frontX, frontY, new Meat("meat1"));
                } else if (map.getTile(frontX, frontY) == TileType.lettuceBox) {
                    ingredientMap.fillTile(frontX, frontY, new PrepIngredient("lettuce1"));
                } else if (map.getTile(frontX, frontY) == TileType.tomatoBox) {
                    ingredientMap.fillTile(frontX, frontY, new PrepIngredient("tomato1"));
                }
                player.pickUp();  
                ingredientMap.printTiles();
            }
        }
    }

    private void updateFrontTile() {
        frontX = player.getTileX();
        frontY = player.getTileY();

        switch (player.getFacing()) {
            case UP -> frontY--;
            case DOWN -> frontY++;
            case LEFT -> frontX--;
            case RIGHT -> frontX++;
        }
        
    }

    public int getFrontX() {
        return frontX;
    }    

    public int getFrontY() {
        return frontY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw map
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                TileType type = map.getTiles()[y][x];
                g.setColor(switch(type) {
                    case empty -> Color.LIGHT_GRAY;
                    case choppingBoard -> Color.ORANGE;
                    case pan -> Color.RED;
                    case trashBin -> Color.BLACK;
                    case tomatoBox -> Color.PINK;
                    case lettuceBox -> Color.GREEN;
                    case meatBox -> Color.MAGENTA;
                    case bunBox -> Color.YELLOW;
                    case orderSubmit -> Color.WHITE;
                    case counterTop -> Color.BLUE;
                });
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
        //Highlight the tile in front
        g.setColor(new Color(255, 255, 0, 128));    
        g.fillRect(frontX * tileSize, frontY * tileSize, tileSize, tileSize);

        //Draw the Player
        int playerSize = 40;
        int x = player.getTileX() * tileSize + (tileSize - playerSize) / 2;
        int y = player.getTileY() * tileSize + (tileSize - playerSize) / 2;
        g.setColor(Color.BLACK);
        g.fillRect(x, y, playerSize, playerSize);
    }
}

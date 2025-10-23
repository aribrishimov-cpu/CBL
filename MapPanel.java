import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import javax.swing.*;

/**
 * everything visual regarding the map happens here.
 */
public class MapPanel extends JPanel {
    private final int tileSize = 64;
    private Map map;
    private Player player;
    private int frontX = -1;
    private int frontY = -1;
    private IngredientMap ingredientMap = new IngredientMap();
    
    private Image bunImg;
    private Image tomatoImg;
    private Image lettuceImg;
    private Image panImg;
    private Image choppingImg;
    private Image meatImg;
    private Image choopedTomatoImg;
    private Image choppedLettuceImg;
    private Image choppedMeatImg;
    private Image cookedMeatImg;
    private int cntr = 0;

    /**
     * constructor for the panel.
     */
    public MapPanel(Map map) {
        this.map = map;
        this.player = new Player(5, 5, ingredientMap, this);
        setPreferredSize(new Dimension(map.getWidth() * tileSize, map.getHeight() * tileSize));
        setFocusable(true);
        setRequestFocusEnabled(true);

        bunImg = new ImageIcon("recources\\bun.png").getImage();
        tomatoImg = new ImageIcon("recources\\tomato.png").getImage();
        lettuceImg = new ImageIcon("recources\\lettuce.png").getImage();
        meatImg = new ImageIcon("recources\\steak.png").getImage();
        panImg = new ImageIcon("recources\\pan.png").getImage();
        choppingImg = new ImageIcon("recources\\chopping_board.png").getImage();
        choopedTomatoImg = new ImageIcon("recources\\chopped_tomato.png").getImage();
        choppedLettuceImg = new ImageIcon("recources\\chopped_lettuce.png").getImage();
        choppedMeatImg = new ImageIcon("recources\\chopped_steak.png").getImage();
        cookedMeatImg = new ImageIcon("recources\\cooked_steak.png").getImage();

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
                boolean hasCutMeat = false;
                TileType frontTile = map.getTile(frontX, frontY);
                Stack<Ingredient> heldStack = player.getHeldStack();

                if (frontTile == TileType.counterTop 
                    || frontTile == TileType.choppingBoard
                    || frontTile == TileType.orderSubmit) {
                    player.drop();
                } else {
                    System.out.println("you cant drop this here");
                }

                for (Ingredient ing : heldStack) {
                    if (ing.getName().contains("meat") && ing.isChopped()) {
                        hasCutMeat = true;
                    }
                }

                if (frontTile == TileType.pan 
                    && heldStack.size() <= 1
                    && hasCutMeat) {
                    player.drop();
                }

                Stack<Ingredient> stack = ingredientMap.getTileStack(frontX, frontY);
                if (frontTile == TileType.pan && !stack.isEmpty()) {
                    Ingredient top = stack.peek(); // Cook top ingredient
                    top.cook();
                    if (top.isCooked()) {
                        System.out.println(top.getName() + " is cooked");
                    }
                }

                ingredientMap.printTiles();
            }
            case KeyEvent.VK_E -> {
                TileType frontTile = map.getTile(frontX, frontY);
                Stack<Ingredient> frontTileStack = ingredientMap.getTileStack(frontX, frontY);
                cntr++;
                if (frontTile == TileType.bunBox) {                
                    ingredientMap.fillTile(frontX, frontY, new Bun("bun" + cntr));
                } else if (frontTile == TileType.meatBox) {
                    ingredientMap.fillTile(frontX, frontY, new Meat("meat" + cntr));
                } else if (frontTile == TileType.lettuceBox) {
                    ingredientMap.fillTile(frontX, frontY, new PrepIngredient("lettuce" + cntr));
                } else if (frontTile == TileType.tomatoBox) {
                    ingredientMap.fillTile(frontX, frontY, new PrepIngredient("tomato" + cntr));
                    System.out.println("created");
                    for (Ingredient ing : frontTileStack) {
                        System.out.println(ing.getName());
                    }
                }
                player.pickUp();  
                ingredientMap.printTiles();
            }
            case KeyEvent.VK_SPACE -> {
                Stack<Ingredient> stack = ingredientMap.getTileStack(frontX, frontY);

                if (!stack.isEmpty() && map.getTile(frontX, frontY) == TileType.choppingBoard) {
                    Ingredient top = stack.peek();
                    top.chop();
                    if (top.isChopped()) {
                        System.out.println(top.getName() + " is now chopped!");
                    }
                } 
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

        // Draw map background and stations
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                TileType type = map.getTiles()[y][x];
                Color tileColor;

                switch (type) {
                    case empty -> tileColor = Color.LIGHT_GRAY;
                    case choppingBoard -> tileColor = Color.BLUE;
                    case pan -> tileColor = Color.BLUE;
                    case trashBin -> tileColor = Color.BLACK;
                    case tomatoBox -> tileColor = Color.ORANGE;
                    case lettuceBox -> tileColor = Color.ORANGE;
                    case meatBox -> tileColor = Color.ORANGE;
                    case bunBox -> tileColor = Color.ORANGE;
                    case orderSubmit -> tileColor = Color.WHITE;
                    case counterTop -> tileColor = Color.BLUE;
                    default -> tileColor = Color.GRAY;
                }

                g.setColor(tileColor);
                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);

                // Draw icons for station tiles
                Image icon = null;
                switch (type) {
                    case bunBox -> icon = bunImg;
                    case lettuceBox -> icon = lettuceImg;
                    case tomatoBox -> icon = tomatoImg;
                    case meatBox -> icon = meatImg;
                    case pan -> icon = panImg;
                    case choppingBoard -> icon = choppingImg;
                    default -> {
                    }
                }

                if (icon != null) {
                    int iconSize = (int) (tileSize * 0.8);
                    int offset = (tileSize - iconSize) / 2;
                    g.drawImage(icon, x * tileSize + offset, y * tileSize + offset,
                        iconSize, iconSize, this);
                }
            }
        }

        // Draw every ingredient currently placed on the map
        for (int y = 0; y < ingredientMap.getHeight(); y++) {
            for (int x = 0; x < ingredientMap.getWidth(); x++) {
                Stack<Ingredient> stack = ingredientMap.getTileStack(x, y);

                if (stack != null && !stack.isEmpty()) {
                    int iconSize = (int) (tileSize * 0.4); // smaller so multiple fit
                    int padding = 2;

                    int i = 0;
                    for (Ingredient ingredient : stack) {
                        Image ingredientIcon = null;

                        switch (ingredient.getClass().getSimpleName()) {
                            case "Bun" -> ingredientIcon = bunImg;
                            case "Meat" -> {
                                if (ingredient.isChopped() && !ingredient.isCooked()) {
                                    ingredientIcon = choppedMeatImg;
                                } else if (ingredient.isCooked()) {
                                    ingredientIcon = cookedMeatImg;
                                } else {
                                    ingredientIcon = meatImg;
                                }
                            }
                            case "PrepIngredient" -> {
                                String name = ingredient.getName().toLowerCase();
                                if (name.contains("tomato")) {
                                    if (ingredient.isChopped()) {
                                        ingredientIcon = choopedTomatoImg;
                                    } else {
                                        ingredientIcon = tomatoImg;
                                    }
                                } else if (name.contains("lettuce")) {
                                    if (ingredient.isChopped()) {
                                        ingredientIcon = choppedLettuceImg;
                                    } else {
                                        ingredientIcon = lettuceImg;
                                    }
                                }
                            }
                            default -> {}
                        }

                        if (ingredientIcon != null) {
                            // draw in tile corners
                            int offsetX = padding + (i % 2) * (tileSize / 2);
                            int offsetY = padding + (i / 2) * (tileSize / 2);
                            g.drawImage(ingredientIcon, x * tileSize + offsetX, y * tileSize 
                                + offsetY, iconSize, iconSize, this);
                            i++;
                        }
                    }
                }
            }
        }


        // Highlight the tile in front of the player
        g.setColor(new Color(255, 255, 0, 128));
        g.fillRect(frontX * tileSize, frontY * tileSize, tileSize, tileSize);

        // Draw the player
        int playerSize = 40;
        int px = player.getTileX() * tileSize + (tileSize - playerSize) / 2;
        int py = player.getTileY() * tileSize + (tileSize - playerSize) / 2;
        g.setColor(Color.BLACK);
        g.fillRect(px, py, playerSize, playerSize);

        // Draw held item above the player
        // Draw held stack above the player
        Stack<Ingredient> heldStack = player.getHeldStack();
        if (heldStack != null && !heldStack.isEmpty()) {
            int iconSize = 20; // smaller so multiple items fit
            int padding = 2;

            int i = 0;
            for (Ingredient held : heldStack) {
                Image heldIcon = null;

                switch (held.getClass().getSimpleName()) {
                    case "Bun" -> heldIcon = bunImg;

                    case "Meat" -> {
                        if (held.isChopped() && !held.isCooked()) {
                            heldIcon = choppedMeatImg;
                        } else if (held.isCooked()) {
                            heldIcon = cookedMeatImg;
                        } else {
                            heldIcon = meatImg;
                        }
                    }

                    case "PrepIngredient" -> {
                        String name = held.getName().toLowerCase();
                        if (name.contains("tomato")) {
                            if (held.isChopped()) {
                                heldIcon = choopedTomatoImg;
                            } else {
                                heldIcon = tomatoImg;
                            }
                        } else if (name.contains("lettuce")) {
                            if (held.isChopped()) {
                                heldIcon = choppedLettuceImg;
                            } else {
                                heldIcon = lettuceImg;
                            }
                        }
                    }
                }

                if (heldIcon != null) {
                    int offsetX = px + padding + (i % 2) * (iconSize + 2);
                    int offsetY = py - iconSize - 5 - (i / 2) * (iconSize + 2);
                    g.drawImage(heldIcon, offsetX, offsetY, iconSize, iconSize, this);
                    i++;
                }
            }
        }

    }
}

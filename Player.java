import java.util.Stack;

/**
 * class for the player.
 */
public class Player {
    private int tileX; 
    private int tileY; 
    private final int tileSize = 64;
    private Direction facing;
    private Stack<Ingredient> heldStack = new Stack<>();
    private MapPanel mapPanel;
    private IngredientMap ingredientMap;

    /**
     * constructor for the player.
     */
    public Player(int startX, int startY, IngredientMap ingredientMap, MapPanel mapPanel) {
        this.tileX = startX;
        this.tileY = startY;
        this.facing = Direction.DOWN;
        this.ingredientMap = ingredientMap;
        this.mapPanel = mapPanel;
    
    }

    /**
     * Player movement.
     */
    public void move(Direction dir, Map map) {
        int newX = tileX;
        int newY = tileY;

        facing = dir;

        switch (dir) {
            case UP -> {
                newY--;
                System.out.println("up");
            }
            case DOWN -> {
                newY++;
                System.out.println("down");
            }
            case LEFT -> {
                newX--;
                System.out.println("left");
            }
            case RIGHT -> {
                newX++;
                System.out.println("right");
            }
        }

        if (newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight()) {
            if (map.getTiles()[newY][newX] == TileType.empty) {
                tileX = newX;
                tileY = newY;
            }
        }
    }

    /**
     * Picking up items.
     */
    public void pickUp() {
        int frontX = mapPanel.getFrontX();
        int frontY = mapPanel.getFrontY();
        Stack<Ingredient> stack = ingredientMap.getTileStack(frontX, frontY);

        if (heldStack.isEmpty() && !stack.isEmpty()) {
            heldStack.addAll(stack);
            ingredientMap.getTileStack(frontX, frontY).clear();
            System.out.println("You picked up: ");
            for (Ingredient ingredient : heldStack) {
                System.out.print(ingredient.getName() + " ");
            }
        } else if (heldStack != null) {
            System.out.println("You are already holding something");
        } else {
            System.out.println("there is no ingredient here");
        }
    }

    /**
     * Dropping items.
     */
    public void drop() {
        int frontX = mapPanel.getFrontX();
        int frontY = mapPanel.getFrontY();

        if (heldStack != null) {
            ingredientMap.getTileStack(frontX, frontY).addAll(heldStack);
            System.out.println("You dropped: ");
            for (Ingredient ing : heldStack) {
                System.out.print(ing.getName() + " ");
            }
            heldStack.clear();
        } else if (heldStack == null) {
            System.out.println("You are not holding anything");
        } else {
            System.out.println("there is an ingredient already there");
        }
    }

    //Getters
    public Direction getFacing() {
        return facing;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Stack<Ingredient> getHeldStack() {
        return heldStack;
    }
}

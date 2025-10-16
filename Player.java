

public class Player {
    private int tileX; 
    private int tileY; 
    private final int tileSize = 64;
    private Direction facing;
    private Ingredient holdIngredient = null;
    private MapPanel mapPanel;
    private IngredientMap ingredientMap;

    public Player(int startX, int startY, IngredientMap ingredientMap, MapPanel mapPanel) {
        this.tileX = startX;
        this.tileY = startY;
        this.facing = Direction.DOWN;
        this.ingredientMap = ingredientMap;
        this.mapPanel = mapPanel;
    
    }

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

    public void pickUp() {
        int frontX = mapPanel.getFrontX();
        int frontY = mapPanel.getFrontY();
        Ingredient ingredient = ingredientMap.getTile(frontX, frontY);

        if (holdIngredient == null && ingredient != null) {
            holdIngredient = ingredient;
            ingredientMap.fillTile(frontX, frontY, null);
            System.out.println("You picked up" + ingredient.getName());
        } else if (holdIngredient != null) {
            System.out.println("You are already holding something");
        } else if (ingredient != null) {
            System.out.println("there is no ingredient here");
        }
    }

    public void drop() {
        int frontX = mapPanel.getFrontX();
        int frontY = mapPanel.getFrontY();
        Ingredient ingredient = ingredientMap.getTile(frontX, frontY);

        if (holdIngredient != null && ingredient == null) {
            System.out.println("You dropped " + holdIngredient.getName());
            ingredientMap.fillTile(frontX, frontY, holdIngredient);
            holdIngredient = null;
        } else if (holdIngredient == null){
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
}

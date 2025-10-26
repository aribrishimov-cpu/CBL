import java.util.Stack;

/**
 * An array that tracks the placement of ingredients around the map.
 */
public class IngredientMap {
    private final int width = 12;
    private final int height = 10;
    private Stack<Ingredient>[][] tiles;

    /**
     * constructor for the array.
     */

    @SuppressWarnings("unchecked")
    public IngredientMap() {
        tiles = new Stack[height][width]; 
        initialFill(tiles);
    }

    
    /**
     * Adding a Ingredinet to a stack if there aren't more than 4 ing there.
     */
    public void fillTile(int j, int i, Ingredient ingredient) {
        if (tiles[i][j].size() <= 4) {
            tiles[i][j].push(ingredient);
        }
    }

    // Returns the entire 2D stack array
    public Stack<Ingredient>[][] getTiles() {
        return tiles;
    }

    /**
     *  Returns the top ingredient at (i, j), or null if empty.
     */
    public Ingredient getTileIngredient(int j, int i) {
        if (tiles[i][j].isEmpty()) {
            return null;
        }
        return tiles[i][j].peek();
    }

    /**
     * Returns the stack of ingredients at (i, j), or null if empty.
     */
    public Stack<Ingredient> getTileStack(int j, int i) {
        return tiles[i][j];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Prints the array.
     */
    public void printTiles() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j].isEmpty()) {
                    System.out.print("null\t");
                } else {
                    System.out.print(tiles[i][j].peek().getName() + "\t");
                }
            }
        }   
        
    }

    // Fills every position with an empty stack.
    private void initialFill(Stack<Ingredient>[][] map) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = new Stack<>();
            }
        }
    }
}

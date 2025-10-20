/*
 * An array that tracks the placement of ingredients around the map.
 */
public class IngredientMap {
    private final int width = 15;
    private final int height = 10;
    private Ingredient[][] tiles;

    /**
     * constructor for the array.
     */
    public IngredientMap() {
        tiles = new Ingredient[height][width]; //filled with null by default
    }

    public void fillTile(int x, int y, Ingredient ingredient) {
        tiles[y][x] = ingredient;
    }

    public Ingredient[][] getTiles() {
        return tiles;
    }

    public Ingredient getTile(int x, int y) {
        return tiles[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printTiles() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j] == null) {
                    System.out.print("null\t");
                } else {
                    System.out.print(tiles[i][j].getName() + "\t");
                }
            }
        }   
        
    }
}

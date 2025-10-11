

/*
 * Initializing the map.
 */
public class Map {
    private final int width = 15;
    private final int height = 10;
    private TileType[][] tiles;

    public Map() {
        tiles = new TileType[height][width];
        Initialize();
    }

    public void Initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1) {
                    tiles[i][j] = TileType.counterTop;
                } else if (j == 0 || j == width - 1) {
                    tiles[i][j] = TileType.counterTop;
                } else {
                    tiles[i][j] = TileType.empty;
                }
            }
        }

        tiles[0][3] = TileType.choppingBoard;
        tiles[0][4] = TileType.choppingBoard;
        tiles[height - 1][5] = TileType.pan;
        tiles[height - 1][6] = TileType.pan;
        tiles[6][0] = TileType.tomatoBox;
        tiles[7][0] = TileType.lettuceBox;
        tiles[2][width - 1] = TileType.bunBox;
        tiles[3][width - 1] = TileType.meatBox;
        tiles[6][width - 1] = TileType.orderSubmit;
        tiles[7][width - 1] = TileType.orderSubmit;
    }

    public TileType[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void main(String[] args) {
        new Map();
}
}

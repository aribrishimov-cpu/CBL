package src.core;

import src.cbl.enums.TileType;

/**
 * Class for the map with all of the staitions and countertops.
 */
public class Map {
    private final int width = 12;       
    private final int height = 10;      
    private TileType[][] tiles;        

    /**
     * contructor for the map.
     */
    public Map() {
        tiles = new TileType[height][width];
        initialize();  
    }

    /**
     * filling the map.
     */
    private void initialize() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
                    tiles[y][x] = TileType.counterTop;
                } else {
                    tiles[y][x] = TileType.empty;
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

    public TileType getTile(int x, int y) {
        return tiles[y][x];
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
}

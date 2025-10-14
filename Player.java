import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player {
    private int tileX; 
    private int tileY; 
    private final int tileSize = 64;
    private Direction facing;

    public Player(int startX, int startY) {
        this.tileX = startX;
        this.tileY = startY;
        this.facing = Direction.DOWN;
    
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

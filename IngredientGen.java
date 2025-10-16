public class IngredientGen {
    public void getTileInFront(Player player) {
        int x = player.getTileX();
        int y = player.getTileY();
        switch (player.getDirection()) {
            case UP -> {
                y--;
                System.out.println("up");
            }
            case DOWN -> {
                y++;
                System.out.println("down");
            }
            case LEFT -> {
                x--;
                System.out.println("left");
            }
            case RIGHT -> {
                x++;
                System.out.println("right");
            }
        }
        return getTiles[x][y];
    }
}

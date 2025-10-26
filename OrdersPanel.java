import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OrdersPanel extends JPanel {
    private static final int PANEL_WIDTH = 300;
    private static final int ORDER_COUNT = 5;
    private static final int PADDING = 12;
    private static final int BOX_PADDING = 10;
    private static final int ICON_SIZE = 48;

    private Image bunImg, tomatoImg, lettuceImg, cookedMeatImg, choppedTomatoImg, choppedLettuceImg;

    private static class OrderEntry {
        final java.util.List<String> items;
        boolean completed;

        OrderEntry(java.util.List<String> items) {
            this.items = new ArrayList<>(items);
            this.completed = false;
        }
    }

    private final java.util.List<OrderEntry> orders = new ArrayList<>();

    public OrdersPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 640));
        setBackground(Color.GRAY);

        loadImages();
        createOrders();
    }

    /** Loads all ingredient images. */
    private void loadImages() {
        bunImg = loadImage("/recources/bun.png");
        tomatoImg = loadImage("/recources/tomato.png");
        lettuceImg = loadImage("/recources/lettuce.png");
        cookedMeatImg = loadImage("/recources/cooked_steak.png");
        choppedTomatoImg = loadImage("/recources/chopped_tomato.png");
        choppedLettuceImg = loadImage("/recources/chopped_lettuce.png");
    }

    /** Loads an image from the given path. Assumes image always exists. */
    private Image loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed to load image: " + path, e);
        }
    }

    /** Creates the predefined list of orders. */
    private void createOrders() {
        orders.clear();
        orders.add(new OrderEntry(Arrays.asList("bun", "cooked_meat", "chopped_lettuce")));
        orders.add(new OrderEntry(Arrays.asList("bun", "cooked_meat")));
        orders.add(new OrderEntry(Arrays.asList("bun", "cooked_meat", "chopped_lettuce", "chopped_tomato")));
        orders.add(new OrderEntry(Arrays.asList("chopped_lettuce", "chopped_tomato")));
        orders.add(new OrderEntry(Arrays.asList("bun", "cooked_meat", "chopped_lettuce")));
    }

    /** Checks if the player’s held ingredients match an order and marks it completed. */
    public synchronized boolean trySubmit(Stack<Ingredient> heldStack) {
        if (heldStack == null || heldStack.isEmpty()) return false;

        java.util.Map<String, Integer> heldCounts = toKeyCounts(heldStack);

        for (OrderEntry entry : orders) {
            if (entry.completed) continue;
            java.util.Map<String, Integer> orderCounts = toKeyCounts(entry.items);
            if (orderCounts.equals(heldCounts)) {
                entry.completed = true;
                repaint();

                if (orders.stream().allMatch(o -> o.completed)) {
                    Victory.showVictory();
                }
                return true;
            }
        }
        return false;
    }

    /** Converts a stack of Ingredient objects to a map of ingredient names and counts. */
    private java.util.Map<String, Integer> toKeyCounts(Stack<Ingredient> stack) {
        java.util.Map<String, Integer> counts = new HashMap<>();
        for (Ingredient ing : stack) {
            String key = ingredientToKey(ing);
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }
        return counts;
    }

    /** Converts a list of ingredient name strings to a map of counts. */
    private java.util.Map<String, Integer> toKeyCounts(java.util.List<String> keys) {
        java.util.Map<String, Integer> counts = new HashMap<>();
        for (String k : keys) counts.put(k, counts.getOrDefault(k, 0) + 1);
        return counts;
    }

    /** Maps an Ingredient object to its string key used in orders. */
    private String ingredientToKey(Ingredient ing) {
        String cls = ing.getClass().getSimpleName();
        if (cls.equals("Bun")) return "bun";
        if (cls.equals("Meat")) return ing.isCooked() ? "cooked_meat" : "meat";
        if (cls.equals("PrepIngredient")) {
            String name = ing.getName().toLowerCase();
            if (name.contains("tomato")) return ing.isChopped() ? "chopped_tomato" : "tomato";
            if (name.contains("lettuce")) return ing.isChopped() ? "chopped_lettuce" : "lettuce";
            return ing.isChopped() ? "chopped_" + name : name;
        }
        return ing.getName().toLowerCase();
    }

    /** Draws the orders panel, showing completed and pending orders with ingredient icons. */
    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();

        int panelW = getWidth();
        int panelH = getHeight();
        int available = panelH - 2 * PADDING;
        int boxH = (available - (ORDER_COUNT - 1) * PADDING) / ORDER_COUNT;

        for (int i = 0; i < ORDER_COUNT; i++) {
            int y = PADDING + i * (boxH + PADDING);
            int x = PADDING;
            int w = panelW - 2 * PADDING;
            int h = boxH;

            OrderEntry entry = orders.get(i);

            g.setColor(entry.completed ? new Color(162, 235, 162) : new Color(230, 230, 230));
            g.fillRoundRect(x, y, w, h, 8, 8);

            g.setColor(Color.DARK_GRAY);
            g.setStroke(new BasicStroke(2f));
            g.drawRoundRect(x, y, w, h, 8, 8);

            int ingredientCount = entry.items.size();
            int totalIconsWidth = ingredientCount * ICON_SIZE + (ingredientCount - 1) * BOX_PADDING;
            int startX = x + (w - totalIconsWidth) / 2;
            int iconY = y + (h - ICON_SIZE) / 2;

            for (int j = 0; j < ingredientCount; j++) {
                Image icon = getImageForKey(entry.items.get(j));
                g.drawImage(icon, startX + j * (ICON_SIZE + BOX_PADDING), iconY, ICON_SIZE, ICON_SIZE, this);
            }
        }

        g.dispose();
    }

    /** Returns the Image object for a given ingredient key. */
    private Image getImageForKey(String key) {
        return switch (key) {
            case "bun" -> bunImg;
            case "tomato" -> tomatoImg;
            case "lettuce" -> lettuceImg;
            case "cooked_meat" -> cookedMeatImg;
            case "chopped_tomato" -> choppedTomatoImg;
            case "chopped_lettuce" -> choppedLettuceImg;
            default -> throw new IllegalArgumentException("Unknown ingredient key: " + key);
        };
    }
}

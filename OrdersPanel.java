import java.awt.*;
import java.util.Stack;
import javax.swing.*;

/**
 * Creating the pannel with the orders.
 */
public class OrdersPanel extends JPanel {
    // Constants for sizes and layout
    private static final int PANEL_WIDTH = 300;
    private static final int ORDER_COUNT = 4;
    private static final int PADDING = 12;
    private static final int BOX_PADDING = 10;
    private static final int ICON_SIZE = 48;

    // Array of orders - stored using stacks internally
    private Order[] orders = new Order[ORDER_COUNT];

    // Ingredient icons
    private Image bunImg;
    private Image tomatoImg;
    private Image lettuceImg;
    private Image meatImg;

    /**
     * constructor for the pannel.
     */
    public OrdersPanel() {
        // Panel setup (size + background color)
        setPreferredSize(new Dimension(PANEL_WIDTH, 640));
        setBackground(Color.GRAY);

        // Create orders using stacks (index must start at 0)
        orders[0] = new Order("bun", "lettuce", "meat");
        orders[1] = new Order("bun", "lettuce", "meat", "tomato");
        orders[2] = new Order("bun", "meat");
        orders[3] = new Order("lettuce", "tomato");

        loadImages();
    }

    // Loads ingredient images from file or fallback placeholders if missing
    private void loadImages() {
        bunImg = new ImageIcon("recources\\bun.png").getImage();
        tomatoImg = new ImageIcon("recources\\chopped_tomato.png").getImage();
        lettuceImg = new ImageIcon("recources\\chopped_lettuce.png").getImage();
        meatImg = new ImageIcon("recources\\cooked_steak.png").getImage();
        
    }
    

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();

        // Smoother icon scaling
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Panel dimensions
        int panelW = getWidth();
        int panelH = getHeight();

        // Calculate height for the 4 order boxes
        int available = panelH - 2 * PADDING;
        int boxH = (available - (ORDER_COUNT - 1) * PADDING) / ORDER_COUNT;

        // Draw each order box
        for (int i = 0; i < ORDER_COUNT; i++) {
            int y = PADDING + i * (boxH + PADDING);
            int x = PADDING;
            int w = panelW - 2 * PADDING;
            int h = boxH;

            // Background rectangle (order ticket)
            g.setColor(new Color(230, 230, 230));
            g.fillRoundRect(x, y, w, h, 8, 8);

            // Border
            g.setColor(Color.DARK_GRAY);
            g.setStroke(new BasicStroke(2f));
            g.drawRoundRect(x, y, w, h, 8, 8);

            // Get the stack of ingredients from this order
            Order order = orders[i];
            Stack<String> stack = order.getItems();

            int ingredientCount = stack.size();

            // Center the ingredient icons horizontally
            int totalIconsWidth = ingredientCount * ICON_SIZE + (ingredientCount - 1) * BOX_PADDING;
            int startX = x + (w - totalIconsWidth) / 2;
            int iconY = y + (h - ICON_SIZE) / 2;

            // Draw each ingredient icon in order
            for (int j = 0; j < stack.size(); j++) {
                String ing = stack.get(j);
                Image icon = getImage(ing);

                g.drawImage(icon, startX + j * (ICON_SIZE + BOX_PADDING), iconY,
                        ICON_SIZE, ICON_SIZE, this);

                // Draw a light border around icon
                g.setColor(new Color(0, 0, 0, 40));
                g.drawRect(startX + j * (ICON_SIZE + BOX_PADDING), iconY,
                        ICON_SIZE, ICON_SIZE);
            }
        }

        g.dispose();
    }

    // Matches string keys to actual ingredient images
    private Image getImage(String ing) {
        return switch (ing) {
            case "bun" -> bunImg;
            case "tomato" -> tomatoImg;
            case "lettuce" -> lettuceImg;
            case "meat" -> meatImg;
            default -> null;
        };
    }
}

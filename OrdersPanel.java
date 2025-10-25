import java.awt.*;
import java.awt.image.*;
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

    private Image bunImg;
    private Image tomatoImg;
    private Image lettuceImg;
    private Image cookedMeatImg;
    private Image choppedTomatoImg;
    private Image choppedLettuceImg;

    private final java.util.List<java.util.List<String>> orders = new ArrayList<>();

    public OrdersPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, 640));
        setBackground(Color.GRAY);

        loadImages();
        createOrders();
    }

    private void loadImages() {
        try {
            bunImg = ImageIO.read(new File("recources\\bun.png"));
            tomatoImg = ImageIO.read(new File("recources\\tomato.png"));
            lettuceImg = ImageIO.read(new File("recources\\lettuce.png"));
            cookedMeatImg = ImageIO.read(new File("recources\\cooked_steak.png"));
            choppedTomatoImg = ImageIO.read(new File("recources\\chopped_tomato.png"));
            choppedLettuceImg = ImageIO.read(new File("recources\\chopped_lettuce.png"));
        } catch (IOException e) {
            bunImg = createPlaceholderIcon("bun");
            tomatoImg = createPlaceholderIcon("tom");
            lettuceImg = createPlaceholderIcon("let");
            cookedMeatImg = createPlaceholderIcon("meat");
            choppedTomatoImg = createPlaceholderIcon("ct");
            choppedLettuceImg = createPlaceholderIcon("cl");
            System.err.println("OrdersPanel: error loading images: " + e.getMessage());
        }
    }

    private Image createPlaceholderIcon(String text) {
        BufferedImage img = new BufferedImage(ICON_SIZE, ICON_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, ICON_SIZE, ICON_SIZE);
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 12f));
        FontMetrics fm = g.getFontMetrics();
        String s = text;
        int w = fm.stringWidth(s);
        g.drawString(s, (ICON_SIZE - w) / 2, (ICON_SIZE + fm.getAscent()) / 2 - 4);
        g.dispose();
        return img;
    }

    private void createOrders() {
        orders.add(Arrays.asList("bun", "cooked_meat", "chopped_lettuce"));

        orders.add(Arrays.asList("bun", "cooked_meat"));

        orders.add(Arrays.asList("bun", "cooked_meat", "chopped_lettuce", "chopped_tomato"));

        orders.add(Arrays.asList("chopped_lettuce", "chopped_tomato"));

        orders.add(Arrays.asList("bun", "cooked_meat", "chopped_lettuce"));
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int panelW = getWidth();
        int panelH = getHeight();

        int available = panelH - 2 * PADDING;
        int boxH = (available - (ORDER_COUNT - 1) * PADDING) / ORDER_COUNT;

        for (int i = 0; i < ORDER_COUNT; i++) {
            int y = PADDING + i * (boxH + PADDING);
            int x = PADDING;
            int w = panelW - 2 * PADDING;
            int h = boxH;

            g.setColor(new Color(230, 230, 230));
            g.fillRoundRect(x, y, w, h, 8, 8);

            g.setColor(Color.DARK_GRAY);
            g.setStroke(new BasicStroke(2f));
            g.drawRoundRect(x, y, w, h, 8, 8);

            java.util.List<String> order = (i < orders.size()) ? orders.get(i) : Collections.emptyList();

            int ingredientCount = order.size();
            if (ingredientCount == 0) continue;

            int totalIconsWidth = ingredientCount * ICON_SIZE + (ingredientCount - 1) * BOX_PADDING;
            int startX = x + (w - totalIconsWidth) / 2;
            int iconY = y + (h - ICON_SIZE) / 2;

            for (int j = 0; j < order.size(); j++) {
                String key = order.get(j);
                Image icon = getImageForKey(key);
                if (icon != null) {
                    g.drawImage(icon, startX + j * (ICON_SIZE + BOX_PADDING), iconY, ICON_SIZE, ICON_SIZE, this);
                    g.setColor(new Color(0,0,0,40));
                    g.drawRect(startX + j * (ICON_SIZE + BOX_PADDING), iconY, ICON_SIZE, ICON_SIZE);
                }
            }
        }

        g.dispose();
    }

    private Image getImageForKey(String key) {
        return switch (key) {
            case "bun" -> bunImg;
            case "tomato" -> tomatoImg;
            case "lettuce" -> lettuceImg;
            case "cooked_meat" -> cookedMeatImg;
            case "chopped_tomato" -> choppedTomatoImg;
            case "chopped_lettuce" -> choppedLettuceImg;
            default -> null;
        };
    }
}

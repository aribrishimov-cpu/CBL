import java.awt.*;
import javax.swing.*;

public class OrdersPanel extends JPanel {
    int orderPanelWidth = 300;
    int lineWidth = 5;
    
    public OrdersPanel() {
        setPreferredSize(new Dimension(orderPanelWidth, 640)); 
        setBackground(Color.GRAY);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK); 

        int panelHeight = getHeight();
        int sectionHeight = panelHeight / 5; 

        for (int i = 0; i <= 5; i++) {
            int y = i * sectionHeight;
            g.fillRect(0, y - lineWidth / 2, getWidth(), lineWidth); 
        }
    }
}
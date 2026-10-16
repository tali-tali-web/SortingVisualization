package src;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel {

    private final int margin = 10;
    private final ArrayModel host;

    public VisualizationPanel(ArrayModel host) {
        super();

        this.host = host;

        setLayout(null);
        repaint();
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(new Color(0, 0, 0));
        g.drawRect(margin, margin, this.getWidth() - 2 * margin, this.getHeight() - 2 * margin);

    }

}
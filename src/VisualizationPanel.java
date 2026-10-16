package src;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel {

    private final int margin;

    public VisualizationPanel() {
        super();

        this.setLayout(null);
        margin = 10;

        // this.add(new JLabel("This is where the canvas goes yes!"));
        // this.add(new JLabel(new ImageIcon("./resources/cute-blinking.gif")));

        this.repaint();

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
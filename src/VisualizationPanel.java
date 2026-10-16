package src;

import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel {

    public VisualizationPanel() {
        super();
        this.add(new JLabel("This is where the canvas goes yes!"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
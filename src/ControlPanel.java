package src;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    public ControlPanel() {
        super();
        this.add(new JButton("Test"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
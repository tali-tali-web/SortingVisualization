package src;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SortingVisualization {

    private final JFrame frame;

    private final ControlPanel controlPanel;
    private final VisualizationPanel visualizationPanel;

    public SortingVisualization() {
        frame = new JFrame();
        controlPanel = new ControlPanel();
        visualizationPanel = new VisualizationPanel();

        controlPanel.add(new JButton("Test"));
        visualizationPanel.add(new JLabel("This is where the canvas goes yes!"));

        frame.setLayout(new BorderLayout());

        frame.add(controlPanel, BorderLayout.EAST);
        frame.add(visualizationPanel, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setTitle("BSIT BASIC ALGORITHMS BOIII");

        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setVisible(true);



    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
    }

}
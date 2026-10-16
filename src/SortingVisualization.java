package src;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SortingVisualization {

    private final JFrame frame;

    private final ControlPanel controlPanel;
    private final VisualizationPanel visualizationPanel;

    public SortingVisualization() {

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setTitle("BSIT BASIC ALGORITHMS BOIII");

        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setVisible(true);

        controlPanel = new ControlPanel();
        visualizationPanel = new VisualizationPanel();

        frame.setLayout(new BorderLayout());

        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.EAST);

    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
    }

}
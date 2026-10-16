package src;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SortingVisualization {

    private final JFrame frame;

    private final ControlPanel controlPanel;
    private final VisualizationPanel visualizationPanel;
    private final ArrayModel host;

    public SortingVisualization() {
        this.host = new ArrayModel();

        frame = new JFrame();
        controlPanel = new ControlPanel(this.host);
        visualizationPanel = new VisualizationPanel(this.host);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("BSIT BASIC ALGORITHMS BOIII");
        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setLayout(new BorderLayout());

        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public void placeholder() {
        System.out.print("Yeah this is working...\n");
    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
        mn.placeholder();
    }

}
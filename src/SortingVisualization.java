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

        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("BSIT BASIC SORTING ALGORITHMS BOIII");
        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setLayout(new BorderLayout());

        this.host = new ArrayModel(-15, -frame.getHeight() / 2, 20);
        controlPanel = new ControlPanel(this.host);
        visualizationPanel = new VisualizationPanel(this.host);

        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        controlPanel.getMenu().addActionListener(e -> { 
            host.updateAlgorithm((int) controlPanel.getMenu().getSelectedIndex()); 
            visualizationPanel.repaint();
        });

        controlPanel.getSpinner().addChangeListener(e -> { 
            host.setLength((int) controlPanel.getSpinner().getValue()); 
            visualizationPanel.repaint();
        });
        
        controlPanel.getSlider().addChangeListener(e -> { 
            host.setSpeed((int) controlPanel.getSlider().getValue()); 
            visualizationPanel.repaint();
        });

        controlPanel.plause().addActionListener(e -> {
            host.setSpeed(host.getSpeed() == 0 ? (int) controlPanel.getSlider().getValue() : 0);
            visualizationPanel.repaint();
        });

        controlPanel.reset().addActionListener(e -> {
            host.setSpeed(0);
            visualizationPanel.repaint();
            // Add option to start from the initial parameters
        });

        controlPanel.randomize().addActionListener(e -> { 
            host.randomize();
            visualizationPanel.repaint();    
        });

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
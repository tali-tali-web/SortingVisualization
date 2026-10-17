package src;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class SortingVisualization {

    private final JFrame frame;

    private final ControlPanel controlPanel;
    private final VisualizationPanel visualizationPanel;
    private final Timer controlTimer;
    private final Sorter sorter;
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

        sorter = new Sorter();
        controlPanel = new ControlPanel();
        visualizationPanel = new VisualizationPanel(this.host);

        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        controlPanel.getMenu().addActionListener(e -> { 
            sorter.sortType = switch ((int) controlPanel.getMenu().getSelectedIndex()) 
            {
                case 0 -> Algorithms.BUBBLE_SORT;
                case 1 -> Algorithms.INSERTION_SORT;
                case 2 -> Algorithms.MERGE_SORT;
                case 3 -> Algorithms.SELECTION_SORT;
                default -> Algorithms.NONE;
            }; 
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

            sorter.step(host);

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

        controlTimer = new Timer(16, e -> {

            if (visualizationPanel.up()) host.addY(-10);
            if (visualizationPanel.down()) host.addY(10);
            if (visualizationPanel.left()) host.addX(-10);
            if (visualizationPanel.right()) host.addX(10);

            if (host.getSpeed() != 0) sorter.step(host);

            visualizationPanel.repaint();
        });
        controlTimer.start();
        frame.setVisible(true);

        Clip clip = null;
        Clip mambo_clip = null;

        try {
            clip = AudioSystem.getClip();
            mambo_clip = AudioSystem.getClip();

            AudioInputStream audio = AudioSystem.getAudioInputStream(
                new File("./resources/audio/boom-boom.wav")
            );

            AudioInputStream mambo = AudioSystem.getAudioInputStream(
                new File("./resources/audio/mambo.wav")
            );

            clip.open(audio);
            audio.close();

            mambo_clip.open(mambo);
            mambo.close();

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            sorter.setSound(mambo_clip);

        } catch (IOException | UnsupportedAudioFileException |
                LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void placeholder() {
        System.out.print("Yeah this is working...\n");
    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
        mn.placeholder();
    }

}
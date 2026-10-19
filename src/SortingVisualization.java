package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import src.panels.CodePanel;
import src.panels.ControlPanel;
import src.panels.VisualizationPanel;

public class SortingVisualization {

    private final JFrame frame;

    private final ControlPanel controlPanel;
    private final VisualizationPanel visualizationPanel;
    private final CodePanel codePanel;
    private final Timer controlTimer;
    private final Timer sortTimer;
    private final Sorter sorter;
    private final ArrayModel host;
    private final JSplitPane splitter;
    private float hue = 0f;

    public SortingVisualization() {

        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("BSIT BASIC SORTING ALGORITHMS BOIII");
        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setLayout(new BorderLayout());

        this.host = new ArrayModel(-15, -frame.getHeight() / 2, 20);

        // Initializing all the relevant parts of the program
        sorter = new Sorter(this.host);
        controlPanel = new ControlPanel();

        visualizationPanel = new VisualizationPanel(this.host);
        codePanel = new CodePanel();

        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, codePanel, new JPanel());
        splitter.setResizeWeight(0.5);
        splitter.setPreferredSize(new Dimension(290, 500));

        // Adding everything relevant
        frame.add(visualizationPanel, BorderLayout.CENTER);
        frame.add(splitter, BorderLayout.EAST);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // These are the action listeners I had to elevate them up to this part 
        // In order to make it possible for them to interact with other panels 
        // I really tried to separate them into their own class files but it's not worth
        // the complexity
        // Following java coding standards, I am using getter functions

        controlPanel.getMenu().addActionListener(e -> { 

            sorter.setSortType(
                switch ((String) controlPanel.getMenu().getSelectedItem()) 
                {
                    case "Bubble Sort" -> Algorithms.BUBBLE_SORT;
                    case "Insertion Sort" -> Algorithms.INSERTION_SORT;
                    case "Merge Sort" -> Algorithms.MERGE_SORT;
                    case "Selection Sort" -> Algorithms.SELECTION_SORT;
                    default -> Algorithms.NONE;
                });

            codePanel.setText(sorter.getCode());
            visualizationPanel.repaint();
        });

        controlPanel.getSpinner().addChangeListener(e -> { 
            host.setLength((int) controlPanel.getSpinner().getValue()); 

            sorter.prepare();

        });
        
        controlPanel.plause().addActionListener(e -> {

            host.setSpeed(host.getSpeed() == 0 ? (int) controlPanel.getSlider().getValue() : 0);

        });

        controlPanel.reset().addActionListener(e -> {
            host.setSpeed(0);

            host.reset();
            sorter.prepare();

        });

        sortTimer = new Timer(16, e -> {
            if (host.getSpeed() != 0) {
                int line = sorter.step();
                if (line != -1) {
                    codePanel.highlightLine(line);
                } else {
                    codePanel.highlightReset();
                }
            }
            visualizationPanel.repaint();
        });

        
        controlTimer = new Timer(16, e -> {

            if (visualizationPanel.up()) host.addY(-10);
            if (visualizationPanel.down()) host.addY(10);
            if (visualizationPanel.left()) host.addX(-10);
            if (visualizationPanel.right()) host.addX(10);

            if (host.getSpeed() == 0) {
                sortTimer.stop();
                visualizationPanel.repaint();

            } else {

                int max = controlPanel.getSlider().getMaximum();
                int min = controlPanel.getSlider().getMinimum();

                int delayms = 50 * (max - host.getSpeed()) / (max - min);
                sortTimer.setDelay(delayms);
                if (!sortTimer.isRunning()) {
                    sortTimer.start();
                }
            }

            hue += 0.005f;
            if (hue >= 1f) hue = 0f;

            controlPanel.setBackground(Color.getHSBColor(hue, 1f, 1f));
            controlPanel.getSlider().setBackground(Color.getHSBColor(hue, 1f, 1f));
            controlPanel.getSpinner().setBackground(Color.getHSBColor(hue, 1f, 1f));

        });
        
        controlTimer.start();
        sortTimer.start();

        controlPanel.randomize().addActionListener(e -> { 

            controlTimer.stop();
            sortTimer.stop();

            host.randomize();
            sorter.prepare();

            controlTimer.start();
            sortTimer.start();

            visualizationPanel.repaint();    
        });

        frame.setVisible(true);

        // Background audio, just loading of all the audio I needed
        try {
            Clip clip = AudioSystem.getClip();
            Clip mambo_clip = AudioSystem.getClip();

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

            // sorter.setSound(mambo_clip);

        } catch (IOException | UnsupportedAudioFileException |
                LineUnavailableException e) {
            e.printStackTrace();
        }

        sorter.prepare();
        codePanel.setText(sorter.getCode());

        System.out.print("Testing\n");
    }

    public void placeholder() {
        System.out.print("Yeah this is working...\n");
    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
        mn.placeholder();
    }

}
package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.Timer;

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
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("BSIT BASIC SORTING ALGORITHMS BOIII");
        frame.setIconImage(new ImageIcon("./resources/icon.png").getImage());
        frame.setLayout(new BorderLayout());



        this.host = new ArrayModel(-15, -frame.getHeight() / 2, 20);

        // Initializing all the relevant parts of the program
        sorter = new Sorter();
        controlPanel = new ControlPanel();

        visualizationPanel = new VisualizationPanel(this.host);
        codePanel = new CodePanel();

        splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, visualizationPanel, codePanel);
        splitter.setResizeWeight(0.8);

        // Adding everything relevant
        frame.add(splitter, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // These are the action listeners I had to elevate them up to this part 
        // In order to make it possible for them to interact with other panels 
        // I really tried to separate them into their own class files but it's not worth
        // the complexity
        // Following java coding standards, I am using getter functions

        controlPanel.getMenu().addActionListener(e -> { 

            String algorithm = (String) controlPanel.getMenu().getSelectedItem();
            switch (algorithm) {
                case "Bubble Sort" -> {
                    sorter.sortType = Algorithms.BUBBLE_SORT;
                    codePanel.setText("""
                                                          
                    void bubbleSort(int[] array, int n) {

                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n - i - 1; j++) {

                                if (array[j] > array[j + 1]) {
                                    swap(j, j + 1);
                                }

                            }
                        }
                    }

                    """);
                } case "Insertion Sort" -> {
                    sorter.sortType = Algorithms.INSERTION_SORT;
                    codePanel.setText("""
                                                          
                    void insertionSort(int[] array) {

                        for (int i = 1; i < array.length; i++) {
                            int key = array[i];
                            int j = i - 1;

                            while (j >= 0 && array[j] > key) {
                                array[j + 1] = array[j];
                                j--;
                            }

                            array[j + 1] = key;
                        }
                    }""");
                } case "Merge Sort" -> {
                    sorter.sortType = Algorithms.MERGE_SORT;
                    codePanel.setText("""
                                                          
                    void mergeSort(int[] array, int n) {

                        int width, left, mid, right;
                        for (width = 1; width < n; width *= 2) {
                            for (left = 0; left < n; left += 2 * width) {

                                mid = Math.min(left + width, n);
                                right = Math.min(mid + width, n);

                                merge(array, left, mid, right);

                            }
                        }
                    }""");
                } case "Selection Sort" -> {
                    sorter.sortType = Algorithms.SELECTION_SORT;
                    codePanel.setText("""
                                                          
                    void selectionSort(int[] array) {

                        for (int i = 0; i < array.length; i++) {
                            int min = i;

                            for (int j = i + 1; j < array.length; j++) {
                                if (array[j] < array[min]) min = j;
                            }

                            swap(i, min);
                        }
                    }""");
                } default -> {
                    sorter.sortType = Algorithms.NONE;
                    System.out.printf("What did you type?? WHAT IS %S\n", algorithm);
                }
            }

            sorter.prepare(host);
            visualizationPanel.repaint();
        });

        controlPanel.getSpinner().addChangeListener(e -> { 
            host.setLength((int) controlPanel.getSpinner().getValue()); 
            sorter.prepare(host);
            visualizationPanel.repaint();

        });
        
        controlPanel.plause().addActionListener(e -> {

            sorter.step(host);
            sorter.prepare(host);

            host.setSpeed(host.getSpeed() == 0 ? (int) controlPanel.getSlider().getValue() : 0);
            
            visualizationPanel.repaint();
        });

        controlPanel.reset().addActionListener(e -> {
            host.setSpeed(0);

            host.reset();
            sorter.prepare(host);
            visualizationPanel.repaint();
            // Add option to start from the initial parameters
        });

        sortTimer = new Timer(16, e -> {
            if (host.getSpeed() != 0) {
                int line = sorter.step(host);
                if (line != -1) {
                    codePanel.highlightLine(line);
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
            
            //controlPanel.setBackground(Color.PINK);


        });
        
        controlTimer.start();
        sortTimer.start();

        controlPanel.randomize().addActionListener(e -> { 

            controlTimer.stop();
            sortTimer.stop();

            host.randomize();
            sorter.prepare(host);

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

            sorter.setSound(mambo_clip);

        } catch (IOException | UnsupportedAudioFileException |
                LineUnavailableException e) {
            e.printStackTrace();
        }

        sorter.prepare(host);
        codePanel.setText("""
                                                          
        void bubbleSort(int[] array, int n) {

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n - i - 1; j++) {

                    if (array[j] > array[j + 1]) {
                        swap(j, j + 1);
                    }

                }
            }
        }

        """);
    }

    public void placeholder() {
        System.out.print("Yeah this is working...\n");
    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
        mn.placeholder();
    }

}
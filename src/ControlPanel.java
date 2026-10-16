
package src;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ControlPanel extends JPanel {

    private final JButton plause, reset, randomize;
    private final JComboBox<String> algoMenu;
    private final JSlider speedSlider;
    private final JSpinner arraySize;

    private final ArrayModel host;

    public ControlPanel(ArrayModel host) {
        this.host = host;

        plause = new JButton("Play/Pause");
        reset = new JButton("Reset");
        randomize = new JButton("Randomize");

        String[] algos = {"Bubble Sort", "Merge Sort", "Insertion Sort", "Selection Sort"};
        algoMenu = new JComboBox<>(algos);
        arraySize = new JSpinner(new SpinnerNumberModel(20, 5, 100, 1));
        speedSlider = new JSlider(1, 10, 5);
        
        speedSlider.setMajorTickSpacing(3);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        add(new JLabel("Array size:"));
        add(new JLabel("Algorithm:"));
        add(new JLabel("Speed:"));

        add(algoMenu);
        add(speedSlider);
        add(arraySize);

        add(plause);
        add(reset);
        add(randomize);
    }
}


package src;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ControlPanel extends JPanel {

    private final JButton plause, reset, randomize;
    private final JComboBox<String> menu;
    private final JSlider speedSlider;
    private final JSpinner arraySize;

    private JTextPane codeText;

    public ControlPanel() {

        plause = new JButton("Play/Pause");
        reset = new JButton("Reset");
        randomize = new JButton("Randomize");

        String[] algos = {"Bubble Sort", "Merge Sort", "Insertion Sort", "Selection Sort"};
        
        menu = new JComboBox<>(algos);
        arraySize = new JSpinner(new SpinnerNumberModel(20, 5, 100, 1));
        speedSlider = new JSlider(1, 10, 5);
        
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        add(new JLabel("Array size:"));
        add(arraySize);

        add(new JLabel("Algorithm:"));
        add(menu);

        add(new JLabel("Speed:"));
        add(speedSlider);

        add(plause);
        add(reset);
        add(randomize);

        codeText = new JTextPane();

        StyledDocument doc = codeText.getStyledDocument();
        Style style = doc.addStyle("highlight", null);

        StyleConstants.setForeground(style, Color.BLUE);
        StyleConstants.setBold(style, true);

        codeText.setStyledDocument(doc);

        try {
            doc.insertString(0, "This is a", style);
            doc.insertString(doc.getLength(), "Button: ", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        this.add(new JScrollPane(codeText));
    }

    public JComboBox<String> getMenu() { return this.menu; }
    public JSpinner getSpinner() { return this.arraySize; }
    public JSlider getSlider() { return this.speedSlider; }

    public JButton plause() { return this.plause; }
    public JButton reset() { return this.reset; }
    public JButton randomize() { return this.randomize; }

}

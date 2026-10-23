package src.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class InformationPanel extends JPanel {

    private final JTextPane textPane;

    public InformationPanel() {
        setLayout(new BorderLayout());

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        add(new JScrollPane(textPane), BorderLayout.CENTER);
    }

    public void setDescription(String algorithm) {
        String description;

        switch (algorithm) {
            case "Bubble Sort" ->
                description = "Bubble Sort repeatedly compares neighboring values and swaps them when they are out of order. "
                    + "This gradually pushes larger values to the end until the whole array is sorted.";
            case "Insertion Sort" ->
                description = "Insertion Sort builds a sorted section one value at a time. "
                    + "Each new element is compared to the values before it and inserted into its correct position.";
            case "Merge Sort" ->
                description = "Merge Sort splits the array into smaller pieces, sorts each piece, and then merges the sorted parts back together. "
                    + "This approach is efficient for large collections of data.";
            case "Selection Sort" ->
                description = "Selection Sort repeatedly finds the smallest remaining value in the unsorted section and swaps it into place. "
                    + "It gradually builds a sorted section from left to right.";
            default -> description = "Select a sorting algorithm to view its description.";
        }

        textPane.setText(description);
    }
}

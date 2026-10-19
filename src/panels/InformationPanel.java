package src.panels;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    private JTextPane textPane;

    public InformationPanel() {

    setLayout(new BorderLayout());

    textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));

    add(new JScrollPane(textPane), BorderLayout.CENTER);
    }

    public void setDescription(String algorithm) {

    String description = "";

    switch (algorithm) {

    case "Bubble Sort":
    description = "Bubble Sort is a sorting algorithm that puts numbers in order by"
                + "by repeatedly comparing two neighboring numbers and swapping them  "
                + "if they're in the wrong order ";

    break;

    case "Insertion Sort":
    description = "Insertion Sort builds the sorted portion of the array one element at a time "
                + "Each new element is compared with the elements before it,"
                + "and larger elements are shifted to the right until the new element "
                + "can be inserted into its correct position. ";

    break;      
    case "Merge Sort":
    description = "Merge Sort divides the array into smaller subarrays until each subarray contains one element. "
                + "It then repeatedly merges these subarrays while comparing their elements "
                + "producing larger sorted subarrays until the entire array is sorted. ";
    break;

    case "Selection Sort":
    description = "Selection Sort repeatedly searches the unsorted portion of the array for the smallest element. "
                + "It then swaps that element with the first element of the unsorted portion "
                + "gradually building a sorted section from left to right..";
    break;

    default:
    description = "Select a sorting algorithm to view its description.";
    break;
        }

        textPane.setText(description);
}
}
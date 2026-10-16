import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingVisualization {

    private JFrame frame;
    private JPanel panel;

    public SortingVisualization() {
        frame = new JFrame();
        panel = new JPanel();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setTitle("BSIT BASIC ALGORITHMS BOIII");

        ImageIcon icon = new ImageIcon("./resources/icon.png");
        frame.setIconImage(icon.getImage());

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        SortingVisualization mn = new SortingVisualization();
    }

}
package src.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CodePanel extends JPanel {

    private final JTextPane codeText;
    private final JScrollPane scrollPane;
    private final JTextArea lineNumbers;

    private final SimpleAttributeSet normal;
    private final SimpleAttributeSet highlighted;

    public CodePanel() {
        super();

        setLayout(new BorderLayout());

        normal = new SimpleAttributeSet();
        highlighted = new SimpleAttributeSet();

        codeText = new JTextPane();
        codeText.setEditable(false);

        StyleConstants.setBackground(normal, getBackground());
        StyleConstants.setBackground(highlighted, Color.YELLOW);

        scrollPane = new JScrollPane(codeText);
        lineNumbers = new JTextArea("1\n2\n3\n4\n5");
        
        lineNumbers.setBorder(null);
        codeText.setBorder(null);

        lineNumbers.setEditable(false);
        lineNumbers.setForeground(Color.DARK_GRAY);
        lineNumbers.setBackground(Color.LIGHT_GRAY);

        scrollPane.setRowHeaderView(lineNumbers);
        add(scrollPane, BorderLayout.CENTER);

    }

    public void setText(String text) {

        String new_text = "";
        for (int i = 0; i < text.lines().count(); i++) {
            new_text += Integer.toString(i) + "\n";
        }

        codeText.setText(text);
        lineNumbers.setText(new_text);
    }

    public void highlightLine(int line) {
        StyledDocument doc = codeText.getStyledDocument();

        Element root = doc.getDefaultRootElement();
        Element element = root.getElement(line);

        int start = element.getStartOffset();
        int end = element.getEndOffset();

        highlightReset();
        doc.setCharacterAttributes(start, end - start, highlighted, true);
    }

    public void highlightReset() {  
        StyledDocument doc = codeText.getStyledDocument();
        doc.setCharacterAttributes(doc.getDefaultRootElement().getStartOffset(), doc.getLength(), normal, true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
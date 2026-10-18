package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CodePanel extends JPanel {

    private final JTextPane codeText;

    public CodePanel() {
        super();

        setLayout(new BorderLayout());

        codeText = new JTextPane();

        StyledDocument doc = codeText.getStyledDocument();
        Style style = doc.addStyle("highlight", null);

        StyleConstants.setForeground(style, Color.BLUE);
        StyleConstants.setBold(style, true);

        codeText.setStyledDocument(doc);
        codeText.setEditable(false);

        add(new JScrollPane(codeText), BorderLayout.CENTER);

    }

    public void setText(String text) {
        codeText.setText(text);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
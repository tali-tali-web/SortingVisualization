package src;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    private final JButton up, down, left, right;
    private int size, x, y;

    public ControlPanel() {
        super();
        

        /*Clip clip;
        AudioInputStream audio;

        try {
            clip = AudioSystem.getClip();
            audio = AudioSystem.getAudioInputStream(new File("resources/sound.wav"));

            clip.open(audio);
            button.addActionListener(e -> {
                clip.setFramePosition(0);
                clip.start();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        this.setLayout(null);
        this.setPreferredSize(new Dimension(256, 256));

        this.x = 75;
        this.y = 50;
        this.size = 32;

        up = new JButton("^"); down = new JButton("v");
        left = new JButton("<");right = new JButton(">");

        this.updateLocation();

        up.addActionListener(e -> {

        });

        down.addActionListener(e -> {
        
        });

        left.addActionListener(e -> {

        });

        right.addActionListener(e -> {

        });

        this.add(left);
        this.add(right);
        this.add(up);
        this.add(down);
        
        this.repaint();

    }

    public void updateLocation() {
        up.setBounds(x + size, y + 0, size, size);
        down.setBounds(x + size, y + size * 2, size, size);
        left.setBounds(x + 0, y + size, size, size);
        right.setBounds(x + size * 2, y + size, size, size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
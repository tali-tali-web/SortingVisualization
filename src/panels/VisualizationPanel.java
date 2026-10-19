package src.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import src.ArrayModel;


public class VisualizationPanel extends JPanel implements KeyListener {

    private final int margin = 10;
    private final ArrayModel host;

    private boolean up, down, left, right;

    public boolean up() { return this.up; }
    public boolean down() { return this.down; }
    public boolean left() { return this.left; }
    public boolean right() { return this.right; }

    public VisualizationPanel(ArrayModel host) {
        super();

        this.host = host;
        this.host.randomize();

        addKeyListener(this);   
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
            }});

        setLayout(null);
        setFocusable(true);
        repaint();
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());


        Image spotlight = new ImageIcon("./resources/spot-light.gif").getImage();
        g.drawImage(spotlight, this.getWidth() - spotlight.getWidth(this), 0, this);

        Image disco = new ImageIcon("./resources/disco.gif").getImage();
        g.drawImage(disco, (this.getWidth() - disco.getWidth(this)) / 2, 0, this);
        
        Image disco_lights = new ImageIcon("./resources/disco-light.gif").getImage();
        g.drawImage(disco_lights, (this.getWidth() - disco_lights.getWidth(this)) / 2, 0, this);

        this.host.draw(g, this);

        g.setColor(new Color(0, 0, 0));
        g.drawRect(margin, margin, this.getWidth() - 2 * margin, this.getHeight() - 2 * margin);

        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                up = true;
                down = false;
            }
            case KeyEvent.VK_A -> {
                left = true;
                right = false;
            }
            case KeyEvent.VK_S -> {
                down = true;
                up = false;
            }
            case KeyEvent.VK_D -> {
                right = true;
                left = false;
            }
            default -> {
                
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_D -> right = false;
            default -> {}
        }
    }


}
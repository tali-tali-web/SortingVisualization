package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;

enum Algorithms {
    BUBBLE_SORT, 
    MERGE_SORT, 
    INSERTION_SORT, 
    SELECTION_SORT,
    NONE
}

public class ArrayModel {

    private ArrayElement[] array;
    private ArrayElement[] original;

    private int speed = 0;
    private final int margin = 5;

    private int x, y;

    public ArrayModel(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.array = new ArrayElement[length];
        this.original = new ArrayElement[length];

    }

    public ArrayElement index(int index) { 
        return (index < this.array.length && index >= 0) ? this.array[index] : null; 
    }

    public void setIndex(ArrayElement element, int i) {
        this.array[i] = element;
    }
    

    public int length() { return this.array.length; }
    public void setLength(int new_length) {
        this.array = new ArrayElement[new_length];
        this.original = new ArrayElement[new_length];

        this.randomize();
    }


    public void setSpeed(int new_speed) { this.speed = new_speed; }
    public int  getSpeed(             ) {return this.speed;       }
    public void addSpeed(int speed    ) { this.speed += speed;    }

    public void addX(int added) { this.x += added; }
    public void addY(int added) { this.y += added; }

    public void draw(Graphics g, VisualizationPanel panel) {
        
        int cumulative = 0;
        for (ArrayElement element : array) {

            BufferedImage image = element.image();
            if (image != null) {
                g.drawImage(
                    image, -this.x + cumulative, 
                    -this.y + (element.lift() ? image.getHeight() + margin : 0),
                    image.getWidth(), 
                    image.getHeight(), 
                    panel
                );

                g.setColor(new Color(255, 255, 255));
                g.drawString(
                    (String) Integer.toString(element.value()), 
                    -this.x + cumulative,
                    -this.y - (element.lift() ? image.getHeight() + margin : 0) - 20
                );
                cumulative += image.getWidth() + margin;

            } else {

                if (element.lift()) {
                    g.setColor(new Color(255, 100, 100));
                } else {
                    g.setColor(new Color(255, 255, 255));
                }
                g.fillRect(
                    -this.x + cumulative, 
                    -this.y - (element.lift() ? 30 + margin : 0) - 30 - element.value() * 2,
                    30, 
                    30 + element.value() * 2
                );

                g.setColor(new Color(0, 0, 0));
                g.drawString(
                    (String) Integer.toString(element.value()), 
                    -this.x + cumulative,
                    -this.y - (element.lift() ? 30 + margin : 0) - 10
                );

                cumulative += 30 + margin;
            }

        }

        g.drawImage(new ImageIcon("./resources/teto-kasane-teto.gif").getImage(), -x - 100, -y + 100,  panel);
        g.drawImage(new ImageIcon("./resources/kasane-teto-teto.gif").getImage(), -x - 100, -y - 200,  panel);
        g.drawImage(new ImageIcon("./resources/pearto-teto.gif").getImage(), -x + 300, -y - 250,  panel);

    }

    public void randomize() {
        Random random = new Random();
        BufferedImage[] options = {null, null, null};

        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayElement(options[random.nextInt(options.length-1)], random.nextInt(100));
            original[i] = array[i];
        }
    }

    public void reset() {
        for (int i = 0; i < array.length; i++) {
            array[i] = original[i];
            array[i].setLift(false);
        }
    }

    public void swap(int i, int j) {
        ArrayElement temp = this.array[j];
        this.array[j] = this.array[i];
        this.array[i] = temp;
    }

}
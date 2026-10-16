package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

enum Algorithms {
    BUBBLE_SORT, 
    MERGE_SORT, 
    INSERTION_SORT, 
    SELECTION_SORT
}

public class ArrayModel {

    private ArrayElement[] array;
    private int speed = 5;
    private final int margin = 5;

    private int x, y;

    public ArrayModel(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.array = new ArrayElement[length];

    }

    public void randomize() {

        Random random = new Random();
        BufferedImage[] options = {null, null, null};

        for (int i = 0; i < array.length; i++) {
            array[i] = new ArrayElement(options[random.nextInt(options.length-1)], random.nextInt(100));
        }

    }

    public void setLength(int new_length) {
        this.array = new ArrayElement[new_length];
        this.randomize();
    }

    public void setSpeed(int new_speed) { this.speed = new_speed; }
    public void addSpeed(int speed) { this.speed += speed; }
    public int getSpeed() {return this.speed; }

    public void addX(int added) { this.x += added; }
    public void addY(int added) { this.y += added; }

    public void updateAlgorithm(int index) {
        if (index == 0) {
            // Fill this in
        }
    }

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

                g.setColor(new Color(0, 0, 0));
                g.fillRect(
                    -this.x + cumulative, 
                    -this.y - (element.lift() ? 30 + margin : 0),
                    30, 
                    30
                );

                g.setColor(new Color(255, 255, 255));
                g.drawString(
                    (String) Integer.toString(element.value()), 
                    -this.x + cumulative,
                    -this.y - (element.lift() ? 30 + margin : 0) + 10
                );

                cumulative += 30 + margin;
            }

        }
    }

}
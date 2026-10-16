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
    public int getSpeed() {return this.speed; }

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
                g.drawImage(image, this.x + cumulative, this.y, panel);
                cumulative += image.getWidth() + margin;
            } else {
                g.setColor(new Color(0));
                g.fillRect(this.x + cumulative, this.y, 30, 30);
                cumulative += 30 + margin;
            }

        }
    }

}
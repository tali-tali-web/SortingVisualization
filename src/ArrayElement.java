package src;
import java.awt.image.BufferedImage;

public class ArrayElement {
    
    private final BufferedImage image;
    private final int value;

    public ArrayElement(BufferedImage image, int value) {
        this.image = image;
        this.value = value;

    }

    public BufferedImage image() {
        return this.image;
    }

    public int value() {
        return this.value;
    }
    
}
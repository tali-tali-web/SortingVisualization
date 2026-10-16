package src;
import java.awt.image.BufferedImage;

public class ArrayElement {
    
    private final BufferedImage image;
    private final int value;
    private boolean lift;

    public ArrayElement(BufferedImage image, int value) {
        this.image = image;
        this.value = value;
        this.lift = false;

    }

    public BufferedImage image() { return this.image; }
    public int value() { return this.value; }

    public boolean lift() { return this.lift; }
    public void setLift(boolean set) { this.lift = set; }


    
}
package src;

public class ArrayElement {
    
    private final int value;
    private boolean lift;

    public ArrayElement(int value) {
        this.value = value;
        this.lift = false;

    }

    public int getValue() { return this.value;     }

    public boolean getLift() { return this.lift; }
    public void setLift(boolean set) { this.lift = set; }

    
}
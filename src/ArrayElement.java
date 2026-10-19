package src;

public class ArrayElement {
    
    private final int value;
    private boolean lift, verified;

    public ArrayElement(int value) {
        this.value = value;
        this.lift = false;
        this.verified = false;

    }

    public int getValue() { 
        return this.value; 
    }


    public boolean getLift() { 
        return this.lift; 
    }

    public void setLift(boolean set) { 
        this.lift = set; 
    }
    

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean set) {
        this.verified = set;
    }

    
}
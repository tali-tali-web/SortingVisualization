package src.sorts;

import javax.sound.sampled.Clip;
import src.ArrayElement;
import src.ArrayModel;

public class MergeSort {

    private final ArrayModel host;

    private ArrayElement[] temporary;
    private ArrayElement a, b;

    private int i, j;
    private int left, middle, right;
    private int stage, counter, width;

    private boolean end;

    public MergeSort(ArrayModel model) {
        this.host = model;
        prepare();
    }

    public void playSound(Clip clip) {
        if (clip == null) return;
        if (clip.isRunning()) clip.stop();

        clip.setFramePosition(0);
        clip.start();
    }

    public String getCode() {
        return """
                                                          
                void mergeSort(int[] array, int n) {

                    int width, left, mid, right;
                    for (width = 1; width < n; width *= 2) {
                        for (left = 0; left < n; left += 2 * width) {

                            mid = Math.min(left + width, n);
                            right = Math.min(mid + width, n);

                            merge(array, left, mid, right);

                        }
                    }
                }""";
    }

    public final void prepare() {
        stage = 0;
        counter = 0;
        end = false;

        width = 1;

        left = 0;
        middle = width; 
        right = middle + width;

        i = left;
        j = middle;

        this.stage = 1;

        temporary = new ArrayElement[this.host.length()];
    }

    public int step(Clip clip) {
        if (end) return -1;
        
        // How is this even supposed to work... ez pz lemon squeezy
        switch (this.stage) {
            case 0 -> {
                left = right;

                if (left >= this.host.length()) {
                    width *= 2;

                    if (width >= this.host.length()) {
                        end = true;
                        return -1;
                    }

                    left = 0;
                }

                middle = Math.min(left + width, this.host.length());
                right = Math.min(middle + width, this.host.length());

                i = left;
                j = middle;
                counter = 0;

                this.stage = 1;
                this.playSound(clip);
                return 4;

            } case 1 -> {
                
                this.stage = 2;
                return 5;

            } case 2 -> {

                a = (i < middle) ? this.host.index(i) : null;
                this.stage = 3;
                return 7;

            } case 3 -> {

                b = (j < right) ? this.host.index(j) : null;
                this.stage = 4;
                return 8;

            } case 4 -> {

                if (a == null && b == null) {
                    i = left;
                    counter = 0;

                    this.stage = 5;
                    this.playSound(clip);

                    return 5;


                } else if (a == null) {
                    b.setLift(true);
                    temporary[counter++] = b;
                    j++;

                } else if (b == null) {
                    a.setLift(true);
                    temporary[counter++] = a;
                    i++;

                } else if (a.getValue() > b.getValue()) {
                    b.setLift(true);
                    temporary[counter++] = b;
                    j++;

                } else {
                    a.setLift(true);
                    temporary[counter++] = a;
                    i++;

                }

                this.stage = 1;
                return 10;

            } case 5 -> {

                if (i < right) {
                    this.host.setIndex(temporary[counter++], i++);
                    temporary[counter-1].setLift(false);
                    
                } else {
                    this.playSound(clip);
                    this.stage = 0;

                    return 4;
                }

                return 10;
                
            } default -> {
                System.out.println("How did you get here.");
            }
        }
        return -1;
    }

    public boolean getEnd() {
        return this.end;
    }

}
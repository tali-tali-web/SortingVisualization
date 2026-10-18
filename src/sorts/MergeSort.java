package src.sorts;

import src.ArrayElement;
import src.ArrayModel;

public class MergeSort {

    private ArrayElement[] temporary;
    private ArrayElement a, b;

    private int i, j;
    private int left, middle, right;
    private int stage, counter, width;

    private boolean end;

    public MergeSort(ArrayModel model) {
        prepare(model);
    }

    public void playSound() {

    }

    public final void prepare(ArrayModel model) {
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

        temporary = new ArrayElement[model.length()];
    }

    public int mergeSort(ArrayModel model) {
        // How is this even supposed to work... ez pz lemon squeezy
        switch (this.stage) {
            case 0 -> {
                left = right;

                if (left >= model.length()) {
                    width *= 2;

                    if (width >= model.length()) {
                        end = true;
                        return -1;
                    }

                    left = 0;
                }

                middle = Math.min(left + width, model.length());
                right = Math.min(middle + width, model.length());

                i = left;
                j = middle;
                counter = 0;

                this.stage = 1;
                this.playSound();
                return 4;

            } case 1 -> {
                
                this.stage = 2;
                return 5;

            } case 2 -> {

                a = (i < middle) ? model.index(i) : null;
                this.stage = 3;
                return 7;

            } case 3 -> {

                b = (j < right) ? model.index(j) : null;
                this.stage = 4;
                return 8;

            } case 4 -> {

                if (a == null && b == null) {
                    i = left;
                    counter = 0;

                    this.stage = 5;
                    this.playSound();

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
                    model.setIndex(temporary[counter++], i++);
                    temporary[counter-1].setLift(false);
                    
                } else {
                    this.playSound();
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

}
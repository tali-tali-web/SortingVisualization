package src.sorts;

import src.ArrayElement;
import src.ArrayModel;

public class BubbleSort {

    private ArrayElement a, b;
    private int i, j, stage;
    private boolean end;

    public BubbleSort(ArrayModel model) {
        prepare(model);
    }

    public void playSound() {

    }

    public final void prepare(ArrayModel model) {
        stage = 0;
        end = false;
        
        i = -1;
        j = model.length() - 1;
    }

    public int step(ArrayModel model) {
        switch (stage) {
            case 0 -> {
                this.stage = 1;
                return 3;

            } case 1 -> {
                this.stage = 2;
                i++;

                if (i >= j) {
                    j--;
                    i = -1;
                    this.stage = 0;

                    if (j == 0) {
                        end = true;
                        return -1;
                    }
                }

                return 4;
            
            } case 2 -> {

                a = model.index(i);
                b = model.index(i + 1);

                a.setLift(true);
                if (a.getValue() > b.getValue()) b.setLift(true);

                this.stage = 3;
                return 6;

            } case 3 -> {

                if (a.getValue() > b.getValue()) {
                    this.stage = 4;
                    return 6;

                } else {

                    a.setLift(false);
                    b.setLift(false);
                    
                    this.stage = 1;
                    return 6;
                }

            } case 4 -> {

                model.swap(i, i + 1);
                this.playSound();
                this.stage = 5;
                return 7;
                
            } case 5 -> {
                
                a.setLift(false);
                b.setLift(false);

                this.stage = 1;
                return 4;

            } default -> {
                System.out.println("How did you get here.");
            }
        }

        return -1;
    }
}
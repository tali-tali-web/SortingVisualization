package src.sorts;

import src.ArrayElement;
import src.ArrayModel;

public class InsertionSort {

    private ArrayElement a, b;
    private int i, j, stage;
    private boolean end;

    public InsertionSort(ArrayModel model) {
        prepare(model);
    }

    public void playSound() {
        
    }

    public final void prepare(ArrayModel model) {
        stage = 0;
        end = false;

        i = 0;
    }

    public int step(ArrayModel model) {
        switch (this.stage) {
            case 0 -> {
                this.stage = 1;
                i++;
                if (i >= model.length()) {
                    end = true;
                    return -1;
                }
                return 3;

            } case 1 -> {
                this.stage = 2;

                a = model.index(i);         
                a.setLift(end);

                return 4;

            } case 2 -> {

                j = i - 1;
                this.stage = 3;
                return 5;

            } case 3 -> {
                
                if (j >= 0 && model.index(j).getValue() > a.getValue()) {
                    this.stage = 4;
                } else {
                    this.stage = 6;
                }

                return 7;
            } case 4 -> {

                model.index(j).setLift(true);
                model.setIndex(model.index(j), j + 1);
                this.stage = 5;    
                return 8;

            } case 5 -> {
            
                this.stage = 3;
                j--;
                return 9;

            } case 6 -> {

                this.stage = 0;
                a.setLift(false);
                model.setIndex(a, j + 1);

                for (int k = i; k > j; k--) model.index(k).setLift(false);
                this.playSound();

                return 12;

            } default -> {
                System.out.print("You are definitely not supposed to be here!!");
            }
        }
        return -1;
    }
}
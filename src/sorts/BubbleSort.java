package src.sorts;

import src.ArrayElement;
import src.ArrayModel;

public class BubbleSort {

    private final ArrayModel host;

    private ArrayElement a, b;
    private int i, j, stage;
    private boolean end;


    public BubbleSort(ArrayModel model) {
        this.host = model;
        prepare();
    }

    public void playSound() {

    }

    public String getCode() {
        return """
                                                          
                void bubbleSort(int[] array, int n) {

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n - i - 1; j++) {

                            if (array[j] > array[j + 1]) {
                                swap(j, j + 1);
                            }

                        }
                    }
                }

                """;
    }

    public final void prepare() {
        stage = 0;
        end = false;

        i = -1;
        j = this.host.length() - 1;
    }

    public int step() {
        if (end) return -1;
        
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

                a = this.host.index(i);
                b = this.host.index(i + 1);

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

                this.host.swap(i, i + 1);
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
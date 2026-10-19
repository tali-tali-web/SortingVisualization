package src.sorts;

import src.ArrayModel;

public class SelectionSort {

    private final ArrayModel host;

    private int i, j, counter, stage;
    private boolean end;

    public SelectionSort(ArrayModel model) {
        this.host = model;
        prepare();
    }

    public void playSound() {

    }

    public String getCode() {
        return """
                                                          
                void selectionSort(int[] array) {

                    for (int i = 0; i < array.length; i++) {
                        int min = i;

                        for (int j = i + 1; j < array.length; j++) {
                            if (array[j] < array[min]) min = j;
                        }

                        swap(i, min);
                    }
                }""";
    }

    public final void prepare() {
        stage = 0;
        counter = 0;
        end = false;

        i = 0;
        j = 1;
    }

    public int step() {
        if (end) return -1;
        
        switch (this.stage) {
            case 0 -> {
                if (counter >= this.host.length()) {
                    end = true;
                    return -1;
                }

                i = counter;
                j = i + 1;
                this.stage = 1;
                return 2;

            } case 1 -> {
                this.stage = 2;
                return 3;

            } case 2 -> {
                if (j < this.host.length()) {
                    this.host.index(i).setLift(true);
                    this.host.index(j).setLift(true);
                    this.stage = 3;
                    return 6;
                }

                this.stage = 4;
                return 6;

            } case 3 -> {
                this.host.index(j - 1).setLift(false);

                if (this.host.index(j).getValue() < this.host.index(i).getValue()) {
                    this.host.index(i).setLift(false);
                    i = j;
                    this.host.index(i).setLift(true);
                    this.stage = 2;
                    j++;
                    return 7;
                }

                j++;
                this.stage = 2;
                return 6;

            } case 4 -> {

                this.host.index(j - 1).setLift(false);
                this.host.swap(i, counter);
                this.playSound();

                this.host.index(i).setLift(false);
                this.host.index(counter).setLift(false);

                counter++;
                this.stage = 0;
                return 10;

            } default -> {
                System.out.println("Yeah don't be here.");
            }
        }
        return -1;
    }


}
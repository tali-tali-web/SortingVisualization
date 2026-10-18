package src.sorts;

import src.ArrayModel;

public class SelectionSort {

    private int i, j, counter, stage;
    private boolean end;

    public SelectionSort(ArrayModel model) {
        prepare(model);
    }

    public void playSound() {

    }

    public final void prepare(ArrayModel model) {
        stage = 0;
        counter = 0;
        end = false;

        i = 0;
        j = 1;
    }

    public int step(ArrayModel model) {
        switch (this.stage) {
            case 0 -> {
                if (counter >= model.length()) {
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
                if (j < model.length()) {
                    model.index(i).setLift(true);
                    model.index(j).setLift(true);
                    this.stage = 3;
                    return 6;
                }

                this.stage = 4;
                return 6;

            } case 3 -> {
                model.index(j - 1).setLift(false);

                if (model.index(j).getValue() < model.index(i).getValue()) {
                    model.index(i).setLift(false);
                    i = j;
                    model.index(i).setLift(true);
                    this.stage = 2;
                    j++;
                    return 7;
                }

                j++;
                this.stage = 2;
                return 6;

            } case 4 -> {

                model.index(j - 1).setLift(false);
                model.swap(i, counter);
                this.playSound();

                model.index(i).setLift(false);
                model.index(counter).setLift(false);

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
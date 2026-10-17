package src;

import javax.sound.sampled.Clip;

public class Sorter {

    private int i, j;
    private ArrayElement a, b;
    public Algorithms sortType;

    private int stage;
    private boolean swap, end;

    private Clip sound;

    public Sorter() {
        i = 0;
        j = 0;

        stage = 0;
        end = false;
        swap = false;
        sound = null;

        sortType = Algorithms.BUBBLE_SORT;
    }

    public void setSound(Clip new_sound) { this.sound = new_sound; }
    public void playSound() {
        if (sound == null) return;
        if (sound.isRunning()) sound.stop();

        sound.setFramePosition(0);
        sound.start();
    }

    public void bubbleStep(ArrayModel model) {
        switch (stage) {
            case 0 -> {
                if (i == j) {
                    i++;
                }
                a = model.index(i);
                b = model.index(j);

                swap = false;
                a.setLift(true);
                b.setLift(true);

            } case 1 -> {
                swap = a.value() > b.value();

            } case 2 -> {
                if (swap) {
                    model.swap(i, j);
                    this.playSound();
                }
            } case 3 -> {

                a.setLift(false);
                b.setLift(false);

                i++;
                if (i >= model.length()) {
                    i = 0;
                    j++;
                }

                if (j >= model.length() || j == model.length() - 1 && i == j) {
                    end = true;
                }

            } default -> {

            }
        }

        stage = (stage + 1) % 4;
    }

    public void insertionSort(ArrayModel model) {

    }

    public void mergeSort(ArrayModel model) {

    }

    public void selectionSort(ArrayModel model) {

    }

    public void step(ArrayModel model) {

        if (end) return;
        
        switch (this.sortType) {
            case Algorithms.BUBBLE_SORT -> {
                this.bubbleStep(model);    
            } case Algorithms.INSERTION_SORT -> {
                this.insertionSort(model);
            } case Algorithms.MERGE_SORT -> {
                this.mergeSort(model);
            } case Algorithms.SELECTION_SORT -> {
                this.selectionSort(model);
            }default -> {
                System.out.println("what the hell kind of sort did you give me...");
            }
        }
    }

    public void prep() {
        
    }

    // SELECT
    // COMPARE
    // DO I SWAP?

}
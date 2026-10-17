package src;

import javax.sound.sampled.Clip;

public class Sorter {

    private int i, j;
    private ArrayElement a, b;

    private int left, middle, right, width, counter;
    private ArrayElement[] temporary;

    public Algorithms sortType;

    private int stage;
    private boolean swap, end;

    private Clip sound;

    public Sorter() {

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
                System.out.println("How did you get here.");
            }
        }

        stage = (stage + 1) % 4;
    }

    public void insertionSort(ArrayModel model) {
        switch (this.stage) {
            case 0 -> {

                swap = false;
                if (i >= model.length()) {
                    end = true;
                    return;

                } else if (i <= 0) {
                    i = j++;
                    stage = 0;
                    return;
                }

                a = model.index(i);
                b = model.index(i-1);

                a.setLift(true);
                b.setLift(true);

                this.stage++;

            } case 1 -> {
                if (b.value() > a.value()) {
                    swap = true;
                }

                this.stage++;
            } case 2 -> {
                a.setLift(false);
                b.setLift(false);

                if (swap) {
                    model.swap(i, i-1);
                    this.playSound();

                    i--;
                    this.stage = 0;
                    return;
                }

                this.stage++;
            } case 3 -> {

                i = j++;
                this.stage = 0;

            } default -> {
                System.out.println("How did you get here.");
            }
        }
    }

    public void mergeSort(ArrayModel model) {
        // How is this even supposed to work...
        switch (this.stage) {
            case 0 -> {
                left = right;

                if (left >= model.length()) {
                    width *= 2;

                    if (width >= model.length()) {
                        end = true;
                        return;
                    }

                    left = 0;
                }

                middle = Math.min(left + width, model.length());
                right = Math.min(middle + width, model.length());

                i = left;
                j = middle;
                counter = 0;

                this.stage = 1;
                
            } case 1 -> {

                a = (i < middle) ? model.index(i) : null;
                b = (j < right) ? model.index(j) : null;

                if (a == null && b == null) {
                    i = left;
                    counter = 0;

                    this.stage = 2;

                } else if (a == null) {
                    temporary[counter++] = b;
                    b.setLift(true);
                    j++;

                } else if (b == null) {
                    temporary[counter++] = a;
                    a.setLift(true);
                    i++;

                } else if (a.value() > b.value()) {
                    temporary[counter++] = b;
                    b.setLift(true);
                    j++;

                } else {
                    temporary[counter++] = a;
                    a.setLift(true);
                    i++;

                }

            } case 2 -> {

                if (i < right) {
                    temporary[counter].setLift(false);
                    model.setIndex(temporary[counter++], i++);
                } else {
                    this.stage = 0;
                }
                
            } default -> {
                System.out.println("How did you get here.");
            }
        }

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
            } default -> {
                System.out.println("what the hell kind of sort did you give me...");
            }
        }
    }

    public void prepare(ArrayModel model) {

        stage = 0;
        end = false;
        swap = false;

        switch (this.sortType) {
            case Algorithms.BUBBLE_SORT -> {
                i = 0;
                j = 1;

            } case Algorithms.INSERTION_SORT -> {
                i = 1;
                j = 1;

            } case Algorithms.MERGE_SORT -> {
                
                width = 1;

                left = 0;
                middle = width; 
                right = middle + width;

                i = left;
                j = middle;

                this.stage = 1;

                temporary = new ArrayElement[model.length()];

            } case Algorithms.SELECTION_SORT -> {

            } default -> {
                System.out.println("what the hell kind of sort did you give me...");
            }
        }
    }

    // SELECT
    // COMPARE
    // DO I SWAP?

}
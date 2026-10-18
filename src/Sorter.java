package src;

import javax.sound.sampled.Clip;

public class Sorter {

    private int i, j;
    private ArrayElement a, b;

    private int left, middle, right, width, counter;
    private ArrayElement[] temporary;

    public Algorithms sortType;

    private int stage;
    private boolean end;

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

    public int bubbleStep(ArrayModel model) {
        switch (stage) {
            case 0 -> {
                if (i == j) {
                    i++;
                }
                a = model.index(i);
                b = model.index(j);

                a.setLift(true);
                b.setLift(true);

                this.stage = 1;
                return 6;

            } case 1 -> {
                if (a.value() > b.value()) {
                    model.swap(i, j);
                    this.playSound();
                }

                this.stage = 2;
                return 2;

            } case 2 -> {

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

                this.stage = 0;
                return 3;

            } default -> {
                System.out.println("How did you get here.");
            }
        }

        return -1;
    }

    public int insertionSort(ArrayModel model) {
        switch (this.stage) {
            case 0 -> {

                if (i >= model.length()) {
                    end = true;
                    return -1;

                } else if (i <= 0) {
                    if (a != null) a.setLift(false);
                    if (b != null) b.setLift(false);

                    i = j++;
                    stage = 0;
                    return 6;
                }

                a = model.index(i);
                b = model.index(i-1);

                a.setLift(true);
                b.setLift(true);

                this.stage++;
                return 3;

            } case 1 -> {
                if (b.value() > a.value()) {
                    model.swap(i, i-1);
                    this.playSound();

                    a.setLift(false);
                    b.setLift(false);

                    i--;
                    this.stage = 0;
                    return 5;

                } else {
                    a.setLift(false);
                    b.setLift(false);
                }

                this.stage++;
                return 9;

            } case 2 -> {

                a.setLift(false);
                b.setLift(false);

                i = j++;
                this.stage = 0;
                return 5;

            } default -> {
                System.out.println("How did you get here.");
            }
        }
        return -1;
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

                a = (i < middle) ? model.index(i) : null;
                b = (j < right) ? model.index(j) : null;

                if (a == null && b == null) {
                    i = left;
                    counter = 0;

                    this.stage = 2;
                    this.playSound();


                } else if (a == null) {
                    b.setLift(true);
                    temporary[counter++] = b;
                    j++;

                } else if (b == null) {
                    a.setLift(true);
                    temporary[counter++] = a;
                    i++;

                } else if (a.value() > b.value()) {
                    b.setLift(true);
                    temporary[counter++] = b;
                    j++;

                } else {
                    a.setLift(true);
                    temporary[counter++] = a;
                    i++;

                }

                return 5;

            } case 2 -> {

                if (i < right) {
                    model.setIndex(temporary[counter++], i++);
                    temporary[counter-1].setLift(false);
                    
                } else {
                    this.playSound();
                    this.stage = 0;
                }

                return 9;
                
            } default -> {
                System.out.println("How did you get here.");
            }
        }
        return -1;
    }

    public int selectionSort(ArrayModel model) {
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
                return 12;

            } case 3 -> {
                model.index(j - 1).setLift(false);

                if (model.index(j).value() < model.index(i).value()) {
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
                model.swap(i, counter);

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

    public int step(ArrayModel model) {

        if (end) {
            model.setSpeed(0); 
            return -1;
        }
        
        switch (this.sortType) {
            case Algorithms.BUBBLE_SORT -> {
                return this.bubbleStep(model);    
            } case Algorithms.INSERTION_SORT -> {
                return this.insertionSort(model);
            } case Algorithms.MERGE_SORT -> {
                return this.mergeSort(model);
            } case Algorithms.SELECTION_SORT -> {
                return this.selectionSort(model);
            } default -> {
                System.out.println("what the hell kind of sort did you give me...");
            }
        }

        return -1;
    }

    public void prepare(ArrayModel model) {

        stage = 0;
        counter = 0;
        end = false;

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
                
                i = 0;
                j = 1;

            } default -> {
                System.out.println("what the hell kind of sort did you give me...");
            }
        }
    }

    // SELECT
    // COMPARE
    // DO I SWAP?

}
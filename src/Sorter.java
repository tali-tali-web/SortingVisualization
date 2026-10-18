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
                if (a.value() > b.value()) b.setLift(true);

                this.stage = 3;
                return 6;

            } case 3 -> {

                if (a.value() > b.value()) {
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

    public int insertionSort(ArrayModel model) {

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
                
                if (j >= 0 && model.index(j).value() > a.value()) {
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

                } else if (a.value() > b.value()) {
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
                return 6;

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
                i = -1;
                j = model.length() - 1;

            } case Algorithms.INSERTION_SORT -> {
                i = 0;

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
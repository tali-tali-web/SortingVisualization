package src.sorts;

import javax.sound.sampled.Clip;
import src.ArrayElement;
import src.ArrayModel;

public class InsertionSort {

    private final ArrayModel host;

    private ArrayElement a;
    private int i, j, stage;
    private boolean end;

    public InsertionSort(ArrayModel model) {

        this.host = model;
        prepare();
    }

    public void playSound(Clip clip) {
        if (clip == null) return;
        if (clip.isRunning()) clip.stop();

        clip.setFramePosition(0);
        clip.start();
    }

    public String getCode() {
        return """
                                                          
                void insertionSort(int[] array) {

                    for (int i = 1; i < array.length; i++) {
                        int key = array[i];
                        int j = i - 1;

                        while (j >= 0 && array[j] > key) {
                            array[j + 1] = array[j];
                            j--;
                        }

                        array[j + 1] = key;
                    }
                }""";
    }

    public final void prepare() {
        stage = 0;
        end = false;

        i = 0;
    }

    public int step(Clip clip) {
        if (end) return -1;
        
        switch (this.stage) {
            case 0 -> {
                this.stage = 1;
                i++;

                if (i < this.host.length()) return 3;
                
                end = true;
                return -1;

            } case 1 -> {
                this.stage = 2;

                a = this.host.index(i);   
                // System.out.printf("%d, %d\n", i, this.host.length());      
                a.setLift(false);

                return 4;

            } case 2 -> {

                j = i - 1;
                this.stage = 3;
                return 5;

            } case 3 -> {
                
                if (j >= 0 && this.host.index(j).getValue() > a.getValue()) {
                    this.stage = 4;
                } else {
                    this.stage = 6;
                }

                return 7;
            } case 4 -> {

                this.host.index(j).setLift(true);
                this.host.setIndex(this.host.index(j), j + 1);
                this.stage = 5;    
                return 8;

            } case 5 -> {
            
                this.stage = 3;
                j--;
                return 9;

            } case 6 -> {

                this.stage = 0;
                a.setLift(false);
                this.host.setIndex(a, j + 1);

                for (int k = i; k > j; k--) this.host.index(k).setLift(false);
                this.playSound(clip);

                return 12;

            } default -> {
                System.out.print("You are definitely not supposed to be here!!");
            }
        }
        return -1;
    }

    public boolean getEnd() {
        return this.end;
    }
}
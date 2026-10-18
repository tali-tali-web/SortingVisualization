package src;

import javax.sound.sampled.Clip;

public class Sorter {

    private Clip sound;

    public Sorter() {
        sound = null;
    }

    public void setSound(Clip new_sound) { this.sound = new_sound; }
    public void playSound() {
        if (sound == null) return;
        if (sound.isRunning()) sound.stop();

        sound.setFramePosition(0);
        sound.start();
    }


    public int step(Algorithms algorithm, ArrayModel model) {

    }

    public void prepare(ArrayModel model) {


    }

    // SELECT
    // COMPARE
    // DO I SWAP?

}
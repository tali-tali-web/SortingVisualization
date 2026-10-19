package src;

import javax.sound.sampled.Clip;
import src.sorts.BubbleSort;
import src.sorts.InsertionSort;
import src.sorts.MergeSort;
import src.sorts.SelectionSort;

public class Sorter {

    private final BubbleSort bubbleSort;
    private final MergeSort mergeSort;
    private final SelectionSort selectionSort;
    private final InsertionSort insertionSort;

    private final Clip clip;

    private Algorithms sortType;
    private final ArrayModel host;
    private int verifyCounter;

    public Sorter(ArrayModel model, Clip clip) {
        
        this.clip = clip;
        this.host = model;
        this.verifyCounter = 0;
        
        this.sortType = Algorithms.BUBBLE_SORT;

        bubbleSort = new BubbleSort(model);
        mergeSort = new MergeSort(model);
        selectionSort = new SelectionSort(model);
        insertionSort = new InsertionSort(model);
    }

    public Algorithms getSortType() {
        return sortType;
    }

    public void setSortType(Algorithms sortType) {
        this.sortType = sortType;
        prepare();
    }

    public String getCode() {
        return switch (this.sortType) 
        {
            case Algorithms.BUBBLE_SORT -> this.bubbleSort.getCode();
            case Algorithms.MERGE_SORT -> this.mergeSort.getCode();
            case Algorithms.SELECTION_SORT -> this.selectionSort.getCode();
            case Algorithms.INSERTION_SORT -> this.insertionSort.getCode();
            default -> "unknown.";
        };
    }

    public void prepare() {
        this.verifyCounter = 0;
        switch (this.sortType) 
        {
            case Algorithms.BUBBLE_SORT -> this.bubbleSort.prepare();
            case Algorithms.MERGE_SORT -> this.mergeSort.prepare();
            case Algorithms.SELECTION_SORT -> this.selectionSort.prepare();
            case Algorithms.INSERTION_SORT -> this.insertionSort.prepare();
        };
    }

    public int step() {
        
        boolean end = false;
        switch (this.sortType) {
            case Algorithms.BUBBLE_SORT ->  {
                if (bubbleSort.getEnd() && verifyCounter < host.length()) host.index(verifyCounter++).setVerified(true);
                return this.bubbleSort.step(this.clip);
            } case Algorithms.MERGE_SORT -> {
                if (mergeSort.getEnd() && verifyCounter < host.length()) host.index(verifyCounter++).setVerified(true);
                return this.mergeSort.step(this.clip);
            } case Algorithms.SELECTION_SORT -> {
                if (selectionSort.getEnd() && verifyCounter < host.length()) host.index(verifyCounter++).setVerified(true);
                return this.selectionSort.step(this.clip);
            } case Algorithms.INSERTION_SORT -> {
                if (insertionSort.getEnd() && verifyCounter < host.length()) host.index(verifyCounter++).setVerified(true);
                return this.insertionSort.step(this.clip);
            } default -> {
                return -1;
            }
        }
    }

    public void verifySort() {

    }

}
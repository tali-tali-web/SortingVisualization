package src;

import src.sorts.BubbleSort;
import src.sorts.InsertionSort;
import src.sorts.MergeSort;
import src.sorts.SelectionSort;

public class Sorter {

    private final BubbleSort bubbleSort;
    private final MergeSort mergeSort;
    private final SelectionSort selectionSort;
    private final InsertionSort insertionSort;

    private Algorithms sortType;
    private final ArrayModel host;

    public Sorter(ArrayModel model) {
        
        this.host = model;
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
        switch (this.sortType) 
        {
            case Algorithms.BUBBLE_SORT -> this.bubbleSort.prepare();
            case Algorithms.MERGE_SORT -> this.mergeSort.prepare();
            case Algorithms.SELECTION_SORT -> this.selectionSort.prepare();
            case Algorithms.INSERTION_SORT -> this.insertionSort.prepare();
        };
    }

    public int step() {
        return switch (this.sortType) 
        {
            case Algorithms.BUBBLE_SORT -> this.bubbleSort.step();
            case Algorithms.MERGE_SORT -> this.mergeSort.step();
            case Algorithms.SELECTION_SORT -> this.selectionSort.step();
            case Algorithms.INSERTION_SORT -> this.insertionSort.step();
            default -> -1;
        };
    }

}
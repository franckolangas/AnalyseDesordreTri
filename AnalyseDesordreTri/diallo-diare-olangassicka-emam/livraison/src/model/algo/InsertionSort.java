package model.algo;

import java.util.*;

public class InsertionSort extends AbstractSorter {

    public InsertionSort() {
    }

    public void trier(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalStateException("Le tableau est vide ou contient un seul élément");
        }
        if (sort) {
            
            insertionSort(array, array.length - 1);
            //System.out.println(Arrays.toString(array));
        }
        else{
            return;
        }
        
    }

}

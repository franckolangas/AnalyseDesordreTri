package model.algo;

import java.util.*;

public class RadixSort extends AbstractSorter {

    public RadixSort() {
    }

    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        // Appel à la méthode interne avec les limites calculées
        if (sort) {
            
            radixsort(array, array.length);
        }
        // fireChange(); // Mise à jour après chaque fusion principale
        //System.out.println(Arrays.toString(array));
    }

    public  int getMax(int array[], int n) {
        int mx = array[0];
        incrementNbDataAccess(1);
        for (int i = 1; i < n; i++)
            if (sort) {
                incrementNbDataAccess(1);
                incrementNbComparison();
                if (array[i] > mx){
                    mx = array[i];
                    incrementNbDataAccess(1);
                }  
            }
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public void countSort(int array[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(array[i] / exp) % 10]++;
            incrementNbDataAccess(1);

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            incrementNbAssignement();
            count[(array[i] / exp) % 10]--;
            incrementNbDataAccess(3);
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++){
            if(sort){

                array[i] = output[i];
                incrementNbDataAccess(2);
                pauseExecution();
            }
        }
        
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort
    public void radixsort(int array[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(array, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10){
            countSort(array, n, exp);
    
    
        }
        
    }

    

}

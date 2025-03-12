package model.algo;


public class HeapSort extends AbstractSorter {

    public HeapSort() {
    }

    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        // Appel à la méthode interne avec les limites calculées
        heapSort(array);
        // fireChange(); // Mise à jour après chaque fusion principale
        //System.out.println(Arrays.toString(array));
    }

    public void heapSort(int array[]) {
        int n = array.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            if (sort) {
                
                heap(array, n, i);
            }
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            if (sort) {
                // Move current root to end
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                incrementNbDataAccess(2);// pour l'acces aux donnée la lecture =1 ,l'ecriture  = 1 et lecture + ecriture  = 1
                incrementNbAssignement();

                //pauseExecution();
                // Call max heapify on the reduced heap
                heap(array, i, 0);
            }

            
        }
    }

    public void heap(int array[], int n, int i) {
        // Initialize largest as root
        int largest = i;

        // left index = 2*i + 1
        int l = 2 * i + 1;

        // right index = 2*i + 2
        int r = 2 * i + 2;

        // If left child is larger than root
        if (l < n && array[l] > array[largest]) {
            largest = l;
            incrementNbComparison();
            incrementNbDataAccess(2);
            
        }

        // If right child is larger than largest so far
        if (r < n && array[r] > array[largest]) {
            largest = r;
            incrementNbComparison();
            incrementNbDataAccess(2);

        }

        // If largest is not root
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            incrementNbDataAccess(2);
            incrementNbAssignement();
            //pauseExecution();

            // Recursively heapify the affected sub-tree
            heap(array, n, largest);
        }
        pauseExecution();
    }

}

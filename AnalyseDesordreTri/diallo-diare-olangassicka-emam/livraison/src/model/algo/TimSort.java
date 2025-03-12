package model.algo;


public class TimSort extends AbstractSorter {

    static int MIN_MERGE = 32;

    public TimSort() {
    }

    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        // Appel à la méthode interne avec les limites calculées
        if (sort) {
            
            timSort(array, array.length);
        }
        // fireChange(); // Mise à jour après chaque fusion principale
        //System.out.println(Arrays.toString(array));
    }

    public static int minRunLength(int n) {
        assert n >= 0;

        // Becomes 1 if any 1 bits are shifted off
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // Iterative Timsort function to sort the
    // array[0...n-1] (similar to merge sort)
    public void timSort(int[] arr, int n) {
        int minRun = minRunLength(MIN_MERGE);

        // Utilisation de ta fonction insertionSort pour trier les sous-tableaux
        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, Math.min(i + minRun - 1, n - 1));
        }

        // Start merging from size
        // RUN (or 32). It will
        // merge to form size 64,
        // then 128, 256 and so on
        // ....
        for (int size = minRun; size < n; size = 2 * size) {

            // Pick starting point
            // of left sub array. We
            // are going to merge
            // arr[left..left+size-1]
            // and arr[left+size, left+2*size-1]
            // After every merge, we
            // increase left by 2*size
            for (int left = 0; left < n; left += 2 * size) {

                // Find ending point of left sub array
                // mid+1 is starting point of right sub
                // array
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),
                        (n - 1));

                // Merge sub array arr[left.....mid] &
                // arr[mid+1....right]
                if (mid < right)
                    merge(arr, left, mid, right);
            }
        }
    }

}

// This code has been contributed by 29AjayKumar

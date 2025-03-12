package model.algo;

import java.util.Arrays;

public class QuickSort extends AbstractSorter {

    public QuickSort() {
        super();
    }

    public void trier(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalStateException("Le tableau ne contient qu'un seul élément ou est vide"); // Rien à trier
        }

        // Appel à la méthode interne avec les limites calculées
        quickSortRecursive(array, 0, array.length - 1);
    
    }

    public void quickSortRecursive(int[] array, int low, int high) {
        if (sort) {
            if (low < high) {
                // Trouver l'indice du pivot après partition
                int pivotIndex = partition(array, low, high);

                // Trier les sous-tableaux gauche et droit
                quickSortRecursive(array, low, pivotIndex - 1);
                quickSortRecursive(array, pivotIndex + 1, high);
            }
        }
        else{
            return;
        }
    }

    private int partition(int[] array, int low, int high) {
            
        incrementNbDataAccess(1);
        // Utiliser le dernier élément comme pivot
        int pivot = array[high];
        int i = low - 1; // Indice pour les éléments plus petits que le pivot

        for (int j = low; j < high; j++) {
            if (sort) {
                
                // Si l'élément courant est plus petit ou égal au pivot
                incrementNbComparison();
                incrementNbDataAccess(1); // acces à array[j]
                if (array[j] <= pivot) {
                    i++;
                    // Échanger array[i] et array[j]
                    swap(array, i, j);
                    //System.out.println(Arrays.toString(array));
                    pauseExecution();
                    notifyRealTime();
                    
                }
            }
        }

        // Placer le pivot à sa position correcte
        swap(array, i + 1, high);
        incrementNbAssignement();
        incrementNbDataAccess(2);
        notifyRealTime();
            
        return i + 1; // Retourner l'indice du pivo
    }

}

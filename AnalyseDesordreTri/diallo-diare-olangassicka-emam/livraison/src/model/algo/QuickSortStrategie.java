// package model.algo;

// import java.util.Arrays;

// public class QuickSortStrategie implements StrategieTri{
    
//     public QuickSortStrategie (){}

//     @Override
//     public void trier(int[] array) {
//         if (array == null || array.length < 2) {
//             throw new IllegalStateException("le tableau ne contient q'un seul element"); // Rien à trier
//         }

//         // Appel à la méthode interne avec les limites calculées
//         quickSortRecursive(array, 0, array.length - 1);
//         System.out.println(Arrays.toString(array));
//     }

//     public void quickSortRecursive(int[] array, int low, int high) {
//         if (low < high) {
//             // Trouver l'indice du pivot après partition
//             int pivotIndex = partition(array, low, high);

//             // Trier les sous-tableaux gauche et droit
//             quickSortRecursive(array, low, pivotIndex - 1);
//             quickSortRecursive(array, pivotIndex + 1, high);
//         }
//     }

//     public int partition(int[] array, int low, int high) {
//         // Utiliser le dernier élément comme pivot
//         int pivot = array[high];
//         int i = low - 1; // Indice du plus grand élément plus petit que le pivot

//         for (int j = low; j < high; j++) {
//             if (array[j] <= pivot) {
//                 i++;
//                 // Échanger array[i] et array[j]
//                 int temp = array[i];
//                 array[i] = array[j];
//                 array[j] = temp;
//             }
//         }

//         // Placer le pivot à sa position correcte
//         int temp = array[i + 1];
//         array[i + 1] = array[high];
//         array[high] = temp;

//         return i + 1; // Retourner l'indice du pivot
//     }
// }
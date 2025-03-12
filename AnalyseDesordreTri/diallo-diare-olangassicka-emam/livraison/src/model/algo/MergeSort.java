// package model.algo;

// import java.util.*;

// public class MergeSort  extends AbstractSorter {

//     public MergeSort() {}

//     public void trier(int[] tableau) {
//         if (tableau.length <= 1) {
//             //System.out.println("Entrer un tableau non vide");
//             return ;
//         }

//         int milieu = tableau.length / 2;
//         int[] partieGauche = Arrays.copyOfRange(tableau, 0, milieu);
//         int[] partieDroite = Arrays.copyOfRange(tableau, milieu, tableau.length);

//         trier(partieGauche);
//         trier(partieDroite);

//         fusionner(tableau, partieGauche, partieDroite);
//     }

//     private void fusionner(int[] tableau, int[] partieGauche, int[] partieDroite) {
//         int i = 0, j = 0, k = 0;

//         while (i < partieGauche.length && j < partieDroite.length) {
//             nbComparison++;
//             if (partieGauche[i] <= partieDroite[j]) {
//                 tableau[k++] = partieGauche[i++];
//                 notifyRealTime();
//                 pauseExecution();
//             } else {
//                 tableau[k++] = partieDroite[j++];
//                 notifyRealTime();
//             }
//             nbAssignement++;
//         }

//         while (i < partieGauche.length) {
//             tableau[k++] = partieGauche[i++];
//             nbAssignement++;
//             notifyRealTime();
//         }

//         while (j < partieDroite.length) {
//             tableau[k++] = partieDroite[j++];
//             nbAssignement++;
//             notifyRealTime();
//         }
//     }

// }

package model.algo;

//import java.util.*;

public class MergeSort extends AbstractSorter {

    public MergeSort() {
    }

    public void trier(int[] array) {
        if (array == null || array.length <= 1) {
            return; // Rien à trier
        }
        mergeSort(array, 0, array.length - 1);
        // System.out.println(Arrays.toString(array));
    }

    public void mergeSort(int array[], int begin, int end) {
        if (sort) {
            
            if (begin < end) {
                int middle = begin + (end - begin) / 2;

                mergeSort(array, begin, middle);
                mergeSort(array, middle + 1, end);

                merge(array, begin, middle, end);
            }
        }
        else{
            return;
        }
    }

}

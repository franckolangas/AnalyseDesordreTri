package model.algo;



public class TriBulle extends AbstractSorter {

    public TriBulle(){}
    
    public void trier(int[] array) {   
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (sort) {
                    incrementNbDataAccess(2); // le nombre de lecture d'une valeur  
                    incrementNbComparison();
                    if (array[j] > array[j + 1]) {
                        // Échanger les éléments 
                        swap(array,j, j + 1);
                        
                        incrementNbDataAccess(2);
                        incrementNbAssignement();
                        pauseExecution();
                    }
                }
            }
        }
    }

    
}


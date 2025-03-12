package model.algo;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import controler.AbstractModeleEcoutable;

public abstract class AbstractSorter extends AbstractModeleEcoutable implements StrategieTri {
    protected int nbAssignement; // nombre d'affectation
    protected int nbComparison; // nombre de comparaison
    protected int nbDataAccess;
    // protected double executionTime; // temps d'execution
    protected int pauseTime; // temps de pause entre chanque permtation
    protected boolean sort;

    public AbstractSorter() {
        this.nbComparison = 0;
        this.nbAssignement = 0;
        this.nbDataAccess = 0;
        this.sort = true;
    }

    @Override
    public abstract void trier(int[] array);

    @Override
    public int getNbDataAccess() {
        return nbDataAccess;
    }

    @Override
    public int getNbAssignement() {
        return nbAssignement;
    }

    @Override
    public int getNbComparison() {
        return nbComparison;
    }

    @Override
    public void resetStat() {
        this.nbComparison = 0;
        this.nbAssignement = 0;
        this.nbDataAccess = 0;
        fireChange();
    }

    @Override
    public void setPauseTime(int time) {
        this.pauseTime = time;
        fireChange();
    }
    @Override
    public void setSortStatus(boolean b){
        this.sort = b;
        fireChange();
    }

    public void incrementNbDataAccess(int nb) {
        this.nbDataAccess += nb;
    }

    public void incrementNbComparison() {
        this.nbComparison++;
    }

    public void incrementNbAssignement() {
        this.nbAssignement++;
    }

    public void swap(int[] array, int i, int j) {
        if (i != j && array[i] != array[j]) { // Éviter les échanges inutiles
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            // System.out.println(Arrays.toString(array));

        }
    }

    public void pauseExecution() {
        try {
            Thread.sleep(pauseTime); // Pause de 500ms pour observer les changements
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public void notifyRealTime() {

        // Important when experimenting i.e we are not using gui
        if (!hasListening()) {
            return;
        }

        // Wait 1 sec before firechangement
        try {
            Timer timer = new Timer();
            SwingUtilities.invokeAndWait(() -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    fireChange();
                }
            }, 0));
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }

    public void insertionSort(int[] array, int n) {
        for (int i = 1; i <= n; i++) {
            if (sort) {
                
                int key = array[i];
                nbDataAccess++; // Lecture de la clé
                int j = i;

                while (j > 0 && array[j - 1] > key) {
                    nbComparison++;
                    incrementNbDataAccess(2); // Lecture de array[j-1] et key
                    array[j] = array[j - 1];
                    incrementNbDataAccess(1);// Lecture de array[j - 1]
                    incrementNbAssignement();

                    j = j - 1;
                    //System.out.println(Arrays.toString(array));
                    pauseExecution();
                }
                
                if (array[j] != key) { // Ne compter que si la valeur change réellement
                    array[j] = key;
                    nbAssignement++; // Comptabilise uniquement les remplacements de valeurs
                }
                pauseExecution();
            }
        }
    }

    public void merge(int array[], int begin, int middle, int end) {
        int n1 = middle - begin + 1;
        int n2 = end - middle;

        int leftArray[] = new int[n1];
        int rightArray[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[begin + i];
            incrementNbAssignement();
            incrementNbDataAccess(2);
            // pauseExecution();
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
            incrementNbAssignement();
            incrementNbDataAccess(2);
            // pauseExecution();
        }

        int i = 0, j = 0;
        int k = begin;
        while (i < n1 && j < n2) {
            incrementNbComparison();
            incrementNbDataAccess(2);
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            incrementNbAssignement();
            incrementNbDataAccess(1);
            pauseExecution();
            // notifyRealTime();
        }

        while (i < n1) {
            if(sort){

                array[k] = leftArray[i];
                i++;
                k++;
                incrementNbAssignement();
                incrementNbDataAccess(1);
                pauseExecution();
            }
            // notifyRealTime();
        }

        while (j < n2) {
            if(sort){

                array[k] = rightArray[j];
                j++;
                k++;
                incrementNbAssignement();
                incrementNbDataAccess(1);
                pauseExecution();
            }
            // notifyRealTime();
        }
    }

}

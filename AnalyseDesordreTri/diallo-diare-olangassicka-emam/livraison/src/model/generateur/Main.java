package model.generateur;

//import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

// import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Créer un générateur avec 10 éléments et 50% de désordre
        Generator1 generateur = new Generator1(10, 0.5, Repartition.DEBUT);
        ContexteGeneration stratGeneration = new ContexteGeneration(generateur);

        // Afficher la liste initiale
        List<Integer> listeInitiale = stratGeneration.getGenerator().getListeInitiale();
        System.out.println("Liste initiale : " + listeInitiale);

        // Appliquer le désordre
        // generateur.generateurDesordre();

        // Afficher la liste après désordre
        int[] listeApresDesordre = generateur.getList();
        System.out.println("Liste après désordre : " + Arrays.toString(listeApresDesordre));

        // // Calculer et afficher la distance de désordre
        // int distance = generateur.calculerDistanceDesordre(listeInitiale);
        // System.out.println("Distance de désordre : " + distance);
        // System.out.println("Liste initiale : " + listeInitiale);

    }
}
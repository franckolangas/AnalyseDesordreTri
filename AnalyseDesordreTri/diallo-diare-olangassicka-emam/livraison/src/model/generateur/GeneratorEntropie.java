package model.generateur;

import java.util.*;

public class GeneratorEntropie implements StrategieGeneration {
    protected int taille;
    protected double entropieCible;
    protected List<Integer> liste;
    protected List<Integer> listeInitiale;

    public GeneratorEntropie(int taille, double entropieCible) {
        this.taille = taille;
        this.entropieCible = entropieCible;
        this.liste = new ArrayList<>();
        this.listeInitiale = new ArrayList<>();
        generateList();
    }

    // Générer une liste triée initialement
    public List<Integer> generateList() {
        liste.clear();
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
        listeInitiale = new ArrayList<>(liste); // Sauvegarde de la liste initiale
        return liste;
    }

    // Calcul de l'entropie de Shannon
    public static double calculerEntropie(List<Integer> liste) {
        Map<Integer, Integer> frequence = new HashMap<>();
        int taille = liste.size();

        // Calcul des fréquences
        for (int val : liste) {
            frequence.put(val, frequence.getOrDefault(val, 0) + 1);
        }

        // Calcul de l'entropie
        double entropie = 0.0;
        for (int count : frequence.values()) {
            double proba = (double) count / taille;
            entropie -= proba * (Math.log(proba) / Math.log(2)); // log2(p) = log(p)/log(2)
        }

        return entropie;
    }

    // Appliquer un désordre contrôlé en fonction de l'entropie cible
    public List<Integer> generateurDesordre() {
        Random random = new Random();
        double entropieActuelle = calculerEntropie(liste);
        double tolerance = 0.05; // Marge pour éviter les recalculs trop fréquents

        int maxIterations = 10 * taille; // Évite une boucle infinie en cas de problème
        int iterations = 0;

        while (Math.abs(entropieActuelle - entropieCible) > tolerance && iterations < maxIterations) {
            int index1 = random.nextInt(taille);
            int index2 = random.nextInt(taille);

            // Échanger deux éléments pour modifier le désordre
            Collections.swap(liste, index1, index2);

            // Mise à jour de l'entropie
            entropieActuelle = calculerEntropie(liste);
            iterations++;
        }

        return liste;
    }

    // Obtenir la liste désordonnée sous forme de tableau
    public int[] getList() {
        List<Integer> list = generateurDesordre();
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    // Obtenir la liste initiale
    public List<Integer> getListeInitiale() {
        return new ArrayList<>(listeInitiale); // Retourne une copie pour éviter toute modification
    }
}

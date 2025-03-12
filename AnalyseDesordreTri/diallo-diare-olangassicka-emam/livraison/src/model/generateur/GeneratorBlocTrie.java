package model.generateur;

import java.util.*;

public class GeneratorBlocTrie implements StrategieGeneration {
    protected int taille;
    protected double quantite;
    protected List<Integer> liste;
    protected List<Integer> listeInitiale;

    public GeneratorBlocTrie(int taille, double quantite) {
        if (quantite < 0.0 || quantite > 1.0) {
            throw new IllegalArgumentException("La quantite doit être entre 0.0 et 1.0");
        }
        this.taille = taille;
        this.quantite = quantite;
        this.liste = new ArrayList<>();
        this.listeInitiale = new ArrayList<>();
        generateList();
    }

    // Générer une liste triée
    public List<Integer> generateList() {
        liste.clear();
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
        // Faire une copie de la liste initiale pour éviter les modifications futures
        listeInitiale = new ArrayList<>(liste);
        return liste;
    }

    // Diviser la liste en blocs et les mélanger
    public List<Integer> generateurDesordre() {
        int nombreBlocs = (int) Math.round(taille * quantite);
        if (nombreBlocs < 2) {
            return new ArrayList<>(liste);
        }

        int tailleBloc = taille / nombreBlocs; // Taille de chaque bloc
        List<List<Integer>> blocs = new ArrayList<>();

        // Découper la liste en blocs
        for (int i = 0; i < nombreBlocs; i++) {
            int debut = i * tailleBloc;
            int fin = (i == nombreBlocs - 1) ? taille : debut + tailleBloc; // Dernier bloc prend le reste
            blocs.add(new ArrayList<>(liste.subList(debut, fin)));
        }

        // Mélanger l'ordre des blocs
        Collections.shuffle(blocs);

        // Reconstruire la liste avec les blocs mélangés
        liste.clear();
        for (List<Integer> bloc : blocs) {
            liste.addAll(bloc);
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

    public int getTaille() {
        return this.taille;
    }

    public double getQuantite() {
        return this.quantite;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}

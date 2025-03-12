package model.generateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGenerator implements StrategieGeneration {
    protected int taille;
    protected double quantite;
    protected List<Integer> liste; // Liste désordonnée
    protected List<Integer> listeInitiale;
    protected Repartition repartition;

    public AbstractGenerator(int taille, double quantite, Repartition repartition) {
        if (quantite < 0.0 || quantite > 1.0) {
            throw new IllegalArgumentException("Le degré de répartition doit être entre 0.0 et 1.0");
        }
        this.taille = taille;
        this.quantite = quantite;
        this.repartition = repartition;
        this.liste = new ArrayList<>();
        this.listeInitiale = new ArrayList<>();

        generateList();
    }

    // Méthode abstraite pour permettre une génération différente dans les sous-classes
    protected abstract void remplirListe();

    public List<Integer> generateList() {
        liste.clear();
        listeInitiale.clear();

        remplirListe();

        // Faire une copie de la liste initiale pour éviter les modifications futures
        listeInitiale = new ArrayList<>(liste);

        return this.liste;
    }

    // Mélanger un pourcentage des éléments en fonction de la répartition
    public List<Integer> generateurDesordre() {
        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int nbreEnDesordre = (int) Math.round(taille * quantite);
        nbreEnDesordre = Math.min(nbreEnDesordre, liste.size());

        List<Integer> indices = new ArrayList<>();

        switch (repartition) {
            case DEBUT:
                for (int i = 0; i < nbreEnDesordre; i++) {
                    indices.add(i);
                }
                break;

            case MILIEU:
                int start = (taille - nbreEnDesordre) / 2;
                for (int i = start; i < start + nbreEnDesordre; i++) {
                    indices.add(i);
                }
                break;

            case FIN:
                for (int i = taille - nbreEnDesordre; i < taille; i++) {
                    indices.add(i);
                }
                break;

            case ALEATOIRE:
                for (int i = 0; i < taille; i++) {
                    indices.add(i);
                }
                Collections.shuffle(indices);
                indices = indices.subList(0, nbreEnDesordre);
                break;

            default:
                throw new IllegalArgumentException("Répartition inconnue");
        }

        // Appliquer le désordre
        permuterListe(liste, indices);

        return liste;
    }

    // Permuter des éléments dans une liste à des indices spécifiés
    private static void permuterListe(List<Integer> liste, List<Integer> indices) {
        List<Integer> selectedElements = new ArrayList<>();
        for (int index : indices) {
            selectedElements.add(liste.get(index));
        }

        // Mélanger les éléments sélectionnés
        Collections.shuffle(selectedElements);

        // Réassigner les éléments mélangés dans la liste
        for (int i = 0; i < indices.size(); i++) {
            liste.set(indices.get(i), selectedElements.get(i));
        }
    }

    // Obtenir la liste initiale
    public List<Integer> getListeInitiale() {
        return new ArrayList<>(listeInitiale); // Retourne une copie pour éviter toute modification
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

    public int getTaille() {
        return this.taille;
    }

    public double getQuantite() {
        return this.quantite;
    }

    public Repartition getRepartition() {
        return this.repartition;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setRepartition(Repartition repartition) {
        this.repartition = repartition;
    }
}

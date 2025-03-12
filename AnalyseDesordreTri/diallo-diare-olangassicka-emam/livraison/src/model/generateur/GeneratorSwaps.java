package model.generateur;

import java.util.*;

public class GeneratorSwaps extends AbstractGenerator {

    public GeneratorSwaps(int taille, double quantite, Repartition repartition) {
        super(taille, quantite, repartition);
    }

    @Override
    protected void remplirListe() {
        for (int i = 0; i < taille; i++) {
            liste.add(i);
        }
    }

    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @Override
    public List<Integer> generateurDesordre() {

        if (liste.isEmpty()) {
            throw new IllegalStateException("La liste est vide. Veuillez générer une liste d'abord.");
        }

        int nbreDeSwaps = (int) Math.round(taille * quantite);
        nbreDeSwaps = Math.min(nbreDeSwaps, liste.size());

        int milieu = (int) Math.round(taille / 2);
        Random random = new Random();

        List<Integer> sousListe;

        switch (repartition) {
            case DEBUT:
                sousListe = liste.subList(0, milieu);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case MILIEU:
                int debut = (taille - milieu) / 2;
                int fin = debut + milieu;
                sousListe = liste.subList(debut, fin);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case FIN:
                sousListe = liste.subList(milieu, taille);
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(sousListe.size());
                    int index2;
                    do {
                        index2 = random.nextInt(sousListe.size());
                    } while (index1 == index2);
                    Collections.swap(sousListe, index1, index2);
                }
                break;

            case ALEATOIRE:
                for (int i = 0; i < nbreDeSwaps; i++) {
                    int index1 = random.nextInt(taille);
                    int index2;
                    do {
                        index2 = random.nextInt(taille);
                    } while (index1 == index2);
                    Collections.swap(liste, index1, index2);
                    System.out.println("swaps entre" + index1 + "et" + index2);
                }
                break;

            default:
                throw new IllegalArgumentException("Répartition inconnue");
        }

        return liste;

    }

}
